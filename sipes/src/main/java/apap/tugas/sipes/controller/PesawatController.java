package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PesawatController {
    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private PesawatTeknisiService pesawatTeknisiService;

    @Autowired
    private TipeService tipeService;

    @Autowired
    private TeknisiService teknisiService;

    @Autowired
    private PenerbanganService penerbanganService;

    @RequestMapping("/")
    private String beranda(
            Model model
    ){ return "beranda";}

    @GetMapping("/pesawat")
    public String listAllPesawat(Model model){

        // Mendapatkan semua Pesawat Model
        List<PesawatModel> listPesawat = pesawatService.getPesawatList();

        // Mendapatkan jumlah pesawat
        Integer size = listPesawat.size();

        // Add variabel semua PesawatModel ke 'listPesawat' untuk dirender pada thymeleaf
        model.addAttribute("listPesawat", listPesawat);

        // Add jumlah PesawatModel ke 'size' untuk dirender pada thymeleaf
        model.addAttribute("size", size);

        // Return view template yang diinginkan
        return "pesawat";
    }

    @RequestMapping(value = "/pesawat/{idPesawat}", method = RequestMethod.GET)
    public String lihatPesawat(
            @PathVariable(value = "idPesawat") Long idPesawat,
            Model model
    ){
        ArrayList<TeknisiModel> listTeknisi = new ArrayList<TeknisiModel>();
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        List<PesawatTeknisiModel> listPesawatTeknisi = pesawatTeknisiService.getListTeknisi(idPesawat);
        for(PesawatTeknisiModel item : listPesawatTeknisi){
            listTeknisi.add(item.getTeknisi());
        }
        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganByPesawatId(pesawat);
        Integer sizeT = listTeknisi.size();
        Integer sizeP = listPenerbangan.size();
        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listTeknisi",listTeknisi);
        model.addAttribute("listPenerbangan", listPenerbangan);
        model.addAttribute("sizeT", sizeT);
        model.addAttribute("sizeP", sizeP);
        return "view-pesawat";
    }

    @RequestMapping(value = "/pesawat/tambah", method = RequestMethod.GET)
    public String tambahPesawat(
            Model model
    ){
        List<TipeModel> listTipe = tipeService.getTipeList();
        List<TeknisiModel> listTeknisiId = teknisiService.getTeknisiList();
        PesawatModel totalTeknisi = new PesawatModel();
        totalTeknisi.setListPesawatTeknisi(new ArrayList<PesawatTeknisiModel>());
        PesawatTeknisiModel teknisi = new PesawatTeknisiModel();
        teknisi.setPesawat(totalTeknisi);
        totalTeknisi.getListPesawatTeknisi().add(teknisi);
        model.addAttribute("listTipe",listTipe);
        model.addAttribute("listTeknisi",listTeknisiId);
        model.addAttribute("totalTeknisi", totalTeknisi);
        return "form-tambah-pesawat";
    }

    @RequestMapping(value = "/pesawat/tambah", method = RequestMethod.POST, params = {"save"})
    private String tambahPesawatSubmit(
            @ModelAttribute PesawatModel totalTeknisi,
            Model model
    ){
        String nomorSeri = pesawatService.buatNomorSeri(totalTeknisi);
        totalTeknisi.setNomorSeri(nomorSeri);

        //Cek duplikat teknisi
        Set<PesawatTeknisiModel> temp = new HashSet<PesawatTeknisiModel>();
        temp.addAll(totalTeknisi.getListPesawatTeknisi());
        ArrayList<PesawatTeknisiModel> idTeknisi = new ArrayList<PesawatTeknisiModel>();
        idTeknisi.addAll(temp);

        PesawatModel pesawatBaru = pesawatService.addPesawat(totalTeknisi);

        for (PesawatTeknisiModel teknisi : idTeknisi){
            PesawatTeknisiModel relasiBaru = new PesawatTeknisiModel();
            relasiBaru.setPesawat(pesawatBaru);
            relasiBaru.setTeknisi(teknisi.getTeknisi());
            pesawatTeknisiService.addPesawatTeknisi(relasiBaru);
        }
        model.addAttribute("pesawat", pesawatBaru);
        return "tambah-pesawat";
    }

    @RequestMapping(value = "/pesawat/tambah", method = RequestMethod.POST, params = {"addOption"})
    private String addOption(
            @ModelAttribute PesawatModel totalTeknisi,
            Model model
    ){
        PesawatTeknisiModel teknisi = new PesawatTeknisiModel();
        teknisi.setPesawat(totalTeknisi);
        totalTeknisi.getListPesawatTeknisi().add(teknisi);
        List<TipeModel> listTipe = tipeService.getTipeList();
        List<TeknisiModel> listTeknisiId = teknisiService.getTeknisiList();
        model.addAttribute("totalTeknisi", totalTeknisi);
        model.addAttribute("listTipe",listTipe);
        model.addAttribute("listTeknisi",listTeknisiId);
        return "form-tambah-pesawat";
    }

    @RequestMapping(value = "/pesawat/tambah", method = RequestMethod.POST, params = {"removeOption"})
    private String removeOption(
            @ModelAttribute PesawatModel totalTeknisi,
            Model model,
            HttpServletRequest req
    ){
        Integer rowId = Integer.valueOf(req.getParameter("removeOption"));
        totalTeknisi.getListPesawatTeknisi().remove(rowId.intValue());
        List<TipeModel> listTipe = tipeService.getTipeList();
        List<TeknisiModel> listTeknisiId = teknisiService.getTeknisiList();
        model.addAttribute("totalTeknisi", totalTeknisi);
        model.addAttribute("listTipe",listTipe);
        model.addAttribute("listTeknisi",listTeknisiId);
        return "form-tambah-pesawat";
    }

    @RequestMapping(value = "/pesawat/hapus/{idPesawat}", method = RequestMethod.GET)
    private String hapusPesawat(
            @PathVariable(value = "idPesawat") Long idPesawat,
            Model model
    ){
        pesawatTeknisiService.deleteRelasi(idPesawat);
        PesawatModel pesawat = pesawatService.deletePesawat(idPesawat);
        model.addAttribute("pesawat", pesawat);
        return "hapus-pesawat";
    }

    @RequestMapping(value = "/pesawat/ubah/{idPesawat}", method = RequestMethod.GET)
    private String ubahPesawat(
            @PathVariable(value = "idPesawat") Long idPesawat,
            Model model
    ){
        List<TipeModel> listTipe = tipeService.getTipeList();
        List<TeknisiModel> listTeknisiId = teknisiService.getTeknisiList();
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        model.addAttribute("totalTeknisi", pesawat);
        model.addAttribute("listTipe",listTipe);
        model.addAttribute("listTeknisi",listTeknisiId);
        return "form-ubah-pesawat";
    }

    @RequestMapping(value = "/pesawat/ubah/{idPesawat}", method = RequestMethod.POST)
    private String ubahPesawatSubmit(
            @ModelAttribute PesawatModel totalTeknisi,
            Model model
    ){
        String nomorSeri = pesawatService.buatNomorSeri(totalTeknisi);
        totalTeknisi.setNomorSeri(nomorSeri);

        //Cek duplikat teknisi
        Set<PesawatTeknisiModel> temp = new HashSet<PesawatTeknisiModel>();
        temp.addAll(totalTeknisi.getListPesawatTeknisi());
        ArrayList<PesawatTeknisiModel> idTeknisi = new ArrayList<PesawatTeknisiModel>();
        idTeknisi.addAll(temp);

        PesawatModel pesawatBaru = pesawatService.addPesawat(totalTeknisi);

        for (PesawatTeknisiModel teknisi : idTeknisi){
            PesawatTeknisiModel relasiBaru = new PesawatTeknisiModel();
            relasiBaru.setPesawat(pesawatBaru);
            relasiBaru.setTeknisi(teknisi.getTeknisi());
            pesawatTeknisiService.addPesawatTeknisi(relasiBaru);
        }
        model.addAttribute("pesawat", pesawatBaru);
        return "tambah-pesawat";
    }
    @RequestMapping(value = "/pesawat/ubah/{idPesawat}", method = RequestMethod.POST, params = {"addOption"})
    private String addOptionUbah(
            @ModelAttribute PesawatModel totalTeknisi,
            Model model
    ){
        PesawatTeknisiModel teknisi = new PesawatTeknisiModel();
        teknisi.setPesawat(totalTeknisi);
        totalTeknisi.getListPesawatTeknisi().add(teknisi);
        List<TipeModel> listTipe = tipeService.getTipeList();
        List<TeknisiModel> listTeknisiId = teknisiService.getTeknisiList();
        model.addAttribute("totalTeknisi", totalTeknisi);
        model.addAttribute("listTipe",listTipe);
        model.addAttribute("listTeknisi",listTeknisiId);
        return "form-tambah-pesawat";
    }

    @RequestMapping(value = "/pesawat/ubah/{idPesawat}", method = RequestMethod.POST, params = {"removeOption"})
    private String removeOptionUbah(
            @ModelAttribute PesawatModel totalTeknisi,
            Model model,
            HttpServletRequest req
    ){
        Integer rowId = Integer.valueOf(req.getParameter("removeOption"));
        totalTeknisi.getListPesawatTeknisi().remove(rowId.intValue());
        List<TipeModel> listTipe = tipeService.getTipeList();
        List<TeknisiModel> listTeknisiId = teknisiService.getTeknisiList();
        model.addAttribute("totalTeknisi", totalTeknisi);
        model.addAttribute("listTipe",listTipe);
        model.addAttribute("listTeknisi",listTeknisiId);
        return "form-tambah-pesawat";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    private String filter(
            Model model
    ){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList();
        List<TeknisiModel> listTeknisi = teknisiService.getTeknisiList();
        List<TipeModel> listTipe = tipeService.getTipeList();
        model.addAttribute("listPenerbangan", listPenerbangan);
        model.addAttribute("listTeknisi", listTeknisi);
        model.addAttribute("listTipe", listTipe);
        return "filter";
    }
}
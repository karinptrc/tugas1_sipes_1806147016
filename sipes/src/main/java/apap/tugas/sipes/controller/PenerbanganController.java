package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PenerbanganDb;
import apap.tugas.sipes.service.PenerbanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PenerbanganController {
    @Autowired
    PenerbanganService penerbanganService;

    @GetMapping("/penerbangan")
    public String listAllPenerbangan(Model model){

        // Mendapatkan semua Pesawat Model
        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList();

        // Mendapatkan jumlah pesawat
        Integer size = listPenerbangan.size();

        // Add variabel semua PesawatModel ke 'listPesawat' untuk dirender pada thymeleaf
        model.addAttribute("listPenerbangan", listPenerbangan);

        // Add jumlah PesawatModel ke 'size' untuk dirender pada thymeleaf
        model.addAttribute("size", size);

        // Return view template yang diinginkan
        return "penerbangan";
    }

    @RequestMapping(value = "/penerbangan/{idPenerbangan}", method = RequestMethod.GET)
    public String lihatPenerbangan(
            @PathVariable(value = "idPenerbangan") Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        if (penerbangan.getPesawat() == null){model.addAttribute("nomorSeri", "null");}
        return "view-penerbangan";
    }

    @GetMapping(value = "/penerbangan/tambah")
    public String tambahPenerbangan(
            Model model
    ){
        model.addAttribute("penerbangan", new PenerbanganModel());
        return "form-tambah-penerbangan";
    }

    @PostMapping(value = "/penerbangan/tambah")
    private String tambahPenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model
    ){
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("kodePenerbangan", penerbangan.getNomorPenerbangan());
        return "tambah-penerbangan";
    }

    @RequestMapping(value = "/penerbangan/hapus/{idPenerbangan}", method = RequestMethod.GET)
    private String hapusPenerbangan(
            @PathVariable(value = "idPenerbangan") Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.deletePenerbangan(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        return "hapus-penerbangan";
    }

    @RequestMapping(value = "/penerbangan/ubah/{idPenerbangan}", method = RequestMethod.GET)
    private String ubahPenerbangan(
            @PathVariable(value = "idPenerbangan") Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        return "form-ubah-penerbangan";
    }

    @RequestMapping(value = "/penerbangan/ubah", method = RequestMethod.POST)
    private String ubahPenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model
    ){
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("kodePenerbangan", penerbangan.getNomorPenerbangan());
        return "ubah-penerbangan";
    }
}

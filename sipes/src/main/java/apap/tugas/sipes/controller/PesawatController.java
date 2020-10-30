package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.service.PesawatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PesawatController {
    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

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
}

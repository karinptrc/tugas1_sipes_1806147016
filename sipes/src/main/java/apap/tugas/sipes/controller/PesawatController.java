package apap.tugas.sipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PesawatController {

    @GetMapping("/")
    private String beranda(
            Model model
    ){ return "beranda";}
}

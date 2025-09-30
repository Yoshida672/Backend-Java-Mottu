package br.com.fiap.monitoramentomottu.controller.view;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeViewController {
    @GetMapping
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails){
        model.addAttribute("user", userDetails);
        return "home";
    }
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }
}

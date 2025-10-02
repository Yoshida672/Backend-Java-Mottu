package br.com.fiap.monitoramentomottu.controller.view.impl;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

public interface IHomeViewController {
    String home(Model model, UserDetails userDetails);
    String logout();
}

package br.com.fiap.monitoramentomottu.controller.view.impl;


import br.com.fiap.monitoramentomottu.dto.request.PatioRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IViewPatioController {
    String listar(Integer page, Model model) throws Exception;

    String nova(Long id,Model model);

    String salvar(PatioRequest dto, Long id, BindingResult bindingResult, Model model) throws Exception;

    String editar(Long id, Model model) throws Exception;

    String deletar(Long id) throws Exception;
}

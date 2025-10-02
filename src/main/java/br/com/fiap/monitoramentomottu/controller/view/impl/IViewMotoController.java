package br.com.fiap.monitoramentomottu.controller.view.impl;


import br.com.fiap.monitoramentomottu.dto.request.MotoRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IViewMotoController {
    String listar(Integer page, Model model) throws Exception;

    String nova(Model model);

    String salvar(MotoRequest dto,  BindingResult bindingResult, Model model) throws Exception;

    String editar(Long id, Model model) throws Exception;

    String deletar(Long id) throws Exception;
}

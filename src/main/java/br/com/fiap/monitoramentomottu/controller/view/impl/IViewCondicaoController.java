package br.com.fiap.monitoramentomottu.controller.view.impl;


import br.com.fiap.monitoramentomottu.dto.request.CondicaoRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IViewCondicaoController {
    String listar(Integer page, Model model) throws Exception;

    String nova(Model model);

    String salvar(CondicaoRequest dto, Long id, BindingResult bindingResult, Model model) throws Exception;

    String editar(Long id, Model model) throws Exception;

    String deletar(Long id) throws Exception;
}

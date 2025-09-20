package br.com.fiap.monitoramentomottu.controller.Interface;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IViewController<TRequest,ID> {
    String listar(Integer page, Model model) throws Exception;

    String nova(Model model);

    String salvar(TRequest dto, ID id, BindingResult bindingResult, Model model) throws Exception;

    String editar(ID id, Model model) throws Exception;

    String deletar(ID id) throws Exception;
}

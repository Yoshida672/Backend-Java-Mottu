package br.com.fiap.monitoramentomottu.controller.view;

import br.com.fiap.monitoramentomottu.dto.request.EnderecoRequest;
import br.com.fiap.monitoramentomottu.dto.request.FilialRequest;
import br.com.fiap.monitoramentomottu.dto.response.FilialResponse;
import br.com.fiap.monitoramentomottu.service.FilialService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filiais")
public class FilialViewController {

    private final FilialService service;

    public FilialViewController(FilialService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public String listar(@RequestParam(defaultValue = "0") Integer page, Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").ascending());
        Page<FilialResponse> filiais = service.getAll(pageable);
        model.addAttribute("filiais", filiais);
        return "filiais/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("filial", new FilialRequest(null, null, null, null));
        return "filiais/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("filial") FilialRequest filialRequest,
            BindingResult bindingResult,
            Model model
    ) throws Exception {

        if (bindingResult.hasErrors()) {
            model.addAttribute("filial", filialRequest);
            return "filiais/formulario";
        }

        FilialResponse filialCriada = service.create(filialRequest);

        return "redirect:/filiais/enderecos/nova?filialId=" + filialCriada.id();
    }

    @GetMapping("/enderecos/nova")
    public String novoEndereco(@RequestParam Long filialId, Model model) throws Exception {
        FilialResponse filial = service.getById(filialId);
        if (filial == null) {
            throw new Exception("Filial não encontrada");
        }
        model.addAttribute("filial", filial);
        model.addAttribute("endereco", new EnderecoRequest(0,"","","","",""));
        return "filiais/formularioEndereco";
    }


    @PostMapping("/enderecos/salvar")
    public String salvarEndereco(
            @RequestParam Long filialId,
            @Valid @ModelAttribute("endereco") EnderecoRequest enderecoRequest,
            BindingResult bindingResult,
            Model model
    ) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("endereco", enderecoRequest);
            return "filiais/formularioEndereco";
        }

        FilialResponse filial = service.getById(filialId);
        FilialRequest filialAtualizada = new FilialRequest(
                filialId,
                filial.nome(),
                filial.cnpj(),
                filial.ano());

        service.addEndereco(filialId, enderecoRequest);

        return "redirect:/filiais/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("filial", service.getById(id));
        return "filiais/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) throws Exception {
        service.delete(id);
        return "redirect:/filiais/listar";
    }
}


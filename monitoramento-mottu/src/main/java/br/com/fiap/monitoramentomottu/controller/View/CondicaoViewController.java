package br.com.fiap.monitoramentomottu.controller.View;

import br.com.fiap.monitoramentomottu.dto.Condicao.CondicaoRequest;
import br.com.fiap.monitoramentomottu.dto.Condicao.CondicaoResponse;
import br.com.fiap.monitoramentomottu.service.CondicaoService;
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
@RequestMapping("/condicoes")
public class CondicaoViewController {
    private final CondicaoService service;

    public CondicaoViewController(CondicaoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public String listar(@RequestParam(defaultValue = "0") Integer page, Model model) {
        Pageable pageable = PageRequest.of(page, 2, Sort.by("id").ascending());
        Page<CondicaoResponse> condicoes = service.getAll(pageable);
        model.addAttribute("condicoes", condicoes);
        return "condicoes/lista";
    }
    @GetMapping("/nova")
    public String novaCondicao(Model model) {
        model.addAttribute("condicoes", new CondicaoRequest(null, null));
        return "condicoes/formulario";
    }
    @PostMapping("/salvar")
    public String salvarCondicoes(@Valid
                                  @ModelAttribute("condicoes") CondicaoRequest condicaoRequest,
                                  BindingResult bindingResult,
                                  Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("condicao", condicaoRequest);
            return "condicoes/formulario";
        }
        service.create(condicaoRequest);
        return "redirect:/condicoes/listar";
    }

}

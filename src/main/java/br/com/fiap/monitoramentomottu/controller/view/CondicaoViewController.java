package br.com.fiap.monitoramentomottu.controller.view;

import br.com.fiap.monitoramentomottu.controller.view.impl.IViewCondicaoController;
import br.com.fiap.monitoramentomottu.dto.request.CondicaoRequest;
import br.com.fiap.monitoramentomottu.dto.response.CondicaoResponse;
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
public class CondicaoViewController implements IViewCondicaoController {
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
    public String nova(Model model) {
        model.addAttribute("condicao", new CondicaoRequest(null,null, null));
        return "condicoes/formulario";
    }
    @PostMapping("/salvar")
    public String salvar(
      @Valid
      @ModelAttribute("condicao") CondicaoRequest condicaoRequest,
      @RequestParam(required = false) Long id,
      BindingResult bindingResult,
      Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("condicao", condicaoRequest);
            return "condicoes/formulario";
        }
        if(id!=null){
            service.update(id,condicaoRequest);
            System.out.println("Update");
        }
        else {
            service.create(condicaoRequest);
            System.out.println("Create");
        }
        return "redirect:/condicoes/listar";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("condicao",service.getById(id));
        return "condicoes/formulario";
    }
    @GetMapping("deletar/{id}")
    public String deletar(@PathVariable Long id) throws Exception {
        service.delete(id);
        return "redirect:/condicoes/listar";
    }
}

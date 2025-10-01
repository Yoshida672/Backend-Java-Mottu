package br.com.fiap.monitoramentomottu.controller.view;

import br.com.fiap.monitoramentomottu.dto.request.PatioRequest;
import br.com.fiap.monitoramentomottu.dto.response.PatioResponse;
import br.com.fiap.monitoramentomottu.service.PatioService;
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
@RequestMapping("/patios")
public class PatioViewController {

    private final PatioService patioService;
    private final FilialService filialService;

    public PatioViewController(PatioService patioService, FilialService filialService) {
        this.patioService = patioService;
        this.filialService = filialService;
    }

    @GetMapping("/listar")
    public String listar(@RequestParam(defaultValue = "0") Integer page, Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").ascending());
        Page<PatioResponse> patios = patioService.getAll(pageable);
        model.addAttribute("patios", patios);
        return "patios/lista";
    }

    @GetMapping("/novo")
    public String novo(@RequestParam(required = false) Long filialId, Model model) {
        PatioRequest patioRequest;
        if (filialId != null) {
            patioRequest = new PatioRequest(null,0, 0, filialId);
        } else {
            patioRequest = new PatioRequest(null,0, 0, null);
        }
        model.addAttribute("patio", patioRequest);
        model.addAttribute("filiais", filialService.getAllFiliais()); // lista de filiais para dropdown
        return "patios/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("patio") PatioRequest patioRequest,
            @RequestParam(required = false) Long id,

            BindingResult bindingResult,
            Model model
    ) throws Exception {

        if (bindingResult.hasErrors()) {
            model.addAttribute("filiais", filialService.getAllFiliais());
            return "patios/formulario";
        }

        if (id == null) {
            patioService.create(patioRequest);
        } else {
            patioService.update(id, patioRequest);
        }

        return "redirect:/patios/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) throws Exception {
        PatioResponse patio = patioService.getById(id);
        PatioRequest patioRequest = new PatioRequest(
                id,
                patio.area(),
                patio.capacidadeMax(),
                patio.filialId());
        model.addAttribute("patio", patioRequest);
        model.addAttribute("filiais", filialService.getAllFiliais());
        return "patios/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) throws Exception {
        patioService.delete(id);
        return "redirect:/patios/listar";
    }
}

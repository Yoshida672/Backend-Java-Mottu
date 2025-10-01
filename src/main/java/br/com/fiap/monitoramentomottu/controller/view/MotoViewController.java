package br.com.fiap.monitoramentomottu.controller.view;
import br.com.fiap.monitoramentomottu.dto.request.MotoRequest;
import br.com.fiap.monitoramentomottu.dto.response.MotoResponse;
import br.com.fiap.monitoramentomottu.repository.CondicaoRepository;
import br.com.fiap.monitoramentomottu.repository.PatioRepository;
import br.com.fiap.monitoramentomottu.service.MotoService;
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
@RequestMapping("/motos")
public class MotoViewController {

    private final MotoService motoService;
    private final PatioRepository patioRepository;
    private final CondicaoRepository condicaoRepository;

    public MotoViewController(MotoService motoService, PatioRepository patioRepository, CondicaoRepository condicaoRepository) {
        this.motoService = motoService;
        this.patioRepository = patioRepository;
        this.condicaoRepository = condicaoRepository;
    }

    @GetMapping("/listar")
    public String listar(@RequestParam(defaultValue = "0") Integer page, Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").ascending());
        Page<MotoResponse> motos = motoService.getAll(pageable);
        model.addAttribute("motos", motos);
        return "motos/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("moto", new MotoRequest(null,"", "", null, null));
        model.addAttribute("patios", patioRepository.findAll());
        model.addAttribute("condicoes", condicaoRepository.findAll());
        return "motos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("moto") MotoRequest motoRequest,
            BindingResult bindingResult,
            Model model
    ) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patios", patioRepository.findAll());
            model.addAttribute("condicoes", condicaoRepository.findAll());
            return "motos/formulario";
        }

        motoService.create(motoRequest);
        return "redirect:/motos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) throws Exception {
        MotoResponse moto = motoService.getById(id);

        MotoRequest motoForm = new MotoRequest(
                id,
                moto.placa(),
                moto.modelo(),
                moto.condicaoId(),
                moto.patioId()
        );

        model.addAttribute("moto", motoForm);
        return "motos/formulario";
    }
        @PostMapping("/atualizar/{id}")
    public String atualizar(
            @PathVariable Long id,
            @Valid @ModelAttribute("moto") MotoRequest motoRequest,
            BindingResult bindingResult,
            Model model
    ) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patios", patioRepository.findAll());
            model.addAttribute("condicoes", condicaoRepository.findAll());
            model.addAttribute("editId", id);
            return "motos/formulario";
        }

        motoService.update(id, motoRequest);
        return "redirect:/motos/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) throws Exception {
        motoService.delete(id);
        return "redirect:/motos/listar";
    }
}

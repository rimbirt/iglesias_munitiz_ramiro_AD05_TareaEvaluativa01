package eus.birt.ad5.controller;

import eus.birt.ad5.domain.Crag;
import eus.birt.ad5.repository.CragRepository;
import eus.birt.ad5.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crag")
@RequiredArgsConstructor
@Slf4j
public class CragController {

    private final CragRepository cragRepository;
    private final RouteRepository routeRepository;

    @GetMapping
    public String getListView(Model model) {
        model.addAttribute("crags", cragRepository.findAll());
        return "crags";
    }

    @GetMapping("/{id}")
    public String getDetailView(Model model, @PathVariable Long id) {
        model.addAttribute("crag", cragRepository.getById(id));
        model.addAttribute("routes", routeRepository.findAll());
        return "crag-detail";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model) {
        model.addAttribute("crag", new Crag());
        return "crag-create";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(Model model, @PathVariable Long id) {
        model.addAttribute("crag", cragRepository.getById(id));
        return "crag-update";
    }

    @PostMapping("/create")
    public String createCrag(Crag crag) {
        cragRepository.save(crag);
        return "redirect:/crag";
    }

    @PutMapping("/{id}/edit")
    public String editCrag(@ModelAttribute Crag crag) {
        cragRepository.save(crag);
        return "redirect:/crag";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        log.debug("Request to delete crag with id: {}", id);
        cragRepository.deleteById(id);
        return "redirect:/crag";
    }
}

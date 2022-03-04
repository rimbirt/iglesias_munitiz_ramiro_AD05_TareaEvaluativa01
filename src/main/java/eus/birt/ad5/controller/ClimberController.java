package eus.birt.ad5.controller;

import eus.birt.ad5.domain.Climber;
import eus.birt.ad5.repository.ClimberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/climber")
@RequiredArgsConstructor
@Slf4j
public class ClimberController {

    private final ClimberRepository climberRepository;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("climbers", climberRepository.findAll());
        return "climbers";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model) {
        model.addAttribute("climber", new Climber());
        return "climber-create";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(Model model, @PathVariable Long id) {
        model.addAttribute("climber", climberRepository.getById(id));
        return "climber-update";
    }

    @GetMapping("/{id}")
    public String getDetailView(Model model, @PathVariable Long id) {
        Climber climber = climberRepository.getById(id);
        model.addAttribute("climber", climber);
        model.addAttribute("ascends", climber.getAscends());
        return "climber-detail";
    }

    @PostMapping("/create")
    public String createClimber(@ModelAttribute Climber climber) {
        climberRepository.save(climber);
        return "redirect:/climber";
    }

    @PutMapping("/{id}/edit")
    public String editClimber(@ModelAttribute Climber climber) {
        climberRepository.save(climber);
        return "redirect:/climber";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        log.debug("Request to delete climber with id: {}", id);
        climberRepository.deleteById(id);
        return "redirect:/climber";
    }
}

package eus.birt.ad5.controller;

import eus.birt.ad5.domain.Ascend;
import eus.birt.ad5.domain.AscendKey;
import eus.birt.ad5.domain.Climber;
import eus.birt.ad5.domain.Grade;
import eus.birt.ad5.domain.Route;
import eus.birt.ad5.repository.AscendRepository;
import eus.birt.ad5.repository.ClimberRepository;
import eus.birt.ad5.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class AscendController {

    private final AscendRepository ascendRepository;
    private final ClimberRepository climberRepository;
    private final RouteRepository routeRepository;

    @GetMapping("/route/{id}/ascend/create")
    public String getCreateForm(Model model, @PathVariable Long id) {
        model.addAttribute("routeId", id);
        model.addAttribute("ascend", new Ascend());
        model.addAttribute("grades", Grade.values());
        model.addAttribute("climbers", climberRepository.getAllWithNoRouteAscends(id));
        return "ascend-create";
    }

    @GetMapping("/route/{routeId}/climber/{climberId}/edit")
    public String getEditForm(Model model, @PathVariable Long routeId, @PathVariable Long climberId) {
        var climber = Climber.builder().id(climberId).build();
        var route = Route.builder().id(routeId).build();
        model.addAttribute("ascend", ascendRepository.findByKeyClimberIdAndKeyRouteId(climberId, routeId));
        model.addAttribute("grades", Grade.values());
        return "ascend-update";
    }

    @PostMapping("/route/{routeId}/ascend/create")
    public String createAscend(Ascend ascend, @PathVariable Long routeId) {
        ascendRepository.save(ascend);
        return "redirect:/route/" + routeId;
    }

    @PutMapping("/route/{routeId}/ascend/edit")
    public String editAscend(Ascend ascend, @PathVariable Long routeId) {
        ascendRepository.save(ascend);
        return "redirect:/route/" + routeId;
    }

    @DeleteMapping("/route/{routeId}/climber/{climberId}")
    public String delete(@PathVariable Long routeId, @PathVariable Long climberId) {
        var climber = Climber.builder().id(climberId).build();
        var route = Route.builder().id(routeId).build();
        ascendRepository.deleteById(AscendKey.builder().climber(climber).route(route).build());
        return "redirect:/route/" + routeId;
    }
}

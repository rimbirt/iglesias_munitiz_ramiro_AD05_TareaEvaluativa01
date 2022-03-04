package eus.birt.ad5.controller;

import eus.birt.ad5.domain.Crag;
import eus.birt.ad5.domain.Grade;
import eus.birt.ad5.domain.Route;
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
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class RouteController {

    private final RouteRepository routeRepository;

    @GetMapping("/route/{id}")
    public String getDetailView(Model model, @PathVariable Long id) {
        Route route = routeRepository.getById(id);
        model.addAttribute("route", route);
        model.addAttribute("ascends", route.getAscends());
        return "route-detail";
    }

    @GetMapping("/crag/{cragId}/route/create")
    public String getCreateForm(Model model, @PathVariable Long cragId) {
        model.addAttribute("route", new Route());
        model.addAttribute("grades", Grade.values());
        model.addAttribute("cragId", cragId);
        return "route-create";
    }

    @GetMapping("/crag/{cragId}/route/{id}/edit")
    public String getEditForm(Model model, @PathVariable Long id, @PathVariable Long cragId) {
        model.addAttribute("route", routeRepository.getById(id));
        model.addAttribute("grades", Grade.values());
        model.addAttribute("cragId", cragId);
        return "route-update";
    }

    @PostMapping("/crag/{cragId}/route/create")
    public String createRoute(Route route, @PathVariable Long cragId) {
        route.setCrag(Crag.builder().id(cragId).build());
        routeRepository.save(route);
        return "redirect:/crag/" + cragId;
    }

    @PutMapping("/crag/{cragId}/route")
    public String editRoute(Route route, @PathVariable Long cragId) {
        routeRepository.save(route);
        return "redirect:/crag/" + cragId;
    }

    @DeleteMapping("/route/{id}")
    public String delete(@PathVariable Long id) {
        log.debug("Request to delete route with id: {}", id);
        routeRepository.deleteById(id);
        return "redirect:/crag";
    }

    @GetMapping
    public String redirectBase() {
        return "redirect:/crag";
    }

}

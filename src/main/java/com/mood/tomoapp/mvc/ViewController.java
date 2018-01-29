package com.mood.tomoapp.mvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.mood.tomoapp.config.LocalDateHelper;
import com.mood.tomoapp.config.LocalDateTimeHelper;
import com.mood.tomoapp.domain.Driver;
import com.mood.tomoapp.domain.Fuel;
import com.mood.tomoapp.domain.Transport;
import com.mood.tomoapp.model.DriverModel;
import com.mood.tomoapp.model.FuelModel;
import com.mood.tomoapp.model.TransportModel;
import com.mood.tomoapp.service.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
@Controller
public class ViewController extends AbstractController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new LocalDateHelper());
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimeHelper());
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/view/form";
    }

    @GetMapping("/view/form")
    public String form(Model model) {
        // models
        model.addAttribute("transport", new TransportModel());
        model.addAttribute("fuel", new FuelModel());

        // drop downs
        model.addAttribute("drivers", drivers.findByActiveIsGreaterThan(0));
        model.addAttribute("trucks", trucks.findByActiveIsGreaterThan(0));
        model.addAttribute("buyers", buyers.findByActiveIsGreaterThan(0));
        model.addAttribute("owners", owners.findByActiveIsGreaterThan(0));
        model.addAttribute("fuelings", fuelings.findByActiveIsGreaterThan(0));

        return "form";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("driver", new DriverModel());
        return "login";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(HttpSession session, DriverModel driver) {
        Optional<Driver> user = drivers.findByDriverAndPasswordAndActiveIsGreaterThan(driver.getDriver(), driver.getPassword(), 0);
        if (user.isPresent()) {
            AuthFilter.setDriverId(session, user.get().getId());
            return "redirect:/view/form";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/view/transport", method = RequestMethod.POST)
    public String transport(HttpSession session, TransportModel transport, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.put("status", "ERROR");
        } else {
            Transport newTransport = new Transport();
            fillTransport(
                newTransport,
                AuthFilter.getDriverId(session),
                transport.getBuyer(),
                transport.getTruck(),
                transport.getOwner()
            );
            newTransport.setLocationIn(transport.getLocationIn());
            newTransport.setDateIn(transport.getDateIn());
            newTransport.setLocationOut(transport.getLocationOut());
            newTransport.setDateOut(transport.getDateOut());
            newTransport.setAssortment(transport.getAssortment());
            newTransport.setQuantity(transport.getQuantity());
            newTransport.setRemark(transport.getRemark());
            transports.save(newTransport);

            model.clear();
            model.addAttribute("status", "OK");
        }
        return "status";
    }

    @RequestMapping(value = "/view/fuel", method = RequestMethod.POST)
    public String fuel(HttpSession session, FuelModel fuel, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.put("status", "ERROR");
        } else {
            Fuel newFuel = new Fuel();
            fillFuel(
                newFuel,
                AuthFilter.getDriverId(session),
                fuel.getTruck(),
                fuel.getFueling()
            );
            newFuel.setDate(fuel.getDate());
            newFuel.setQuantity(fuel.getQuantity());
            newFuel.setKm(fuel.getKm());
            newFuel.setFacture(fuel.getFacture());
            newFuel.setRemark(fuel.getRemark());
            fuels.save(newFuel);

            model.clear();
            model.addAttribute("status", "OK");
        }
        return "status";
    }
}

package com.mood.tomoapp.mvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.mood.tomoapp.cache.TempCacheManager;
import com.mood.tomoapp.config.LocalDateHelper;
import com.mood.tomoapp.config.LocalDateTimeHelper;
import com.mood.tomoapp.domain.Driver;
import com.mood.tomoapp.domain.Fuel;
import com.mood.tomoapp.domain.Transport;
import com.mood.tomoapp.model.DriverModel;
import com.mood.tomoapp.model.FuelModel;
import com.mood.tomoapp.model.TransportModel;
import com.mood.tomoapp.service.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TempCacheManager cacheManager;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new LocalDateHelper());
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimeHelper());
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/view/menu";
    }

    @GetMapping("/view/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/view/purge")
    public String purge(final ModelMap model) {
        cacheManager.purge();
        model.addAttribute("status", "PURGED");
        return "status";
    }

    @GetMapping("/view/tf")
    public String transportForm(Model model) {
        // models
        model.addAttribute("transport", new TransportModel());

        // drop downs
        model.addAttribute("trucks", getTrucks(0));
        model.addAttribute("buyers", getBuyers(0));
        model.addAttribute("owners", getOwners(0));
        model.addAttribute("payers", getPayers(0));
        model.addAttribute("assortments", getAssortments(0));
        model.addAttribute("locations", getLocations(0));

        return "transport";
    }

    @GetMapping("/view/ff")
    public String fuelForm(Model model) {
        // models
        model.addAttribute("fuel", new FuelModel());

        // drop downs
        model.addAttribute("trucks", getTrucks(0));
        model.addAttribute("fuelings", getFuelings(0));
        model.addAttribute("locations", getLocations(0));

        return "fuel";
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
            return "menu";
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
                transport.getOwner(),
                transport.getPayer(),
                transport.getAssortment(),
                transport.getLocationIn(),
                transport.getLocationOut()
            );
            newTransport.setLocationInNew(transport.getLocationInNew());
            newTransport.setDateIn(transport.getDateIn());
            newTransport.setLocationOutNew(transport.getLocationOutNew());
            newTransport.setDateOut(transport.getDateOut());
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
                fuel.getFueling(),
                fuel.getLocation()
            );
            newFuel.setLocationNew(fuel.getLocationNew());
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

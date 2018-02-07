/*
 * Copyright 2016-2017 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mood.tomoapp.service;

import java.util.List;

import com.mood.tomoapp.domain.Assortment;
import com.mood.tomoapp.domain.Buyer;
import com.mood.tomoapp.domain.Driver;
import com.mood.tomoapp.domain.Fuel;
import com.mood.tomoapp.domain.Fueling;
import com.mood.tomoapp.domain.Location;
import com.mood.tomoapp.domain.Owner;
import com.mood.tomoapp.domain.Payer;
import com.mood.tomoapp.domain.Transport;
import com.mood.tomoapp.domain.Truck;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AppController extends AbstractController {

    @GetMapping("/drivers")
    public List<Driver> getDrivers(@RequestParam(name = "active", defaultValue = "-1") int active) {
        return super.getDrivers(active);
    }

    @GetMapping("/trucks")
    public List<Truck> getTrucks(@RequestParam(name = "active", defaultValue = "-1") int active) {
        return super.getTrucks(active);
    }

    @GetMapping("/buyers")
    public List<Buyer> getBuyers(@RequestParam(name = "active", defaultValue = "-1") int active) {
        return super.getBuyers(active);
    }

    @GetMapping("/owners")
    public List<Owner> getOwners(@RequestParam(name = "active", defaultValue = "-1") int active) {
        return super.getOwners(active);
    }

    @GetMapping("/fuelings")
    public List<Fueling> getFuelings(@RequestParam(name = "active", defaultValue = "-1") int active) {
        return super.getFuelings(active);
    }

    @GetMapping("/payers")
    public List<Payer> getPayers(@RequestParam(name = "active", defaultValue = "-1") int active) {
        return super.getPayers(active);
    }

    @GetMapping("/assortiments")
    public List<Assortment> getAssortments(@RequestParam(name = "active", defaultValue = "-1") int active) {
        return super.getAssortments(active);
    }

    @GetMapping("/locations")
    public List<Location> getLocations(@RequestParam(name = "active", defaultValue = "-1") int active) {
        return super.getLocations(active);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/transport")
    @Transactional
    public Transport transport(@RequestBody Transport transport) {

        fillTransport(
            transport,
            transport.getDriver().getId(),
            transport.getBuyer().getId(),
            transport.getTruck().getId(),
            transport.getOwner().getId(),
            transport.getPayer().getId(),
            transport.getAssortment().getId(),
            transport.getLocationIn().getId(),
            transport.getLocationOut().getId()
        );

        return transports.save(transport);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/fuel")
    @Transactional
    public Fuel fuel(@RequestBody Fuel fuel) {

        fillFuel(fuel,
            fuel.getDriver().getId(),
            fuel.getTruck().getId(),
            fuel.getFueling().getId(),
            fuel.getLocation().getId()
        );

        return fuels.save(fuel);
    }
}

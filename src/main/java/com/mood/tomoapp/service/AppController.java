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

import com.mood.tomoapp.domain.Buyer;
import com.mood.tomoapp.domain.Driver;
import com.mood.tomoapp.domain.Fuel;
import com.mood.tomoapp.domain.Fueling;
import com.mood.tomoapp.domain.Owner;
import com.mood.tomoapp.domain.Transport;
import com.mood.tomoapp.domain.Truck;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AppController extends AbstractController {

    @GetMapping("/drivers")
    public List<Driver> getDrivers() {
        return drivers.findByActiveIsGreaterThan(0);
    }

    @GetMapping("/trucks")
    public List<Truck> getTrucks() {
        return trucks.findByActiveIsGreaterThan(0);
    }

    @GetMapping("/buyers")
    public List<Buyer> getBuyers() {
        return buyers.findByActiveIsGreaterThan(0);
    }

    @GetMapping("/owners")
    public List<Owner> getOwners() {
        return owners.findByActiveIsGreaterThan(0);
    }

    @GetMapping("/fuelings")
    public List<Fueling> getFuelings() {
        return fuelings.findByActiveIsGreaterThan(0);
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
            transport.getOwner().getId()
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
            fuel.getFueling().getId()
        );

        return fuels.save(fuel);
    }
}

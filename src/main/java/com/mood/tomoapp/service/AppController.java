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

import java.time.LocalDateTime;
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
        return toList(drivers.findAll());
    }

    @GetMapping("/trucks")
    public List<Truck> getTrucks() {
        return toList(trucks.findAll());
    }

    @GetMapping("/buyers")
    public List<Buyer> getBuyers() {
        return toList(buyers.findAll());
    }

    @GetMapping("/owners")
    public List<Owner> getOwners() {
        return toList(owners.findAll());
    }

    @GetMapping("/fuelings")
    public List<Fueling> getFuelings() {
        return toList(fuelings.findAll());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/transport")
    @Transactional
    public Transport transport(@RequestBody Transport transport) {

        Driver driver = drivers.findOne(transport.getDriver().getId());
        transport.setDriver(driver);

        Buyer buyer = buyers.findOne(transport.getBuyer().getId());
        transport.setBuyer(buyer);

        Truck truck = trucks.findOne(transport.getTruck().getId());
        transport.setTruck(truck);

        Owner owner = owners.findOne(transport.getOwner().getId());
        transport.setOwner(owner);

        transport.setTimestamp(LocalDateTime.now());

        return transports.save(transport);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/fuel")
    @Transactional
    public Fuel fuel(@RequestBody Fuel fuel) {

        Driver driver = drivers.findOne(fuel.getDriver().getId());
        fuel.setDriver(driver);

        Fueling fueling = fuelings.findOne(fuel.getFueling().getId());
        fuel.setFueling(fueling);

        Truck truck = trucks.findOne(fuel.getTruck().getId());
        fuel.setTruck(truck);

        fuel.setTimestamp(LocalDateTime.now());

        return fuels.save(fuel);
    }
}

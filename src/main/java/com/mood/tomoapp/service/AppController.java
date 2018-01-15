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
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.mood.tomoapp.domain.Buyer;
import com.mood.tomoapp.domain.Driver;
import com.mood.tomoapp.domain.Fuel;
import com.mood.tomoapp.domain.Fueling;
import com.mood.tomoapp.domain.Owner;
import com.mood.tomoapp.domain.Transport;
import com.mood.tomoapp.domain.Truck;
import com.mood.tomoapp.repos.BuyerRepository;
import com.mood.tomoapp.repos.DriverRepository;
import com.mood.tomoapp.repos.FuelRepository;
import com.mood.tomoapp.repos.FuelingRepository;
import com.mood.tomoapp.repos.OwnerRepository;
import com.mood.tomoapp.repos.TransportRepository;
import com.mood.tomoapp.repos.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AppController {

    @Autowired
    private DriverRepository drivers;

    @Autowired
    private TruckRepository trucks;

    @Autowired
    private BuyerRepository buyers;

    @Autowired
    private OwnerRepository owners;

    @Autowired
    private FuelingRepository fuelings;

    @Autowired
    private TransportRepository transports;

    @Autowired
    private FuelRepository fuels;

    private static <T> List<T> toList(Iterable<T> iterable) {
        Spliterator<T> splits = iterable.spliterator();
        return StreamSupport.stream(splits, false).collect(Collectors.toList());
    }

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
    public Transport transport(@RequestBody Transport transport) {
        return transports.save(transport);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/fuel")
    public Fuel fuel(@RequestBody Fuel fuel) {
        return fuels.save(fuel);
    }
}

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
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import com.mood.tomoapp.repos.AssortimentRepository;
import com.mood.tomoapp.repos.BuyerRepository;
import com.mood.tomoapp.repos.DriverRepository;
import com.mood.tomoapp.repos.FuelRepository;
import com.mood.tomoapp.repos.FuelingRepository;
import com.mood.tomoapp.repos.LocationRepository;
import com.mood.tomoapp.repos.OwnerRepository;
import com.mood.tomoapp.repos.PayerRepository;
import com.mood.tomoapp.repos.TransportRepository;
import com.mood.tomoapp.repos.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

public class AbstractController {

    @Autowired
    protected DriverRepository drivers;

    @Autowired
    protected TruckRepository trucks;

    @Autowired
    protected BuyerRepository buyers;

    @Autowired
    protected OwnerRepository owners;

    @Autowired
    protected FuelingRepository fuelings;

    @Autowired
    protected TransportRepository transports;

    @Autowired
    protected FuelRepository fuels;

    @Autowired
    protected PayerRepository payers;

    @Autowired
    protected AssortimentRepository assortments;

    @Autowired
    protected LocationRepository locations;
    
    protected List<Driver> getDrivers(int active) {
        return drivers.findByActiveIsGreaterThan(active, new Sort("driver"));
    }

    protected List<Truck> getTrucks(int active) {
        return trucks.findByActiveIsGreaterThan(active, new Sort("truck"));
    }

    protected List<Buyer> getBuyers(int active) {
        return buyers.findByActiveIsGreaterThan(active, new Sort("buyer"));
    }

    protected List<Owner> getOwners(int active) {
        return owners.findByActiveIsGreaterThan(active, new Sort("owner"));
    }

    protected List<Fueling> getFuelings(int active) {
        return fuelings.findByActiveIsGreaterThan(active, new Sort("fueling"));
    }

    protected List<Payer> getPayers(int active) {
        return payers.findByActiveIsGreaterThan(active, new Sort("payer"));
    }

    protected List<Assortment> getAssortments(int active) {
        return assortments.findByActiveIsGreaterThan(active, new Sort("assortment"));
    }

    protected List<Location> getLocations(int active) {
        return locations.findByActiveIsGreaterThan(active, new Sort("rank", "location"));
    }
    
    protected static <T> List<T> toList(Iterable<T> iterable) {
        Spliterator<T> splits = iterable.spliterator();
        return StreamSupport.stream(splits, false).collect(Collectors.toList());
    }

    protected void fillTransport(Transport transport, int driverId, int buyerId, int truckId, int ownerId, int payerId, int assortmentId, int locationIn, int locationOut) {
        Driver driver = drivers.findOne(driverId);
        transport.setDriver(driver);

        Buyer buyer = buyers.findOne(buyerId);
        transport.setBuyer(buyer);

        Truck truck = trucks.findOne(truckId);
        transport.setTruck(truck);

        Owner owner = owners.findOne(ownerId);
        transport.setOwner(owner);

        Payer payer = payers.findOne(payerId);
        transport.setPayer(payer);

        Assortment assortment = assortments.findOne(assortmentId);
        transport.setAssortment(assortment);

        Location location = locations.findOne(locationIn);
        transport.setLocationIn(location);

        location = locations.findOne(locationOut);
        transport.setLocationOut(location);

        transport.setTimestamp(LocalDateTime.now());
    }


    protected void fillFuel(Fuel fuel, int driverId, int truckId, int fuelingId, int locationId) {
        Driver driver = drivers.findOne(driverId);
        fuel.setDriver(driver);

        Truck truck = trucks.findOne(truckId);
        fuel.setTruck(truck);

        Fueling fueling = fuelings.findOne(fuelingId);
        fuel.setFueling(fueling);

        Location location = locations.findOne(locationId);
        fuel.setLocation(location);

        fuel.setTimestamp(LocalDateTime.now());
    }

}

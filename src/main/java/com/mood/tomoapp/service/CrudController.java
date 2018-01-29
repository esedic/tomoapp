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

import com.mood.tomoapp.domain.Buyer;
import com.mood.tomoapp.domain.Driver;
import com.mood.tomoapp.domain.Fueling;
import com.mood.tomoapp.domain.Owner;
import com.mood.tomoapp.domain.Payer;
import com.mood.tomoapp.domain.Truck;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/crud")
@Transactional
public class CrudController extends AbstractController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addDriver")
    public Driver add(@RequestBody Driver driver) {
        return drivers.save(driver);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addTruck")
    public Truck add(@RequestBody Truck truck) {
        return trucks.save(truck);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addBuyer")
    public Buyer add(@RequestBody Buyer buyer) {
        return buyers.save(buyer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addOwner")
    public Owner add(@RequestBody Owner owner) {
        return owners.save(owner);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addFueling")
    public Fueling add(@RequestBody Fueling fueling) {
        return fuelings.save(fueling);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addPayer")
    public Payer add(@RequestBody Payer payer) {
        return payers.save(payer);
    }

}

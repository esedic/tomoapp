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

package com.mood.tomoapp.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Kraj")
public class Location extends Activeable {

    @Id
    @Column(name = "KrajId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Kraj")
    private String location;

    @Column(name = "Rang")
    private int rank;

    @JsonIgnore
    @OneToMany(mappedBy = "locationIn")
    private Set<Transport> transportsIn;

    @JsonIgnore
    @OneToMany(mappedBy = "locationOut")
    private Set<Transport> transportsOut;

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private Set<Fuel> fuels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Set<Transport> getTransportsIn() {
        return transportsIn;
    }

    public void setTransportsIn(Set<Transport> transportsIn) {
        this.transportsIn = transportsIn;
    }

    public Set<Transport> getTransportsOut() {
        return transportsOut;
    }

    public void setTransportsOut(Set<Transport> transportsOut) {
        this.transportsOut = transportsOut;
    }

    public Set<Fuel> getFuels() {
        return fuels;
    }

    public void setFuels(Set<Fuel> fuels) {
        this.fuels = fuels;
    }
}

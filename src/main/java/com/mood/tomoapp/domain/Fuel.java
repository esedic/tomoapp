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

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mood.tomoapp.config.LocalDateHelper;
import com.mood.tomoapp.config.LocalDateTimeHelper;

@Entity
@Table(name = "Gorivo")
public class Fuel {

    @Id
    @Column(name = "GorivoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Fueling fueling;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "KrajTocenjaId")
    private Location location;

    @Column(name = "KrajTocenjaNov")
    private String locationNew;

    @JsonSerialize(using = LocalDateHelper.Serializer.class)
    @JsonDeserialize(using = LocalDateHelper.Deserializer.class)
    @Convert(converter = LocalDateHelper.class)
    @Column(name = "DatumTocenja")
    private LocalDate date;

    @Column(name = "Kolicina")
    private Double quantity;

    @Column(name = "Km")
    private Integer km;

    @Column(name = "FakturaSt")
    private String facture;

    @Column(name = "Opomba")
    private String remark;

    @JsonIgnore
    @Convert(converter = LocalDateTimeHelper.class)
    @Column(name = "DatumVpisa")
    private LocalDateTime timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Fueling getFueling() {
        return fueling;
    }

    public void setFueling(Fueling fueling) {
        this.fueling = fueling;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationNew() {
        return locationNew;
    }

    public void setLocationNew(String locationNew) {
        this.locationNew = locationNew;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public String getFacture() {
        return facture;
    }

    public void setFacture(String facture) {
        this.facture = facture;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

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
@Table(name = "Prevoz")
public class Transport {

    @Id
    @Column(name = "PrevozId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Truck truck;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Payer payer;

    @ManyToOne
    @JoinColumn(name = "KrajNakladanjaId")
    private Location locationIn;

    @Column(name = "KrajNakladanjaNov")
    private String locationInNew;

    @JsonSerialize(using = LocalDateHelper.Serializer.class)
    @JsonDeserialize(using = LocalDateHelper.Deserializer.class)
    @Convert(converter = LocalDateHelper.class)
    @Column(name = "DatumNakladanja")
    private LocalDate dateIn;

    @ManyToOne
    @JoinColumn(name = "KrajRazkladanjaId")
    private Location locationOut;

    @Column(name = "KrajRazkladanjaNov")
    private String locationOutNew;

    @JsonSerialize(using = LocalDateHelper.Serializer.class)
    @JsonDeserialize(using = LocalDateHelper.Deserializer.class)
    @Convert(converter = LocalDateHelper.class)
    @Column(name = "DatumRazkladanja")
    private LocalDate dateOut;

    @ManyToOne
    private Assortment assortment;

    @Column(name = "Kolicina")
    private Double quantity;

    @Column(name = "Opombe")
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Location getLocationIn() {
        return locationIn;
    }

    public void setLocationIn(Location locationIn) {
        this.locationIn = locationIn;
    }

    public String getLocationInNew() {
        return locationInNew;
    }

    public void setLocationInNew(String locationInNew) {
        this.locationInNew = locationInNew;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    public Location getLocationOut() {
        return locationOut;
    }

    public void setLocationOut(Location locationOut) {
        this.locationOut = locationOut;
    }

    public String getLocationOutNew() {
        return locationOutNew;
    }

    public void setLocationOutNew(String locationOutNew) {
        this.locationOutNew = locationOutNew;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public Assortment getAssortment() {
        return assortment;
    }

    public void setAssortment(Assortment assortment) {
        this.assortment = assortment;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

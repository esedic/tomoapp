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

package com.mood.tomoapp.model;

import java.time.LocalDate;

public class TransportModel {

    private Integer driver;
    private Integer buyer;
    private Integer truck;
    private Integer owner;
    private Integer payer;

    private Integer locationIn;
    private String locationInNew;
    private LocalDate dateIn;

    private Integer locationOut;
    private String locationOutNew;
    private LocalDate dateOut;

    private Integer assortment;

    private Double quantity;

    private String remark;

    public Integer getDriver() {
        return driver;
    }

    public void setDriver(Integer driver) {
        this.driver = driver;
    }

    public Integer getBuyer() {
        return buyer;
    }

    public void setBuyer(Integer buyer) {
        this.buyer = buyer;
    }

    public Integer getTruck() {
        return truck;
    }

    public void setTruck(Integer truck) {
        this.truck = truck;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getPayer() {
        return payer;
    }

    public void setPayer(Integer payer) {
        this.payer = payer;
    }

    public Integer getLocationIn() {
        return locationIn;
    }

    public void setLocationIn(Integer locationIn) {
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

    public Integer getLocationOut() {
        return locationOut;
    }

    public void setLocationOut(Integer locationOut) {
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

    public Integer getAssortment() {
        return assortment;
    }

    public void setAssortment(Integer assortment) {
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
}

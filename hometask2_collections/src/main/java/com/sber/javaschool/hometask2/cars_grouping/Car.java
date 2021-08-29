package com.sber.javaschool.hometask2.cars_grouping;

import java.util.Objects;

public class Car implements Comparable<Car> {
    private final String model;
    private final String type;

    public Car(String model, String type) {
        this.model = model;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return model.equals(car.model) && type.equals(car.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, type);
    }

    @Override
    public int compareTo(Car car) {
        return this.type.compareTo(car.type);
    }

    @Override
    public String toString() {
        return "Car(" + '\'' + model + " " + type + '\'' + '}';
    }
}

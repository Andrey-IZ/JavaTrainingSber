package com.sber.javaschool.hometask2.cars_grouping;

import java.util.Collection;

public class CarInitializing {
    public static void init(Collection<Car> car) {
        car.add(new Car("Лада", "седан"));
        car.add(new Car("Мерседес", "седан"));
        car.add(new Car("Бмв", "кроссовер"));
        car.add(new Car("Форд", "хэтчбек"));
        car.add(new Car("Пежо", "кроссовер"));
        car.add(new Car("Тойота", "седан"));

    }
}

package com.sber.javaschool.hometask2.cars_grouping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {
    public static void main(String[] args) {
        cars_grouping();
    }

    /**
     * Задача.
     * Имеется список парка машин Car(String model, String type). Необходимо разбить его на списки
     * сгруппированные по type.
     * Пример исходного списка: Лада седан, Лада хэтчбек, Мерседес седан, Бмв кроссовер, Форд хэтчбек,
     * Пежо кроссовер, Тойота седан и т.п.
     */
    private static void cars_grouping() {
        List<Car> cars = new ArrayList<>();
        CarInitializing.init(cars);

//        var hm = cars.stream().collect(Collectors.groupingBy(Car::getType));
//        System.out.println(hm);

        HashMap<String, List<Car>> groupList = new HashMap<>();
        for (Car car : cars) {
            var key = car.getType();
            var list = groupList.getOrDefault(key, new ArrayList<>());
            list.add(car);
            groupList.put(key, list);
        }
        System.out.println(groupList);
    }
}

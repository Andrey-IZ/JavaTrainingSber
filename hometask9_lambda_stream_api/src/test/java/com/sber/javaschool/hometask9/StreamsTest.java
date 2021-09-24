package com.sber.javaschool.hometask9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamsTest {

    private List<Person> people;

    @BeforeEach
    void setup() {
        people = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person("Person" + i, 30 + i);
            people.add(person);
        }
    }

    @Test
    void of() {
        List<Person> list = Streams.of(people).toList();
        assertArrayEquals(list.toArray(), people.toArray());
    }

    @Test
    void filter() {
        Predicate<Person> predicate = p -> p.getAge() > 20;
        List<Person> list = Streams.of(people)
                .filter(predicate).toList();
        assertEquals(list, people.stream().filter(predicate).collect(Collectors.toList()));
    }

    @Test
    void toMap() {
        Map<Person, String> map = Streams.of(people).toMap(person -> person, Person::getName);
        assertEquals(map, people.stream().collect(Collectors.toMap(p -> p, Person::getName)));
    }

    @Test
    void transform() {
        Function<? super Person, ? extends Person> function = p -> new Person(p.getName(), p.getAge() + 30);
        List<Person> list = Streams.of(people)
                .transform(function).toList();
        assertEquals(list, people.stream().map(function).collect(Collectors.toList()));

    }
}
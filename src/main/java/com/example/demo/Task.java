package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.Group.G1;
import static jdk.nashorn.internal.objects.NativeMath.max;

public class Task {

  /*
  Zwróć listę aktywnych graczy posortowanych po ich wyniku malejąco
   */

    public static List<Person> getActivePlayersByScoreDesc(List<Person> people) {

        return people.stream()
                .filter(p -> p.isActive() == true)
                .collect(Collectors.toList())
                .stream().filter((p -> p.getScore() > 0)).sorted()
                .collect(Collectors.toList());

    }

  /*
  Zwróć listę aktywnych graczy z danej grupy posortowanych po ich wyniku malejąco
   */

    public static List<Person> getActivePlayersByScoreDesc(List<Person> people, Group group) {
        people = List.of(new Person("name", 2, group, true),
                new Person("name", 1, group, true)
        );
        return people.stream()
                .filter(p -> p.getTeam().equals(group) == true)
                .collect(Collectors.toList())
                .stream()
                .filter(p -> p.isActive() == true)
                .collect(Collectors.toList())
                .stream().filter((p -> p.getScore() > 0)).sorted()
                .collect(Collectors.toList());


    }

    /*
    Zwróć grupę, która posiada najwyższy wynik. Jeżeli wynik wielu grup jest taki sam, zwróć tę, która ma mniejszą liczbę aktywnych członków.
    Jeżeli ta liczba jest również równa, zwróć którąkolwiek z nich.
     */
    public static Group getGroupWithHighestScore(List<Person> people) {
        return people.stream()
                .map(s->max(s.getScore())).findAny()
                .get(getGroupWithHighestScore());


    }

  /*
  Zwróć listę wyników posortowaną malejąco na podstawie ilości punktów per zespół.
  Pojedynczy String powinien mieć format: "NazwaGrupy CałkowityWynik (lista_aktywnych_członków) [ilość nieaktywnych członków]"
   */

    public static List<String> printPoints(List<Person> people) {
        throw new RuntimeException("Not Implemented!");
    }
}

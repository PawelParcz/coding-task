package com.example.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Task {

  /*
  Zwróć listę aktywnych graczy posortowanych po ich wyniku malejąco
   */

    public static List<Person> getActivePlayersByScoreDesc(List<Person> people) {
        List<Integer> listScoreWithActive = people.stream()
                .filter(p -> p.isActive() == true)
                .map(p -> p.getScore()).sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        List<Person> listPeople = new ArrayList<>();

        for (Person s : people) {
            for (Integer i : listScoreWithActive) {
                if (s.getScore().equals(i)) {
                    listPeople.add(s);
                    return listPeople;
                }
            }
        }
        return listPeople;
    }

  /*
  Zwróć listę aktywnych graczy z danej grupy posortowanych po ich wyniku malejąco
   */

    public static List<Person> getActivePlayersByScoreDesc(List<Person> people, Group group) {
        people = List.of(new Person("name", 2, group, true),
                new Person("name", 1, group, true)
        );
        people = people.stream()
                .filter(p -> p.getTeam().equals(group))
                .collect(Collectors.toList())
                .stream()
                .filter(p -> p.isActive() == true)
                .collect(Collectors.toList());
        return getActivePlayersByScoreDesc(people);

    }

    /*
    Zwróć grupę, która posiada najwyższy wynik. Jeżeli wynik wielu grup jest taki sam, zwróć tę, która ma mniejszą liczbę aktywnych członków.
    Jeżeli ta liczba jest również równa, zwróć którąkolwiek z nich.
     */
    public static Group getGroupWithHighestScore(List<Person> people) {
        List<Integer> scoreList = people.stream().map(p -> p.getScore()).collect(Collectors.toList());
        Group group;
        int score1 = people.stream().filter(p -> p.getTeam().equals(Group.G1)).map(p -> p.getScore()).reduce(0, (a, b) -> a + b);
        int score2 = people.stream().filter(p -> p.getTeam().equals(Group.G2)).map(p -> p.getScore()).reduce(0, (a, b) -> a + b);
        int score3 = people.stream().filter(p -> p.getTeam().equals(Group.G3)).map(p -> p.getScore()).reduce(0, (a, b) -> a + b);
        if (score1 > score2 && score1 > score3) {
            return Group.G1;
        } else if (score2 > score1 && score2 > score3) {
            return Group.G2;
        } else if (score3 > score2 && score3 > score1) {
            return Group.G3;
        } else if (score1 == score2 || score1 == score3 || score2 == score3) {
            return Group.G1;
        }
        return null;
    }

  /*
  Zwróć listę wyników posortowaną malejąco na podstawie ilości punktów per zespół.
  Pojedynczy String powinien mieć format: "NazwaGrupy CałkowityWynik  [ilość nieaktywnych członków]"
   */

    public static String printPoints(List<Person> people) {
        Group group;
        String nazwaGr= getActivePlayersByScoreDesc(people).stream().map(p->p.getTeam().getDeclaringClass().getName()).toString();
        String wynik=getActivePlayersByScoreDesc(people).stream().map(p->p.getScore()).toString();
       long ilość= people.stream().map(p->p.isActive()==false).count();

       String il= nazwaGr+wynik+ilość;

       return il;


    }
}

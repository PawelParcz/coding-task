package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static jdk.nashorn.internal.objects.NativeMath.max;
import static jdk.nashorn.internal.objects.NativeMath.min;


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
                .filter(p -> p.getTeam().equals(group))
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
        List<Integer> scoreList = people.stream().map(p -> p.getScore()).collect(Collectors.toList());

        for (int i = people.size() - 1; 0 <= i; i--) {
            for (int j = people.size() - 1; 0 <= j; j--) {
                if (max(scoreList.get(i)) > max(scoreList.get(j + 1))) {
                    return people.get(i).getTeam();
                } else if (max(scoreList.get(i)) == max(scoreList.get(j + 1))) {
                    List<Boolean> activList = people.stream().map(p -> p.isActive() == false).collect(Collectors.toList());
                    if (min(activList.get(i)) < min(activList.get(j))) {
                        return people.get(i).getTeam();
                    } else if (min(activList.get(i)) == min(activList.get(j))) {
                        return people.get(j).getTeam();
                    }
                }
            }
        }
        return null;
    }

  /*
  Zwróć listę wyników posortowaną malejąco na podstawie ilości punktów per zespół.
  Pojedynczy String powinien mieć format: "NazwaGrupy CałkowityWynik  [ilość nieaktywnych członków]"
   */

    public static List<String> printPoints(List<Person> people) {
        List<String> scoreSort = people.stream()
                .map(p->p.getScore()).sorted().map(p->p.toString()).collect(Collectors.toList());
        for (int i = 0; i < people.size(); i++) {
           List<String> newListAd=new ArrayList<>();
           newListAd.add(people.get(i).getTeam().name()+scoreSort.get(i)+
                   people.stream().filter(a->a.isActive()==true).collect(Collectors.toList()));
                  // (people.stream().filter(p->p.isActive()==false).count());
           return newListAd;
        }
     return null;
    }
}

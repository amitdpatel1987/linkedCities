package com.example.demo;

import java.util.*;


public class Commute {

    private Commute() { }

    public static boolean commute(City origin, City destination) {

        if (origin.equals(destination)) return true;

        if (origin.getNearby().contains(destination)) return true;

        Set<City> visited = new HashSet<>(Collections.singleton(origin));

        Deque<City> bucketlist = new ArrayDeque<>(origin.getNearby());


        while (!bucketlist.isEmpty()) {


            City city = bucketlist.getLast();

            if (city.equals(destination)) return true;

            if (!visited.contains(city)) {

                visited.add(city);

                bucketlist.addAll(city.getNearby());
                bucketlist.removeAll(visited);

            } else {
                bucketlist.removeAll(Collections.singleton(city));
            }
        }

        return false;
    }

}

package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class CityTest {

    @Test
    public void build() {
        City city = City.build("Bostan");
        Assert.assertEquals("Bostan", city.getName());
    }

    @Test
    public void buildWithNeighbours() {
        City city = City.build("Bostan");
        city.addNearby(City.build("New York"))
                .addNearby(City.build("Newark"));

        Set<City> nearby = city.getNearby();
        Assert.assertEquals(2, nearby.size());
        Assert.assertTrue(nearby.contains(City.build("New York")));
    }


    @Test
    public void addNearby() {
        City city = City.build("Bostan");
        city.addNearby(City.build("New York"))
                .addNearby(City.build("Newark"));

        Assert.assertEquals(2, city.getNearby().size());
    }

    @Test
    public void addNearbyDuplicates() {
        City city = City.build("Bostan");
        city.addNearby(City.build("New York"))
                .addNearby(City.build("NEWARK"))
                .addNearby(City.build(" NEW YORK"))
                .addNearby(City.build("  NEW YORK "));

        Assert.assertEquals(1, city.getNearby().size());
    }


}
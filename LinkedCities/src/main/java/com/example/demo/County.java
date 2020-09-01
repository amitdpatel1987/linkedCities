package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class County {

    private Map<String, City> cityMap = new HashMap<>();

    @Value("${data.file:classpath:city.txt}")
    private String CITIES;

    @Autowired
    private ResourceLoader resourceLoader;


    public Map<String, City> getCityMap() {
        return cityMap;
    }

    @PostConstruct
    private void read() throws IOException {

        Resource resource = resourceLoader.getResource(CITIES);

        InputStream is;

        if (!resource.exists()) {
            is = new FileInputStream(new File(CITIES));
        } else {
            is = resource.getInputStream();
        }

        Scanner scan = new Scanner(is);

        while (scan.hasNext()) {

            String line = scan.nextLine();
            if (StringUtils.isEmpty(line)) continue;

            String[] split = line.split(",");
            String data1 = split[0].trim().toUpperCase();
            String data2 = split[1].trim().toUpperCase();

            if (!data1.equals(data2)) {
                City A = cityMap.getOrDefault(data1, City.build(data1));
                City B = cityMap.getOrDefault(data2, City.build(data2));

                A.addNearby(B);
                B.addNearby(A);

                cityMap.put(A.getName(), A);
                cityMap.put(B.getName(), B);
            }
        }
        
        scan.close();
    }

    public City getCity(String name) {
        return cityMap.get(name);
    }

}

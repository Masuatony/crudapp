package com.example.firstcrud.LearnStreams.MockData;

import com.example.firstcrud.LearnStreams.EntityDtos.Car;
import com.example.firstcrud.LearnStreams.EntityDtos.Person;
import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static List<Person> getPeople() throws IOException {
        InputStream inputStream = Resources.getResource("Data/people.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Person>>(){
        }.getType();
        return new Gson().fromJson(json, listType);
    }

    public static List<Car> getCars() throws IOException {
        InputStream inputStream = Resources.getResource("Data/cars.json").openStream();
        String json = IOUtils.toString(inputStream,StandardCharsets.UTF_8);
        Type listType =  new TypeToken<ArrayList<Car>>(){}
                .getType();
        return new Gson().fromJson(json,listType);
    }

}

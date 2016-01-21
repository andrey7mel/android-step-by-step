package com.andrey7mel.testrx.other;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {

    private Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

    public void log(String log) {
        System.out.println(log);
    }

    public Gson getGson() {
        return gson;
    }


    public <T> T readJson(String fileName, Class<T> inClass) {
        return gson.fromJson(readString(fileName), inClass);
    }

    public String readString(String fileName) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
            int size = stream.available();
            byte[] buffer = new byte[size];
            int result = stream.read(buffer);
            return new String(buffer, "utf8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}

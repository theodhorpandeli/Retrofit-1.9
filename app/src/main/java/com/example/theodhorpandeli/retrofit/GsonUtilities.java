package com.example.theodhorpandeli.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Timestamp;

import retrofit.client.Response;

/**
 * Created by Theodhor Pandeli on 2/11/2016.
 */
public class GsonUtilities {

    public static Gson getGsonWithDateTypeAdapter() {
        return getGsonWithDateTypeAdapter("yyyy-MM-dd HH:mm:ss");
    }

    public static Gson getGsonWithDateTypeAdapter(String format) {
        return new GsonBuilder().setDateFormat(format).create();
    }

    public static Gson getGsonWithDateTypeAdapterAndBooleanAdapter(String format) {
        return new GsonBuilder()
                .registerTypeAdapter(Boolean.class, new BooleanSerializer())
                .setDateFormat(format)
                .create();
    }

    public static Gson getGsonWithDateTimestamp(String dateformat){
        GsonBuilder builder = new GsonBuilder()
                .setDateFormat(dateformat);
        builder.registerTypeAdapter(Timestamp.class, new JsonDeserializer<Timestamp>() {
            public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Timestamp(json.getAsJsonPrimitive().getAsLong()*1000); // since we have the json giving us the value in seconds then we multiply 1000
            }
        });
        return builder.create();
    }


    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * This is a function that get a response from the server and returns the plain
     * string of the response.
     * @param result
     * @return
     */
    public static String getStringServerResponse(Response result){
        //Try to get response body
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {

            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        return sb.toString();
    }
}
package ru.ya.litun.academyyandex.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ru.ya.litun.academyyandex.model.Genre;

/**
 * Created by Litun on 20.07.2016.
 */
public class GenreDeserializer implements JsonDeserializer<Genre> {
    @Override
    public Genre deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Genre g = new Genre();
        g.setName(json.toString());
        return g;
    }
}

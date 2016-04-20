package ru.ya.litun.academyyandex.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Litun on 21.04.2016.
 */
public class Artist {
    int id;
    String name;
    List<String> genres = new ArrayList<>();
    int tracks;
    int albums;
    String link;
    String description;
    Cover cover;
}

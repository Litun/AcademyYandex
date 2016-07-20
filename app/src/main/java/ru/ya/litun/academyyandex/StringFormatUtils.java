package ru.ya.litun.academyyandex;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.ya.litun.academyyandex.model.Genre;

/**
 * Created by Litun on 21.04.2016.
 */
public class StringFormatUtils {
    public static String concatWithCommas(Collection<Genre> genres) {
        if (genres == null || genres.size() == 0)
            return "";
        List<Genre> strings = new ArrayList<>(genres);
        StringBuilder sb = new StringBuilder()
                .append(strings.get(0).getName());

        for (int i = 1; i < strings.size(); i++) {
            sb.append(", ")
                    .append(strings.get(i).getName());
        }
        return sb.toString();
    }

    private static Resources resources;

    public static void initResources(Context context) {
        resources = context.getResources();
    }

    public static String formatAlbumsAndSongs(int albums, int songs) {
        if (resources == null)
            return "";

        return albums + " " + resources.getQuantityString(R.plurals.album_plurals, albums) +
                ", " + songs + " " + resources.getQuantityString(R.plurals.song_plurals, songs);
    }
}

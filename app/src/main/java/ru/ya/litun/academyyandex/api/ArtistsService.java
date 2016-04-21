package ru.ya.litun.academyyandex.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.ya.litun.academyyandex.model.Artist;

/**
 * Created by Litun on 21.04.2016.
 */
public interface ArtistsService {
    @GET("artists.json")
    Call<List<Artist>> listArtist();
}

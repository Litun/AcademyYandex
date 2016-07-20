package ru.ya.litun.academyyandex.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.ya.litun.academyyandex.model.Artist;
import ru.ya.litun.academyyandex.model.Genre;

/**
 * Encapsulates api work.
 * Created by Litun on 21.04.2016.
 */
public class ApiManager {
    private static final String URL = "http://cache-default06d.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/";
    private ArtistsService service;
    private ArtistsListener artistsListener;
    private FailureListener failureListener;

    public ApiManager() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Genre.class, new GenreDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(ArtistsService.class);
    }

    public void requestArtists(ArtistsListener listener) {
        setArtistsListener(listener);
        requestArtists();
    }

    public void requestArtists() {
        service.listArtist().enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                if (artistsListener != null)
                    artistsListener.onArtistsLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                if (failureListener != null)
                    failureListener.onFailure(t);
            }
        });
    }

    public void setArtistsListener(ArtistsListener artistsListener) {
        this.artistsListener = artistsListener;
    }

    public void setFailureListener(FailureListener failureListener) {
        this.failureListener = failureListener;
    }

    /**
     * Independent api listener.
     */
    public interface ArtistsListener {
        void onArtistsLoaded(List<Artist> artists);
    }

    public interface FailureListener {
        void onFailure(Throwable t);
    }
}

package ru.ya.litun.academyyandex.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.ya.litun.academyyandex.model.Artist;

/**
 * Created by Litun on 21.04.2016.
 */
public class ApiManager {
    private static final String URL = "http://cache-default06d.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/";
    private ArtistsService service;
    private ArtistsListener artistsListener;
    private FailureListener failureListener;

    public ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
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

    public interface ArtistsListener {
        void onArtistsLoaded(List<Artist> artists);
    }

    public interface FailureListener {
        void onFailure(Throwable t);
    }
}

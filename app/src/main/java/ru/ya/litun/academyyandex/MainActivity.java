package ru.ya.litun.academyyandex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.ya.litun.academyyandex.model.Artist;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArtistsAdapter artistsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistsAdapter = new ArtistsAdapter(this);
        artistsAdapter.setClickListener(new ArtistsAdapter.OnArtistClickListener() {
            @Override
            public void onClick(int id) {
                startAboutActivity(id);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setAdapter(artistsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration());

        App.getDataManager().requestArtistsUpdate(new DataManager.DataListener() {
            @Override
            public void onArtistsUpdated(List<Artist> artists) {
                artistsAdapter.newList(artists);
            }
        });
    }

    private void startAboutActivity(int id) {
        Intent intent = new Intent(this, AboutActivity.class);
        intent.putExtra(AboutActivity.ID_KEY, id);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        artistsAdapter.newList(App.getDataManager().getCachedArtists());
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.getDataManager().unregisterListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        artistsAdapter.setClickListener(null);
    }
}

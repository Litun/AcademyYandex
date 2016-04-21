package ru.ya.litun.academyyandex;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import ru.ya.litun.academyyandex.model.Artist;

public class MainActivity extends AppCompatActivity
        implements DataManager.DataListener, DataManager.UpdateFailureListener {

    private RecyclerView recyclerView;
    private ArtistsAdapter artistsAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refresh;
    private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        artistsAdapter = new ArtistsAdapter(this);
        artistsAdapter.setClickListener(new ArtistsAdapter.OnArtistClickListener() {
            @Override
            public void onClick(int id) {
                startAboutActivity(id);
            }
        });
        recyclerView.setAdapter(artistsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration());

        List<Artist> artists = App.getDataManager().getCachedArtists();
        if (artists.size() != 0) {
            artistsAdapter.newList(artists);
            showList();
        }
        App.getDataManager().setListener(this);
        App.getDataManager().setFailureListener(this);
        App.getDataManager().requestArtistsUpdate();

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                App.getDataManager().requestArtistsUpdate();
            }
        });
    }

    private void findViews() {
        root = findViewById(R.id.root);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
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
    protected void onDestroy() {
        super.onDestroy();
        App.getDataManager().unregisterListeners();
        artistsAdapter.setClickListener(null);
    }

    @Override
    public void onArtistsUpdated(List<Artist> artists) {
        artistsAdapter.newList(artists);
        showList();
    }

    @Override
    public void onFailure() {
        Snackbar.make(root, R.string.error, Snackbar.LENGTH_LONG)
                .show();
        showList();
    }

    void showList() {
        progressBar.setVisibility(View.INVISIBLE);
        refresh.setVisibility(View.VISIBLE);
        refresh.setRefreshing(false);
    }
}

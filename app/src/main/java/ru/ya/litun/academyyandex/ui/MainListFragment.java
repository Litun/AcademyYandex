package ru.ya.litun.academyyandex.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import ru.ya.litun.academyyandex.App;
import ru.ya.litun.academyyandex.DataManager;
import ru.ya.litun.academyyandex.R;
import ru.ya.litun.academyyandex.model.Artist;

public class MainListFragment extends ToolbarFragment
        implements DataManager.DataListener, DataManager.UpdateFailureListener {

    private OnFragmentListener mListener;

    private RecyclerView recyclerView;
    private ArtistsAdapter artistsAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refresh;
    private View root;

    public MainListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);

        findViews(view);
        artistsAdapter = new ArtistsAdapter(getContext());
        artistsAdapter.setClickListener(new ArtistsAdapter.OnArtistClickListener() {
            @Override
            public void onClick(int id) {
                showAboutFragment(id);
            }
        });
        recyclerView.setAdapter(artistsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

        setToolbar((Toolbar) view.findViewById(R.id.toolbar));
        setTitle(R.string.artists);

        return view;
    }


    private void showAboutFragment(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(AboutFragment.ID_KEY, id);
        mListener.onOpenNewFragment(MainActivity.ABOUT_FRAGMENT_KEY, bundle);
    }

    void showList() {
        progressBar.setVisibility(View.INVISIBLE);
        refresh.setVisibility(View.VISIBLE);
        refresh.setRefreshing(false);
    }

    private void findViews(View v) {
        root = v.findViewById(R.id.root);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        progressBar = (ProgressBar) v.findViewById(R.id.progress);
        refresh = (SwipeRefreshLayout) v.findViewById(R.id.refresh);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            mListener = (OnFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.getDataManager().unregisterListeners();
        artistsAdapter.setClickListener(null);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    public interface OnFragmentListener {
        void onOpenNewFragment(int fragmentKey, Bundle bundle);
    }
}

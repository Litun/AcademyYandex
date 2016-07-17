package ru.ya.litun.academyyandex.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ru.ya.litun.academyyandex.R;

public class MainActivity extends AppCompatActivity implements MainListFragment.OnFragmentListener {

    public static final int MAIN_FRAGMENT_KEY = 1;
    public static final int ARTIST_FRAGMENT_KEY = 2;
    public static final int ABOUT_FRAGMENT_KEY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment mainFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (mainFragment == null) {
            mainFragment = new MainListFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, mainFragment);
//            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onOpenNewFragment(int fragmentKey, Bundle bundle) {
        switch (fragmentKey) {
            case MAIN_FRAGMENT_KEY:
                break;
            case ARTIST_FRAGMENT_KEY:
                ArtistFragment artistFragment = new ArtistFragment();
                artistFragment.setArguments(bundle);
                placeFragment(artistFragment);
                break;
            case ABOUT_FRAGMENT_KEY:
                AboutFragment aboutFragment = new AboutFragment();
                placeFragment(aboutFragment);
                break;
        }
    }

    private void placeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

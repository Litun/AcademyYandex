package ru.ya.litun.academyyandex.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Litun on 17.07.2016.
 */
public class ToolbarFragment extends Fragment {
    private AppCompatActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AppCompatActivity)
            activity = (AppCompatActivity) context;
    }

    protected void setToolbar(Toolbar toolbar) {
        if (activity != null)
            activity.setSupportActionBar(toolbar);
    }

    protected void setTitle(String title) {
        if (activity != null)
            activity.getSupportActionBar().setTitle(title);
    }

    protected void upButon(boolean enabled) {
        if (activity != null)
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }
}

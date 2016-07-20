package ru.ya.litun.academyyandex.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import ru.ya.litun.academyyandex.R;

/**
 * Created by Litun on 17.07.2016.
 */
public class ToolbarFragment extends Fragment {
    private AppCompatActivity activity;
    private TextView customTitle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AppCompatActivity)
            activity = (AppCompatActivity) context;
    }

    protected void setToolbar(Toolbar toolbar) {
        if (activity != null)
            activity.setSupportActionBar(toolbar);
        customTitle = (TextView) toolbar.findViewById(R.id.title);
    }

    protected void setTitle(String title) {
        if (customTitle != null)
            customTitle.setText(title);
        else if (activity != null)
            activity.getSupportActionBar().setTitle(title);
    }

    protected void setTitle(int resId) {
        if (customTitle != null)
            customTitle.setText(resId);
        else if (activity != null)
            activity.getSupportActionBar().setTitle(resId);
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

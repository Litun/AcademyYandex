package ru.ya.litun.academyyandex.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.ya.litun.academyyandex.R;

public class AboutFragment extends ToolbarFragment {

    public AboutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        setToolbar((Toolbar) view.findViewById(R.id.toolbar));
        setTitle(R.string.about);
        upButon(true);
        return view;
    }

}

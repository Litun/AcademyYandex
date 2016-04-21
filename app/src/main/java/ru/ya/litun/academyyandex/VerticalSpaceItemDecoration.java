package ru.ya.litun.academyyandex;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Litun on 21.04.2016.
 */
public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private static final int DEFAULT_SPACE = 1;
    private final int mVerticalSpaceHeight;

    public VerticalSpaceItemDecoration(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    public VerticalSpaceItemDecoration() {
        this.mVerticalSpaceHeight = DEFAULT_SPACE;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        //if not last
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = mVerticalSpaceHeight;
        }
    }
}

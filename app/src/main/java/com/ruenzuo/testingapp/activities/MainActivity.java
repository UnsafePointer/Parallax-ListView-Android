package com.ruenzuo.testingapp.activities;

import android.app.ListActivity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;

import com.ruenzuo.testingapp.R;
import com.ruenzuo.testingapp.adapters.PictureAdapter;
import com.ruenzuo.testingapp.extensions.ObservableListView;

import static android.util.TypedValue.*;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] media = getResources().getStringArray(R.array.pictures);
        PictureAdapter adapter = new PictureAdapter(this, R.layout.picture_row_layout);
        adapter.addAll(media);
        setListAdapter(adapter);
        final ObservableListView listView = (ObservableListView) getListView();
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                for (int i = firstVisibleItem; i < visibleItemCount; i++) {
                    View row = listView.getChildAt(i);
                    float px = applyDimension(COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
                    float velocity = applyDimension(COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics());
                    float offset = ((listView.getScroll() - listView.getViewHeigth(row)) / px ) * velocity;
                    PictureAdapter.MediaViewHolder holder = (PictureAdapter.MediaViewHolder)row.getTag();
                    Matrix matrix = holder.imgViewPicture.getImageMatrix();
                    matrix.setTranslate(0, offset);
                    holder.imgViewPicture.setImageMatrix(matrix);
                    holder.imgViewPicture.invalidate();
                }
            }

        });
    }

}

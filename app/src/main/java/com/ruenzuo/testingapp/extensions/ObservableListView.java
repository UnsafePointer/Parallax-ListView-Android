package com.ruenzuo.testingapp.extensions;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListView;

import java.util.Dictionary;
import java.util.Hashtable;

import static android.util.TypedValue.applyDimension;

/**
 * Created by renzocrisostomo on 14/06/14.
 */
public class ObservableListView extends ListView {

    private float px;

    private Callbacks mCallbacks;

    public ObservableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        px = applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mCallbacks != null) {
            mCallbacks.onScrollChanged();
        }
    }

    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    public void setCallbacks(Callbacks listener) {
        mCallbacks = listener;
    }

    public static interface Callbacks {
        public void onScrollChanged();
    }

    private Dictionary<Integer, Integer> listViewItemHeights = new Hashtable<Integer, Integer>();

    public int getScroll() {
        View c = getChildAt(0);
        if (c == null) {
            return 0;
        }
        int scrollY = -c.getTop();
        listViewItemHeights.put(getFirstVisiblePosition(), c.getHeight());
        for (int i = 0; i < getFirstVisiblePosition(); ++i) {
            if (listViewItemHeights.get(i) != null) {
                scrollY += listViewItemHeights.get(i);
            }
        }
        return scrollY;
    }

    public int getViewHeigth(View v) {
        int viewPosition = getPositionForView(v);
        int scrollY = Math.round(viewPosition * px);
        return scrollY;
    }

}

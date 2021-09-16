package com.example.likedouyin.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author : wangzhirui
 * date : 2021/9/9
 * description :
 */
public class ViewPagerLayoutManager extends LinearLayoutManager {

    private PagerSnapHelper mPageSnapHelp;
    private RecyclerView mParentRecycleView;

    public ViewPagerLayoutManager(Context context) {
        super(context);
        init();
    }

    public ViewPagerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        init();
    }

    public ViewPagerLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        mPageSnapHelp = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mParentRecycleView = view;
        mPageSnapHelp.attachToRecyclerView(view);
    }
}

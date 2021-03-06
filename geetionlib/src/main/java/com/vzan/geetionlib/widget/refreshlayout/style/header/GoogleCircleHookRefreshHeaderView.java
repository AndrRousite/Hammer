package com.vzan.geetionlib.widget.refreshlayout.style.header;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.vzan.geetionlib.R;
import com.vzan.geetionlib.widget.refreshlayout.listener.SwipeRefreshTrigger;
import com.vzan.geetionlib.widget.refreshlayout.listener.SwipeTrigger;
import com.vzan.geetionlib.widget.refreshlayout.style.google.CircleProgressView;

public class GoogleCircleHookRefreshHeaderView extends FrameLayout implements SwipeTrigger,
        SwipeRefreshTrigger {
    private CircleProgressView progressView;

    private int mTriggerOffset;

    private int mFinalOffset;

    public GoogleCircleHookRefreshHeaderView(Context context) {
        this(context, null);
    }

    public GoogleCircleHookRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoogleCircleHookRefreshHeaderView(Context context, AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTriggerOffset = context.getResources().getDimensionPixelOffset(R.dimen
                .refresh_header_height_google);
        mFinalOffset = context.getResources().getDimensionPixelOffset(R.dimen
                .refresh_final_offset_google);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        progressView = (CircleProgressView) findViewById(R.id.googleProgress);
        progressView.setColorSchemeResources(
                R.color.swiperefresh_color1,
                R.color.swiperefresh_color2,
                R.color.swiperefresh_color3,
                R.color.swiperefresh_color4
        );
        progressView.setStartEndTrim(0, (float) 0.75);
    }

    @Override
    public void onRefresh() {
        progressView.start();
    }

    @Override
    public void onPrepare() {
        progressView.setStartEndTrim(0, (float) 0.75);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        float alpha = y / (float) mTriggerOffset;
        ViewCompat.setAlpha(progressView, alpha);
        if (!isComplete) {
            progressView.setProgressRotation(y / (float) mFinalOffset);
        }
    }

    @Override
    public void onRelease() {

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void onComplete() {
        progressView.animate().scaleX(0).scaleY(0).setDuration(300);
    }

    @Override
    public void onReset() {
        progressView.stop();
        ViewCompat.setAlpha(progressView, 1f);
        ViewCompat.setScaleX(progressView, 1f);
        ViewCompat.setScaleY(progressView, 1f);
    }

}

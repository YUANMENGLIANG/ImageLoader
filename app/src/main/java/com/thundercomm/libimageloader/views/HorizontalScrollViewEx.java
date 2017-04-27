package com.thundercomm.libimageloader.views;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by yuanml0715 on 2017/4/27.
 */

public class HorizontalScrollViewEx extends ViewGroup {
    private static final String TAG = "HorizontalScrollViewEx";
    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;

    private int mLastX = 0;
    private int mLastY = 0;

    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private void init()
    {
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }

    public HorizontalScrollViewEx(Context context)
    {
        super(context);
        init();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int)ev.getX();
        int y = (int)ev.getY();

        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                intercepted = false;
                if(!mScroller.isFinished())
                {
                    mScroller.abortAnimation();
                    intercepted = true;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                int detaX = x-mLastXIntercept;
                int detaY = y - mLastYIntercept;
                if(Math.abs(detaX)>Math.abs(detaY))
                {
                    intercepted = true;
                }
                else
                    intercepted = false;
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                intercepted = false;
                break;
            }
            default:
                break;
        }
        Log.d(TAG,"intercepted="+intercepted);
        mLastX = x;
        mLastX = y;
        mLastXIntercept = x;
        mLastYIntercept = y;

        return intercepted;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                if(!mScroller.isFinished())
                    mScroller.abortAnimation();
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                int detaX = x - mLastX;
                int detaY = y - mLastY;
                scrollBy(-detaX,0);
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                int scrollX = getScrollX();
                int scrollToChildIndex = scrollX / mChildWidth;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if(Math.abs(xVelocity)>=50)
                {
                    mChildIndex = xVelocity>0? mChildIndex - 1 : mChildIndex + 1;
                }
                else
                {
                    mChildIndex = (scrollX+mChildWidth/2)/mChildWidth;
                }
                mChildIndex = Math.max(0,Math.min(mChildIndex,mChildrenSize-1));
                int dx = mChildIndex * mChildWidth -scrollX;
                smoothScrollBy(dx,0);
                mVelocityTracker.clear();
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }
    private void smoothScrollBy(int dx,int dy)
    {
        mScroller.startScroll(getScrollX(),0,dx,dy,500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset())
        {
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}

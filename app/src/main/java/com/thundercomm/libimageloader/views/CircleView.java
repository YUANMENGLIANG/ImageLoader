package com.thundercomm.libimageloader.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.thundercomm.imageloader.R;

/**
 * Created by yuanml0715 on 2017/4/27.
 * 第一种自定义View方法，继承View，重写onDraw方法
 * 这种方法的padding和wrap_content无效
 * padding需要在onDraw方法中做处理
 * wrap_content需要重写onMeasure方法，指定一个默认宽高
 *
 * 有时候我们需要让自定义View提供自定义属性，如何添加自定义属性，遵循以下几步：
 * 第一步：在values文件夹下创建自定义属性的XML，比如attrs.xml
 * 第二步：在View的构造方法中解析自定义属性的值，并做相应的处理
 * 第三步：在布局文件中使用自定义属性(需要在布局文件中添加xmlns:app="http://schemas.android.com/apk/res-auto")
 */

public class CircleView extends View {

    private int mWidth = 200;
    private int mHeight = 200;
    private int mColor = Color.RED;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public  CircleView(Context context)
    {
        super(context);
        init();
    }
    private void init()
    {
        mPaint.setColor(mColor);
    }
    public CircleView(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        init();
    }
    public CircleView(Context context,AttributeSet attrs,int defStyleAttr)
    {
        super(context,attrs,defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = array.getColor(R.styleable.CircleView_circle_color,Color.RED);
        array.recycle();
        init();
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        //处理padding无效的方法
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom =getPaddingBottom();

        int width = getWidth()-paddingRight-paddingLeft;
        int height = getHeight()-paddingTop-paddingBottom;
        int radius = Math.min(width,height)/2;
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,radius,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMeasureSpec == MeasureSpec.AT_MOST && heightMeasureSpec == MeasureSpec.AT_MOST)
        {
            //宽高都是wrap_content
            setMeasuredDimension(mWidth,mHeight);
        }
        else if(widthMeasureSpec == MeasureSpec.AT_MOST)
        {
            setMeasuredDimension(mWidth,heightSpecSize);
        }
        else if(heightMeasureSpec == MeasureSpec.AT_MOST)
        {
            setMeasuredDimension(widthMeasureSpec,mHeight);
        }
    }
}

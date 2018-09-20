package com.ms.banner.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class ArcShapeView extends View {

    private Paint mPaint;
    private Path mPath;
    private int arcHeight = 0;
    private int background = 0XFFFFFFFF;
    private int direction = 0;

    public ArcShapeView(Context context) {
        this(context, null);
    }

    public ArcShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(background);

        if (direction == 0) {
            mPath.moveTo(0, getHeight());
            mPath.quadTo(getWidth() / 2, getHeight() - 2 * arcHeight, getWidth(), getHeight());
            canvas.drawPath(mPath, mPaint);
        } else {
            mPath.moveTo(0, getHeight());
            mPath.lineTo(0, getHeight() - arcHeight);
            mPath.quadTo(getWidth() / 2, getHeight(), getWidth(), getHeight() - arcHeight);
            mPath.lineTo(getWidth(), getHeight());
            canvas.drawPath(mPath, mPaint);
        }
    }

    public void setArcHeight(int arcHeight) {
        this.arcHeight = arcHeight;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}

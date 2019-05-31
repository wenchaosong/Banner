package com.ms.banner.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class ArcShapeView extends View {

    private Paint mPaint;
    private Path mPath;
    private int arcHeight = 0;
    private int startColor = 0XFFFFFFFF;
    private int endColor = 0XFFFFFFFF;
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

        LinearGradient gradient = new LinearGradient(0, 0, getWidth(), getHeight(),
                startColor, endColor, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);

        if (direction == 0) {
            //up
            mPath.moveTo(0, getHeight());
            mPath.quadTo(getWidth() / 2, getHeight() - 2 * arcHeight, getWidth(), getHeight());
            canvas.drawPath(mPath, mPaint);
        } else {
            //down
            mPath.moveTo(0, getHeight() - arcHeight);
            mPath.lineTo(0, getHeight());
            mPath.lineTo(getWidth(), getHeight());
            mPath.lineTo(getWidth(), getHeight() - arcHeight);
            mPath.quadTo(getWidth() / 2, getHeight() + arcHeight, 0, getHeight() - arcHeight);
            canvas.drawPath(mPath, mPaint);
        }
    }

    public void setArcHeight(int arcHeight) {
        this.arcHeight = arcHeight;
    }

    public void setBackground(int startColor, int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}

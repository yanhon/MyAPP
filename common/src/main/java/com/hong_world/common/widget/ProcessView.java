package com.hong_world.common.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hong_world.common.R;
import com.hong_world.library.utils.DensityUtils;

/**
 * Date: 2018/11/14. 17:51
 * Author: hong_world
 * Description: 渐变环形进度圈
 * Version:
 */
@BindingMethods({
        @BindingMethod(type = ProcessView.class, attribute = "arc_radius", method = "setRadius"),
        @BindingMethod(type = ProcessView.class, attribute = "arc_progress", method = "setArcProgress"),
        @BindingMethod(type = ProcessView.class, attribute = "arc_StrokeWidth", method = "setArcStrokeWidth"),
        @BindingMethod(type = ProcessView.class, attribute = "arc_textSize", method = "setTextSize"),
        @BindingMethod(type = ProcessView.class, attribute = "arc_textColor", method = "setTextColor"),
        @BindingMethod(type = ProcessView.class, attribute = "arc_backColor", method = "setBackArcColor"),
        @BindingMethod(type = ProcessView.class, attribute = "arc_startAngle", method = "setStartAngle"),
        @BindingMethod(type = ProcessView.class, attribute = "arc_sweepAngle", method = "setSweepAngle"),
        @BindingMethod(type = ProcessView.class, attribute = "arc_startColor", method = "setStartColor"),
        @BindingMethod(type = ProcessView.class, attribute = "arc_endColor", method = "setEndColor")
})
public class ProcessView extends View {
    private static final float DEFAULT_RADIUS = DensityUtils.dp2px(0);
    private static final float DEFAULT_ARC_STROKE_WIDTH = DensityUtils.dp2px(5);
    private static final float DEFAULT_TEXT_SIZE = DensityUtils.sp2px(12);
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final float DEFAULT_PROGRESS = 0;
    private static final int DEFAULT_BACK_ARC_COLOR = Color.GRAY;
    private static final float DEFAULT_START_ANGLE = 135;
    private static final float DEFAULT_SWEEP_ANGLE = 270;

    int startColor = Color.RED;
    int endColor = 0xFFF49AB8;
    int[] colors = {startColor, endColor};

    float radius = DEFAULT_RADIUS;
    float arcStrokeWidth = DEFAULT_ARC_STROKE_WIDTH;
    float textSize = DEFAULT_TEXT_SIZE;
    int textColor = DEFAULT_TEXT_COLOR;
    float progress = DEFAULT_PROGRESS;
    int backArcColor = DEFAULT_BACK_ARC_COLOR;
    float startAngle = DEFAULT_START_ANGLE;
    float sweepAngle = DEFAULT_SWEEP_ANGLE;

    float padding = arcStrokeWidth / 2 + DensityUtils.dp2px(2);
    float textPadding = arcStrokeWidth / 2 + DensityUtils.dp2px(2);

    RectF rect = new RectF();
    Paint paint;
    ObjectAnimator animator;
    private SweepGradient shader;


    public ProcessView(Context context) {
        this(context, null);
    }

    public ProcessView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProcessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(attrs);
        initPaint();
        initAnimator();
    }

    private void initAnimator() {
        animator = new ObjectAnimator();
        animator.setTarget(this);
        animator.setPropertyName("progress");
        if (progress > 0)
            animator.setFloatValues(0, progress);
        else
            animator.setFloatValues(0, 0);
        //        animator = ObjectAnimator.ofFloat(this, "progress", 0, progress);

        animator.setDuration(1000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
    }

    /*
       <declare-styleable name="ProcessView">
            <attr name="arc_radius" format="dimension" />
            <attr name="arc_StrokeWidth" format="dimension" />
            <attr name="arc_textSize" format="dimension" />
            <attr name="arc_textColor" format="color" />
            <attr name="arc_backColor" format="color" />
            <attr name="arc_startAngle" format="float" />
            <attr name="arc_sweepAngle" format="float" />
            <attr name="arc_progress" format="float" />

            <attr name="arc_Colors" format="reference" />
            <attr name="arc_startColor" format="color" />
            <attr name="arc_endColor" format="color" />
     */
    private void obtainStyledAttributes(AttributeSet attrs) {
        final TypedArray attributes = getContext().obtainStyledAttributes(
                attrs, R.styleable.ProcessView);
        radius = attributes.getDimension(R.styleable.ProcessView_arc_radius, DEFAULT_RADIUS);
        arcStrokeWidth = attributes.getDimension(R.styleable.ProcessView_arc_StrokeWidth, DEFAULT_ARC_STROKE_WIDTH);
        progress = attributes.getFloat(R.styleable.ProcessView_arc_progress, DEFAULT_PROGRESS);
        startAngle = attributes.getFloat(R.styleable.ProcessView_arc_startAngle, DEFAULT_START_ANGLE);
        sweepAngle = attributes.getFloat(R.styleable.ProcessView_arc_startAngle, DEFAULT_SWEEP_ANGLE);
        backArcColor = attributes.getColor(R.styleable.ProcessView_arc_backColor, DEFAULT_BACK_ARC_COLOR);
        textColor = attributes.getColor(R.styleable.ProcessView_arc_textColor, DEFAULT_TEXT_COLOR);
        colors[0] = attributes.getColor(R.styleable.ProcessView_arc_startColor, startColor);
        colors[1] = attributes.getColor(R.styleable.ProcessView_arc_endColor, endColor);
        textSize = attributes.getDimension(R.styleable.ProcessView_arc_textSize, DEFAULT_TEXT_SIZE);
        attributes.recycle();
    }

    public void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
    }


    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public void setArcProgress(float progress) {
        this.progress = progress;
        if (animator != null)
            animator.setFloatValues(0, progress);
        animator.start();
        invalidate();
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    public void setColors(int[] colors) {
        if (colors != null && colors.length >= 2)
            this.colors = colors;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setArcStrokeWidth(float arcStrokeWidth) {
        this.arcStrokeWidth = arcStrokeWidth;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setBackArcColor(int backArcColor) {
        this.backArcColor = backArcColor;
    }

    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (animator != null)
            animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null)
            animator.end();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpec = measureWidth(widthMeasureSpec);
        int heightSpec = measureHeight(heightMeasureSpec);

        setMeasuredDimension(widthSpec, heightSpec);
        Log.i("wist", "widthSpec:" + widthSpec + ",heightSpec" + heightSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        checkRadius(centerX, centerY);

        initSweepGradient(90, centerX, centerY);

        rect.set(centerX - radius - arcStrokeWidth + padding, centerY - radius - arcStrokeWidth + padding, centerX + radius + arcStrokeWidth - padding, centerY + radius + arcStrokeWidth - padding);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(arcStrokeWidth);
        drawBackArc(canvas);

        drawArc(canvas);

        drawText(canvas, centerX, centerY);
    }

    private void checkRadius(float centerX, float centerY) {
        Log.i("wist", "centerX:" + centerX + ",centerY:" + centerY);
        if (radius == 0) {
            if (centerX == arcStrokeWidth) {
                radius = getHeight() / 2 - arcStrokeWidth;
            } else
                radius = getWidth() / 2 - arcStrokeWidth;
        } else if (getWidth() <= getHeight()) {
            if (getWidth() / 2 < radius + arcStrokeWidth) {
                radius = getWidth() / 2 - arcStrokeWidth;
            }
        } else {
            if (getHeight() / 2 < radius + arcStrokeWidth) {
                radius = getHeight() / 2 - arcStrokeWidth;
            }
        }
    }

    private void drawText(Canvas canvas, float centerX, float centerY) {
        paint.setShader(null);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText((int) progress + "%", centerX, centerY + getTextSize(paint, false, "100%"), paint);
    }

    private void drawArc(Canvas canvas) {
        paint.setShader(shader);
        canvas.drawArc(rect, startAngle, progress * (sweepAngle / 100), false, paint);
    }

    private void drawBackArc(Canvas canvas) {
        paint.setColor(backArcColor);
        canvas.drawArc(rect, startAngle, sweepAngle, false, paint);
    }

    private void initSweepGradient(float degrees, float centerX, float centerY) {
        if (shader == null) {
            shader = new SweepGradient(centerX, centerY, colors, null);
            Matrix matrix = new Matrix();
            matrix.setRotate(degrees, centerX, centerY);
            shader.setLocalMatrix(matrix);
        }
    }

    private int measureWidth(int measureSpec) {
        float result = 100;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED://父元素部队自元素施加任何束缚，子元素可以得到任意想要的大小
                Log.i("wist", "measureWidth UNSPECIFIED");
            case MeasureSpec.AT_MOST://子元素至多达到指定大小的值
                if (radius == 0)
                    result = getTextSize(paint, true, "100%") * 2 + arcStrokeWidth * 2 + textPadding * 2;
                else
                    result = radius * 2 + arcStrokeWidth * 2;
                if (specMode == MeasureSpec.AT_MOST) {
                    Log.i("wist", "measureWidth AT_MOST");
                    result = Math.min(result, specSize);
                }
                break;
            case MeasureSpec.EXACTLY://父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小
                Log.i("wist", "measureWidth EXACTLY");
                result = specSize;
                break;
        }
        return (int) result;
    }

    private int measureHeight(int measureSpec) {
        float result = 100;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                Log.i("wist", "measureHeight UNSPECIFIED");
            case MeasureSpec.AT_MOST:
                if (radius == 0)
                    result = getTextSize(paint, true, "100%") * 2 + arcStrokeWidth * 2 + textPadding * 2;
                else
                    result = radius * 2 + arcStrokeWidth * 2;
                if (specMode == MeasureSpec.AT_MOST) {
                    Log.i("wist", "measureHeight AT_MOST");
                    result = Math.min(result, specSize);
                }
                break;
            case MeasureSpec.EXACTLY:
                Log.i("wist", "measureHeight EXACTLY");
                result = specSize;
                break;
        }
        return (int) result;
    }

    public int getTextSize(Paint paint, boolean iswidth, String text) {
        Rect rect = new Rect();
        paint.setTextSize(textSize);
        paint.getTextBounds(text, 0, text.length(), rect);
        if (iswidth) {
            return rect.width() / 2;//文本的宽度
        } else
            return rect.height() / 2;//文本的高度
    }
}

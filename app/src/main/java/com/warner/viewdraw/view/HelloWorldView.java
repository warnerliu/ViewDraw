package com.warner.viewdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by warner on 8/24/16.
 */
public class HelloWorldView extends View {

	private Paint paint;
	private String drawText = "Hello World";
	private Rect boundsRect;
	private int width, height;

	public HelloWorldView(Context context) {
		this(context, null);
	}

	public HelloWorldView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HelloWorldView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
	}

	private void initPaint() {

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.rgb(0x00, 0xff, 0x00));
		paint.setTextSize(100);
		boundsRect = new Rect();
		paint.getTextBounds(drawText, 0, drawText.length(), boundsRect);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
//		canvas.drawText(drawText, getMeasuredWidth() / 2 - boundsRect.width() / 2, getMeasuredHeight() / 2 - boundsRect.height() / 2, paint);
		canvas.drawText(drawText, 0, boundsRect.height(), paint);
	}


	/**
	 * 覆写onMeasure方法的时候，子类有责任确保measured height and width至少为这个View的最小height和width。
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
	}

	private int measureWidth(int widthMeasureSpec) {
		int measuredWidth;
		int specMode = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		if (specMode == MeasureSpec.EXACTLY) {

			measuredWidth = specSize;
		} else {
			measuredWidth = boundsRect.width();
			if (specMode == MeasureSpec.AT_MOST) {
				measuredWidth = Math.min(measuredWidth, specSize);
			}
		}
		return measuredWidth;
	}

	private int measureHeight(int heightMeasureSpec) {
		int measureHeight;
		int specMode = MeasureSpec.getMode(heightMeasureSpec);
		int specSize = MeasureSpec.getSize(heightMeasureSpec);
		if (specMode == MeasureSpec.EXACTLY) {
			measureHeight = specSize;
		} else {
			measureHeight = boundsRect.height();
			if (specMode == MeasureSpec.AT_MOST) {
				measureHeight = Math.min(measureHeight, specSize);
			}
		}
		return measureHeight;
	}
}

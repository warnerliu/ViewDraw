package com.warner.viewdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by warner on 16/1/6.
 */
public class DrawPathOnView extends View {

	private Paint paint;
	private Path path;
	private int lastX;
	private int lastY;

	public DrawPathOnView(Context context) {
		this(context, null);
	}

	public DrawPathOnView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DrawPathOnView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaintAndPath();
	}

	private void initPaintAndPath() {
		paint = new Paint();
		path = new Path();
		paint.setColor(Color.GREEN);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(20.0f);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setAntiAlias(true);
		paint.setDither(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (action) {
			case MotionEvent.ACTION_DOWN:
				lastX = x;
				lastY = y;
				path.moveTo(x, y);
				break;
			case MotionEvent.ACTION_MOVE:
				path.lineTo(x, y);
				break;
			case MotionEvent.ACTION_UP:
				if (x == lastX && y == lastY) {
					path.lineTo(x + 1, y + 1);
				}
				break;
			default:
				break;
		}
		invalidate();
		return true;
	}
}

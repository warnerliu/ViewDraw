package com.warner.viewdraw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by warner on 16/1/6.
 */
public class PorterDuffView extends View {

	private Paint paint;
	private Path path;
	private int lastX;
	private int lastY;
	private Context context;
	private Bitmap backgroundBitmap;
	private Paint maskPaint;
	private Canvas mCanvas;
	private Bitmap mBitmap;
	private RectF rectF;

	public PorterDuffView(Context context) {
		this(context, null);
	}

	public PorterDuffView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PorterDuffView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initPaintAndPath();
		initBitmapData();
		initMaskPaint();
	}

	private void initPaintAndPath() {
		paint = new Paint();
		path = new Path();
		paint.setColor(Color.parseColor("#c0c0c0"));
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(20.0f);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
	}

	private void initMaskPaint() {
		maskPaint = new Paint();
		maskPaint.setColor(Color.CYAN);
		maskPaint.setStyle(Paint.Style.FILL);
	}

	private void initBitmapData() {

		backgroundBitmap = BitmapFactory.decodeResource(context.getResources(), com.warner.viewdraw.R.drawable.pic_large);
		rectF = new RectF(backgroundBitmap.getWidth()/2, backgroundBitmap.getHeight()/2, backgroundBitmap.getWidth(), backgroundBitmap.getHeight());
		mBitmap = Bitmap.createBitmap(backgroundBitmap.getWidth(), backgroundBitmap.getHeight(), Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		maskPaint = new Paint();
		maskPaint.setStyle(Paint.Style.FILL);
		maskPaint.setColor(Color.GREEN);
		mCanvas.drawBitmap(backgroundBitmap, 0, 0, maskPaint);
		maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		mCanvas.drawRect(rectF, maskPaint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawBitmap(mBitmap, 0, 0, null);
//		canvas.drawBitmap(backgroundBitmap, 0, 0, null);
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
//		mCanvas.drawPath(path, paint);
//		invalidate();
		return true;
	}
}

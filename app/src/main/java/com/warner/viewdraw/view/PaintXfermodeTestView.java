package com.warner.viewdraw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by warner on 1/6/16.
 */
public class PaintXfermodeTestView extends View {

	private Paint mPaint;
	private Paint forePaint;
	private Paint modePaint;
	private RectF rectF;
	private int width;
	private int height;
	private final int Radius = 100;
	private Canvas mCanvas;
	private Bitmap mBitmap;
	private Bitmap foreBitmap;
	private Canvas foreCanvas;
	private PorterDuff.Mode paintXfermodel = PorterDuff.Mode.CLEAR;

	public PaintXfermodeTestView(Context context) {
		this(context, null);
	}

	public PaintXfermodeTestView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PaintXfermodeTestView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
		initCanvas();
	}

	private void initPaint() {
		mPaint = new Paint();
		forePaint = new Paint();
		forePaint.setStyle(Paint.Style.FILL);
		mPaint.setStyle(Paint.Style.FILL);
		rectF = new RectF();
		modePaint = new Paint();
		modePaint.setStyle(Paint.Style.FILL);
	}

	private void initCanvas() {

		width = 400;
		height = 400;

		mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mCanvas.drawColor(Color.GRAY);
		mPaint.setColor(Color.GREEN);
		mCanvas.drawCircle(width / 2, height / 2, Radius, mPaint);

		foreBitmap = Bitmap.createBitmap(width / 2, height / 2, Bitmap.Config.ARGB_8888);
		foreCanvas = new Canvas(foreBitmap);
		forePaint.setColor(Color.RED);
		rectF.set(width / 2, height / 2, width / 2 + Radius, height / 2 + Radius);
		foreCanvas.drawRect(rectF, forePaint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mBitmap != null) {
//			canvas.drawBitmap(mBitmap, 0, 0, modePaint);
//			modePaint.setXfermode(new PorterDuffXfermode(paintXfermodel));
			canvas.drawBitmap(foreBitmap, 0, 0, modePaint);
//			modePaint.setXfermode(null);
		}
	}

	public void setPaintXfermodel(PorterDuff.Mode paintXfermodel) {

		this.paintXfermodel = paintXfermodel;
		invalidate();
	}
}

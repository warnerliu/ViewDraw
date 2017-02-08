package com.warner.viewdraw;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.warner.viewdraw.view.PaintXfermodeTestView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by warner on 16/1/7.
 */
public class XfermodeActivity extends Activity implements View.OnClickListener {
	@Bind(R.id.clear)
	TextView clear;
	@Bind(R.id.src)
	TextView src;
	@Bind(R.id.dst)
	TextView dst;
	@Bind(R.id.srcOver)
	TextView srcOver;
	@Bind(R.id.dstOver)
	TextView dstOver;
	@Bind(R.id.srcIn)
	TextView srcIn;
	@Bind(R.id.dstIn)
	TextView dstIn;
	@Bind(R.id.srcOut)
	TextView srcOut;
	@Bind(R.id.dstOut)
	TextView dstOut;
	@Bind(R.id.srcATop)
	TextView srcATop;
	@Bind(R.id.dstATop)
	TextView dstATop;
	@Bind(R.id.Xor)
	TextView Xor;
	@Bind(R.id.darken)
	TextView darken;
	@Bind(R.id.lighten)
	TextView lighten;
	@Bind(R.id.multiply)
	TextView multiply;
	@Bind(R.id.screen)
	TextView screen;
	@Bind(R.id.paintXfermodeView)
	PaintXfermodeTestView paintXfermodeView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xfermode);
		ButterKnife.bind(this);
		setClickListener();
	}

	private void setClickListener() {
		clear.setOnClickListener(this);
		src.setOnClickListener(this);
		dst.setOnClickListener(this);
		srcOver.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.clear:
				paintXfermodeView.setPaintXfermodel(PorterDuff.Mode.CLEAR);
				break;
			case R.id.src:
				paintXfermodeView.setPaintXfermodel(PorterDuff.Mode.SRC);
				break;
			case R.id.dst:
				paintXfermodeView.setPaintXfermodel(PorterDuff.Mode.DST);
				break;
			case R.id.srcOver:
				paintXfermodeView.setPaintXfermodel(PorterDuff.Mode.SRC_OVER);
				break;
			default:
				break;
		}
	}
}

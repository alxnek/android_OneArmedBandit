package com.onearmedbandit;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends Activity
{

	private final static String TAG = "MainActivity";

	AnimationDrawable fruitAnimation1;
	ImageView fruitView1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		Log.v(TAG, "Create layout");
		this.fruitView1 = (ImageView) this.findViewById(R.id.imageView1);
		this.fruitView1.setBackgroundResource(R.anim.fruit1);
		this.fruitAnimation1 = (AnimationDrawable) this.fruitView1.getBackground();
	}

	/*
	 * (Detect user touching the screen)
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			this.fruitAnimation1.start();
			Log.v(TAG, "TOUCH detected");
			return true;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

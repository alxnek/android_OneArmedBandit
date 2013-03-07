package com.onearmedbandit;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener
{

	private final static String TAG = "MainActivity";

	AnimationDrawable fruitAnimation1,fruitAnimation2,fruitAnimation3;
	ImageView fruitView1,fruitView2,fruitView3;
	Button b;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		Log.v(TAG, "Create layout");
		
//		this.b = (Button) this.findViewById(R.id.button1);
//		b.setOnClickListener(this);
		
		this.fruitView1 = (ImageView) this.findViewById(R.id.ImageView01);
		this.fruitView1.setBackgroundResource(R.anim.fruit);
		this.fruitAnimation1 = (AnimationDrawable) this.fruitView1.getBackground();
		
		this.fruitView2 = (ImageView) this.findViewById(R.id.ImageView02);
		this.fruitView2.setBackgroundResource(R.anim.fruit);
		this.fruitAnimation2 = (AnimationDrawable) this.fruitView2.getBackground();
		
		this.fruitView3 = (ImageView) this.findViewById(R.id.ImageView03);
		this.fruitView3.setBackgroundResource(R.anim.fruit);
		this.fruitAnimation3 = (AnimationDrawable) this.fruitView3.getBackground();
		
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
			this.fruitAnimation2.start();
			this.fruitAnimation3.start();
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


	@Override
	public void onClick(View v)
	{
		//fruitAnimation1.start();
		
		// TODO Auto-generated method stub
		
	}

}

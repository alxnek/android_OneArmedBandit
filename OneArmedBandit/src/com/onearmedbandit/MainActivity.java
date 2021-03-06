package com.onearmedbandit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{

	private final static String TAG = "MainActivity";

	private MyThread t1, t2, t3 = null;

	boolean t1running, t2running, t3running = false;

	AnimationDrawable fruitAnimation1, fruitAnimation2, fruitAnimation3;
	ImageView fruitView1, fruitView2, fruitView3;
	Button b1, b2, b3, bStart;
	String ListPreference;
	int bet;
	int money = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		Log.v(TAG, "Create layout");

		this.getPrefs();

		this.bStart = (Button) this.findViewById(R.id.buttonStart);
		this.bStart.setOnClickListener(this);

		this.b1 = (Button) this.findViewById(R.id.button1);
		this.b1.setOnClickListener(this);
		this.b2 = (Button) this.findViewById(R.id.button2);
		this.b2.setOnClickListener(this);
		this.b3 = (Button) this.findViewById(R.id.button3);
		this.b3.setOnClickListener(this);

		this.fruitView1 = (ImageView) this.findViewById(R.id.ImageView01);
		this.fruitView1.setBackgroundResource(R.anim.fruit);
		this.fruitAnimation1 = (AnimationDrawable) this.fruitView1.getBackground();

		this.fruitView2 = (ImageView) this.findViewById(R.id.ImageView02);
		this.fruitView2.setBackgroundResource(R.anim.fruit2);
		this.fruitAnimation2 = (AnimationDrawable) this.fruitView2.getBackground();

		this.fruitView3 = (ImageView) this.findViewById(R.id.ImageView03);
		this.fruitView3.setBackgroundResource(R.anim.fruit3);
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

			Log.v(TAG, "TOUCH detected");
			return true;
		}
		return super.onTouchEvent(event);
	}

	// Called when an options item is clicked
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		switch (item.getItemId())
		{
		// this shit is for launch the preferences teh other list
			case R.id.itemPrefs:
				this.startActivity(new Intent(this, PrefsActivity.class));
				break;
		}
		return true; //
	}

	private void getPrefs()
	{
		// Get the xml/preferences.xml preferences
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());

		this.ListPreference = prefs.getString("bet_list", "1");
		Log.v(TAG, "bet_list value loaded-> " + this.ListPreference);
		this.bet = Integer.parseInt(this.ListPreference);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = this.getMenuInflater(); //
		inflater.inflate(R.menu.menu, menu); //
		return true; //
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// this.getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	@Override
	public void onClick(View v)
	{
		if (v == this.bStart) {

			//get prefs 
			this.getPrefs();
			
			this.fruitAnimation1.start();
			this.fruitAnimation2.start();
			this.fruitAnimation3.start();
			this.t1 = new MyThread();
			this.t1.start();
			// t1running = true;
			this.t2 = new MyThread();
			this.t2.start();
			// Log.v(TAG, "vivo? "+t2.isAlive());
			this.t3 = new MyThread();
			this.t3.start();
		}

		if (v == this.b1) {

			this.fruitAnimation1.stop();
			this.t1.interrupt();

		}
		if (v == this.b2) {

			this.fruitAnimation2.stop();
			this.t2.interrupt();

		}
		if (v == this.b3) {

			this.fruitAnimation3.stop();
			this.t3.interrupt();

		}

		this.ifAllStoped_check();

		// TODO Auto-generated method stub

	}

	public Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg)
		{

			super.handleMessage(msg);

			String str = msg.getData().getString(MyThread.MSG_STRING);
			boolean checked = false;

			Log.v(TAG, "Got MESSAGE FROM HANDLER " + str);

			if (Integer.parseInt(str) == 10) {

				MainActivity.this.fruitAnimation1.stop();
				MainActivity.this.t1.interrupt();
				checked = MainActivity.this.ifAllStoped_check();

			}
			if (Integer.parseInt(str) == 12) {

				MainActivity.this.fruitAnimation2.stop();
				MainActivity.this.t2.interrupt();
				checked = MainActivity.this.ifAllStoped_check();

			}
			if (Integer.parseInt(str) == 14) {

				MainActivity.this.fruitAnimation3.stop();
				MainActivity.this.t3.interrupt();
				checked = MainActivity.this.ifAllStoped_check();

			}

		}

	};

	/**
	 * Checks if all the animations are stoped, if so, check scores
	 * 
	 * @return boolean
	 */
	public boolean ifAllStoped_check()
	{

		boolean stoped = false;

		if (!this.fruitAnimation1.isRunning() && !this.fruitAnimation2.isRunning()
				&& !this.fruitAnimation3.isRunning()) {
			stoped = true;
			this.checkScore();
		}
		return stoped;
	}

	/**
	 * Check the score
	 */
	public void checkScore()
	{
		
		if (this.getFrame(fruitAnimation1)==this.getFrame(fruitAnimation2)
				&& this.getFrame(fruitAnimation1)==this.getFrame(fruitAnimation3)) {

			this.money += this.bet * 50;
			Toast.makeText(this, "GREAT! 3/3. Your $-> " + this.money, Toast.LENGTH_LONG).show();
			

		}
		else if (this.getFrame(fruitAnimation1)==this.getFrame(fruitAnimation2)
				|| this.getFrame(fruitAnimation1)==this.getFrame(fruitAnimation3)) {

			this.money += this.bet * 5;
			Toast.makeText(this, "Nice. 2/3. Your $-> " + this.money, Toast.LENGTH_LONG).show();
			
		}
		else {
			this.money -= this.bet;
			Toast.makeText(this, ":( Try again! Your $-> " + this.money, Toast.LENGTH_LONG).show();
		}

		Log.v(TAG, "Stuff checked =) "+"-"+money+"-"+bet);

	}
	
	/**
	 * 
	 * @param ad Animation Drawable
	 * @return int of the frame (actual drawable img)
	 */
	public int getFrame(AnimationDrawable ad){
		
		// The variable that will guard the frame number
		int frameNumber = 0;
		// Get the frame of the animation
		Drawable currentFrame, checkFrame;
		currentFrame = ad.getCurrent();		
		
		// Checks the position of the frame
		for (int i = 0; i < ad.getNumberOfFrames(); i++) {
		    checkFrame = ad.getFrame(i);
		    if (checkFrame == currentFrame) {
		        frameNumber = i;
		        break;
		    }
		}
		return frameNumber;
	}

	class MyThread extends Thread
	{

		public static final String MSG_STRING = "string";

		@Override
		public void run()
		{

			int count = 0;

			while (true) {

				Message msg = MainActivity.this.myHandler.obtainMessage();

				Bundle data = new Bundle();
				data.putString(MSG_STRING, "" + count);
				msg.setData(data);
				msg.sendToTarget();

				try {

					Thread.sleep(1000);

				} catch (InterruptedException e) {

					e.printStackTrace();
					break;
				}
				count++;

			}

		}
	}
}

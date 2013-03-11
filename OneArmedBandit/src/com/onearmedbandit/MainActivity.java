package com.onearmedbandit;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

	private MyThread t1, t2, t3 = null;

	AnimationDrawable fruitAnimation1, fruitAnimation2, fruitAnimation3;
	ImageView fruitView1, fruitView2, fruitView3;
	Button b1, b2, b3, bStart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		Log.v(TAG, "Create layout");

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
		if (v == this.bStart) {

			this.fruitAnimation1.start();
			this.fruitAnimation2.start();
			this.fruitAnimation3.start();
			this.t1 = new MyThread();
			this.t1.start();
			this.t2 = new MyThread();
			this.t2.start();
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
		//

		// TODO Auto-generated method stub

	}


	public Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg)
		{

			super.handleMessage(msg);

			String str = msg.getData().getString(MyThread.MSG_STRING);

			Log.v(TAG, "Got MESSAGE FROM HANDLER " + str);

			 if (Integer.parseInt(str)== 10){
			
				 MainActivity.this.fruitAnimation1.stop();
				 MainActivity.this.t1.interrupt();
			 }
			 if (Integer.parseInt(str)== 12){
					
				 MainActivity.this.fruitAnimation2.stop();
				 MainActivity.this.t2.interrupt();
			 }
			 if (Integer.parseInt(str)== 14){
					
				 MainActivity.this.fruitAnimation3.stop();
				 MainActivity.this.t3.interrupt();
			 }
		}

	};

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

package com.onearmedbandit;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;


public class MyThread extends Thread
{
	public static final String MSG_STRING = "string";

	@Override
	public void run()
	{

		int count = 0;

		while (true) {

//			Message msg = MainActivity.this.myHandler.obtainMessage();

//			Bundle data = new Bundle();
//			data.putString(MSG_STRING, "Thread running " + count);
//			msg.setData(data);
//			msg.sendToTarget();

			// TODO Auto-generated method stub
			Log.v("MyThread", "Thread running :-)" + count);
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



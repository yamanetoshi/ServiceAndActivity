package jp.shuri.android.saa;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class SampleService extends Service implements Runnable {
	private int counter;
	private static final int TIMER_PERIOD = 30 * 1000;
	
	private Handler mHandler;
	private boolean mRunning;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("SampleService", "onCreate");
		
		counter = 0;
		mRunning = false;
		mHandler = new Handler();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.d("SampleService", "onBind");
		return ISampleServiceBinder;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d("SampleService", "onStart");
		if(!mRunning) {
			mRunning = true;
			mHandler.postDelayed(this, TIMER_PERIOD);
		}
	}

	private final ISampleService.Stub ISampleServiceBinder = new ISampleService.Stub() {

		@Override
		public int getCount() throws RemoteException {
			return counter;
		}
		
	};

	@Override
	public void run() {
		if(!mRunning) {
			return;
		} else {
			Log.d("SampleService", "count = " + counter++);
			mHandler.postDelayed(this, TIMER_PERIOD);
		}
	}

}

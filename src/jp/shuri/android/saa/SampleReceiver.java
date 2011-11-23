package jp.shuri.android.saa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SampleReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("SampleReceiver", "onReceive start");
		//context.startService(new Intent(context, SampleService.class));
		
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			Log.d("SampleReceiver", "BOOT_COMPLETED");
			Intent i = new Intent(context, SampleService.class);
			context.startService(i);
		} else if (intent.getAction().equals("android.intent.action.REBOOT")) {
			Log.d("SampleReceiver", "REBOOT");
			// nothing
		} else if (intent.getAction().equals("android.intent.action.SHUTDOWN")) {
			Log.d("SampleReceiver", "SHUTDOWN");
			// nothing
		}
		
	}

}

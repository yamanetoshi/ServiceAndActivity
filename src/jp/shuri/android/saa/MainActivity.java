package jp.shuri.android.saa;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ISampleService binder;
	private TextView tv;
	
	@Override
	protected void onPause() {
		super.onPause();
		unbindService(connection);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent i = new Intent(MainActivity.this, SampleService.class);
		//bindService(i, connection, BIND_AUTO_CREATE);
		bindService(i, connection, 0);
	}

	private static final String TAG = "MainActivity";
	
    private ServiceConnection connection = new ServiceConnection() {

        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
                Log.d(TAG, "onServiceConnected");
                binder = ISampleService.Stub.asInterface(arg1);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
                Log.d(TAG, "onServiceDisconnected");
                binder = null;
        }
        
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView)findViewById(R.id.textview);
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					int i = binder.getCount();
					tv.setText(new Integer(i).toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        	
        });
    }
}
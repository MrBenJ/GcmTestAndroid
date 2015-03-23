package prismmobile.gcmtestandroid;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by benjunya on 3/23/15.
 *
 * Handles Google Cloud Messeger's messages.
 */
public class GcmMessageHandler extends IntentService {

    String message;
    private Handler handler;
    public GcmMessageHandler() {
        super("GcmMessageHandler");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // getMessageType() intent parameter must be the intent received in GcmBroadcastReceiver

        String messageType = gcm.getMessageType(intent);

        message = extras.getString("title");
        showToast(message);
    }


    public void showToast(final String text) {
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            }
        });
    }
}

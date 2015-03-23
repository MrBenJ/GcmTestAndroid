package prismmobile.gcmtestandroid;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by benjunya on 3/23/15.
 *
 * This is the Google Cloud Messenger Broadcast Receiver. It handles the data coming from the broadcasts sent to the device.
 * Server must be configured on Google Developers Console
 */
public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Explicity specify this class will handle the intent (intent filter stuff)
        ComponentName componentName = new ComponentName(context.getPackageName(),
                GcmMessageHandler.class.getName());

        // Start the service, keeping the device awake while launching

        startWakefulService(context, (intent.setComponent(componentName)));
        setResultCode(Activity.RESULT_OK);

    }
}

package prismmobile.gcmtestandroid;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private String PROJECT_NUMBER = "601579226917";
    private String mRegId;

    private EditText mEditText;
    GoogleCloudMessaging mGcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = (Button) findViewById(R.id.button_regid);
        mEditText = (EditText) findViewById(R.id.editText);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRegId();
            }
        });
    }

    private void getRegId() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                String message = "";
                Log.i("TAG", "Doing in background...");
                try {
                    if (mGcm == null) {
                        mGcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }

                    mRegId = mGcm.register(PROJECT_NUMBER);
                    message = "Device registered! RegID = " + mRegId;
                    Log.i("GCM", message);

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                return message;
            }

            @Override
            protected void onPostExecute(String message) {
                mEditText.setText(message);
            }

        }.execute(null, null, null);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

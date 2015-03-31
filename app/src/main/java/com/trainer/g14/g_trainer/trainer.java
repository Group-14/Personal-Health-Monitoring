package com.trainer.g14.g_trainer;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;

import java.util.concurrent.TimeUnit;

public class trainer extends ActionBarActivity
    implements OnDataPointListener{
    String type;
    private Button pause;
    private Button stop;
    private boolean Pause=false;

    private OnDataPointListener mListener;
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView textView2;

    private static final int REQUEST_OAUTH = 1;

    /**
     *  Track whether an authorization activity is stacking over the current activity, i.e. when
     *  a known auth error is being resolved, such as showing the account chooser or presenting a
     *  consent dialog. This avoids common duplications as might happen on screen rotations, etc.
     */
    private static final String AUTH_PENDING = "auth_state_pending";
    private boolean authInProgress = false;
    private static GoogleApiClient mClient = null;

    @Override
    protected void onStart() {
        super.onStart();
        // Connect to the Fitness API
        Log.i(TAG, "Connecting...");
        mClient.connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buildFitnessClient();

        setContentView(R.layout.activity_trainer);
        Intent intent = getIntent();
        type = intent.getStringExtra(workout.TYPE);

        // Create the text view
        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText("Current Exercise:");

        textView2 = (TextView) findViewById(R.id.progress);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        //show pic of exercise
        ImageView picture = (ImageView) findViewById(R.id.pic);
        Drawable[] drawables = new Drawable[] {
                getResources().getDrawable(R.drawable.run),
        };
        picture.setImageDrawable(drawables[0]);

        /*mListener = new OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                final int steps = dataPoint.getValue(Field.FIELD_STEPS).asInt();
                runOnUiThread(new Runnable(){
                    public void run() {
                        while(!Pause) {
                            Log.i(TAG, "Detected DataPoint value: " + steps);
                            TextView textView = (TextView) findViewById(R.id.progress);
                            textView.setText("Steps: " + steps);
                        }

                    }
                });
            }
        };*/

        addListener();
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Pause) {
                    pause.setText("Resume");
                    Pause=true;
                    removeListener();
                }else {
                    pause.setText("Pause");
                    Pause=false;
                    addListener();
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeListener();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mClient.isConnected()) {
            mClient.disconnect();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_OAUTH) {
            authInProgress = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mClient.isConnecting() && !mClient.isConnected()) {
                    mClient.connect();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(AUTH_PENDING, authInProgress);
    }

    @Override
    public void onDataPoint(DataPoint dataPoint) {
        final int steps = dataPoint.getValue(Field.FIELD_STEPS).asInt();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "Detected DataPoint value: " + steps);
                textView2.setText("Steps: " + steps);
            }
        });

    }

    private void addListener(){
        Fitness.SensorsApi.add(
                mClient,
                new SensorRequest.Builder()
                        //.setDataSource(dataSource) // Optional but recommended for custom data sets.
                        .setDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE) // Can't be omitted.
                        .setSamplingRate(1, TimeUnit.MICROSECONDS)
                        .build(),
                this).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                if (status.isSuccess()) {
                    Log.i(TAG, "Listener registered!");
                } else {
                    Log.i(TAG, "Listener not registered.");
                }
            }
        });
    }

    private void removeListener(){
        Fitness.SensorsApi.remove(
                mClient,
                this)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            Log.i(TAG, "Listener was removed!");
                        } else {
                            Log.i(TAG, "Listener was not removed.");
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trainer, menu);
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

    /**
     *  Build a {@link GoogleApiClient} that will authenticate the user and allow the application
     *  to connect to Fitness APIs. The scopes included should match the scopes your app needs
     *  (see documentation for details). Authentication will occasionally fail intentionally,
     *  and in those cases, there will be a known resolution, which the OnConnectionFailedListener()
     *  can address. Examples of this include the user never having signed in before, or having
     *  multiple accounts on the device and needing to specify which account to use, etc.
     */
    private void buildFitnessClient() {
        // Create the Google API Client
        mClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.SENSORS_API)
                .addApi(Fitness.RECORDING_API)
                .addApi(Fitness.HISTORY_API)
                .addApi(Fitness.SESSIONS_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addScope(new Scope(Scopes.FITNESS_BODY_READ_WRITE))
                .addScope(new Scope(Scopes.FITNESS_LOCATION_READ))
                .addConnectionCallbacks(
                        new GoogleApiClient.ConnectionCallbacks() {

                            @Override
                            public void onConnected(Bundle bundle) {
                                Log.i(TAG, "Connected!!!");
                                // Now you can make calls to the Fitness APIs.
                                // Put application specific code here.

                            }

                            @Override
                            public void onConnectionSuspended(int i) {
                                // If your connection to the sensor gets lost at some point,
                                // you'll be able to determine the reason and react to it here.
                                if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
                                    Log.i(TAG, "Connection lost.  Cause: Network Lost.");
                                } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
                                    Log.i(TAG, "Connection lost.  Reason: Service Disconnected");
                                }

                            }
                        }
                )
                .addOnConnectionFailedListener(
                        new GoogleApiClient.OnConnectionFailedListener() {
                            // Called whenever the API client fails to connect.
                            @Override
                            public void onConnectionFailed(ConnectionResult result) {
                                Log.i(TAG, "Connection failed. Cause: " + result.toString());
                                TextView textView = (TextView) findViewById(R.id.fitconnect);
                                textView.setText("Google Fit Status: Connection Failed");
                                if (!result.hasResolution()) {
                                    // Show the localized error dialog
                                    GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(),
                                            trainer.this, 0).show();
                                    return;
                                }
                                // The failure has a resolution. Resolve it.
                                // Called typically when the app is not yet authorized, and an
                                // authorization dialog is displayed to the user.
                                if (!authInProgress) {
                                    try {
                                        Log.i(TAG, "Attempting to resolve failed connection");
                                        authInProgress = true;
                                        result.startResolutionForResult(trainer.this,
                                                REQUEST_OAUTH);
                                    } catch (IntentSender.SendIntentException e) {
                                        Log.e(TAG,
                                                "Exception while starting resolution activity", e);
                                    }
                                }
                            }
                        }
                )
                .build();
    }

}

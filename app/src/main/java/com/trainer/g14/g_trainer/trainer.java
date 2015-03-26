package com.trainer.g14.g_trainer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class trainer extends ActionBarActivity {
    String type;
    private Button pause;
    private Button stop;
    private boolean Pause=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);
        Intent intent = getIntent();
        type = intent.getStringExtra(workout.TYPE);

        // Create the text view
        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText("Current Exercise:");

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        //show pic of exercise
        ImageView picture = (ImageView) findViewById(R.id.pic);
        Drawable[] drawables = new Drawable[] {
                getResources().getDrawable(R.drawable.pu),
        };
        picture.setImageDrawable(drawables[0]);

        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Pause) {
                    pause.setText("Resume");
                    Pause=true;
                }else {
                    pause.setText("Pause");
                    Pause=false;
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
}

package com.hfad.stopwatch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Handler;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class StopWatchActivity extends Activity {
    private int sec=0;
    private boolean running=false;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        if(savedInstanceState !=null) {
            sec = savedInstanceState.getInt("sec");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();
    }
    @Override
    protected void onPause()
    {   super.onPause();
        wasRunning=running;
        running=false;
    }
    @Override
    protected void onResume()
        {
            super.onResume();
           if(wasRunning)
           {running = true;}
        }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
            savedInstanceState.putInt("sec",sec);
            savedInstanceState.putBoolean("running",running);
            savedInstanceState.putBoolean("wasRunning",wasRunning);
        }
    public void onClickStart(View view)
    {running=true;}


    public void onClickStop(View view)
    {running=false;}


    public void onClickReset(View view)
    {running=false;
    sec=0;}
    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
          @Override
        public void run() {
                int hours = sec / 3600;
                int mins = (sec % 3600) / 60;
                int seconds = sec % 60;
                String time = String.format("%d:%02d:%02d", hours, mins, seconds);
                timeView.setText(time);
                if (running) {
                    sec++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}

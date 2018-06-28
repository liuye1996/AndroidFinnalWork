package com.example.n551.finnal;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class randomActivity extends AppCompatActivity implements SensorEventListener{

    Button ranBtn;
    private SensorManager mSensorManager;
    private Vibrator Vibrator;
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        ranBtn = (Button)findViewById(R.id.ranBtn);
        ranBtn.setOnClickListener(new mClick());
        Vibrator = (Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);

    }
    class mClick implements OnClickListener{
        public void onClick(View v){
            Intent intent = new Intent(randomActivity.this,random2Activity.class);
            startActivity(intent);
            randomActivity.this.finish();
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager = (SensorManager)getApplication().getSystemService(SENSOR_SERVICE);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onStop(){
        super.onStop();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        float[] values = event.values;
        if(sensorType == Sensor.TYPE_ACCELEROMETER){
            if(Math.abs(values[0])>14 || Math.abs(values[1])>14 || Math.abs(values[2])>14 ){
                if (flag){
                    flag = false;
                    Vibrator.vibrate(new long[]{0,100,0,0},-1);
                    Intent intent = new Intent(randomActivity.this,random2Activity.class);
                    startActivity(intent);
                    randomActivity.this.finish();
                }
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

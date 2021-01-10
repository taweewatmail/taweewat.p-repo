package com.example.user.simplesolar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class page1 extends Activity implements SensorEventListener, AdapterView.OnItemSelectedListener {
    public float currentDegree = 0f;
    public TextView tvHeading;
    public TextView myLightSensorValue, myRotationValue, latlong;
    public SensorManager lightSensorManager, rotationSensorManager, compassSensorManager;
    public Sensor myLightSensor, myRotationSensor;
    public ImageButton ib;
    public float x;
    public Spinner spinner;
    public Sensor rotationVectorSensor;
    public SensorEventListener rvListener;
    public int RotationVal, realRotationVal, rotationSelect, lightVal, realLightVal, realDegree;
    private SensorEventListener mSensorListener;
    private Sensor sensor;
    private SensorEvent eventStore;
    Button getMapB;
    int latitude, longitude;
    String latitudeVariable, longitudeVariable;
    Button b8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_page1);

        b8 = (Button) findViewById(R.id.ib8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B8 = new Intent(page1.this, MainActivity.class);
                startActivity(B8);
            }
        });

        ib = (ImageButton) findViewById(R.id.imageButton3);

        //Light
        myLightSensorValue = (TextView) findViewById(R.id.light_value_textview);
        lightSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //myLightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        //Rotation
        myRotationValue = (TextView) findViewById(R.id.light_value_textview_3);
        rotationSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //rotationVectorSensor = rotationSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);


        getMapB = findViewById(R.id.button2);
        getMapB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B1 = new Intent(page1.this, mapApp.class);
                startActivity(B1);

            }
        });
        Bundle extras = getIntent().getExtras();
        latitudeVariable = extras.getString("latitudeVariable");
        longitudeVariable = extras.getString("longitudeVariable");
        latlong = (TextView) findViewById(R.id.light_value_textview_4);
        latlong.setText("    Latitude " + latitudeVariable + latitude + "\n    Longitude " + longitudeVariable);
        latitude = (int)Float.parseFloat(latitudeVariable);
        TextView lat = (TextView) findViewById(R.id.light_value_textview_2);
        lat.setText("เท่ากับ " + latitude + " องศา");
    }


    @Override
    protected void onPause() {
        super.onPause();
            lightSensorManager.unregisterListener(this);
            rotationSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lightSensorManager.registerListener(this, lightSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
        rotationSensorManager.registerListener(this, rotationSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_NORMAL);
        /*compassSensorManager.registerListener(this, compassSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);*/


    }

    protected int positiveVal() {
        RotationVal = RotationVal + 90;
        if(RotationVal < 0){
            RotationVal = 0 - RotationVal;

        }
        return RotationVal;
    }

    @Override
    public void onAccuracyChanged (Sensor arg0,int arg1){

    }
        public void onSensorChanged (SensorEvent event){


                    float[] rotationMatrix = new float[16];
                    SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);

                    float[] remappedRotationMatrix = new float[16];
                    SensorManager.remapCoordinateSystem(rotationMatrix,
                            SensorManager.AXIS_Y,
                            SensorManager.AXIS_Z,
                            remappedRotationMatrix);

                    float[] orientations = new float[3];
                    SensorManager.getOrientation(remappedRotationMatrix, orientations);

                    for (int i = 0; i < 3; i++) {
                        orientations[i] = (float) (Math.toDegrees(orientations[i]));
                    }
                    RotationVal = (int) orientations[2];
                    positiveVal();
                    if(RotationVal != 180 /*&& RotationVal != 179*/) {
                        myRotationValue.setText("เท่ากับ " + Float.toString(RotationVal) + " องศา");
                        realRotationVal = RotationVal;
                    }
                    //Light
                    lightVal = (int) x;
                    x = event.values[0];
                    if(lightVal != 0) {
                        myLightSensorValue.setText("เท่ากับ " + lightVal + " lux");
                        realLightVal = lightVal;
                    }





            //Callculate



            final Dialog dialog = new Dialog(page1.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.customdialog);
            dialog.setCancelable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (realLightVal >= 10000 && realRotationVal == latitude) {
                        Toast.makeText(getApplicationContext()
                                , "Close dialog", Toast.LENGTH_SHORT);
                        dialog.cancel();
                        TextView textView1 = (TextView) dialog.findViewById(R.id.textView1);
                        textView1.setText("สามารถติดตั้งได้");
                        dialog.show();


                    } else {
                        Toast.makeText(getApplicationContext()
                                , "Close dialog", Toast.LENGTH_SHORT);
                        dialog.cancel();
                        TextView textView1 = (TextView) dialog.findViewById(R.id.textView1);
                        textView1.setText("ไม่ควรติดตั้ง");
                        dialog.show();

                    }
                }
            });

        }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
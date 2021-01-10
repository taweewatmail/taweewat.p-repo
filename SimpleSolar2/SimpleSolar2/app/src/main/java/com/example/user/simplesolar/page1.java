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
    public TextView myLightSensorValue, myRotationValue;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_page1);

        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.จัดหวัด, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                if (position == 0) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("13 องศา");
                    rotationSelect = 13;
                } else if (position == 1) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("8 องศา");
                    rotationSelect = 8;
                } else if (position == 2) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 3) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("16 องศา");
                    rotationSelect = 16;
                } else if (position == 4) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("16 องศา");
                    rotationSelect = 16;
                } else if (position == 5) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("16 องศา");
                    rotationSelect = 16;
                } else if (position == 6) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("12 องศา");
                    rotationSelect = 12;
                } else if (position == 7) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("13 องศา");
                    rotationSelect = 13;
                } else if (position == 8) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("13 องศา");
                    rotationSelect = 13;
                } else if (position == 9) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("15 องศา");
                    rotationSelect = 15;
                } else if (position == 10) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("15 องศา");
                    rotationSelect = 15;
                } else if (position == 11) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("10 องศา");
                    rotationSelect = 10;
                } else if (position == 12) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("19 องศา");
                    rotationSelect = 19;
                } else if (position == 13) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("18 องศา");
                    rotationSelect = 18;
                } else if (position == 14) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("7 องศา");
                    rotationSelect = 7;
                } else if (position == 15) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("12 องศา");
                    rotationSelect = 12;
                } else if (position == 16) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("16 องศา");
                    rotationSelect = 16;
                } else if (position == 17) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 18) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("13 องศา");
                    rotationSelect = 13;
                } else if (position == 19) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("17 องศา");
                    rotationSelect = 17;
                } else if (position == 20) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 21) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("8 องศา");
                    rotationSelect = 8;
                } else if (position == 22) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("15 องศา");
                    rotationSelect = 15;
                } else if (position == 23) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("13 องศา");
                    rotationSelect = 13;
                } else if (position == 24) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("6 องศา");
                    rotationSelect = 6;
                } else if (position == 25) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("18 องศา");
                    rotationSelect = 18;
                } else if (position == 26) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("18 องศา");
                    rotationSelect = 18;
                } else if (position == 27) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 28) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 29) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("11 องศา");
                    rotationSelect = 11;
                } else if (position == 30) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 31) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("6 องศา");
                    rotationSelect = 6;
                } else if (position == 32) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 33) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("8 องศา");
                    rotationSelect = 8;
                } else if (position == 34) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("7 องศา");
                    rotationSelect = 7;
                } else if (position == 35) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("16 องศา");
                    rotationSelect = 16;
                } else if (position == 36) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("17 องศา");
                    rotationSelect = 17;
                } else if (position == 37) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("12 องศา");
                    rotationSelect = 12;
                } else if (position == 38) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("16 องศา");
                    rotationSelect = 16;
                } else if (position == 39) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("18 องศา");
                    rotationSelect = 18;
                } else if (position == 40) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("19 องศา");
                    rotationSelect = 19;
                } else if (position == 41) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("7 องศา");
                    rotationSelect = 7;
                } else if (position == 42) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("16 องศา");
                    rotationSelect = 16;
                } else if (position == 43) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("16 องศา");
                    rotationSelect = 16;
                } else if (position == 44) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("19 องศา");
                    rotationSelect = 19;
                } else if (position == 45) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("6 องศา");
                    rotationSelect = 6;
                } else if (position == 46) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("15 องศา");
                    rotationSelect = 15;
                } else if (position == 47) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("16 องศา");
                    rotationSelect = 16;
                } else if (position == 48) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("9 องศา");
                    rotationSelect = 9;
                } else if (position == 49) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("12 องศา");
                    rotationSelect = 12;
                } else if (position == 50) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("13 องศา");
                    rotationSelect = 13;
                } else if (position == 51) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 52) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("18 องศา");
                    rotationSelect = 18;
                } else if (position == 53) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("18 องศา");
                    rotationSelect = 18;
                } else if (position == 54) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("17 องศา");
                    rotationSelect = 17;
                } else if (position == 55) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("15 องศา");
                    rotationSelect = 15;
                } else if (position == 56) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("17 องศา");
                    rotationSelect = 17;
                } else if (position == 57) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("7 องศา");
                    rotationSelect = 7;
                } else if (position == 58) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("6 องศา");
                    rotationSelect = 6;
                } else if (position == 59) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("13 องศา");
                    rotationSelect = 13;
                } else if (position == 60) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("13 องศา");
                    rotationSelect = 13;
                } else if (position == 61) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("13 องศา");
                    rotationSelect = 13;
                } else if (position == 62) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("13 องศา");
                    rotationSelect = 13;
                } else if (position == 63) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 64) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 65) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("17 องศา");
                    rotationSelect = 17;
                } else if (position == 66) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 67) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("9 องศา");
                    rotationSelect = 9;
                } else if (position == 68) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 69) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("17 องศา");
                    rotationSelect = 17;
                } else if (position == 70) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("17 องศา");
                    rotationSelect = 17;
                } else if (position == 71) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("14 องศา");
                    rotationSelect = 14;
                } else if (position == 72) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("17 องศา");
                    rotationSelect = 17;
                } else if (position == 73) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("15 องศา");
                    rotationSelect = 15;
                } else if (position == 74) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("17 องศา");
                    rotationSelect = 17;
                } else if (position == 75) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("15 องศา");
                    rotationSelect = 15;
                } else if (position == 76) {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("15 องศา");
                    rotationSelect = 15;
                } else {
                    TextView myAwesomeTextView = (TextView) findViewById(R.id.light_value_textview_2);
                    myAwesomeTextView.setText("15 องศา");
                    rotationSelect = 15;
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

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
                        myRotationValue.setText("เท่ากับ = " + Float.toString(RotationVal));
                        realRotationVal = RotationVal;
                    }
                    //Light
                    lightVal = (int) x;
                    x = event.values[0];
                    if(lightVal != 0) {
                        myLightSensorValue.setText("เท่ากับ = " + lightVal);
                        realLightVal = lightVal;
                    }





            //Callculate



            final Dialog dialog = new Dialog(page1.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.customdialog);
            dialog.setCancelable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (realLightVal >= 1000 && realRotationVal == rotationSelect) {
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
                        textView1.setText("ไม่สามารถติดตั้งได้");
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
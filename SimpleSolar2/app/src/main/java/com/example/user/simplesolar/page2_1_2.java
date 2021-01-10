package com.example.user.simplesolar;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class page2_1_2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> selection = new ArrayList<String>();
    String[] ขนาดแผง = {"10", "20", "50", "80", "100", "120", "150", "180", "200", "250", "340"};
    Spinner spin5;
    String wattStr;
    int wattPerDay, numSo;
    Button cal, b16, b13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_1_2);

        final Dialog dialog = new Dialog(page2_1_2.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogp3);
        dialog.setCancelable(true);
        Toast.makeText(getApplicationContext()
                , "Close dialog", Toast.LENGTH_SHORT);
        dialog.cancel();
        TextView textView1 = (TextView) dialog.findViewById(R.id.textView1);
        textView1.setText("   กรุณาเลือกขนาด" + "\n แผงโซล่าเซลล์ที่ต้องการ");
        dialog.show();

        b13 = (Button) findViewById(R.id.ib13);
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B13 = new Intent(page2_1_2.this, page2_1.class);
                startActivity(B13);
            }
        });
        b16 = (Button) findViewById(R.id.ib16);
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B16 = new Intent(page2_1_2.this, MainActivity.class);
                startActivity(B16);
            }
        });

        spin5 = (Spinner) findViewById(R.id.spinner2);
        spin5.setOnItemSelectedListener(this);
        ArrayAdapter ee = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ขนาดแผง);
        ee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin5.setAdapter(ee);
        Bundle extras = getIntent().getExtras();
        wattStr = extras.getString("wattVariable");
        wattPerDay = (int)Float.parseFloat(wattStr);
        cal = findViewById(R.id.button4);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSo = wattPerDay/Integer.parseInt(spin5.getSelectedItem().toString());
                TextView textNumSo = (TextView) findViewById(R.id.textView6);
                if(wattPerDay < Integer.parseInt(spin5.getSelectedItem().toString())){
                    numSo = 1;
                }
                textNumSo.setText("\n        จำนวนแผงโซลล่าเซลล์ที่ต้องใช้ " + numSo + " แผง");
            }
        });
        numSo = wattPerDay/Integer.parseInt(spin5.getSelectedItem().toString());
        TextView textNumSo = (TextView) findViewById(R.id.textView6);
        if(wattPerDay < Integer.parseInt(spin5.getSelectedItem().toString())){
            numSo = 1;
        }
        textNumSo.setText("\n         จำนวนแผงโซลล่าเซลล์ที่ต้องใช้ " + numSo + " แผง");

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

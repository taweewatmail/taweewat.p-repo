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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class page2_2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> selection = new ArrayList<String>();
    String[] ขนาดแผงโซล่า = {"100x200 mm", "540x510 mm", "760x670 mm", "1210x808 mm", "1020x670 mm", "1470x670 mm", "1956x922 mm", "1960x992 mm", "1954x990 mm", "2010x1095 mm"};
    Spinner spin6;
    int width, longg, useWidth, useLong;
    Button bCal, b14,b15;
    EditText widthEdit, longgEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_2);


        b14 = (Button) findViewById(R.id.ib14);
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B14 = new Intent(page2_2.this, page2.class);
                startActivity(B14);
            }
        });
        b15 = (Button) findViewById(R.id.ib15);
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B15 = new Intent(page2_2.this, MainActivity.class);
                startActivity(B15);
            }
        });

        spin6 = (Spinner) findViewById(R.id.spinner3);
        spin6.setOnItemSelectedListener(this);
        ArrayAdapter hh = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ขนาดแผงโซล่า);
        hh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin6.setAdapter(hh);
        widthEdit = (EditText)findViewById(R.id.editText2);
        longgEdit = (EditText)findViewById(R.id.editText);
        bCal = (Button) findViewById(R.id.buttonnn5);


        final Dialog dialog = new Dialog(page2_2.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customdialog);
        dialog.setCancelable(true);
        bCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSola();
                if(widthEdit.getText().toString() != "" && longgEdit.getText().toString() != "") {
                    useWidth = (int) (Float.parseFloat(widthEdit.getText().toString()) * 1000);
                    useLong = (int) (Float.parseFloat(longgEdit.getText().toString()) * 1000);
                    int widthCanUse = useWidth / width;
                    int longCanUse = useLong / longg;
                    if (widthCanUse == 0 && useWidth > width || longCanUse == 0 && useLong > longg) {
                        Toast.makeText(getApplicationContext()
                                , "Close dialog", Toast.LENGTH_SHORT);
                        dialog.cancel();
                        TextView textView1 = (TextView) dialog.findViewById(R.id.textView1);
                        textView1.setText("ไม่สามารถติดตั้งได้");
                        dialog.show();

                    } else {
                        int numSola = widthCanUse * longCanUse;
                        Toast.makeText(getApplicationContext()
                                , "Close dialog", Toast.LENGTH_SHORT);
                        dialog.cancel();
                        TextView textView1 = (TextView) dialog.findViewById(R.id.textView1);
                        textView1.setText("สามารถติดตั้งได้ " + numSola + " แผง");
                        dialog.show();
                    }
                }

            }
        });
    }
    protected void selectSola(){
        if(spin6.getSelectedItem().toString() =="100x200 mm"){
            width = 100; longg = 200;
        }else if(spin6.getSelectedItem().toString() =="540x510 mm"){
            width = 540; longg = 510;
        }else if(spin6.getSelectedItem().toString() =="760x670 mm"){
            width = 760; longg = 670;
        }else if(spin6.getSelectedItem().toString() =="1210x808 mm"){
            width = 1210; longg = 808;
        }else if(spin6.getSelectedItem().toString() =="1020x670 mm"){
            width = 1020; longg = 670;
        }else if(spin6.getSelectedItem().toString() =="1470x670 mm"){
            width = 1470; longg = 670;
        }else if(spin6.getSelectedItem().toString() =="1956x922 mm"){
            width = 1956; longg = 922;
        }else if(spin6.getSelectedItem().toString() =="1960x992 mm"){
            width = 1960; longg = 992;
        }else if(spin6.getSelectedItem().toString() =="1954x990 mm"){
            width = 1954; longg = 990;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

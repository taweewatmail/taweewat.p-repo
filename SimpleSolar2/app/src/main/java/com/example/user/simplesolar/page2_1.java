
package com.example.user.simplesolar;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class page2_1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> selection = new ArrayList<String>();
    String[] ประเภท = {"TV", "พัดลม", "เครื่องปรับอากาศ", "ตู้เย็น", "กระติกน้ำร้อน", "ไมโครเวฟ", "เครื่องปิ้งขนมปัง", "คอมพิวเตอร์", "เครื่องซักผ้า", "หลอดไฟ", "เครื่องทำน้ำอุ่น","เครื่องดูดฝุ่น", "เตารีด", "หม้อหุงข้าว", "อุปกรณ์ชาร์จมือถือ", "เครื่องเล่นDVD"};
    String[] กำลังไฟ = {"3", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60","65", "70", "75", "80", "85", "90", "95", "100", "110", "120", "125", "130",
                    "150", "170", "180", "200", "250", "270", "300", "350", "400", "450", "500", "550", "600", "650", "700", "750", "800", "900", "1000", "1200", "1500", "1800", "2000",
                    "2500", "2700", "3000", "3500", "4000"};
    String[] การใช้งาน = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", };
    String[] จำนวน = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    ArrayList<String> StringArray = new ArrayList<String>();
    ArrayList<String> StringArray2 = new ArrayList<String>();
    ArrayList<String> StringArray3 = new ArrayList<String>();
    ArrayList<String> StringArray4 = new ArrayList<String>();
    ImageButton getValue, deleteValue;
    Spinner spin, spin2, spin3, spin4;
    int wattOfEach, wattTotal;
    float solarGetWattPerDay;
    TextView dispCal;
    Button b7, sentWatt,b12;
    Intent sentW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_1);

        final Dialog dialog = new Dialog(page2_1.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogp3);
        dialog.setCancelable(true);
        Toast.makeText(getApplicationContext()
                , "Close dialog", Toast.LENGTH_SHORT);
        dialog.cancel();
        TextView textView1 = (TextView) dialog.findViewById(R.id.textView1);
        textView1.setText("กรุณากรอกข้อมูล");
        dialog.show();

        b7 = (Button) findViewById(R.id.ib7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B7 = new Intent(page2_1.this, page2_1_2.class);
                startActivity(B7);
            }
        });
        b12 = (Button) findViewById(R.id.ib12);
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B12 = new Intent(page2_1.this, page2.class);
                startActivity(B12);
            }
        });

        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ประเภท);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        spin2 = (Spinner) findViewById(R.id.spinner5);
        spin2.setOnItemSelectedListener(this);
        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,กำลังไฟ);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(bb);

        spin3 = (Spinner) findViewById(R.id.spinner6);
        spin3.setOnItemSelectedListener(this);
        ArrayAdapter cc = new ArrayAdapter(this,android.R.layout.simple_spinner_item,การใช้งาน);
        cc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(cc);

        spin4 = (Spinner) findViewById(R.id.spinner7);
        spin4.setOnItemSelectedListener(this);
        ArrayAdapter dd = new ArrayAdapter(this,android.R.layout.simple_spinner_item,จำนวน);
        dd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin4.setAdapter(dd);

        getValue = findViewById(R.id.imb5);
        sentWatt = findViewById(R.id.ib7);
        final TextView DisplayStringArray = (TextView) findViewById(R.id.textView15);
        final TextView DisplayStringArray2 = (TextView) findViewById(R.id.textView16);
        final TextView DisplayStringArray3 = (TextView) findViewById(R.id.textView17);
        final TextView DisplayStringArray4 = (TextView) findViewById(R.id.textView14);
        dispCal = (TextView) findViewById(R.id.textView18);

        getValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayStringArray.setText("");
                DisplayStringArray2.setText("");
                DisplayStringArray3.setText("");
                DisplayStringArray4.setText("");
                dispCal.setText("");
                StringArray.add(spin.getSelectedItem().toString());
                StringArray2.add(spin2.getSelectedItem().toString());
                StringArray3.add(spin3.getSelectedItem().toString());
                StringArray4.add(spin4.getSelectedItem().toString());
                for (int i=0; i<StringArray.size();i++) {
                    DisplayStringArray.append(StringArray.get(i));
                    DisplayStringArray.append("\n");
                    DisplayStringArray2.append(StringArray2.get(i));
                    DisplayStringArray2.append("\n");
                    DisplayStringArray3.append(StringArray3.get(i));
                    DisplayStringArray3.append("\n");
                    DisplayStringArray4.append(StringArray4.get(i));
                    DisplayStringArray4.append("\n");
                }
                wattOfEach = 0;
                wattTotal = 0;
                solarGetWattPerDay = 0;
                for(int i = 0; i < StringArray.size(); i++) {
                    int watt = Integer.parseInt(StringArray2.get(i));
                    int hour = Integer.parseInt(StringArray3.get(i));
                    int numb = Integer.parseInt(StringArray4.get(i));
                    wattOfEach = watt*hour*numb;
                    wattTotal+=wattOfEach;
                    solarGetWattPerDay = (int)(wattTotal/6);
                    dispCal.setText("\n  พลังงานทั้งหมดที่ต้องการ " + wattTotal + "W" +"\n  ขนาดแผงโซลล่าเซลล์ " + solarGetWattPerDay + "W");

                }
            }
        });
        deleteValue = findViewById(R.id.imb4);
        deleteValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StringArray.size()!=0) {
                    DisplayStringArray.setText("");
                    DisplayStringArray2.setText("");
                    DisplayStringArray3.setText("");
                    DisplayStringArray4.setText("");
                    dispCal.setText("");
                    StringArray.remove(StringArray.size() - 1);
                    StringArray2.remove(StringArray2.size() - 1);
                    StringArray3.remove(StringArray3.size() - 1);
                    StringArray4.remove(StringArray4.size() - 1);
                    for (int i = 0; i < StringArray.size(); i++) {
                        DisplayStringArray.append(StringArray.get(i));
                        DisplayStringArray.append("\n");
                        DisplayStringArray2.append(StringArray2.get(i));
                        DisplayStringArray2.append("\n");
                        DisplayStringArray3.append(StringArray3.get(i));
                        DisplayStringArray3.append("\n");
                        DisplayStringArray4.append(StringArray4.get(i));
                        DisplayStringArray4.append("\n");

                    }
                }else {
                    DisplayStringArray.setText("");
                }
                wattOfEach = 0;
                wattTotal = 0;
                solarGetWattPerDay = 0;
                for(int i = 0; i < StringArray.size(); i++) {
                    int watt = Integer.parseInt(StringArray2.get(i));
                    int hour = Integer.parseInt(StringArray3.get(i));
                    int numb = Integer.parseInt(StringArray4.get(i));
                    wattOfEach = watt*hour*numb;
                    wattTotal+=wattOfEach;
                    solarGetWattPerDay = (int)(wattTotal/6);
                    dispCal.setText(  "พลังงานทั้งหมดที่ต้องการ " + wattTotal + " \n  ขนาดแผงโซลล่าเซลล์ " + solarGetWattPerDay);

                }
            }

        });
        sentWatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentW = new Intent(getApplicationContext(), page2_1_2.class);
                sentW.putExtra("wattVariable", Float.toString(solarGetWattPerDay));
                startActivity(sentW);
            }
        });
        //DisplayStringArray.setTextSize(25);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
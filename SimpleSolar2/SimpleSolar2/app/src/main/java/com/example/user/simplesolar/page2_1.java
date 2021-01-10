
package com.example.user.simplesolar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_1);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ประเภท);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        Spinner spin2 = (Spinner) findViewById(R.id.spinner5);
        spin2.setOnItemSelectedListener(this);
        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,กำลังไฟ);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(bb);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
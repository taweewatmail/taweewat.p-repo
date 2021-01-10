package com.example.user.simplesolar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class page3 extends AppCompatActivity {
    Button b20, b21, b22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        b20 = (Button) findViewById(R.id.ib20);
        b21 = (Button) findViewById(R.id.ib21);
        b22 = (Button) findViewById(R.id.ib22);
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B20 = new Intent(page3.this, page3_1.class);
                startActivity(B20);
            }
        });
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B21 = new Intent(page3.this, page3_2.class);
                startActivity(B21);
            }
        });
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B22 = new Intent(page3.this, page3_3.class);
                startActivity(B22);
            }
        });
    }
}

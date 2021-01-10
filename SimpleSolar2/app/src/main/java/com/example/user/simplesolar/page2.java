package com.example.user.simplesolar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class page2 extends AppCompatActivity {
    Button b5, b6, b9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        b5 = (Button) findViewById(R.id.ib5);
        b6 = (Button) findViewById(R.id.ib6);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B5 = new Intent(page2.this, page2_1.class);
                startActivity(B5);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent B6 = new Intent(page2.this, page2_2.class);
                startActivity(B6);
            }
        });
    }
}

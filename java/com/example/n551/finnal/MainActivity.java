package com.example.n551.finnal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton addBtn;
    ImageButton turnBtn;
    ImageButton ranBtn;
    ImageButton webBtn;
    ImageButton selectBtn;
    ImageButton outBtn;
    DBConnection helper;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = (ImageButton)findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new addClick());
        turnBtn = (ImageButton)findViewById(R.id.turnBtn);
        turnBtn.setOnClickListener(new turnClick());
        ranBtn = (ImageButton)findViewById(R.id.ranBtn);
        ranBtn.setOnClickListener(new ranClick());
        webBtn = (ImageButton)findViewById(R.id.noBtn);
        webBtn.setOnClickListener(new webClick());
        selectBtn = (ImageButton)findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(new selectClick());
        outBtn = (ImageButton)findViewById(R.id.outBtn);
        outBtn.setOnClickListener(new outClick());
    }
    class addClick implements View.OnClickListener{
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this, addActivtiy.class);
            startActivity(intent);
        }
    }
    class turnClick implements View.OnClickListener{
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this, turnActivity.class);
            startActivity(intent);
        }
    }
    class ranClick implements View.OnClickListener{
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this, randomActivity.class);
            startActivity(intent);
        }
    }
    class webClick implements View.OnClickListener{
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this,webActivity.class);
            startActivity(intent);
        }
    }
    class selectClick implements View.OnClickListener{
        public void onClick(View v){
            Intent intent = new Intent(MainActivity.this, selectActivity.class);
            startActivity(intent);
        }
    }
    class outClick implements View.OnClickListener{
        public void onClick(View v){
            System.exit(0);
        }
    }
}

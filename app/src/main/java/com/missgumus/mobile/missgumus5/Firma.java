package com.missgumus.mobile.missgumus5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Firma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma);

    }
    public void linkAnasayfa(View view){

        Intent intent= new Intent(getApplicationContext(),MainActivity.class);

        startActivity(intent);
    }

    public void kategoriLink(View view){

        Intent intent= new Intent(getApplicationContext(),MainActivity.class);

        startActivity(intent);
    }

    public void iletisimLink(View view){

        Intent intent= new Intent(getApplicationContext(),IletisimActivity.class);

        startActivity(intent);
    }

    public void MainActivity(View view){

        Intent intent= new Intent(getApplicationContext(),MainActivity.class);

        startActivity(intent);
    }

    public void Hesap(View view){

        Intent intent= new Intent(getApplicationContext(),Hesap.class);

        startActivity(intent);
    }

    public void List(View view){

        Intent intent= new Intent(getApplicationContext(),Firma.class);

        startActivity(intent);
    }

    public void Sepet(View view){

        Intent intent= new Intent(getApplicationContext(),Sepet.class);

        startActivity(intent);
    }
}


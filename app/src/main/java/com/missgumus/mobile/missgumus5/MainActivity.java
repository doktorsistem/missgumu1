package com.missgumus.mobile.missgumus5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    UrunClass urun;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ListView customListView;
    ArrayList<UrunClass> urunlistesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customListView=(ListView) findViewById(R.id.liste);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        getDataFromFirebase();
        fillListView();


    }

   public void MainActivity(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(intent);
    }

    public void Hesap(View view) {

        Intent intent = new Intent(getApplicationContext(), Hesap.class);

        startActivity(intent);
    }

    public void List(View view) {

        Intent intent = new Intent(getApplicationContext(), Firma.class);

        startActivity(intent);
    }

    public void Sepet(View view) {

        Intent intent = new Intent(getApplicationContext(), Sepet.class);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_post, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_post) {

            Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void fillListView(){
        urunlistesi = new ArrayList<UrunClass>();

        CustomAdapter customAdapter=new CustomAdapter(this,urunlistesi,this);
        customListView.setAdapter(customAdapter);
    }
    protected void getDataFromFirebase() {

        DatabaseReference newReference = firebaseDatabase.getReference("Urun");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //System.out.println("children" + dataSnapshot.getChildren());
                //System.out.println("key" + dataSnapshot.getKey());
                //System.out.println("value" + dataSnapshot.getValue());

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    urun=ds.getValue(UrunClass.class);
                    urunlistesi.add(urun);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}

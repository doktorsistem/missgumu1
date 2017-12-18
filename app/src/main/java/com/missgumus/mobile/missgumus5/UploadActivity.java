package com.missgumus.mobile.missgumus5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UploadActivity extends AppCompatActivity {

    EditText editTextKategori;
    EditText editTextAd;
    EditText editTextStokAdeti;
    EditText editTextUrun_id;
    EditText editTextFiyat;
    EditText editTextAciklama;
    ImageView urunImage;
    Button resimEkle;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private StorageReference mStorageRef;
    Uri selected;
    UrunClass urun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        editTextKategori=(EditText) findViewById(R.id.kategori);
        editTextAd=(EditText) findViewById(R.id.ad);
        editTextStokAdeti=(EditText) findViewById(R.id.stokAdeti);
        //editTextUrun_id=(EditText) findViewById(R.id.urun_id);
        editTextFiyat=(EditText) findViewById(R.id.fiyat);
        editTextAciklama=(EditText) findViewById(R.id.aciklama);
        urunImage=(ImageView) findViewById(R.id.urunImage);
        resimEkle=(Button) findViewById(R.id.resimEkle);

        urun=new UrunClass();


        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();
        mStorageRef= FirebaseStorage.getInstance().getReference();


    }

    public void upload(View view){

        urun.kategori=editTextKategori.getText().toString();
        urun.ad=editTextAd.getText().toString();
        urun.adet=Integer.parseInt(editTextStokAdeti.getText().toString());
        urun.fiyat=Double.parseDouble(editTextFiyat.getText().toString());
        urun.aciklama=editTextAciklama.getText().toString();

        urun.urun_id=myRef.push().getKey();




        String urunImage="urunler/"+urun.urun_id+".png";

        StorageReference storageReference=mStorageRef.child(urunImage);

        storageReference.putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                String downloadURL = taskSnapshot.getDownloadUrl().toString();//indireceği linki kaydediyor burada
                urun.urunresim=downloadURL;

                //FirebaseUser user = mAuth.getCurrentUser();
                //String userEmail = user.getEmail().toString();

                myRef.child("Urun").child(urun.urun_id).setValue(urun);
                Toast.makeText(getApplicationContext(),"Ürün Eklendi.",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }


    public void chooseImage(View view){

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,1);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            selected = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selected);
                urunImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
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

}
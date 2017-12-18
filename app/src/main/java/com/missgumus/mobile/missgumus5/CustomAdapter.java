package com.missgumus.mobile.missgumus5;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hamditari on 17.12.2017.
 */

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<UrunClass> mUrunListesi;
    private Context context;
    File localFile;
    ImageView imageView;
    FirebaseStorage storage=FirebaseStorage.getInstance();

    public CustomAdapter(Activity activity, List<UrunClass> urunler,Context context) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        mUrunListesi = urunler;
        this.context=context;
    }

    @Override
    public int getCount() {
        return mUrunListesi.size();
    }

    @Override
    public UrunClass getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return mUrunListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView;

        customView = mInflater.inflate(R.layout.custom_view, null);
        TextView Kategori = (TextView) customView.findViewById(R.id.kategori);
        TextView Ad = (TextView) customView.findViewById(R.id.ad);
        TextView Fiyat = (TextView) customView.findViewById(R.id.fiyat);
        imageView = (ImageView) customView.findViewById(R.id.urunImage2);

        try {
            localFile = File.createTempFile("images","png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        UrunClass urun = mUrunListesi.get(position);

        Kategori.setText(urun.getKategori());
        Ad.setText(urun.getAd());
        Fiyat.setText(Double.toString(urun.getFiyat()));

        StorageReference gsReference = storage.getReferenceFromUrl(urun.getUrunresim());
        Glide.with(this.context)
                .using(new FirebaseImageLoader())
                .load(gsReference)
                .override(400,600)
                .into(imageView);

        return customView;
    }
}

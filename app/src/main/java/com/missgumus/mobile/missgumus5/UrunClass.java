package com.missgumus.mobile.missgumus5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;

/**
 * Created by derya on 16.12.2017.
 */


public class UrunClass {

    public String kategori;
    public String ad;
    public String urun_id;
    public String aciklama;
    public double fiyat;
    public int adet;
    public String urunresim;


    public UrunClass(){
        this.kategori = null;
        this.ad = null;
        this.urun_id=null;
        this.aciklama = null;
        this.fiyat = 0;
        this.adet=0;
        this.urunresim=null;

    }

    public UrunClass(String kategori, String ad, String urun_id,String aciklama, double fiyat, int adet, String urunresim){
        this.kategori = kategori;
        this.ad = ad;
        this.urun_id=urun_id;
        this.aciklama = aciklama;
        this.fiyat = fiyat;
        this.adet=adet;
        this.urunresim=urunresim;

    }

    public String getKategori(){
        return kategori;
    }

    public String getAd(){
        return ad;
    }

    public String getUrun_id(){
        return urun_id;
    }

    public String getAciklama(){
        return aciklama;
    }

    public double getFiyat(){
        return fiyat;
    }

    public int getAdet(){
        return adet;
    }

    public String getUrunresim(){
        return urunresim;
    }


}

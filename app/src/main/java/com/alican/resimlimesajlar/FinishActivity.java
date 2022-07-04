package com.alican.resimlimesajlar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alican.resimlimesajlar.FinishPage.finishAdapter;
import com.alican.resimlimesajlar.FinishPage.finishName;
import com.alican.resimlimesajlar.HomePage.homeName;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class FinishActivity extends AppCompatActivity {

    private Toolbar finishToolbar;
    private RecyclerView finishRV;

    private ArrayList<finishName> finishNameArrayList;
    private finishAdapter adapter;

    //PutExtra
    private homeName name;

    //Admob
    private AdView adViewFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        name = (homeName) getIntent().getSerializableExtra("url_id");

        //Toast.makeText(this,name.getText(),Toast.LENGTH_LONG).show();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        //Admob
        adViewFinish = findViewById(R.id.adViewFinish);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewFinish.loadAd(adRequest);
        //ca-app-pub-2231993968861276~1786782209
        //ca-app-pub-2231993968861276/2450224665

        finishToolbar = findViewById(R.id.finishToolbar);
        finishToolbar.setTitle(name.getText());
        setSupportActionBar(finishToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        finishRV = findViewById(R.id.finishRv);
        finishRV.setHasFixedSize(true);
        finishRV.setLayoutManager(new GridLayoutManager(this,3));

        finishNameArrayList = new ArrayList<>();


        if (name.getId() == 0){
            for (int i = 1; i<=149; i++){
                finishNameArrayList.add(new finishName("https://aynadakiyazar.com/apps/cuma-mesajlari/a"+i+".jpg"));
            }
        }else if (name.getId() == 1){
            for (int i = 1; i<=74; i++){
                finishNameArrayList.add(new finishName("https://aynadakiyazar.com/apps/kurban-bayrami/"+i+".jpg"));
            }
        }else if (name.getId() == 2){
            for (int i = 1; i<=20; i++){
                finishNameArrayList.add(new finishName("https://aynadakiyazar.com/apps/ramazan-bayrami/"+i+".jpg"));
            }
        }else if (name.getId() == 3){
            for (int i = 1; i<=50; i++){
                finishNameArrayList.add(new finishName("https://aynadakiyazar.com/apps/gunaydin-mesajlari/"+i+".jpg"));
            }
        }else if (name.getId() == 4){
            for (int i = 1; i<=40; i++){
                finishNameArrayList.add(new finishName("https://aynadakiyazar.com/apps/iyi-geceler/"+i+".jpg"));
            }
        }else if (name.getId() == 5){
            for (int i = 1; i<=50; i++){
                finishNameArrayList.add(new finishName("https://aynadakiyazar.com/apps/mevlid-kandili/"+i+".jpg"));
            }
        }else if (name.getId() == 6){
            for (int i = 1; i<=50; i++){
                finishNameArrayList.add(new finishName("https://aynadakiyazar.com/apps/mirac-kandili/"+i+".jpg"));
            }
        }
        else{

            Toast.makeText(this,"Resimler Bulunamadı...",Toast.LENGTH_LONG).show();
        }



        adapter = new finishAdapter(FinishActivity.this,finishNameArrayList);
        finishRV.setAdapter(adapter);
        //Collections.shuffle(finishNameArrayList);




    }


}
































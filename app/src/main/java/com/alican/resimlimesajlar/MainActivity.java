package com.alican.resimlimesajlar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private AdView mAdView;

    private SharedPreferences sp;
    private SharedPreferences.Editor e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*


        sp = getSharedPreferences("DarkMod",MODE_PRIVATE);
        e = sp.edit();

        Boolean darkMode = sp.getBoolean("darkMode",false);

        if (darkMode){
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //Toast.makeText(this,"true", Toast.LENGTH_LONG).show();
        }

         */

        //networkController(); //İnternet Kontrol

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        bottomNav = findViewById(R.id.bottomNav);

        mAdView = findViewById(R.id.adViewFinish);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //ca-app-pub-2231993968861276~1786782209
        //ca-app-pub-2231993968861276/8358277025

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        NavigationUI.setupWithNavController(bottomNav,navHostFragment.getNavController());
    }

    public boolean isConnected() {
        //NETWROK BAGLANTINIS KONTOL EDER VE BIZE BOOL BIR DEGER DONDURUR
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    public void networkController()  {

        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        // Check for Internet Connection
        if (isConnected()) {
            //Toast.makeText(getApplicationContext(), "Bağlandı", Toast.LENGTH_SHORT).show();

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("İnternet Bağlantısı!");
            builder.setMessage("İnternet Bağlantını Kontrol Edip Tekrar Giriş Yapman Lazım");
            builder.setPositiveButton("TEKRAR DENE", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent homeIntent = new Intent(MainActivity.this, SplashActivity.class);
                    startActivity(homeIntent);
                    finish();

                }
            });
            builder.show();

        }

    }
}
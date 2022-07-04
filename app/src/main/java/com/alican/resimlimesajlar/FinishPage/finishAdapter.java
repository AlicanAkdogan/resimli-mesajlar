package com.alican.resimlimesajlar.FinishPage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.alican.resimlimesajlar.FinishActivity;
import com.alican.resimlimesajlar.R;
import com.alican.resimlimesajlar.SplashActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class finishAdapter extends RecyclerView.Adapter<finishAdapter.cardDesign> {

    private Context mContext;
    private List<finishName> finishNameList;

    public finishAdapter(Context mContext, List<finishName> finishNameList) {
        this.mContext = mContext;
        this.finishNameList = finishNameList;
    }

    public class cardDesign extends RecyclerView.ViewHolder {

        private ImageView finishImageView;
        private ProgressBar finishProgressBar;
        private Dialog finishDialog;

        //Dialog
        private ImageView dialogImageView, dialogX;
        private Button dialogButton, dialogButton2;

        public cardDesign(@NonNull View itemView) {
            super(itemView);

            finishImageView = itemView.findViewById(R.id.finishImageView);
            finishProgressBar = itemView.findViewById(R.id.finishProgressBar);
            finishDialog = new Dialog(mContext);
        }

        public void popupWindow(String URL) {
            finishDialog.setContentView(R.layout.popup2);
            finishDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            finishDialog.setCancelable(false);
            finishDialog.show();

            //Dialog
            dialogImageView = finishDialog.findViewById(R.id.dialogImage);
            dialogButton = finishDialog.findViewById(R.id.dialogButton);
            dialogButton2 = finishDialog.findViewById(R.id.dialogButton2);
            //dialogX = finishDialog.findViewById(R.id.dialogX);
            Picasso.get().load(URL).into(dialogImageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(mContext,"Resim Yüklenmedi",Toast.LENGTH_LONG).show();
                }
            });

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    finishDialog.dismiss();


                }
            });

            dialogButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    BitmapDrawable bitmapDrawable = (BitmapDrawable) dialogImageView.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    String bitmapPath = MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
                            bitmap, "Cuma Mesajları", null);
                    Uri bitmapUri = Uri.parse(bitmapPath);
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                    mContext.startActivity(Intent.createChooser(intent, "Share Image"));


                }
            });

/*
            dialogX.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finishDialog.dismiss();
                }
            });

 */
        }

    }

    @NonNull
    @Override
    public finishAdapter.cardDesign onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finish_card_desing,parent,false);
        return new cardDesign(view);
    }

    @Override
    public void onBindViewHolder(@NonNull finishAdapter.cardDesign holder, int position) {

        final finishName name = finishNameList.get(position);

        holder.finishImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.popupWindow(name.getImageURL());
            }
        });

        Picasso.get().load(name.getImageURL()).fit().centerCrop().into(holder.finishImageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.finishProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

                //Toast.makeText(mContext, "Resim Yüklenme Hatası", Toast.LENGTH_SHORT).show();
                //networkController();

            }
        });

    }

    @Override
    public int getItemCount() {
        return finishNameList.size();
    }

    /*
    public boolean isConnected() {
        //NETWROK BAGLANTINIS KONTOL EDER VE BIZE BOOL BIR DEGER DONDURUR
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    public void networkController()  {

        ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        // Check for Internet Connection
        if (isConnected()) {
            //Toast.makeText(getApplicationContext(), "Bağlandı", Toast.LENGTH_SHORT).show();

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("İnternet Bağlantısı!");
            builder.setMessage("İnternet Bağlantını Kontrol Edip Tekrar Giriş Yapman Lazım");
            builder.setPositiveButton("TEKRAR DENE", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent homeIntent = new Intent(mContext, SplashActivity.class);
                    mContext.startActivity(homeIntent);
                    ((Activity)mContext).finish();

                }
            });
            builder.show();

        }

    }

     */

}






































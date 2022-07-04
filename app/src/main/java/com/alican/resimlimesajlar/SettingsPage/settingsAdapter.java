package com.alican.resimlimesajlar.SettingsPage;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alican.resimlimesajlar.BuildConfig;
import com.alican.resimlimesajlar.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class settingsAdapter extends RecyclerView.Adapter<settingsAdapter.cardDesign> {

    private Context mContext;
    private List<settingName> settingNameList;

    public settingsAdapter(Context mContext, List<settingName> settingNameList) {
        this.mContext = mContext;
        this.settingNameList = settingNameList;
    }


    public class cardDesign extends RecyclerView.ViewHolder {

        private ImageView settingImageView;
        private TextView settingTextView;
        private CardView settingCardView;

        public cardDesign(@NonNull View itemView) {
            super(itemView);

            settingImageView = itemView.findViewById(R.id.settingsImage);
            settingTextView = itemView.findViewById(R.id.settingsText);
            settingCardView = itemView.findViewById(R.id.settingCardView);
        }
    }

    @NonNull
    @Override
    public settingsAdapter.cardDesign onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_card_design,parent,false);
        return new cardDesign(view); //
    }

    @Override
    public void onBindViewHolder(@NonNull settingsAdapter.cardDesign holder, int position) {

        final settingName name = settingNameList.get(position);
        holder.settingTextView.setText(name.getText());

        Picasso.get().load(settingNameList.get(position).getImage()).into(holder.settingImageView);

        holder.settingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.getAdapterPosition() == 0){
                    //Toast.makeText(mContext,"Uygulama Puanlama", Toast.LENGTH_LONG).show();
                    try {
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=" + mContext.getPackageName())));
                    } catch (ActivityNotFoundException e) {
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=" + mContext.getPackageName())));
                    }
                }else if (holder.getAdapterPosition() == 1){
                    AlertDialog.Builder ad = new AlertDialog.Builder(mContext);
                    ad.setTitle("Uygulama Sürümü");
                    ad.setMessage(BuildConfig.VERSION_NAME);
                    ad.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    ad.create().show();
                }else if (holder.getAdapterPosition() == 2){
                    AlertDialog.Builder ad = new AlertDialog.Builder(mContext);
                    ad.setTitle("İletişim");
                    ad.setMessage("Mail : alican@aynadakiyazar.com");
                    ad.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    ad.create().show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return settingNameList.size();
    }


}


































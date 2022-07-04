package com.alican.resimlimesajlar.SettingsPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alican.resimlimesajlar.MainActivity;
import com.alican.resimlimesajlar.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class settingsSwitchAdapter extends RecyclerView.Adapter<settingsSwitchAdapter.cardDesign> {

    private Context context;
    private List<settingName> nameList;
    private Boolean darkMode;

    public settingsSwitchAdapter(Context context, List<settingName> nameList) {
        this.context = context;
        this.nameList = nameList;

    }

    public class cardDesign extends RecyclerView.ViewHolder {

        private ImageView settingImageView2;
        private TextView settingTextView2;
        private CardView settingCardView2;
        private Switch settingsSwitch;


        public cardDesign(@NonNull View itemView) {
            super(itemView);

            settingImageView2 = itemView.findViewById(R.id.settingsImage2);
            settingTextView2 = itemView.findViewById(R.id.settingsText2);
            settingCardView2 = itemView.findViewById(R.id.settingCardView2);
            settingsSwitch = itemView.findViewById(R.id.settingsSwitch);



        }
    }

    @NonNull
    @Override
    public settingsSwitchAdapter.cardDesign onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_card_design_switch,parent,false);
        return new cardDesign(view);
    }

    @Override
    public void onBindViewHolder(@NonNull settingsSwitchAdapter.cardDesign holder, int position) {

        SharedPreferences sp = (SharedPreferences) context.getSharedPreferences("DarkMod",Context.MODE_PRIVATE);


        final settingName names = nameList.get(position);
        holder.settingTextView2.setText(names.getText());

        Picasso.get().load(nameList.get(position).getImage()).into(holder.settingImageView2);

        //holder.settingsSwitch.setChecked(true);

        int currentNightMode = context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;

        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                //Toast.makeText(context,"Gece Modu Aktif Değil",Toast.LENGTH_LONG).show();
                holder.settingsSwitch.setChecked(false);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                //Toast.makeText(context,"Gece Modu Aktif",Toast.LENGTH_LONG).show();
                holder.settingsSwitch.setChecked(true);
                break;

        }



        holder.settingsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if (b){
                    //Toast.makeText(context,"Açık",Toast.LENGTH_LONG).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    darkMode = true;
                    restartApp();
                }else{
                    Toast.makeText(context,"Kapalı",Toast.LENGTH_LONG).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    darkMode = false;
                    restartApp();
                }
                SharedPreferences.Editor e = sp.edit();
                e.putBoolean("darkMode",darkMode);
                e.commit();
            }
        });




    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public void restartApp(){
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
        ((Activity)context).finish();
    }


}



















































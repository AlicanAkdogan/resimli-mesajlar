package com.alican.resimlimesajlar.SettingsPage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.alican.resimlimesajlar.R;

import java.util.ArrayList;


public class SettingsFragment extends Fragment {

    private RecyclerView settingRv;
    private Toolbar settingTools;

    private ArrayList<settingName> settingNameArrayList;
    private settingsAdapter adapter;

    private RecyclerView recyclerView;
    private ArrayList<settingName> nameArrayList;
    private settingsSwitchAdapter getAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        settingTools = rootView.findViewById(R.id.settingToolbar);
        settingTools.setTitle("Ayarlar");
        ((AppCompatActivity)getActivity()).setSupportActionBar(settingTools);

        settingRv = rootView.findViewById(R.id.settingRv);
        settingRv.setHasFixedSize(true);
        settingRv.setLayoutManager(new LinearLayoutManager(getContext()));

        settingNameArrayList = new ArrayList<>();
        settingNameArrayList.add(new settingName(0,R.drawable.star,"Uygulamamızı Değerlendir"));
        settingNameArrayList.add(new settingName(1,R.drawable.information,"Uygulama Sürümü"));
        settingNameArrayList.add(new settingName(2,R.drawable.contact,"İletişim"));

        adapter = new settingsAdapter(getContext(),settingNameArrayList);
        settingRv.setAdapter(adapter);

        /*---------------------------------------------------*/

        /*
        recyclerView = rootView.findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        nameArrayList = new ArrayList<>();
        nameArrayList.add(new settingName(0,R.drawable.star,"Karanlık Mod"));

        getAdapter = new settingsSwitchAdapter(getContext(),nameArrayList);
        recyclerView.setAdapter(getAdapter);


         */


        return rootView;
    }
}







































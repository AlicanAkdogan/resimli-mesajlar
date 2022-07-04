package com.alican.resimlimesajlar.HomePage;

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

import com.alican.resimlimesajlar.SettingsPage.settingsAdapter;

import java.util.ArrayList;

public class HomePageFragment extends Fragment {

    private RecyclerView homeRv;
    private Toolbar homeTools;

    private ArrayList<homeName> homeNameArrayList;
    private homeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);

        homeTools = rootView.findViewById(R.id.homeToolbar);
        homeTools.setTitle("Ana Sayfa");
        ((AppCompatActivity)getActivity()).setSupportActionBar(homeTools);


        homeRv = rootView.findViewById(R.id.homeRv);
        homeRv.setHasFixedSize(true);
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));

        homeNameArrayList = new ArrayList<>();
        homeNameArrayList.add(new homeName(0,R.drawable.t1,"Cuma Mesajlar"));
        homeNameArrayList.add(new homeName(1,R.drawable.t2,"Kurban Bayramı"));
        homeNameArrayList.add(new homeName(2,R.drawable.t3,"Ramazan Bayramı"));
        homeNameArrayList.add(new homeName(3,R.drawable.t4,"Günaydın Mesajları"));
        homeNameArrayList.add(new homeName(4,R.drawable.t5,"İyi Geceler Mesajları"));
        homeNameArrayList.add(new homeName(5,R.drawable.t6,"Mevlid Kandili"));
        homeNameArrayList.add(new homeName(6,R.drawable.t7,"Miraç Kandili"));



        adapter = new homeAdapter(getContext(),homeNameArrayList);

        homeRv.setAdapter(adapter);

        return rootView;
    }
}
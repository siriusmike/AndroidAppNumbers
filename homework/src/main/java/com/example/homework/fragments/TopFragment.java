package com.example.homework.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Model;
import com.example.homework.R;

import java.util.ArrayList;
import java.util.List;

public class TopFragment extends Fragment {

    public Button btn;
    public static Activity activity;
    List<Model> list = new ArrayList<>();
    MyRecyclerViewAdapter adapter;
    String NUMBER_KEY = "size";
    int size;


    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity)context;
        }
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int count = 100;
        if(savedInstanceState != null){
            count = savedInstanceState.getInt(NUMBER_KEY, count);
        }
        for (int i = 0; i < count; ++i) {
            list.add(createNewElement());
            ++size;
        }
        
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_layout, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.Numbers);

        int numberOfColumnsLandScape = 4;
        int numberOfColumnsPortrait = 3;

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), numberOfColumnsLandScape));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), numberOfColumnsPortrait));
        }

        MyInterface InterfaceContext = (MyInterface) getActivity();
        adapter = new MyRecyclerViewAdapter(list, InterfaceContext);
        recyclerView.setAdapter(adapter);

        btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addElement();
                ++size;
            }
        });

    }

    Model createNewElement(){
        int count = list.size() + 1;
        int color;
        if (count % 2 == 0) {
            color = Color.RED;
        } else {
            color = Color.BLUE;
        }
        return new Model(String.valueOf(count), color);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(NUMBER_KEY, size);
        super.onSaveInstanceState(outState);
    }
}

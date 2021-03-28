package com.example.homework.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.homework.R;

public class ChildFragment extends Fragment {

    public static final String KEY = "text";

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.child_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String number = getArguments().getString(KEY);
        TextView textView = view.findViewById(R.id.ChildTextView);
        if (Integer.parseInt(number) % 2 == 0){
            textView.setTextColor(Color.RED);
        }
        else {
            textView.setTextColor(Color.BLUE);
        }
        textView.setText(number);
    }
}

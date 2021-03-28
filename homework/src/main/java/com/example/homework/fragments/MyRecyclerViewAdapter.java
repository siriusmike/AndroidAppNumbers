package com.example.homework.fragments;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.Model;
import com.example.homework.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    List<Model> data;
    MyInterface myInterface;

    public MyRecyclerViewAdapter(List<Model> list, MyInterface interfaceContext) {
        data = list;
        myInterface = interfaceContext;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model model = data.get(position);
        holder.bind(model);
    }


    public void addElement() {
        int index = getItemCount() + 1;
        int color;
        if (index % 2 == 0) {
            color = Color.RED;
        } else {
            color = Color.BLUE;
        }
        data.add(new Model(String.valueOf(index), color));
        notifyItemInserted(getItemCount());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final Button btn;

        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.RVText);
            btn = itemView.findViewById(R.id.btn);

        }

        public void bind(Model model){
            date.setText(model.mDate);
            date.setTextColor(model.mColor);
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = v.findViewById(v.getId());
                    String number = String.valueOf(textView.getText());
                    ((MyInterface) TopFragment.activity).onOpenChildFragment(number);
                }
            });

        }


    }

    }



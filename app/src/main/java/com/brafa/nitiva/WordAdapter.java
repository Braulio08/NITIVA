package com.brafa.nitiva;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends BaseAdapter {
    ArrayList<String> word = new ArrayList<>();
    Context context;

    public WordAdapter(ArrayList<String> word, Context context) {
        this.word = word;
        this.context = context;
    }

    @Override
    public int getCount() {
        return word.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.word, parent,false);
        TextView textView = view.findViewById(R.id.textView4);
        textView.setText(word.get(position));
        return view;
    }
}

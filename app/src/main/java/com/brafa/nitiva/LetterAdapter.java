package com.brafa.nitiva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class LetterAdapter extends BaseAdapter {
    private String[] letras;
    private LayoutInflater infoLetra;
    public LetterAdapter(Context context){
        letras = new String[26];
        for (int i = 0; i < letras.length; i++) {
            letras[i]=""+(char)(i+'A');
        }
        infoLetra = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return letras.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button btnLetra;
        if(view == null){
            btnLetra=(Button)infoLetra.inflate(R.layout.letter, viewGroup, false);
        }else{
            btnLetra=(Button)view;
        }
        btnLetra.setText(letras[i]);
        return btnLetra;
    }
}

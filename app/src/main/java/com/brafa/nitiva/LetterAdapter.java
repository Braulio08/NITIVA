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
        letras = new String[27];
        letras[0]=""+(char)('A');
        letras[1]=""+(char)('B');
        letras[2]=""+(char)('C');
        letras[3]=""+(char)('D');
        letras[4]=""+(char)('E');
        letras[5]=""+(char)('F');
        letras[6]=""+(char)('G');
        letras[7]=""+(char)('H');
        letras[8]=""+(char)('I');
        letras[9]=""+(char)('J');
        letras[10]=""+(char)('K');
        letras[11]=""+(char)('M');
        letras[12]=""+(char)('N');
        letras[13]=""+(char)('Ñ');
        letras[14]=""+(char)('L');
        letras[15]=""+(char)('O');
        letras[16]=""+(char)('P');
        letras[17]=""+(char)('Q');
        letras[18]=""+(char)('R');
        letras[19]=""+(char)('S');
        letras[20]=""+(char)('T');
        letras[21]=""+(char)('U');
        letras[22]=""+(char)('V');
        letras[23]=""+(char)('W');
        letras[24]=""+(char)('X');
        letras[25]=""+(char)('Y');
        letras[26]=""+(char)('Z');
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

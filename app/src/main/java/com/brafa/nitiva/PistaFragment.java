package com.brafa.nitiva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PistaFragment extends Fragment {

    TextView pistaResult;

    public PistaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pista, container, false);
        pistaResult = view.findViewById(R.id.textViewPista);
        Bundle bundle = getArguments();
        String pista = bundle.getString("pista");

        pistaResult.setText(pista);



        return view;
    }
}
package com.example.finedu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class module extends Fragment {
    Button stock, fundamental, technical, futures, options, forex, taxes;
    Context context;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public module() {

    }

    public static module newInstance(String param1, String param2) {
        module fragment = new module();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_module, container, false);
        stock = rootView.findViewById(R.id.stock);
        fundamental = rootView.findViewById(R.id.fundamental);
        technical = rootView.findViewById(R.id.technical);
        futures = rootView.findViewById(R.id.futures);
        options = rootView.findViewById(R.id.option);
        forex = rootView.findViewById(R.id.forex);
        taxes = rootView.findViewById(R.id.taxes);
        context=container.getContext();

        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StockMarket.class);
                startActivity(intent);
            }
        });

        fundamental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Fundamental.class);
                startActivity(intent);
            }
        });

        technical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Technical.class);
                startActivity(intent);
            }
        });

        futures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Futures.class);
                startActivity(intent);
            }
        });

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Options.class);
                startActivity(intent);
            }
        });

        forex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Forex.class);
                startActivity(intent);
            }
        });
        taxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Taxation.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
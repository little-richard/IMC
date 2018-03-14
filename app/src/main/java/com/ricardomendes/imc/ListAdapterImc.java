package com.ricardomendes.imc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Ricardo Mendes on 14/03/2018.
 */

public class ListAdapterImc extends ArrayAdapter<String>{
    private Context context;
    private ArrayList<String> lista;

    public ListAdapterImc(Context context, ArrayList<String> lista){
        super(context, R.layout.item2, lista);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String itemPosicao = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item2, null);
        EditText edt = (EditText)convertView.findViewById(R.id.textView3);
        edt.setText(itemPosicao);

        return convertView;
    }
}

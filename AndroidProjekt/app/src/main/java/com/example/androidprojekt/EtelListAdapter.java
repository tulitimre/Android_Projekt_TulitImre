package com.example.androidprojekt;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EtelListAdapter extends ArrayAdapter {

    private List<Etelek> etelLista;
    private Activity context;

    public EtelListAdapter(Activity context, List<Etelek> etelLista) {
        super(context, R.layout.eteltipusalapjan_row, etelLista);

        this.context = context;
        this.etelLista = etelLista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.eteltipusalapjan_row, null, true);

        TextView etelNev = rowView.findViewById(R.id.eteltipusalNev);
        TextView etelTipus = rowView.findViewById(R.id.eteltipusalTipus);
        TextView etelAr = rowView.findViewById(R.id.eteltipusalAr);
        TextView etelMennyiseg = rowView.findViewById(R.id.eteltipusalMennyiseg);
        TextView etelEtterem = rowView.findViewById(R.id.eteltipusalEtterem);


        etelNev.setText(etelLista.get(position).getNev());
        etelTipus.setText(etelLista.get(position).getTípus());
        etelAr.setText((etelLista.get(position).getAr()));
        etelMennyiseg.setText((etelLista.get(position).getMennyiseg()).toString());
        etelEtterem.setText(etelLista.get(position).getEtterem());
        return rowView;
    }
}

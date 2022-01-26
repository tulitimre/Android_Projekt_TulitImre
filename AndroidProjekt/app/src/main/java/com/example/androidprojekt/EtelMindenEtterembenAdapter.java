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

public class EtelMindenEtterembenAdapter extends ArrayAdapter {

    private List<Etelek> etelLista;
    private Activity context;

    public EtelMindenEtterembenAdapter(Activity context, List<Etelek> etelLista) {
        super(context, R.layout.etel_minden_etteremben_list_row, etelLista);

        this.context = context;
        this.etelLista = etelLista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.etel_minden_etteremben_list_row, null, true);

        TextView etelNev = rowView.findViewById(R.id.etelmNev);
        TextView etelTipus = rowView.findViewById(R.id.etelmTipus);
        TextView etelAr = rowView.findViewById(R.id.etelmAr);
        TextView etelMennyiseg = rowView.findViewById(R.id.etelmMennyiseg);
        TextView etelEtterem = rowView.findViewById(R.id.etelmCim);
        TextView etelLegjobArMennyiseg = rowView.findViewById(R.id.etelmLegjobbArMennyiseg);
        TextView etelmLartw = rowView.findViewById(R.id.etelmLartw);


        etelNev.setText(etelLista.get(position).getNev());
        etelTipus.setText(etelLista.get(position).getTípus());
        etelAr.setText((etelLista.get(position).getAr()));
        etelMennyiseg.setText((etelLista.get(position).getMennyiseg()).toString());
        etelEtterem.setText(etelLista.get(position).getEtterem());
        if (etelLista.get(position).getLegjobb_ar_mennyiseg() != null) {
            etelLegjobArMennyiseg.setText((etelLista.get(position).getLegjobb_ar_mennyiseg()).toString());
        } else {
            etelLegjobArMennyiseg.setVisibility(View.INVISIBLE);
            etelmLartw.setVisibility(View.INVISIBLE);
        }
        return rowView;
    }
}

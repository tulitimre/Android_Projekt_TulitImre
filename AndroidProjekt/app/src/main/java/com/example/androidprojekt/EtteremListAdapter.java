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

public class EtteremListAdapter extends ArrayAdapter {

    private List<Ettermek> etteremLista;
    private Activity context;

    public EtteremListAdapter(Activity context, List<Ettermek> etteremLista) {
        super(context, R.layout.ettermeklistview_row, etteremLista);
        this.context = context;
        this.etteremLista = etteremLista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.ettermeklistview_row, null, true);

        TextView nevtw = rowView.findViewById(R.id.etteremNevtw);
        TextView ertekelestw = rowView.findViewById(R.id.etteremErtekelestw);
        TextView ertekelesdbtw = rowView.findViewById(R.id.etteremErtekelesDbtw);
        TextView hazhozszallittw = rowView.findViewById(R.id.etteremHazhozszallittw);
        TextView szalArtw = rowView.findViewById(R.id.etteremSzalArtw);
        TextView minimumRendelestw = rowView.findViewById(R.id.etteremMinimumRendelestw);
        TextView cimtw = rowView.findViewById(R.id.etteremCimtw);
        TextView telefonszamtw = rowView.findViewById(R.id.etteremtelefonszamtw);

        nevtw.setText(etteremLista.get(position).getNev());
        ertekelestw.setText(etteremLista.get(position).getErtekeles());
        ertekelesdbtw.setText((etteremLista.get(position).getErtekelesDb()).toString());
        hazhozszallittw.setText((etteremLista.get(position).getHazhozszallit()).toString());
        szalArtw.setText(etteremLista.get(position).getSzalAr());
        minimumRendelestw.setText((etteremLista.get(position).getMinimumRendeles()).toString());
        cimtw.setText(etteremLista.get(position).getCim());
        telefonszamtw.setText(etteremLista.get(position).getTelefonszam());
        return rowView;
    }
}

package com.example.androidprojekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.List;

public class EttermekActvity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ettermeklistview);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int colorCodeDark = Color.parseColor("#8f1609");
        window.setStatusBarColor(colorCodeDark);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6b1006")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Éttermek");

        Intent i = getIntent();
        List<Ettermek> ettermek = (List<Ettermek>) i.getSerializableExtra("LIST");

        list = findViewById(R.id.ettermekList);
        EtteremListAdapter theAdapter = new EtteremListAdapter(this, ettermek);
        list.setAdapter(theAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
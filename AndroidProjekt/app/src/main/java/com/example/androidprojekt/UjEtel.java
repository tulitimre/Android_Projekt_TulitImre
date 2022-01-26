package com.example.androidprojekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UjEtel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uj_etel);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int colorCodeDark = Color.parseColor("#8f1609");
        window.setStatusBarColor(colorCodeDark);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6b1006")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Új étel");

        EditText ujEtelEtterem = findViewById(R.id.ujEtelEtterem);
        EditText ujEtelEteltipus = findViewById(R.id.ujEtelEteltipus);
        EditText ujEtelEtelnev = findViewById(R.id.ujEtelEtelnev);
        EditText ujEtelAr = findViewById(R.id.ujEtelAr);
        EditText ujEtelMennyiseg = findViewById(R.id.ujEtelMennyiseg);

        TextView ujEtelEredmeny = findViewById(R.id.ujEtelEredmeny);
        Button ujEtelsbmbtn = findViewById(R.id.ujEtelsbmbtn);
        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        ujEtelsbmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Etelek> call = service.ujEtel(ujEtelEtterem.getText().toString(), ujEtelEteltipus.getText().toString(), ujEtelEtelnev.getText().toString(), ujEtelAr.getText().toString(), ujEtelMennyiseg.getText().toString());
                call.enqueue(new Callback<Etelek>() {
                    @Override
                    public void onResponse(Call<Etelek> call, Response<Etelek> response) {
                        Etelek etel = response.body();
                        if (ujEtelEtterem.getText().toString().equals("") || ujEtelEteltipus.getText().toString().equals("")
                                || ujEtelEtelnev.getText().toString().equals("") || ujEtelAr.getText().toString().equals("") || ujEtelMennyiseg.getText().toString().equals("")) {
                            Toast.makeText(UjEtel.this, "Töltsön ki minden mezőt", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                if (etel.getEredmény() == false) {
                                    Toast.makeText(UjEtel.this, etel.getÜzenet(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                try {
                                    ujEtelEtterem.setText("");
                                    ujEtelEteltipus.setText("");
                                    ujEtelEtelnev.setText("");
                                    ujEtelAr.setText("");
                                    ujEtelMennyiseg.setText("");
                                    ujEtelEredmeny.setText("");
                                    ujEtelEredmeny.setText("Nev: " + etel.getNev() + "\nÉtterem: " + etel.getEtterem() + "\nTípus: " + etel.getTipus() + "\nÁr: " + etel.getAr() + "\nMennyiseg: " + etel.getMennyiseg());
                                    Log.d("info", etel.toString());
                                } catch (Exception exception) {
                                    Toast.makeText(UjEtel.this, "Nem megfelelő adatok", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Etelek> call, Throwable t) {
                        Toast.makeText(UjEtel.this, "Nem megfelelő adatok", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
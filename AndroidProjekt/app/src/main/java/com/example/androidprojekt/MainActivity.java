package com.example.androidprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int colorCodeDark = Color.parseColor("#8f1609");
        window.setStatusBarColor(colorCodeDark);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6b1006")));
        getSupportActionBar().setTitle("Főoldal");

        Button ettermekbtn = findViewById(R.id.etteremekbtn);
        Button eteltipusalbtn = findViewById(R.id.eteltipusalbtn);
        Button etelmEtterembenbtn = findViewById(R.id.etelmindenetterembenbtn);
        Button eteltipusEtterembenBtn = findViewById(R.id.eteltipusEtterembenBtn);
        Button ujEtelBtn = findViewById(R.id.ujEtelBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
        Button etelModositBtn = findViewById(R.id.etelModositBtn);


        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        ettermekbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("info", "");
                Call<List<Ettermek>> call = service.getEttermek();
                call.enqueue(new Callback<List<Ettermek>>() {
                    @Override
                    public void onResponse(Call<List<Ettermek>> call, Response<List<Ettermek>> response) {
                        List<Ettermek> ettermek = response.body();
                        Intent i = new Intent(getBaseContext(), EttermekActvity.class);
                        i.putExtra("LIST", (Serializable) ettermek);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<List<Ettermek>> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "Éttermek megjelenítése sikertelen", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        eteltipusalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), EtelTipusAlapjan.class);
                startActivity(i);
            }
        });

        etelmEtterembenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), EtelMindenEtteremben.class);
                startActivity(i);
            }
        });

        eteltipusEtterembenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), EteltipusEtteremben.class);
                startActivity(i);
            }
        });

        ujEtelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), UjEtel.class);
                startActivity(i);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), EtelTorles.class);
                startActivity(i);
            }
        });

        etelModositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), EtelModosit.class);
                startActivity(i);
            }
        });
    }
}
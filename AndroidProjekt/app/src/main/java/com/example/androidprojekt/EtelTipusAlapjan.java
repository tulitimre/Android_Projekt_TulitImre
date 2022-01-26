package com.example.androidprojekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EtelTipusAlapjan extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etel_tipus_alapjan);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int colorCodeDark = Color.parseColor("#8f1609");
        window.setStatusBarColor(colorCodeDark);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6b1006")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Étel Típus alapján");

        Intent i = getIntent();

        Button submit = findViewById(R.id.aETASbmBtn);
        EditText tipius = findViewById(R.id.aETATipusEt);
        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("info", "eteltipus btn click");
                if (tipius.getText().toString().equals("")) {
                    Toast.makeText(EtelTipusAlapjan.this, "Töltsön ki minden mezőt!", Toast.LENGTH_SHORT).show();
                } else {
                    Call<List<Etelek>> call = service.getEtelek(tipius.getText().toString());
                    call.enqueue(new Callback<List<Etelek>>() {
                        @Override
                        public void onResponse(Call<List<Etelek>> call, Response<List<Etelek>> response) {
                            List<Etelek> etelek = response.body();
                            Boolean joTipus = true;

                            if (!(tipius.getText().toString().toUpperCase().equals("LEVES") || tipius.getText().toString().toUpperCase().equals("PIZZA") || tipius.getText().toString().toUpperCase().equals("SZENDVICS")
                                    || tipius.getText().toString().toUpperCase().equals("FŐÉTEL") || tipius.getText().toString().toUpperCase().equals("SALÁTA"))) {
                                joTipus = false;
                            }

                            if (joTipus) {
                                list = findViewById(R.id.etelTipusalLista);
                                EtelListAdapter theAdapter = new EtelListAdapter(EtelTipusAlapjan.this, etelek);
                                list.setAdapter(theAdapter);
                            } else {
                                Toast.makeText(EtelTipusAlapjan.this, "Nem megfelelő ételtípus", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<Etelek>> call, Throwable t) {
                            Toast.makeText(EtelTipusAlapjan.this, "Nem megfelelő ételtípus", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


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
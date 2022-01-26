package com.example.androidprojekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

public class EtelMindenEtteremben extends AppCompatActivity {


    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etel_minden_etteremben);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int colorCodeDark = Color.parseColor("#8f1609");
        window.setStatusBarColor(colorCodeDark);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6b1006")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Étel minden étteremben");

        EditText etelNeve = findViewById(R.id.etelmEtterembenet);
        Button etelmetterembenbtn = findViewById(R.id.etelmEtterembenBtn);
        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        etelmetterembenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etelNeve.getText().toString().equals("")) {
                    Toast.makeText(EtelMindenEtteremben.this, "Töltsön ki minden mezőt", Toast.LENGTH_SHORT).show();
                } else {
                    Call<List<Etelek>> call = service.getEtelMindenEtteremben(etelNeve.getText().toString());

                    call.enqueue(new Callback<List<Etelek>>() {
                        @Override
                        public void onResponse(Call<List<Etelek>> call, Response<List<Etelek>> response) {
                            List<Etelek> etelek = response.body();

                            list = findViewById(R.id.etelmetterembenList);
                            EtelMindenEtterembenAdapter theAdapter = new EtelMindenEtterembenAdapter(EtelMindenEtteremben.this, etelek);
                            list.setAdapter(theAdapter);
                        }

                        @Override
                        public void onFailure(Call<List<Etelek>> call, Throwable t) {
                            Toast.makeText(EtelMindenEtteremben.this, "Nincs Ilyen étel", Toast.LENGTH_SHORT).show();
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
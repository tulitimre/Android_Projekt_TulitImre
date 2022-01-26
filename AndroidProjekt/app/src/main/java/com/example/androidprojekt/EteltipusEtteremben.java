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
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EteltipusEtteremben extends AppCompatActivity {


    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eteltipus_etteremben);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int colorCodeDark = Color.parseColor("#8f1609");
        window.setStatusBarColor(colorCodeDark);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6b1006")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ételtípus étteremben");

        EditText eteltipusEtterembenTipusEt = findViewById(R.id.eteltipusEtterembenTipusEt);
        EditText eteltipusEtterembenEtteremEt = findViewById(R.id.eteltipusEtterembenEtteremEt);
        Button eteltipusEtterembensbmBtn = findViewById(R.id.eteltipusEtterembensbmBtn);
        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        eteltipusEtterembensbmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("info", "eteltipus btn click");
                Call<List<Etelek>> call = service.getEteltipusEtteremben(eteltipusEtterembenEtteremEt.getText().toString(), eteltipusEtterembenTipusEt.getText().toString());
                call.enqueue(new Callback<List<Etelek>>() {
                    @Override
                    public void onResponse(Call<List<Etelek>> call, Response<List<Etelek>> response) {

                        List<Etelek> etelek = response.body();

                        if (eteltipusEtterembenEtteremEt.getText().toString().equals("") || eteltipusEtterembenTipusEt.getText().toString().equals("")) {
                            Toast.makeText(EteltipusEtteremben.this, "Töltsön ki minden mezőt", Toast.LENGTH_SHORT).show();
                        } else {
                            list = findViewById(R.id.eteltipusEtterembenList);
                            EteltipusEtterembenAdapter theAdapter = new EteltipusEtterembenAdapter(EteltipusEtteremben.this, etelek);
                            list.setAdapter(theAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Etelek>> call, Throwable t) {
                        Toast.makeText(EteltipusEtteremben.this, "Nem megfelelő adatok", Toast.LENGTH_SHORT).show();
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
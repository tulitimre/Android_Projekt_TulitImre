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
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EtelModosit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etel_modosit);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int colorCodeDark = Color.parseColor("#8f1609");
        window.setStatusBarColor(colorCodeDark);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6b1006")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Étel módosítása");

        EditText modositId = findViewById(R.id.modositId);
        EditText modositNev = findViewById(R.id.modositNev);
        EditText modositEteltipusId = findViewById(R.id.modositEteltipusId);
        EditText modositEtteremId = findViewById(R.id.modositEtteremId);
        EditText modositAr = findViewById(R.id.modositAr);
        EditText modositMennyiseg = findViewById(R.id.modositMennyiseg);
        Button modositBtn = findViewById(R.id.modositBtn);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        modositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (modositId.getText().toString().equals("") || modositNev.getText().toString().equals("") || modositEteltipusId.getText().toString().equals("")
                        || modositEtteremId.getText().toString().equals("") || modositAr.getText().toString().equals("") || modositMennyiseg.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Töltsön ki minden mezőt", Toast.LENGTH_SHORT).show();
                } else {
                    Call<Etelek> call = service.etelModosit(Integer.parseInt(modositId.getText().toString()),
                            new Etelek(modositNev.getText().toString(), modositAr.getText().toString(), Integer.parseInt(modositMennyiseg.getText().toString()),
                                    Integer.parseInt(modositEteltipusId.getText().toString()), Integer.parseInt(modositEtteremId.getText().toString())));
                    call.enqueue(new Callback<Etelek>() {
                        @Override
                        public void onResponse(Call<Etelek> call, Response<Etelek> response) {
                            Etelek etel = response.body();
                            if (etel == null) {
                                Toast.makeText(getApplicationContext(), "Étel modosítása sikertelen", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Étel sikeresen modosítva", Toast.LENGTH_SHORT).show();
                                modositId.setText("");
                                modositNev.setText("");
                                modositEteltipusId.setText("");
                                modositEtteremId.setText("");
                                modositAr.setText("");
                                modositMennyiseg.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<Etelek> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Étel modosítása sikertelen", Toast.LENGTH_SHORT).show();
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
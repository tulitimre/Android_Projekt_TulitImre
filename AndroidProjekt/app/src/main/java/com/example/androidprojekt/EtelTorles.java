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

public class EtelTorles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etel_torles);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int colorCodeDark = Color.parseColor("#8f1609");
        window.setStatusBarColor(colorCodeDark);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6b1006")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Étel törlése");

        EditText etelId = findViewById(R.id.eteltorlesEteld);
        Button eteldelBtn = findViewById(R.id.eteltorlesDeleteBtn);
        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        eteldelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etelId.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Adja meg az Id-t", Toast.LENGTH_SHORT).show();
                } else {
                    Call<Etelek> call = service.etelTorol(Integer.parseInt(etelId.getText().toString()));

                    call.enqueue(new Callback<Etelek>() {
                        @Override
                        public void onResponse(Call<Etelek> call, Response<Etelek> response) {
                            Etelek etel = response.body();
                            if (etel == null) {
                                Toast.makeText(getApplicationContext(), "Étel törlése sikertelen", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Étel sikeresen törölve", Toast.LENGTH_SHORT).show();
                                etelId.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<Etelek> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Étel törlése sikertelen", Toast.LENGTH_SHORT).show();
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
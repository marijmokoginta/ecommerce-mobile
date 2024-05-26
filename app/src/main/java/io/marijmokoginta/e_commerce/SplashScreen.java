package io.marijmokoginta.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import io.marijmokoginta.e_commerce.model.User;

public class SplashScreen extends AppCompatActivity {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ProgressBar loading = findViewById(R.id.loading);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userId = mSettings.getString("userId", "MISSING");

        new Handler().postDelayed(() -> {
            if (!userId.equals("MISSING")) {
                db.collection("users").whereEqualTo("id", userId).get().addOnSuccessListener(documentSnapshots -> {
                    for (QueryDocumentSnapshot snapshot : documentSnapshots) {
                        User user = snapshot.toObject(User.class);
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        intent.putExtra("nama_lengkap", user.getNama());
                        intent.putExtra("nim", user.getNim());
                        intent.putExtra("jenis_kelamin", user.getJenisKelamin());
                        startActivity(intent);
                        finish();
                    }
                });
            } else {
                loading.setVisibility(View.GONE);
                btnLogin.setVisibility(View.VISIBLE);
                btnRegister.setVisibility(View.VISIBLE);

                btnLogin.setOnClickListener(v -> {
                    Intent in = new Intent(getApplicationContext(), MainActivity.class);
                    in.putExtra("logType", "login");
                    startActivity(in);
                    finish();
                });
                btnRegister.setOnClickListener(v -> {
                    Intent in = new Intent(getApplicationContext(), MainActivity.class);
                    in.putExtra("logType", "register");
                    startActivity(in);
                    finish();
                });
            }
        }, 2000);
    }
}
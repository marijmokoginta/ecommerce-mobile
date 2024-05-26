package io.marijmokoginta.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Calendar;
import java.util.Date;

import io.marijmokoginta.e_commerce.model.User;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView welcomeText, textTime, nlError, nmError;
    EditText namaLengkap, nim;
    Button btnSumbit;

    private final String[] jenisKelamin = {"laki-laki", "perempuan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getData = getIntent();
        String logType = getData.getStringExtra("logType");

        Spinner spjenisKel = findViewById(R.id.spn_jenis_kel);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, jenisKelamin);

        spjenisKel.setAdapter(adapter);

        initView();
        appHeader();

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "SetTextI18n"})
            @Override
            public void onClick(View view) {
                String nama, nm, jk;
                nama = namaLengkap.getText().toString();
                nm = nim.getText().toString();
                jk = spjenisKel.getSelectedItem().toString();

                if (nama.length() == 0) {
                    nlError.setText("please fill this field");
                    // namaLengkap.setBackgroundTintList(ColorStateList.valueOf(R.color.red));
                } else if (nm.length() == 0) {
                    nlError.setText("");
                    nmError.setText("please fill this field");
                    // nim.setBackgroundTintList(ColorStateList.valueOf(R.color.red));
                } else {
                    nmError.setText("");

                    User user = new User();
                    user.setNama(nama);
                    user.setNim(nm);
                    user.setJenisKelamin(jk);

                    if (logType.equals("register")) {
                        db.collection("users").add(user).addOnSuccessListener(documentReference -> {
                            Toast.makeText(getApplicationContext(), "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

                            intent.putExtra("nama_lengkap", nama);
                            intent.putExtra("nim", nm);
                            intent.putExtra("jenis_kelamin", jk);
                            startActivity(intent);
                        }).addOnFailureListener(e -> {
                            Toast.makeText(getApplicationContext(), "Gagal Mendaftar", Toast.LENGTH_SHORT).show();
                        });
                    } else if (logType.equals("login")) {
                        db.collection("users").get().addOnSuccessListener(documentSnapshots -> {
                            boolean isExist = false;
                            for (QueryDocumentSnapshot snapshot : documentSnapshots) {
                                User user1 = snapshot.toObject(User.class);
                                if (nama.equals(user1.getNama()) && nm.equals(user1.getNim())) {
                                    SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor = mSettings.edit();
                                    editor.putString("userId", user.getId());
                                    editor.apply();

                                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

                                    intent.putExtra("nama_lengkap", user1.getNama());
                                    intent.putExtra("nim", user1.getNim());
                                    intent.putExtra("jenis_kelamin", user1.getJenisKelamin());
                                    startActivity(intent);

                                    isExist = true;
                                }
                            }

                            if (!isExist) {
                                Toast.makeText(MainActivity.this, "nama/nim salah", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(e -> {
                            Toast.makeText(getApplicationContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void initView() {
        welcomeText = findViewById(R.id.welcome_text);
        textTime = findViewById(R.id.text_time);
        btnSumbit = findViewById(R.id.btn_submit);
        namaLengkap = findViewById(R.id.et_nama_lengkap);
        nim = findViewById(R.id.et_nim);

        nlError = findViewById(R.id.nama_lengkap_error);
        nmError = findViewById(R.id.nim_error);
    }

    @SuppressLint("SetTextI18n")
    private void appHeader() {
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String greet = "Good";

        if (hour >= 1 && hour <= 11) {
            greet += " Morning";
        } else if (hour > 11 && hour < 15) {
            greet += " Afternoon";
        } else if (hour >= 15 && hour < 19) {
            greet += " Evening";
        } else {
            greet += " Night";
        }

        greet += ", Chief";

        welcomeText.setText(greet);
        textTime.setText(String.valueOf(hour) + "." + String.valueOf(minute));
    }
}
package io.marijmokoginta.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import io.marijmokoginta.e_commerce.adapter.CustomAdapter;
import io.marijmokoginta.e_commerce.model.Product;
import io.marijmokoginta.e_commerce.service.ProductService;

public class MenuActivity extends AppCompatActivity {

    ProductService productService;

    ListView listProduct;
    ImageButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        productService = new ProductService();

        Intent getData = getIntent();

        listProduct = findViewById(R.id.list_product);
        btnLogout = findViewById(R.id.btnLogout);

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), productService.getProducts());
        listProduct.setAdapter(adapter);

        listProduct.setOnItemClickListener((adapterView, view, index, l) -> {
            Product product = (Product) adapterView.getItemAtPosition(index);

            Intent intent = new Intent(getApplicationContext(), OrderActivity.class);

            // put user data
            intent.putExtra("nama_lengkap", getData.getStringExtra("nama_lengkap"));
            intent.putExtra("nim", getData.getStringExtra("nim"));
            intent.putExtra("jenis_kelamin", getData.getStringExtra("jenis_kelamin"));

            // put product data
            intent.putExtra("product", product);

            startActivity(intent);
        });

        listProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });

        btnLogout.setOnClickListener(v -> {
            SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = mSettings.edit();
            editor.clear().apply();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
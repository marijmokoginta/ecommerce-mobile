package io.marijmokoginta.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.marijmokoginta.e_commerce.fragment.DetailProduct;
import io.marijmokoginta.e_commerce.fragment.OrderResult;
import io.marijmokoginta.e_commerce.model.Product;

public class OrderActivity extends AppCompatActivity {

    Button btnBayar;
    TextView headerText;

    Product product;

    String nama, nim, jenisKelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent getData = getIntent();

        // passing data from menu activity
        product = getData.getParcelableExtra("product");
        nama = getData.getStringExtra("nama_lengkap");
        nim = getData.getStringExtra("nim");
        jenisKelamin = getData.getStringExtra("jenis_kelamin");

        Bundle bundle = new Bundle();

        bundle.putString("nama_lengkap", nama);
        bundle.putString("nim", nim);
        bundle.putString("jenis_kelamin", jenisKelamin);
        bundle.putParcelable("product", product);

        DetailProduct detailProduct = new DetailProduct();
        detailProduct.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.order_layout, detailProduct).commit();

        btnBayar = findViewById(R.id.btn_bayar);

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                // set result fragment
                OrderResult orderResult = new OrderResult();
                getSupportFragmentManager().beginTransaction().replace(R.id.order_layout, orderResult).commit();

                headerText = findViewById(R.id.detail_pesanan);
                headerText.setText("Result");

                // goto home button
                btnBayar.setText("Return Home");
                btnBayar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}
package io.marijmokoginta.e_commerce.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.marijmokoginta.e_commerce.R;
import io.marijmokoginta.e_commerce.model.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailProduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailProduct extends Fragment {

    Product product;
    TextView namaLengkap, nim, jenisKelamin, namaProduct, deskProduct, priceProduct;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailProduct() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailProduct.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailProduct newInstance(String param1, String param2) {
        DetailProduct fragment = new DetailProduct();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_product, container, false);

        // user init view
        namaLengkap = view.findViewById(R.id.nama_pemesan);
        nim = view.findViewById(R.id.nim_pemesan);
        jenisKelamin = view.findViewById(R.id.jk_pemesan);

        // product init view
        namaProduct = view.findViewById(R.id.nama_product);
        deskProduct = view.findViewById(R.id.deskripsi_produk);
        priceProduct = view.findViewById(R.id.total_pembayaran);

        assert getArguments() != null;
        product = getArguments().getParcelable("product");

        namaLengkap.setText("Nama : " + getArguments().getString("nama_lengkap"));
        nim.setText("Nim : " + getArguments().getString("nim"));
        jenisKelamin.setText("Jenis Kelamin : " + getArguments().getString("jenis_kelamin"));

        namaProduct.setText(product.getName());
        deskProduct.setText("Deskripsi : \n" + product.getDescription());
        priceProduct.setText("Total Pembayaran : Rp." + String.valueOf(product.getPrice()));

        return view;
    }
}
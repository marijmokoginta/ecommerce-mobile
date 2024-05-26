package io.marijmokoginta.e_commerce.service;

import java.util.ArrayList;

import io.marijmokoginta.e_commerce.model.Product;

public class ProductService {

    private final ArrayList<Product> products;

    public ProductService () {
        products = new ArrayList<>();

        products.add(new Product("Aviator mouse Wireless", "new original product", 99000.00));
        products.add(new Product("Blue Botton Ankle Pants", "new original product", 179000.00));
        products.add(new Product("T-Shirt 24s black", "new original product", 89000.00));
        products.add(new Product("Firegoods Kaos - Ringer", "new original product", 39000.00));
        products.add(new Product("Larocking - Nexus Hijau", "new original product", 99000.00));
        products.add(new Product("Aukey TWS EP-T25 High Fidelity Audio", "new original product", 289000.00));
        products.add(new Product("Tas Ransel Bodypack", "new original product", 709000.00));
        products.add(new Product("Les Catino Micronesia", "new original product", 359000.00));
        products.add(new Product("Levi's LMC 502 Half Dome", "new original product", 1324000.00));
        products.add(new Product("Jaket Pria Harington", "new original product", 99000.00));
        products.add(new Product("Spray Perawatan jaket", "new original product", 29000.00));
        products.add(new Product("Air Force 1 Low White", "new original product", 239000.00));
        products.add(new Product("Kemeja Pria Slim fit", "new original product", 76000.00));
        products.add(new Product("Kaos Polos New States", "new original product", 42000.00));
        products.add(new Product("Donson Hoodie Bo Pain Black", "new original product", 35000.00));
        products.add(new Product("Anti Jamur, Pewangi, Pelembut", "new original product", 27000.00));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct (Product product) {
        products.add(product);
    }

    public void deleteProduct (int position) {
        products.remove(position);
    }
}

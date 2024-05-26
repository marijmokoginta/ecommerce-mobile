package io.marijmokoginta.e_commerce.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private long productId;
    private String name;
    private String description;
    private double price;

    public Product (String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    protected Product(Parcel in) {
        productId = in.readLong();
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(productId);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeDouble(price);
    }
}

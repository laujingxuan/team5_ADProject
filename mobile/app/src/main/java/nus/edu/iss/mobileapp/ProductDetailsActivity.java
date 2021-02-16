package nus.edu.iss.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import nus.edu.iss.mobileapp.model.Product;
import nus.edu.iss.mobileapp.nonEntityModel.ProductType;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Product product;
    private double lat;
    private double longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("Product");
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.Description1);
        Button map = findViewById(R.id.map);
        if (map != null)
            map.setOnClickListener(this);

        if (product.getType().equals(ProductType.ATTRACTION)){
            lat = product.getAttraction().getLat();
            longi = product.getAttraction().getLongi();
            name.setText(product.getAttraction().getName());
            String text = "";
            text += "Description        : " + product.getAttraction().getDescription()+ "\n\n";
            text += "Rating               : " + product.getAttraction().getRating() + "\n\n";
            text += "Address            : " + product.getAttraction().getLocation() + "\n\n";
            description.setText(text);
            setImage(product.getAttraction().getImageURL());
        }else{
            lat = product.getRoomType().getHotel().getLat();
            longi = product.getRoomType().getHotel().getLongi();
            name.setText(product.getRoomType().getHotel().getName() + ": " + product.getRoomType().getRoomType() + " Room");
            String text = "";
            text += "Description        : " + product.getRoomType().getDescription()+ "\n\n";
            text += "Hotel Rating       : " + product.getRoomType().getHotel().getRating() + "\n\n";
            text += "Address              : " + product.getRoomType().getHotel().getLocation() + "\n\n";
            text += "Hotel Amenities : " + product.getRoomType().getHotel().getEmenities() + "\n\n";
            description.setText(text);
            setImage(product.getRoomType().getImageURL());
        }
    }

    public void setImage(String urlString){
        ImageView imageView = findViewById(R.id.imageView2);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(urlString);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Bitmap bmp = null;
                try {
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap finalBmp = bmp;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(finalBmp);
                    }
                });
            }
        });
        thread.start();
    }

    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse("geo:" + lat + "," + longi);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}
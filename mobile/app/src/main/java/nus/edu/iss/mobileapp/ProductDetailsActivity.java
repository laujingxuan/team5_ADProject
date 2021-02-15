package nus.edu.iss.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import nus.edu.iss.mobileapp.nonEntityModel.Product;
import nus.edu.iss.mobileapp.nonEntityModel.ProductType;

public class ProductDetailsActivity extends AppCompatActivity {

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("Product");
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.Description1);
//        TextView ratingLeft = findViewById(R.id.Rating1);
//        TextView rating = findViewById(R.id.Rating2);
//        TextView address = findViewById(R.id.Address2);
//        TextView amenities1 = findViewById(R.id.Amenities1);
//        TextView amenities2 = findViewById(R.id.Amenities2);

        if (product.getType().equals(ProductType.ATTRACTION)){
            name.setText(product.getAttraction().getName());
            String text = "";
            text += "Description:         " + product.getAttraction().getDescription()+ "\n\n";
            text += "Rating:                " + product.getAttraction().getRating() + "\n\n";
            text += "Address:             " + product.getAttraction().getLocation() + "\n\n";
            description.setText(text);
//            description.setText(product.getAttraction().getDescription());
//            rating.setText(String.valueOf(product.getAttraction().getRating()));
//            address.setText(product.getAttraction().getLocation());
            setImage(product.getAttraction().getImageURL());
        }else{
            name.setText(product.getRoomType().getHotel().getName() + ": " + product.getRoomType().getRoomType() + " Room");

            String text = "";
            text += "Description:       " + product.getRoomType().getDescription()+ "\n\n";
            text += "Hotel Rating:      " + product.getRoomType().getHotel().getRating() + "\n\n";
            text += "Address:             " + product.getRoomType().getHotel().getLocation() + "\n\n";
            text += "Amenities:           " + product.getRoomType().getHotel().getEmenities() + "\n\n";
            description.setText(text);
//            rating.setText(String.valueOf(product.getRoomType().getHotel().getRating()));
//            ratingLeft.setText("Hotel Rating:");
//            address.setText(product.getRoomType().getHotel().getLocation());
//            amenities2.setVisibility(View.VISIBLE);
//            amenities1.setVisibility(View.VISIBLE);
//            amenities2.setText(product.getRoomType().getHotel().getEmenities());
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
}
package nus.edu.iss.mobileapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import nus.edu.iss.mobileapp.model.Product;
import nus.edu.iss.mobileapp.nonEntityModel.ProductType;

public class MyCustomAdapter extends ArrayAdapter {
    private final Context context;
    private Activity activity;
    protected List<Product> products;

    public MyCustomAdapter(Context context, int resId) {
        super(context, resId);
        this.context = context;
        activity = (Activity) context;
    }

    public void setData(List<Product> products) {
        this.products = products;

        for (int i=0; i<products.size(); i++) {
            add(null);
        }
    }

    @androidx.annotation.NonNull
    public View getView(int pos, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);

            // if we are not responsible for adding the view to the parent,
            // then attachToRoot should be 'false' (which is in our case)
            view = inflater.inflate(R.layout.row, parent, false);
        }

        // set the image for ImageView
        ImageView imageView = view.findViewById(R.id.imageView);
        // set the text for TextView
        TextView textView = view.findViewById(R.id.textView);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    if (products.get(pos).getType().equals(ProductType.ATTRACTION)) {
                        url = new URL(products.get(pos).getAttraction().getImageURL());
                    } else {
                        //roomType url, might need to change to hotel url later
                        url = new URL(products.get(pos).getRoomType().getImageURL());
                    }
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
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (products.get(pos).getType().equals(ProductType.ATTRACTION)) {
                            textView.setText(products.get(pos).getAttraction().getName());
                        }else{
                            textView.setText(products.get(pos).getRoomType().getHotel().getName() + "\n" + products.get(pos).getRoomType().getRoomType() + " Room");
                        }
                        imageView.setImageBitmap(finalBmp);
                    }
                });
            }
        });
        thread.start();
        return view;
    }
}
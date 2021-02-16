package nus.edu.iss.mobileapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import nus.edu.iss.mobileapp.model.Booking;
import nus.edu.iss.mobileapp.model.BookingDetails;

public class BookingHistoryAdapter extends ArrayAdapter {
    private final Context context;
    private Activity activity;
    protected List<Booking> bookings;

    public BookingHistoryAdapter(Context context, int resId) {
        super(context, resId);
        this.context = context;
        activity = (Activity) context;
    }

    public void setData(List<Booking> bookings) {
        this.bookings = bookings;

        for (int i=0; i<bookings.size(); i++) {
            add(null);
        }
    }

    @androidx.annotation.NonNull
    public View getView(int pos, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.bookings_row, parent, false);
        }

        // set the image for ImageView
        TextView date = view.findViewById(R.id.date);
        // set the text for TextView
        TextView info = view.findViewById(R.id.bookingInfo);
        date.setText("Booking Date: " + bookings.get(pos).getBookingDate());

        double total = 0;
        for (BookingDetails b: bookings.get(pos).getBookingDetails()){
            total += b.getPrice();
        }

        String infos = "";
        infos += "  Amount Paid(S$)              : " + total + "\n";
        infos += "  Travel Package Discount : " + bookings.get(pos).getTravelPackageDiscount() + "\n";
        infos += "  Number of items               : " + bookings.get(pos).getBookingDetails().size() + "\n";
        info.setText(infos);

        return view;
    }
}

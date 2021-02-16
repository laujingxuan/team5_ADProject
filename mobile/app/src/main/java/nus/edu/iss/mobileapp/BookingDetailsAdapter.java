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
import java.util.Map;

import nus.edu.iss.mobileapp.model.Booking;
import nus.edu.iss.mobileapp.model.BookingDetails;
import nus.edu.iss.mobileapp.nonEntityModel.AttractionBooking;
import nus.edu.iss.mobileapp.nonEntityModel.BookingWrapper;
import nus.edu.iss.mobileapp.nonEntityModel.HotelBooking;
import nus.edu.iss.mobileapp.nonEntityModel.ProductType;

public class BookingDetailsAdapter extends ArrayAdapter {
    private final Context context;
    private Activity activity;
    private Map<BookingDetails, BookingWrapper> bookingMap;

    public BookingDetailsAdapter(Context context, int resId) {
        super(context, resId);
        this.context = context;
        activity = (Activity) context;
    }

    public void setData(Map<BookingDetails, BookingWrapper> bookingMap) {
        this.bookingMap = bookingMap;

        for (int i=0; i<bookingMap.size(); i++) {
            add(null);
        }
    }

    @androidx.annotation.NonNull
    public View getView(int pos, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.booking_details_row, parent, false);
        }

        TextView name = view.findViewById(R.id.name);
        // set the text for TextView
        TextView details = view.findViewById(R.id.bookingDetails);
        BookingDetails bkDetails = (BookingDetails) bookingMap.keySet().toArray()[pos];
        if(bkDetails.getProduct().getType().equals(ProductType.ATTRACTION)){
            AttractionBooking attractBook = (AttractionBooking) bookingMap.get(bkDetails);
            name.setText(bkDetails.getProduct().getAttraction().getName());

            String infos = "";
            infos += "           Quantity             :     " + attractBook.getQuantity() + "\n";
            infos += "           Price (S$)           :     " + bkDetails.getPrice() + "\n";
            infos += "           Date                    :     " + attractBook.getDate() + "\n";
            details.setText(infos);
        }else{
            HotelBooking hotelBook = (HotelBooking) bookingMap.get(bkDetails);
            name.setText(bkDetails.getProduct().getRoomType().getHotel().getName() + "\n" + bkDetails.getProduct().getRoomType().getRoomType() + " Room");

            String infos = "";
            infos += "           Number of rooms        :     " + hotelBook.getNumRooms() + "\n";
            infos += "           Price (S$)                     :     " + bkDetails.getPrice() + "\n";
            infos += "           Start Date                     :     " + hotelBook.getStartDate() + "\n";
            infos += "           End Date                       :     " + hotelBook.getEndDate() + "\n";
            infos += "           Special Request           :     " + hotelBook.getRemarks() + "\n";
            details.setText(infos);
        }
        return view;
    }

}

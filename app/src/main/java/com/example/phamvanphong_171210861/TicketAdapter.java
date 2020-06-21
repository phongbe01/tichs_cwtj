package com.example.phamvanphong_171210861;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TicketAdapter extends BaseAdapter {

    private ArrayList<Ticket> tickets;
    private Activity context;

//    private RestaurantFilter filter;
//    private ArrayList<Restaurant> filterlist;


    public TicketAdapter(ArrayList<Ticket> tickets, Activity context) {
        this.tickets = tickets;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public Object getItem(int position) {
        return tickets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.single_row, null);
        }
        Ticket ticket  = (Ticket) getItem(position);
        if ((ticket != null))
        {
            TextView textAddr = (TextView) view.findViewById(R.id.txtAddr);
            TextView textPrice = (TextView) view.findViewById(R.id.txtPrice);
            TextView textType = (TextView) view.findViewById(R.id.txtType);

            String s = ticket.getGaDen() + "->" + ticket.getGaDi();
            textAddr.setText(s);
            textPrice.setText(ticket.getDonGia()+ "");
            String t = (ticket.isTheLoai() == 1)?"Khứ hồi":"Một chiều";
            textType.setText(t);
        }
        return view;
    }
}

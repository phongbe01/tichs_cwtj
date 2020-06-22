package com.example.phamvanphong_171210861;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class TicketAdapter extends BaseAdapter implements Filterable {

    private ArrayList<Ticket> tickets;
    private Activity context;

    private TicketFilter filter;
    private ArrayList<Ticket> filterlist;


    public TicketAdapter(ArrayList<Ticket> tickets, Activity context) {
        this.tickets = tickets;
        this.context = context;
        this.filterlist = tickets;
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

            String s = ticket.getGaDi() + "->" + ticket.getGaDen();
            textAddr.setText(s);
            textPrice.setText(ticket.getDonGia()+ "");
            String t = (ticket.isTheLoai() == 1)?"Khứ hồi":"Một chiều";
            textType.setText(t);
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
        {
            filter = new TicketFilter();
        }
        return filter;
    }

    public class TicketFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() >0)
            {
                constraint = constraint.toString().toUpperCase();
                ArrayList<Ticket> filters = new ArrayList<>();
                for (int i =0; i < filterlist.size(); i++)
                {
                    if (filterlist.get(i).getGaDi().toUpperCase().contains(constraint))
                    {
                        Ticket ticket = new Ticket(filterlist.get(i).getId(), filterlist.get(i).getGaDen(), filterlist.get(i).getGaDi(), filterlist.get(i).getDonGia(), filterlist.get(i).isTheLoai());
                        filters.add(ticket);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            } else
            {
                results.count = filterlist.size();
                results.values = filterlist;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            tickets = (ArrayList<Ticket>) results.values;
            notifyDataSetChanged();
        }
    }
}

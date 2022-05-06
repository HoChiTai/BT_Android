package com.example.btqtcau2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CustomCountryAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    ArrayList<Country> arrayCountry;

    ImageView flagCountry;

    public CustomCountryAdapter(Context myContext, int myLayout, ArrayList<Country> arrayCountry) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arrayCountry = arrayCountry;
    }

    @Override
    public int getCount() {
        return arrayCountry.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(myLayout, null);

        TextView nameCountry = (TextView) view.findViewById(R.id.countryName);
        nameCountry.setText(arrayCountry.get(i).getName());

        flagCountry = (ImageView) view.findViewById(R.id.countryFlag);

        Picasso.get()
                .load(arrayCountry.get(i).getFlag())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.error)
                .into(flagCountry);

        return view;
    }


}

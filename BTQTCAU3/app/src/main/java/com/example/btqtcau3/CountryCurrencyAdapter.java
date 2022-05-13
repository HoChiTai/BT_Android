package com.example.btqtcau3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountryCurrencyAdapter extends BaseAdapter {

    Context context;
    int myLayout;
    ArrayList<CountryCurrency> arrayList;

    public CountryCurrencyAdapter(Context context, int myLayout, ArrayList<CountryCurrency> arrayList) {
        this.context = context;
        this.myLayout = myLayout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(myLayout,null);

        ImageView countryFlag = (ImageView) view.findViewById(R.id.countryFlag);
        TextView nameCurrency = (TextView) view.findViewById(R.id.nameCurrency);
        TextView unitCurrency = (TextView) view.findViewById(R.id.unitCurrency);

        nameCurrency.setText(arrayList.get(i).getName());
        unitCurrency.setText(arrayList.get(i).getUnit());

        Picasso.get()
                .load(arrayList.get(i).getFlag())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.error)
                .into(countryFlag);

        return view;
    }
}

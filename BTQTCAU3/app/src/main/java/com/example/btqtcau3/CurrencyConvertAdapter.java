package com.example.btqtcau3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CurrencyConvertAdapter extends BaseAdapter {

    Context context;
    int myLayout;
    ArrayList<CurrencyConvert> arrayList;

    public CurrencyConvertAdapter(Context context, int myLayout, ArrayList<CurrencyConvert> arrayList) {
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

        TextView text1 = (TextView) view.findViewById(R.id.text1);
        TextView text2 = (TextView) view.findViewById(R.id.text2);

        text1.setText(arrayList.get(i).getCurrencyFirst());
        text2.setText(arrayList.get(i).getCurrencyLast());

        return view;
    }
}

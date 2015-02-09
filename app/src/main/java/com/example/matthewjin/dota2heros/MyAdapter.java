package com.example.matthewjin.dota2heros;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Hero> {

    private final List<Hero> heros;

    public MyAdapter(Context context, int resource, List<Hero> heros) {
        super(context, resource, heros);
        this.heros = heros;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getHeroView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getHeroView(position, convertView, parent);
    }

    public View getHeroView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activeity_detail, null);

        TextView textView = (TextView) row.findViewById(R.id.rowText);
        textView.setText(heros.get(position).getName());
/*
        try {
            ImageView imageView = (ImageView) row.findViewById(R.id.rowImage);
            String filename = heros.get(position).getFilename();
            InputStream inputStream = getContext().getAssets().open(filename);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/        
        return row;    }
}
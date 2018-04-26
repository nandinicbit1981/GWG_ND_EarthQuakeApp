package com.example.android.quakereport.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.model.QuakeModel;

import java.util.List;

/**
 * Created by nandpa on 4/25/18.
 */

public class QuakeListAdapter extends ArrayAdapter<QuakeModel> {
    public QuakeListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView =  convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.listitem,parent, false);
        }

        QuakeModel quakeModel = (QuakeModel) getItem(position);

        TextView magnitudeTxt = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView locationTxt = (TextView) listItemView.findViewById(R.id.location);
        TextView timeTxt = (TextView) listItemView.findViewById(R.id.date);

        magnitudeTxt.setText(String.valueOf(quakeModel.getMagnitude()));
        locationTxt.setText(quakeModel.getLocation());
        timeTxt.setText(String.valueOf(quakeModel.getTime()));

        return listItemView;
    }
}

package com.example.android.quakereport.adapter;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.activity.EarthquakeActivity;
import com.example.android.quakereport.model.QuakeModel;
import com.example.android.quakereport.util.DateUtil;
import com.example.android.quakereport.util.Utils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by nandpa on 4/25/18.
 */

public class QuakeListAdapter extends ArrayAdapter<QuakeModel> {
    public QuakeListAdapter(@NonNull EarthquakeActivity context, int resource, @NonNull List objects) {
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
        TextView directionTxt = (TextView) listItemView.findViewById(R.id.direction);
        TextView dateTxt = (TextView) listItemView.findViewById(R.id.date);
        TextView timeTxt = (TextView) listItemView.findViewById(R.id.time);
        DecimalFormat decFormat = new DecimalFormat("0.0");
        int color = Utils.getMagnitudeColor(quakeModel.getMagnitude(), getContext());
        magnitudeTxt.setText(decFormat.format(quakeModel.getMagnitude()));
        GradientDrawable background = (GradientDrawable) magnitudeTxt.getBackground();
        background.setColor(color);
        String consolidatedText =  quakeModel.getLocation();
        final Uri url = quakeModel.getUrl();
        String[] location = consolidatedText.split("of");
        if(location.length == 2) {
            locationTxt.setText(location[1]);
            directionTxt.setText(location[0] + " of");
        } else {
            locationTxt.setText(consolidatedText);
        }

        dateTxt.setText(DateUtil.formatDate(quakeModel.getDate()));
        timeTxt.setText(DateUtil.formatTime(quakeModel.getDate()));

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                getContext().startActivity(intent);
            }
        });

        return listItemView;
    }


}

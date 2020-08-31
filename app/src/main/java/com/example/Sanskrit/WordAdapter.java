package com.example.Sanskrit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mresourceColorId;

    public WordAdapter(Activity context, ArrayList<Word> al, int resourceColourId) {
        super(context, 0, al);
        mresourceColorId = resourceColourId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentitem = getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.sanskrit_textView);
        nameTextView.setText(currentitem.getSanskritTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.default_textView);
        numberTextView.setText(currentitem.getDefaultTranslation());

        ImageView imageResource = listItemView.findViewById(R.id.image);
        if(currentitem.hasImage()){
            imageResource.setImageResource(currentitem.getmImageResourceId());
            imageResource.setVisibility(View.VISIBLE);
        }
        else{
            imageResource.setVisibility(View.GONE);
        }

        View textViewcontainer = listItemView.findViewById(R.id.txtcontainer);
        int color = ContextCompat.getColor(getContext(), mresourceColorId);
        textViewcontainer.setBackgroundColor(color);

        return listItemView;
    }
}

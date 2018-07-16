package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sherif M. Eldeeb on 3/30/2018.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    /** color var. */
    private int mcolorRes;

    public WordAdapter(Activity context , ArrayList<Word> Words , int colorRes){
        super(context,0,Words);
        mcolorRes = colorRes;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItems = convertView;
        if (ListItems == null){
            ListItems = LayoutInflater.from(getContext()).inflate(R.layout.list , parent , false);
        }

        Word currentWord = getItem(position);

        TextView OriginalText = (TextView) ListItems.findViewById(R.id.Enword);
        OriginalText.setText(currentWord.getoriginal());

        TextView MiwokText = (TextView) ListItems.findViewById(R.id.Miword);
        MiwokText.setText(currentWord.getMiwok());

        ImageView image = (ImageView) ListItems.findViewById(R.id.image);
        if (currentWord.hasImage()){
            image.setVisibility(View.VISIBLE);
            image.setImageResource(currentWord.getResourceId());
        }else{
            image.setVisibility(View.GONE);
        }
        // set the theme to the listItems...
        View container = ListItems.findViewById(R.id.container);
        // get the color  from the resource ID
        int backColor = ContextCompat.getColor(getContext(),mcolorRes);
        // Set the Color to the View..
        container.setBackgroundColor(backColor);

        return  ListItems;
    }
}

package com.example.memoriz;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class CardAdapter extends ArrayAdapter<Card> {
    CardAdapter(Context context, int resources) {
        super(context, resources);
    }

    public View getView(int position, View convertView, ViewGroup viewParent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View card = inflater.inflate(R.layout.card_item, null);
        ImageView cardImg = card.findViewById(R.id.card_image);
        Card item = getItem(position);

        cardImg.setImageResource(item.getImage());
        return card;
    }
}

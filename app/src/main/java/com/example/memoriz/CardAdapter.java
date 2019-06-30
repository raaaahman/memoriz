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

    public View getView(int position, View cardView, ViewGroup viewParent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //Initialization of cards
        if (cardView == null) {
            cardView = inflater.inflate(R.layout.card_item, null);
            Card item = getItem(position);
            ImageView cardImg = cardView.findViewById(R.id.card_image);
            cardImg.setTranslationZ(2f);
            cardImg.setImageResource(item.getImage());
            ImageView cardBack = cardView.findViewById(R.id.card_back);
            cardBack.setTranslationZ(1f);
            cardBack.setImageResource(Card.CardBack);
        }

        return cardView;
    }
}

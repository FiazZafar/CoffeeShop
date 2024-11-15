package com.example.coffeetaste;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ItemDec extends RecyclerView.ItemDecoration {
    private final int spacing;

    public ItemDec(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position % 2 == 0) { // First column
            outRect.left = spacing; // Add spacing to the left
            outRect.right = spacing / 2; // Less spacing to the right
        } else { // Second column
            outRect.left = spacing / 2; // Less spacing to the left
            outRect.right = spacing; // Add spacing to the right
        }
        outRect.top = spacing; // Add spacing to the top
    }
}

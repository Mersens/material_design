package com.example.material_design_test;

import android.graphics.drawable.Drawable;
public class NavigationItem {
    private String mText;
    private Drawable mDrawable;
    private Style style;
	public NavigationItem(String text, Drawable drawable,Style style) {
        mText = text;
        mDrawable = drawable;
        this.style=style;
    }

    public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}
    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }
    
    public enum Style{
    	DEFAULT,HASLINE,NO_ICON;
    }
}

package com.piyush.wallpaper.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

@SuppressLint("AppCompatCustomView")
public class SquareImage extends ImageView {
    public SquareImage(Context context) {
        super(context);
    }

    public SquareImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SquareImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(),getMeasuredWidth());
    }
}

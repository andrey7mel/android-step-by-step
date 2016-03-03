package com.andrey7mel.stepbystep.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class TestableProgressBar extends ProgressBar {

    private int countChangeVisibility;

    private int countSetVISIBLE;
    private int countSetINVISIBLE;
    private int countSetGONE;

    public TestableProgressBar(Context context) {
        super(context);
    }

    public TestableProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestableProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getCountChangeVisibility() {
        return countChangeVisibility;
    }

    public int getCountSetVISIBLE() {
        return countSetVISIBLE;
    }

    public int getCountSetINVISIBLE() {
        return countSetINVISIBLE;
    }

    public int getCountSetGONE() {
        return countSetGONE;
    }

    @Override
    public void setVisibility(int v) {
        super.setVisibility(v);
        countChangeVisibility++;

        switch (v) {
            case VISIBLE:
                countSetVISIBLE++;
                break;
            case INVISIBLE:
                countSetINVISIBLE++;
                break;
            case GONE:
                countSetGONE++;
                break;
        }

    }
}

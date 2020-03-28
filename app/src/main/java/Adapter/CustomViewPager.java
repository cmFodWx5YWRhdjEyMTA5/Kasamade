package Adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import transforms.VerticalPageTransformer;

public class CustomViewPager extends ViewPager {
    public  boolean horizontalswap=true;


    public CustomViewPager(Context context) {
        super(context);
        init();
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public  void setPageTransformer()
    {
        horizontalswap=true;
        setPageTransformer(true, new VerticalPageTransformer());


    }
    private void init() {
        horizontalswap=true;
        setPageTransformer(true, new VerticalPageTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private MotionEvent swapXY(MotionEvent ev) {

        if(horizontalswap)
            return  ev;

        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){

        boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
        swapXY(ev); // return touch coordinates to original reference frame for any child views
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return super.onTouchEvent(swapXY(ev)

        );
    }

}
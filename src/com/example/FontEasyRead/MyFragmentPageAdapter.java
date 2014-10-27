package com.example.FontEasyRead;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 自定义fragment适配器
 * @author ZHF
 *
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    public MyFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyFragment_FontSmall();
            case 1:
                return new MyFragment_FontNormal();
            case 2:
                return new MyFragment_FontLarge();
            default:
                return null;
        }
    }
}
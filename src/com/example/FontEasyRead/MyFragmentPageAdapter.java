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
                return MyFragment.newInstance(position);
            case 1:
                return MyFragment.newInstance(position);
            case 2:
                return MyFragment.newInstance(position);
            default:
                return null;
        }
    }
}
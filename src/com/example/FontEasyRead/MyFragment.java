package com.example.FontEasyRead;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * 用于创建Fragment对象，作为ViewPager的叶片
 * @author ZHF
 *
 */

public class MyFragment extends Fragment {

    List<View> viewList;

    private View viewFontSmall,viewFontNormal,viewFontLarge;

    private int mNum; //頁碼

    private RelativeLayout mLayout;
    private TextView mTxtAppName;
    private TextView mTxtAppSize;
    private TextView mTxtStorageTitle;
    private TextView mTxtStorageTotal;
    private TextView mTxtStorageApp;
    private TextView mTxtStorageUsbStorageApp;
    private TextView mTxtStorageData;
    private TextView mTxtStorageUsbStorageData;
    private TextView mTxtStorageTotalText;
    private TextView mTxtStorageAppText;
    private TextView mTxtStorageUsbStorageAppText;
    private TextView mTxtStorageDataText;
    private TextView mTxtStorageUsbStorageDataText;
    private TextView mTxtCacheTitle;
    private TextView mTxtCache1;
    private TextView mTxtCache1Text;
    private TextView mTxtLaunchTitle;
    private TextView mTxtLaunch1;
    private TextView mTxtPermissionTitle;
    private TextView mTxtPermissionMsg;
    private TextView mTxtPermission1;
    private TextView mTxtPermission2;

    private Button mBtnFirstTwoBtnLeft;
    private Button mBtnFirstTwoBtnRight;
    private Button mBtnSecondTwoBtnLeft;
    private Button mBtnSecondTwoBtnRight;
    private Button mBtnCacheClear;
    private Button mBtnLaunchClear;

    private CheckBox mCheckBtn;

    public static MyFragment newInstance(int num) {
        MyFragment fragment = new MyFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取得頁碼
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;

    }
    /**为Fragment加载布局时调用**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
        View view = inflater.inflate(R.layout.fragment_pager_list, null);
        TextView tv = (TextView) view.findViewById(R.id.text);
        tv.setText("fragment+" + mNum);
        */
        //指向畫面位址
        viewFontSmall = inflater.inflate(R.layout.installed_app_details_small, null);
        viewFontNormal = inflater.inflate(R.layout.installed_app_details_normal, null);
        viewFontLarge = inflater.inflate(R.layout.installed_app_details_large, null);
        //添加畫面資料
        viewList = new ArrayList<View>();
        viewList.add(viewFontSmall);
        viewList.add(viewFontNormal);
        viewList.add(viewFontLarge);

        findViewSmall();

        return viewList.get(mNum);
    }

    private void findViewSmall(){
        mLayout = (RelativeLayout) viewFontSmall.findViewById(R.id.main_layout);
        mTxtAppName = (TextView)viewFontSmall.findViewById(R.id.app_name);
        mTxtAppSize = (TextView)viewFontSmall.findViewById(R.id.app_size);
        mTxtStorageTitle = (TextView)viewFontSmall.findViewById(R.id.storage_title);
        mTxtStorageTotal = (TextView)viewFontSmall.findViewById(R.id.total_size_prefix);
        mTxtStorageApp = (TextView)viewFontSmall.findViewById(R.id.application_size_prefix);
        mTxtStorageUsbStorageApp = (TextView)viewFontSmall.findViewById(R.id.external_code_size_prefix);
        mTxtStorageData = (TextView)viewFontSmall.findViewById(R.id.data_size_prefix);
        mTxtStorageUsbStorageData = (TextView)viewFontSmall.findViewById(R.id.external_data_size_prefix);
        mTxtStorageTotalText = (TextView)viewFontSmall.findViewById(R.id.total_size_text);
        mTxtStorageAppText = (TextView)viewFontSmall.findViewById(R.id.application_size_text);
        mTxtStorageUsbStorageAppText = (TextView)viewFontSmall.findViewById(R.id.external_code_size_text);
        mTxtStorageDataText = (TextView)viewFontSmall.findViewById(R.id.data_size_text);
        mTxtStorageUsbStorageDataText = (TextView)viewFontSmall.findViewById(R.id.external_data_size_text);
        mTxtCacheTitle = (TextView)viewFontSmall.findViewById(R.id.cache_header);
        mTxtCache1 = (TextView)viewFontSmall.findViewById(R.id.cache_child);
        mTxtCache1Text = (TextView)viewFontSmall.findViewById(R.id.cache_size_text);
        mTxtLaunchTitle = (TextView)viewFontSmall.findViewById(R.id.auto_launch_title);
        mTxtLaunch1 = (TextView)viewFontSmall.findViewById(R.id.auto_launch);
        mTxtPermissionTitle = (TextView)viewFontSmall.findViewById(R.id.permiss_title);
        mTxtPermissionMsg = (TextView)viewFontSmall.findViewById(R.id.security_settings_billing_desc);
        mTxtPermission1 = (TextView)viewFontSmall.findViewById(R.id.permiss_1);
        mTxtPermission2 = (TextView)viewFontSmall.findViewById(R.id.permiss_2);

        mBtnFirstTwoBtnLeft = (Button)viewFontSmall.findViewById(R.id.two_1_left_button);
        mBtnFirstTwoBtnRight = (Button)viewFontSmall.findViewById(R.id.two_1_right_button);
        mBtnSecondTwoBtnLeft = (Button)viewFontSmall.findViewById(R.id.two_2_left_button);
        mBtnSecondTwoBtnRight = (Button)viewFontSmall.findViewById(R.id.two_2_right_button);
        mBtnCacheClear = (Button)viewFontSmall.findViewById(R.id.clear_cache_button);
        mBtnLaunchClear = (Button)viewFontSmall.findViewById(R.id.clear_activities_button);

        mCheckBtn = (CheckBox)viewFontSmall.findViewById(R.id.notification_switch);
    }


    private void backgroundColorControl(boolean background){
        //判斷接收背景顏色資料
        if (background) {
            mLayout.setBackgroundColor(Color.WHITE);
            //mViewPager.setBackgroundColor(Color.WHITE);

            mTxtAppName.setTextColor(Color.BLACK);
            mTxtAppSize.setTextColor(Color.BLACK);
            mTxtStorageTitle.setTextColor(Color.BLACK);
            mTxtStorageTotal.setTextColor(Color.BLACK);
            mTxtStorageApp.setTextColor(Color.BLACK);
            mTxtStorageUsbStorageApp.setTextColor(Color.BLACK);
            mTxtStorageData.setTextColor(Color.BLACK);
            mTxtStorageUsbStorageData.setTextColor(Color.BLACK);
            mTxtStorageTotalText.setTextColor(Color.BLACK);
            mTxtStorageAppText.setTextColor(Color.BLACK);
            mTxtStorageUsbStorageAppText.setTextColor(Color.BLACK);
            mTxtStorageDataText.setTextColor(Color.BLACK);
            mTxtStorageUsbStorageDataText.setTextColor(Color.BLACK);
            mTxtCacheTitle.setTextColor(Color.BLACK);
            mTxtCache1.setTextColor(Color.BLACK);
            mTxtCache1Text.setTextColor(Color.BLACK);
            mTxtLaunchTitle.setTextColor(Color.BLACK);
            mTxtLaunch1.setTextColor(Color.BLACK);
            mTxtPermissionTitle.setTextColor(Color.BLACK);
            mTxtPermissionMsg.setTextColor(Color.BLACK);
            mTxtPermission1.setTextColor(Color.BLACK);
            mTxtPermission2.setTextColor(Color.BLACK);

            mBtnFirstTwoBtnLeft.setTextColor(Color.BLACK);
            mBtnFirstTwoBtnRight.setTextColor(Color.BLACK);
            mBtnSecondTwoBtnLeft.setTextColor(Color.BLACK);
            mBtnSecondTwoBtnRight.setTextColor(Color.BLACK);
            mBtnCacheClear.setTextColor(Color.BLACK);
            mBtnLaunchClear.setTextColor(Color.BLACK);

            mCheckBtn.setTextColor(Color.BLACK);
        } else {
            mLayout.setBackgroundColor(Color.BLACK);
            //mViewPager.setBackgroundColor(Color.BLACK);

            mTxtAppName.setTextColor(Color.WHITE);
            mTxtAppSize.setTextColor(Color.WHITE);
            mTxtStorageTitle.setTextColor(Color.WHITE);
            mTxtStorageTotal.setTextColor(Color.WHITE);
            mTxtStorageApp.setTextColor(Color.WHITE);
            mTxtStorageUsbStorageApp.setTextColor(Color.WHITE);
            mTxtStorageData.setTextColor(Color.WHITE);
            mTxtStorageUsbStorageData.setTextColor(Color.WHITE);
            mTxtStorageTotalText.setTextColor(Color.WHITE);
            mTxtStorageAppText.setTextColor(Color.WHITE);
            mTxtStorageUsbStorageAppText.setTextColor(Color.WHITE);
            mTxtStorageDataText.setTextColor(Color.WHITE);
            mTxtStorageUsbStorageDataText.setTextColor(Color.WHITE);
            mTxtCacheTitle.setTextColor(Color.WHITE);
            mTxtCache1.setTextColor(Color.WHITE);
            mTxtCache1Text.setTextColor(Color.WHITE);
            mTxtLaunchTitle.setTextColor(Color.WHITE);
            mTxtLaunch1.setTextColor(Color.WHITE);
            mTxtPermissionTitle.setTextColor(Color.WHITE);
            mTxtPermissionMsg.setTextColor(Color.WHITE);
            mTxtPermission1.setTextColor(Color.WHITE);
            mTxtPermission2.setTextColor(Color.WHITE);

            mBtnFirstTwoBtnLeft.setTextColor(Color.WHITE);
            mBtnFirstTwoBtnRight.setTextColor(Color.WHITE);
            mBtnSecondTwoBtnLeft.setTextColor(Color.WHITE);
            mBtnSecondTwoBtnRight.setTextColor(Color.WHITE);
            mBtnCacheClear.setTextColor(Color.WHITE);
            mBtnLaunchClear.setTextColor(Color.WHITE);

            mCheckBtn.setTextColor(Color.WHITE);
        }
    }

}
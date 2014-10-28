package com.example.FontEasyRead;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


/**
 * 創建Fragment對象，作為ViewPager的分頁
 * 使用於 Font Size Small
 *
 */

public class MyFragment_FontSmall extends Fragment {

    private View view;

    private ScrollView mLayout;
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
    /*
    public static MyFragment_FontNormal newInstance(int num) {
        MyFragment_FontNormal fragment = new MyFragment_FontNormal();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        fragment.setArguments(args);
        return fragment;
    }
    */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*
        //取得頁碼
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        Log.d("Arphic Log", "Small" + String.valueOf(mNum));
    */

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
        view = inflater.inflate(R.layout.installed_app_details_small, null);

        findView();
        //設定背景色
        backgroundColorControl(MyFontShow.inBackground);
        //更改font typeface
        fontTypefaceControl(MyFontShow.inFontWeight);        //mask by v1.1.7.1
        //return viewList.get(mNum);
        return view;
    }
    /*add by v1.1.7.2 begin*/
    //設定font typeface
    private void fontTypefaceControl(int fontWeight){
        /*add by v1.1.7.3 begin*/
        int btnFontWeight,txtSmallStringFontWeight;

        //判斷頭尾，避免崩潰
        if (fontWeight == 0) {
            txtSmallStringFontWeight = fontWeight;
            btnFontWeight = fontWeight + 1;
        } else if (fontWeight == MyActivity.mTypefaceTable.length - 1) {
            btnFontWeight = MyActivity.mTypefaceTable.length - 1;
            txtSmallStringFontWeight = fontWeight - 1;

        } else {
            btnFontWeight = fontWeight + 1;
            txtSmallStringFontWeight = fontWeight - 1;
        }
        /*add by v1.1.7.3 begin*/

        //設定接收font weight資料
        mTxtAppName.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtAppSize.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageTitle.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageTotal.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageApp.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageUsbStorageApp.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageData.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageUsbStorageData.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageTotalText.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageAppText.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageUsbStorageAppText.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageDataText.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtStorageUsbStorageDataText.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtCacheTitle.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtCache1.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtCache1Text.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtLaunchTitle.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtPermissionTitle.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        /* mask by v1.1.7.3 begin
        mTxtLaunch1.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtPermissionMsg.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtPermission1.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mTxtPermission2.setTypeface(MyActivity.mTypefaces.get(fontWeight));

        mBtnFirstTwoBtnLeft.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mBtnFirstTwoBtnRight.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mBtnSecondTwoBtnLeft.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mBtnSecondTwoBtnRight.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mBtnCacheClear.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mBtnLaunchClear.setTypeface(MyActivity.mTypefaces.get(fontWeight));
        mask by v1.1.7.3 end*/

        /*add by v1.1.7.3 begin*/
        //small font
        mTxtLaunch1.setTypeface(MyActivity.mTypefaces.get(txtSmallStringFontWeight));
        mTxtPermissionMsg.setTypeface(MyActivity.mTypefaces.get(txtSmallStringFontWeight));
        mTxtPermission1.setTypeface(MyActivity.mTypefaces.get(txtSmallStringFontWeight));
        mTxtPermission2.setTypeface(MyActivity.mTypefaces.get(txtSmallStringFontWeight));

        //btn
        mBtnFirstTwoBtnLeft.setTypeface(MyActivity.mTypefaces.get(btnFontWeight));
        mBtnFirstTwoBtnRight.setTypeface(MyActivity.mTypefaces.get(btnFontWeight));
        mBtnSecondTwoBtnLeft.setTypeface(MyActivity.mTypefaces.get(btnFontWeight));
        mBtnSecondTwoBtnRight.setTypeface(MyActivity.mTypefaces.get(btnFontWeight));
        mBtnCacheClear.setTypeface(MyActivity.mTypefaces.get(btnFontWeight));
        mBtnLaunchClear.setTypeface(MyActivity.mTypefaces.get(btnFontWeight));
        /*add by v1.1.7.3 end*/

        mCheckBtn.setTypeface(MyActivity.mTypefaces.get(fontWeight));
    }

    private void findView(){
        mLayout = (ScrollView) view.findViewById(R.id.app_show);
        mTxtAppName = (TextView)view.findViewById(R.id.app_name);
        mTxtAppSize = (TextView)view.findViewById(R.id.app_size);
        mTxtStorageTitle = (TextView)view.findViewById(R.id.storage_title);
        mTxtStorageTotal = (TextView)view.findViewById(R.id.total_size_prefix);
        mTxtStorageApp = (TextView)view.findViewById(R.id.application_size_prefix);
        mTxtStorageUsbStorageApp = (TextView)view.findViewById(R.id.external_code_size_prefix);
        mTxtStorageData = (TextView)view.findViewById(R.id.data_size_prefix);
        mTxtStorageUsbStorageData = (TextView)view.findViewById(R.id.external_data_size_prefix);
        mTxtStorageTotalText = (TextView)view.findViewById(R.id.total_size_text);
        mTxtStorageAppText = (TextView)view.findViewById(R.id.application_size_text);
        mTxtStorageUsbStorageAppText = (TextView)view.findViewById(R.id.external_code_size_text);
        mTxtStorageDataText = (TextView)view.findViewById(R.id.data_size_text);
        mTxtStorageUsbStorageDataText = (TextView)view.findViewById(R.id.external_data_size_text);
        mTxtCacheTitle = (TextView)view.findViewById(R.id.cache_header);
        mTxtCache1 = (TextView)view.findViewById(R.id.cache_child);
        mTxtCache1Text = (TextView)view.findViewById(R.id.cache_size_text);
        mTxtLaunchTitle = (TextView)view.findViewById(R.id.auto_launch_title);
        mTxtLaunch1 = (TextView)view.findViewById(R.id.auto_launch);
        mTxtPermissionTitle = (TextView)view.findViewById(R.id.permiss_title);
        mTxtPermissionMsg = (TextView)view.findViewById(R.id.security_settings_billing_desc);
        mTxtPermission1 = (TextView)view.findViewById(R.id.permiss_1);
        mTxtPermission2 = (TextView)view.findViewById(R.id.permiss_2);

        mBtnFirstTwoBtnLeft = (Button)view.findViewById(R.id.two_1_left_button);
        mBtnFirstTwoBtnRight = (Button)view.findViewById(R.id.two_1_right_button);
        mBtnSecondTwoBtnLeft = (Button)view.findViewById(R.id.two_2_left_button);
        mBtnSecondTwoBtnRight = (Button)view.findViewById(R.id.two_2_right_button);
        mBtnCacheClear = (Button)view.findViewById(R.id.clear_cache_button);
        mBtnLaunchClear = (Button)view.findViewById(R.id.clear_activities_button);

        mCheckBtn = (CheckBox)view.findViewById(R.id.notification_switch);
    }


    private void backgroundColorControl(boolean background){
        //判斷接收背景顏色資料
        if (background) {
            mLayout.setBackgroundColor(Color.WHITE);

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
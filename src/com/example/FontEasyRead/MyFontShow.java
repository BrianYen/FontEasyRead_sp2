package com.example.FontEasyRead;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * fragment 要分3張
 *
 *
 * https://www.google.com.tw/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=android+viewpager+fragment&tbs=qdr:y&start=20
 * http://men4661273.iteye.com/blog/2122885
 * http://www.360doc.com/content/13/1115/23/14218823_329557474.shtml
 * https://github.com/codepath/android_guides/wiki/ViewPager-with-FragmentPagerAdapter
 * http://www.it165.net/pro/html/201310/7454.html
 *
 * Created by Brian on 2014/10/21.
 *
 */
public class MyFontShow extends FragmentActivity {

    private String[] mTypefaceTable = {
            "fonts/gjxh00l-noh.ttf",        //Light font
            "fonts/gjxh00m_noh.ttf",
            "fonts/gjxh00db_noh.ttf",
            "fonts/gjxh00b_noh.ttf",
            "fonts/gjxh0eb_noh.ttf",
            "fonts/gjxh00h_noh.ttf"
    };

    private final static boolean IsEnableUserFont = true;

    private static final int MAX_AVAILABLE = 100;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    private ArrayList<Typeface> mTypefaces = new ArrayList<Typeface>(
            mTypefaceTable.length
    );

    //private ScrollView mLayout;
    private RelativeLayout mLayout;
    private MyFragmentPageAdapter mAdapter;
    private ViewPager mViewPager;

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

    //Typeface initializer
    public void typefacesInit(AssetManager am) {

        // initialize Util for TypeFace
        Util.init(am);

        for (String ignored : mTypefaceTable) mTypefaces.add(null);

        for (int i = 0; i < mTypefaceTable.length; i++) {
            if (i == 0) {
                new Thread(new Runnable() {
                    public void run() {
                        if (IsEnableUserFont) {
                            Typeface tf = Util
                                    .getTypeface(mTypefaceTable[0]);
                            try {
                                available.acquire();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            mTypefaces.set(0, tf);
                            available.release();
                        }
                    }
                }).start();
            } else if (i == 1) {
                new Thread(new Runnable() {
                    public void run() {
                        if (IsEnableUserFont) {
                            Typeface tf = Util
                                    .getTypeface(mTypefaceTable[1]);
                            try {
                                available.acquire();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            mTypefaces.set(1, tf);
                            available.release();
                        }
                    }
                }).start();
            } else if (i == 2) {
                new Thread(new Runnable() {
                    public void run() {
                        if (IsEnableUserFont) {
                            Typeface tf = Util
                                    .getTypeface(mTypefaceTable[2]);
                            try {
                                available.acquire();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            mTypefaces.set(2, tf);
                            available.release();
                        }
                    }
                }).start();
            } else if (i == 3) {
                new Thread(new Runnable() {
                    public void run() {
                        if (IsEnableUserFont) {
                            Typeface tf = Util
                                    .getTypeface(mTypefaceTable[3]);
                            try {
                                available.acquire();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            mTypefaces.set(3, tf);
                            available.release();
                        }
                    }
                }).start();
                new Thread(new Runnable() {
                    public void run() {
                        if (IsEnableUserFont) {
                            Typeface tf = Util
                                    .getTypeface(mTypefaceTable[4]);
                            try {
                                available.acquire();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            mTypefaces.set(4, tf);
                            available.release();
                        }
                    }
                }).start();
            }  else {
                new Thread(new Runnable() {
                    public void run() {
                        if (IsEnableUserFont) {
                            Typeface tf = Util
                                    .getTypeface(mTypefaceTable[5]);
                            try {
                                available.acquire();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            mTypefaces.set(5, tf);
                            available.release();
                        }
                    }
                }).start();
            }
        }
    }
    /*
    private void loadFile() {       //add by v1.0.1
        //File file = new File(path);       //mask by v1.0.1

        String line, text = "";
        try {

            InputStream inStream = getResources().getAssets().open("texts/FontEasyRead_example.html");      //add by v1.0.1

            //FileInputStream inStream = new FileInputStream(file);     //mask by v1.0.1

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(inStream, "UTF-8"));

            while ((line = br.readLine()) != null) {
                text += line;
            }
        } catch (IOException e) {

            Toast.makeText(
                    MyFontShow.this,
                    "Load File Error",
                    Toast.LENGTH_LONG
            ).show();
        }

        //設定顯示內容
        mTextView.setText(Html.fromHtml(text));
    }
    */
    /* 字體大小調整功能
    private synchronized void setFontSize(TextView showTitle,TextView readText,int size) {
        String sizeTitle;
        if (size == MAX_FONT_SIZE) {
            size = MAX_FONT_SIZE;
            //fontSize.setIsZoomInEnabled(false);
            btn_addSize.setVisibility(View.INVISIBLE);
            sizeTitle = String.valueOf(size) + " (MAX)";
        } else if (size == MIN_FONT_SIZE) {
            size = MIN_FONT_SIZE;
            //fontSize.setIsZoomOutEnabled(false);
            btn_subSize.setVisibility(View.INVISIBLE);
            sizeTitle = String.valueOf(size) + " (MIN)";
        } else
            sizeTitle = String.valueOf(size);

        showTitle.setText(sizeTitle);
        readText.setTextSize(size);
        mSize = size;

    }
    */

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
    private void fontTypefaceControl(int fontWeight){
        //設定接收font weight資料

        mTxtAppName.setTypeface(mTypefaces.get(fontWeight));
        mTxtAppSize.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageTitle.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageTotal.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageApp.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageUsbStorageApp.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageData.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageUsbStorageData.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageTotalText.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageAppText.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageUsbStorageAppText.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageDataText.setTypeface(mTypefaces.get(fontWeight));
        mTxtStorageUsbStorageDataText.setTypeface(mTypefaces.get(fontWeight));
        mTxtCacheTitle.setTypeface(mTypefaces.get(fontWeight));
        mTxtCache1.setTypeface(mTypefaces.get(fontWeight));
        mTxtCache1Text.setTypeface(mTypefaces.get(fontWeight));
        mTxtLaunchTitle.setTypeface(mTypefaces.get(fontWeight));
        mTxtLaunch1.setTypeface(mTypefaces.get(fontWeight));
        mTxtPermissionTitle.setTypeface(mTypefaces.get(fontWeight));
        mTxtPermissionMsg.setTypeface(mTypefaces.get(fontWeight));
        mTxtPermission1.setTypeface(mTypefaces.get(fontWeight));
        mTxtPermission2.setTypeface(mTypefaces.get(fontWeight));

        mBtnFirstTwoBtnLeft.setTypeface(mTypefaces.get(fontWeight));
        mBtnFirstTwoBtnRight.setTypeface(mTypefaces.get(fontWeight));
        mBtnSecondTwoBtnLeft.setTypeface(mTypefaces.get(fontWeight));
        mBtnSecondTwoBtnRight.setTypeface(mTypefaces.get(fontWeight));
        mBtnCacheClear.setTypeface(mTypefaces.get(fontWeight));
        mBtnLaunchClear.setTypeface(mTypefaces.get(fontWeight));

        mCheckBtn.setTypeface(mTypefaces.get(fontWeight));
    }
    private void fontTypefaceInit(){
        Typeface tf = Typeface.createFromAsset(getAssets(), mTypefaceTable[0]);      //if not't use mTypeface[0] will be error

        mTxtAppName.setTypeface(tf);
        mTxtAppSize.setTypeface(tf);
        mTxtStorageTitle.setTypeface(tf);
        mTxtStorageTotal.setTypeface(tf);
        mTxtStorageApp.setTypeface(tf);
        mTxtStorageUsbStorageApp.setTypeface(tf);
        mTxtStorageData.setTypeface(tf);
        mTxtStorageUsbStorageData.setTypeface(tf);
        mTxtStorageTotalText.setTypeface(tf);
        mTxtStorageAppText.setTypeface(tf);
        mTxtStorageUsbStorageAppText.setTypeface(tf);
        mTxtStorageDataText.setTypeface(tf);
        mTxtStorageUsbStorageDataText.setTypeface(tf);
        mTxtCacheTitle.setTypeface(tf);
        mTxtCache1.setTypeface(tf);
        mTxtCache1Text.setTypeface(tf);
        mTxtLaunchTitle.setTypeface(tf);
        mTxtLaunch1.setTypeface(tf);
        mTxtPermissionTitle.setTypeface(tf);
        mTxtPermissionMsg.setTypeface(tf);
        mTxtPermission1.setTypeface(tf);
        mTxtPermission2.setTypeface(tf);

        mBtnFirstTwoBtnLeft.setTypeface(tf);
        mBtnFirstTwoBtnRight.setTypeface(tf);
        mBtnSecondTwoBtnLeft.setTypeface(tf);
        mBtnSecondTwoBtnRight.setTypeface(tf);
        mBtnCacheClear.setTypeface(tf);
        mBtnLaunchClear.setTypeface(tf);

        mCheckBtn.setTypeface(tf);
    }
    private void findView(){
        //mLayout = (ScrollView) findViewById(R.id.app_show);
        mLayout = (RelativeLayout) findViewById(R.id.main_layout);
        mTxtAppName = (TextView)findViewById(R.id.app_name);
        mTxtAppSize = (TextView)findViewById(R.id.app_size);
        mTxtStorageTitle = (TextView)findViewById(R.id.storage_title);
        mTxtStorageTotal = (TextView)findViewById(R.id.total_size_prefix);
        mTxtStorageApp = (TextView)findViewById(R.id.application_size_prefix);
        mTxtStorageUsbStorageApp = (TextView)findViewById(R.id.external_code_size_prefix);
        mTxtStorageData = (TextView)findViewById(R.id.data_size_prefix);
        mTxtStorageUsbStorageData = (TextView)findViewById(R.id.external_data_size_prefix);
        mTxtStorageTotalText = (TextView)findViewById(R.id.total_size_text);
        mTxtStorageAppText = (TextView)findViewById(R.id.application_size_text);
        mTxtStorageUsbStorageAppText = (TextView)findViewById(R.id.external_code_size_text);
        mTxtStorageDataText = (TextView)findViewById(R.id.data_size_text);
        mTxtStorageUsbStorageDataText = (TextView)findViewById(R.id.external_data_size_text);
        mTxtCacheTitle = (TextView)findViewById(R.id.cache_header);
        mTxtCache1 = (TextView)findViewById(R.id.cache_child);
        mTxtCache1Text = (TextView)findViewById(R.id.cache_size_text);
        mTxtLaunchTitle = (TextView)findViewById(R.id.auto_launch_title);
        mTxtLaunch1 = (TextView)findViewById(R.id.auto_launch);
        mTxtPermissionTitle = (TextView)findViewById(R.id.permiss_title);
        mTxtPermissionMsg = (TextView)findViewById(R.id.security_settings_billing_desc);
        mTxtPermission1 = (TextView)findViewById(R.id.permiss_1);
        mTxtPermission2 = (TextView)findViewById(R.id.permiss_2);

        mBtnFirstTwoBtnLeft = (Button)findViewById(R.id.two_1_left_button);
        mBtnFirstTwoBtnRight = (Button)findViewById(R.id.two_1_right_button);
        mBtnSecondTwoBtnLeft = (Button)findViewById(R.id.two_2_left_button);
        mBtnSecondTwoBtnRight = (Button)findViewById(R.id.two_2_right_button);
        mBtnCacheClear = (Button)findViewById(R.id.clear_cache_button);
        mBtnLaunchClear = (Button)findViewById(R.id.clear_activities_button);

        mCheckBtn = (CheckBox)findViewById(R.id.notification_switch);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.installed_app_details_normal);
        setContentView(R.layout.test);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);      //add by v1.1.7

        //為了支持3.0以下版本，需繼承FragmentActivity，通過getSupportFragmentManager()獲取FragmentManager
        //3.0及其以上版本，只需繼承Activity，通過getFragmentManager獲取事件
        FragmentManager fm = getSupportFragmentManager();
        //初始化自訂義篩選器
        mAdapter =  new MyFragmentPageAdapter(fm);

        //設置篩選器
        mViewPager.setAdapter(mAdapter);

        //設定默認主畫面
        mViewPager.setCurrentItem(1);       //normal



        //findView
        findView();

        //初始化Typeface
        //typefacesInit(getAssets());       //mask by v1.0.1 begin
        //初始設定Typeface
        //fontTypefaceInit();

        //接收設定資料
        Bundle bundle = getIntent().getExtras();
        int inFontWeight = bundle.getInt("fontWeight");
        boolean inBackground = bundle.getBoolean("background");

        //讀取檔案
        //loadFile();       //mask by v1.1.5

        //更改背景
        backgroundColorControl(inBackground);

        //更改font typeface
//        fontTypefaceControl(inFontWeight);







    }
}

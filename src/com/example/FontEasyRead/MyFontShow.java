package com.example.FontEasyRead;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by Brian on 2014/10/21.
 *
 */
public class MyFontShow extends Activity {

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

    private TextView mTextView;


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.installed_app_details);
/*
        //findView
        mTextView = (TextView)findViewById(R.id.textView);
        LinearLayout mLayout = (LinearLayout) findViewById(R.id.fontShowLayout);

        //啟動觸碰捲動
        mTextView.setMovementMethod(ScrollingMovementMethod.getInstance());        //文章太長不會斷行

        //初始化Typeface
        typefacesInit(getAssets());       //mask by v1.0.1 begin
        //初始設定Typeface
        Typeface tf = Typeface.createFromAsset(getAssets(), mTypefaceTable[0]);      //if not't use mTypeface[0] will be error
        mTextView.setTypeface(tf);

        //接收資料
        Bundle bundle = getIntent().getExtras();
        int inFontWeight = bundle.getInt("fontWeight");
        boolean inBackground = bundle.getBoolean("background");

        //讀取檔案
        loadFile();

        //判斷接收背景顏色資料
        if (inBackground) {
            mLayout.setBackgroundColor(Color.WHITE);
            mTextView.setTextColor(Color.BLACK);
        } else {
            mLayout.setBackgroundColor(Color.BLACK);
            mTextView.setTextColor(Color.WHITE);
        }

        //設定接收font weight資料
        mTextView.setTypeface(mTypefaces.get(inFontWeight));
*/
    }
}

package com.example.FontEasyRead;

import android.app.Activity;
import android.content.Intent;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Semaphore;


public class MyActivity extends Activity implements
        SensorEventListener {
    /**
     *
     * 2014/11/10   brian   v1.1.7.7    1.change UI (v1.1.7.5 - v1.1.7.7)
     * 2014/10/29   brian   v1.1.7.4    1.fix font demi bold set typeface error
     * 2014/10/28   brian   v1.1.7.3    1.control fragment Btn,Txt font weight
     *
	 * 2014/10/28   brian   v1.1.7.2    1.create fragment change font weight module
     *                                  2.change main layout color
     *                                  3.support JP Language
     *
     * 2014/10/27   brian   v1.1.7.1    1.create fragment change background module
     *
     * 2014/10/27   brian   v1.1.7      1.add 3 size fragment layout
     *
     * 2014/10/22   brian   v1.1.6      1.add android UI
     *                                  2.change background button
     *
     * 2014/10/20   brian   v1.1.5      1.change used font
     *                                  2.change GUI
     *
     * 2014/09/23	andrew	v1.1.4	    1.change font file name
	 *
     * 2014/08/22   brian   v1.1.1      1. add Auto Brightness Control Button.
     *                                  2. update save csv file dir code.
     *
     * 2014/07/30   brian   v1.1.0      1.copy from v1.0.1, close auto font width, add Brightness Module
     *
     *
     */
    //字型存放路徑設定
    public static String[] mTypefaceTable = {
            "fonts/gjxh00l-noh.ttf",        //Light font
            "fonts/gjxh00m_noh.ttf",
            //"fonts/gjxh00db_noh.ttf",     //mask by v1.1.7.4
            "fonts/gjxh0db_noh.ttf",        //add by v1.1.7.4
            "fonts/gjxh00b_noh.ttf",
            "fonts/gjxh0eb_noh.ttf",
            "fonts/gjxh00h_noh.ttf"
    };

    // add by v1.1.0 begin
    private static String[] fontWightName = {
        "Light",
        "Medium",
        "Demi Bold",
        "Bold",
        "Extra Bold",
        "Heavy"
    };
    //add by v1.1.0 end

    /*
    public final static int DEFAULT_FONT_SIZE = 24;
    public final static int MAX_FONT_SIZE = 72;
    public final static int MIN_FONT_SIZE = 8;
    private static int mSize = DEFAULT_FONT_SIZE;
    */

    private final static boolean IsEnableUserFont = true;

    private static final int MAX_AVAILABLE = 100;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    public static ArrayList<Typeface> mTypefaces = new ArrayList<Typeface>(
            mTypefaceTable.length
            );


    //private Typeface typefaceWeight;        //add by v1.0.1, mask by v1.1.0

    // add by v1.1.0 begin
    //private TextView txt_read;        //mask by v1.1.5
    private TextView txt_fontWidth;
    private TextView txt_background;
    private TextView txt_defaultLight;
    //private TextView txt_outLux;      //mask by v1.1.2
    private TextView txt_mSeekBarTitle;
    private TextView txt_defaultLight_note;

    //private Button btn_background;        //mask by v1.1.5
    private ToggleButton btn_background;
    private Button btn_saveInformation;

    //private RelativeLayout mApp;      //mask by v1.1.5

    private SensorManager mgr;
    private Sensor sensor;

    private SeekBar mSeekBar;

    private ToggleButton mTogBtn;       //add by v1.1.2

    private int widthFlag = 0;
    private long saveCountNum = 0;
    private static String saveCSVFilePath;
    private boolean checkAutoBringOFF = false;      //add by v1.1.2
    // add by v1.1.0 end
    //add by v1.1.5 begin
    private Button btn_addFontWeight;
    private Button btn_subFontWeight;

    private TextView txt_showFontWeight;

    private Button btn_show;

    private int outFontWeight;
    private boolean outBackground;
    //add by v1.1.5 end

    /* mask by v1.1.0 begin
    private TextView fontSizeMsg;
    private TextView lightValue;
    private ZoomControls fontSize;

    private static String filePath = Environment.getExternalStorageDirectory().getPath() +
            "/texts/FontEasyRead_example.html";
    mask by v1.1.0 end*/

    //Typeface initializer
    public void typefacesInit(AssetManager am) {

        // initialize Util for TypeFace
        Util.init(am);

        for (int i=0;i<mTypefaceTable.length;i++)
            mTypefaces.add(null);

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

    /*mask by v1.1.5 begin
    //private void loadFile(final String path) {        //mask by v1.0.1
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
                    MyActivity.this,
                    "Load File Error",
                    Toast.LENGTH_LONG
            ).show();
        }

        //設定顯示內容
        //txt_read.setText(Html.fromHtml(text));
    }
    mask by v1.1.5 end*/
    /* mask by v1.1.0 begin
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

    private ZoomControls.OnClickListener mFontSizeAddListener = new ZoomControls.OnClickListener() {

        @Override
        public void onClick(View v) {
            fontSize.setIsZoomOutEnabled(true);
            setFontSize(mSize + 2);
        }
    };

    private ZoomControls.OnClickListener mFontSizeSubListener = new ZoomControls.OnClickListener() {

        @Override
        public void onClick(View v) {
            fontSize.setIsZoomInEnabled(true);
            setFontSize(mSize - 2);
        }
    };
    mask by v1.1.0 end*/

    private void generateCsvFile() throws IOException {

        long saveNum = ++saveCountNum;

        CSVWriter writer = new CSVWriter(new FileWriter(saveCSVFilePath + "/saveInfo_" + saveNum +".csv"));

        //String mSaveOutLux = txt_outLux.getText().toString();     //mask by v1.1.1
        String mSaveLight = txt_defaultLight.getText().toString();
        String mSaveFontWidth = txt_fontWidth.getText().toString();
        String mSaveBackGround = txt_background.getText().toString();

        List<String[]> data = new ArrayList<String[]>();
        //data.add(new String[] {"OutSizeLux", mSaveOutLux});       //mask by v1.1.1
        data.add(new String[] {"ScreenLight", mSaveLight});
        data.add(new String[] {"FontWidth", mSaveFontWidth});
        data.add(new String[] {"BackGround", mSaveBackGround});

        writer.writeAll(data);

        writer.close();
    }
    /*mask by v1.1.5 begin
    //add by v1.1.0 begin
    private  Button.OnClickListener mBackgroundListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            String ButtonText = btn_background.getText().toString();
            Log.d("Arphic Log",ButtonText);
            if(ButtonText.equals(R.string.back_black)){
                Log.d("Arphic Log","IN");
                //code for save
                //btn_background.setText(R.string.back_white);
                txt_background.setText(R.string.back_white);
                //mApp.setBackgroundColor(Color.WHITE);     //mask by v1.1.5
                //txt_read.setTextColor(Color.BLACK);       //mask by v1.1.5

                outBackground = true;       //傳回背景設定 (true:黑, false:白)      //add by v1.1.5
            }
            else{
                //code for edit
                //btn_background.setText(R.string.back_black);
                txt_background.setText(R.string.back_black);
                //mApp.setBackgroundColor(Color.BLACK);     //mask by v1.1.5
                //txt_read.setTextColor(Color.WHITE);       //mask by v1.1.5

                outBackground = false;      //傳回背景設定 (true:黑, false:白)      //add by v1.1.5
            }
        }
    };
    mask by v1.1.5 end*/
    private  ToggleButton.OnClickListener mBackgroundListener = new ToggleButton.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(btn_background.isChecked()) {
                txt_background.setText(R.string.back_black);
                outBackground = false;       //傳回背景設定 (true:白, false:黑)      //add by v1.1.5
                btn_background.setTextColor(Color.BLACK);       //add by v1.1.7.2
            }
            // 當按鈕在次按下動作 OFF
            else {
                txt_background.setText(R.string.back_white);
                outBackground = true;      //傳回背景設定 (true:白, false:黑)      //add by v1.1.5
                btn_background.setTextColor(Color.WHITE);       //add by v1.1.7.2
            }
        }
    };
    //add by v1.1.1 begin
    //ToggleBtn 監聽器
    private ToggleButton.OnClickListener mTogBtnListener = new ToggleButton.OnClickListener () {

        @Override
        public void onClick(View v) {
            // 當按鈕按下第一次動作 ON
            if (mTogBtn.isChecked()) {

                startAutoBrightness();
                mTogBtn.setTextColor(Color.GREEN);        //add by v1.1.7.2
            }
            // 當按鈕在次按下動作 OFF
            else {

                //關閉自動調整亮度
                stopAutoBrightness();
                //seekBar初始化
                seekBarInit();      //add by v1.1.0
                mTogBtn.setTextColor(Color.RED);      //add by v1.1.7.2
            }
        }
    };
    private Button.OnClickListener mAddFontWeight = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            int count = ++widthFlag;

            count=count%mTypefaceTable.length;

            btn_subFontWeight.setEnabled(true);
            //txt_read.setTypeface(mTypefaces.get(count));      //mask by v1.1.5

            outFontWeight = count;      //傳送typeface        //add by v1.1.5

            txt_fontWidth.setText(fontWightName[count]);
            txt_showFontWeight.setText(fontWightName[count]);

            if (count == mTypefaceTable.length - 1){
                btn_addFontWeight.setEnabled(false);
            }


        }
    };

    private Button.OnClickListener mSubFontWeight = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            int count = --widthFlag;

            if (count<0) count=count+mTypefaceTable.length;
            count=count%mTypefaceTable.length;

            btn_addFontWeight.setEnabled(true);
            //txt_read.setTypeface(mTypefaces.get(count));      //mask by v1.1.5

            outFontWeight = count;      //傳送typeface        //add by v1.1.5

            txt_fontWidth.setText(fontWightName[count]);
            txt_showFontWeight.setText(fontWightName[count]);

            if (count <= 0) {
                btn_subFontWeight.setEnabled(false);
            }

        }
    };

    private Button.OnClickListener mSaveInformation = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            try {
                generateCsvFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    /* mask by v1.1.5 begin
    *      浮動視窗功能設定
    *      於v1.1.5放棄此功能

    private void popupWindowInit() {

        //自訂義浮動視窗layout
        View popupView = getLayoutInflater().inflate(R.layout.gui_test1, null);
        //浮動視窗初始設定
        mPopupWindow = new PopupWindow(popupView, TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, true);
        //點擊空白處關閉視窗
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        //findView
        txt_fontSize = (TextView) popupView.findViewById(R.id.txt_fontSize);
        txt_fontWeight = (TextView) popupView.findViewById(R.id.txt_fontWeight);

        btn_addSize = (Button)popupView.findViewById(R.id.btn_addSize);
        btn_subSize = (Button)popupView.findViewById(R.id.btn_subSize);
        btn_nextWeight = (ImageButton)popupView.findViewById(R.id.btn_nextWeight);
        btn_backWeight = (ImageButton)popupView.findViewById(R.id.btn_backWeight);

        //Listener
        btn_addSize.setOnClickListener(mBtnAddSizeListener);
        btn_subSize.setOnClickListener(mBtnSubSizeListener);
        btn_nextWeight.setOnClickListener(mBtnNextWeightListener);
        btn_backWeight.setOnClickListener(mBtnBackWeightListener);

    }

     *      浮動視窗物件監聽器

    private Button.OnClickListener mBtnAddSizeListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            btn_subSize.setVisibility(View.VISIBLE);
            setFontSize(txt_fontSize,inputTextView,mSize + 2);
        }
    };

    private Button.OnClickListener mBtnSubSizeListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            btn_addSize.setVisibility(View.VISIBLE);
            setFontSize(txt_fontSize,inputTextView,mSize - 2);
        }
    };

    private Button.OnClickListener mBtnNextWeightListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    private Button.OnClickListener mBtnBackWeightListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };
    //浮動視窗物件監聽器 (end)

    private Button.OnClickListener mFontText1Listener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

    //            inputTextView = txt_read1;
    //
    //            txt_fontSize.setText(String.valueOf(txt_read1.getTextSize()));

            // 显示浮动窗口
            mPopupWindow.showAsDropDown(v);
        }
    };

    private Button.OnClickListener mFontText2Listener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            // 显示浮动窗口
            mPopupWindow.showAsDropDown(v);
        }
    };

    private Button.OnClickListener mFontText3Listener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            // 显示浮动窗口
            mPopupWindow.showAsDropDown(v);
        }
    };
    mask by v1.1.5 end*/

    private Button.OnClickListener mFontShow = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MyActivity.this,MyFontShow.class);

            //new一個Bundle物件，並將要傳遞的資料傳入
            Bundle bundle = new Bundle();
            bundle.putInt("fontWeight",outFontWeight );//傳遞Int
            bundle.putBoolean("background",outBackground);//傳遞Boolean

            //將Bundle物件傳給intent
            intent.putExtras(bundle);

            startActivity(intent);
        }
    };

    private SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            if (fromUser) {

                int tmpInt = seekBar.getProgress();
                //System.out.println(tmpInt);
                // 51 (seek scale) * 5 = 255 (max brightness)
                // Old way

                android.provider.Settings.System.putInt(getContentResolver(),
                        android.provider.Settings.System.SCREEN_BRIGHTNESS,
                        tmpInt); // 0-255
                tmpInt = Settings.System.getInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS, -1);

                // Cupcake way..... sucks
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                // lp.screenBrightness = 1.0f;
                // Float tmpFloat = (float)tmpInt / 255;
                if (0<= tmpInt && tmpInt <= 255) {
                    lp.screenBrightness = tmpInt;
                }
                getWindow().setAttributes(lp);

                txt_defaultLight.setText(Integer.toString(tmpInt));
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //default add
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //default add
        }
    };
    // 關閉螢幕自動亮度調整 layout畫面調整
    private void stopAutoBrightnessLayoutInit() {
        mSeekBar.setVisibility(View.VISIBLE);
        txt_mSeekBarTitle.setVisibility(View.VISIBLE);
        txt_defaultLight_note.setVisibility(View.VISIBLE);
        //mTogBtn.setChecked(false);
    }
    // 開啟螢幕自動亮度調整 layout畫面調整
    private void startAutoBrightnessLayoutInit() {
        mSeekBar.setVisibility(View.INVISIBLE);
        txt_mSeekBarTitle.setVisibility(View.INVISIBLE);
        txt_defaultLight_note.setVisibility(View.INVISIBLE);
        txt_defaultLight.setText(R.string.light_msg);
        //mTogBtn.setChecked(true);

    }

    // 關閉螢幕自動亮度調整
    private void stopAutoBrightness() {
        Settings.System.putInt(this.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

        stopAutoBrightnessLayoutInit();

        checkAutoBringOFF = true;

    }
    // 開啟螢幕自動亮度調整
    private void startAutoBrightness() {
        Settings.System.putInt(this.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);

        startAutoBrightnessLayoutInit();

        checkAutoBringOFF = false;
    }
    //add by v1.1.1 end
    //seekBar取得亮度並顯示
    private void seekSet() {
        //取得螢幕亮度
        try {
            int brightness = Settings.System.getInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS);
            mSeekBar.setProgress(brightness);
            //顯示
            txt_defaultLight.setText(Integer.toString(brightness));
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init() {

        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);   //判斷sd記憶卡是否存在

        if (sdCardExist) {

            /*取得日期*/
            Calendar c = Calendar.getInstance();//建立抓日期物件c
            int mYear = c.get(Calendar.YEAR);//年
            int mMonth = c.get(Calendar.MONTH);//月
            int mDay = c.get(Calendar.DAY_OF_MONTH);//日
            /*mask by v1.1.1 begin
            File file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/save");
            //判斷文件夾save是否存在,如果不存在則建立文件夾
            if (!file1.exists()) {
                file1.mkdirs();
            }
            mask by v1.1.1 end*/
            saveCSVFilePath = Environment.getExternalStorageDirectory().getPath() +
                    "/" +
                    getString(R.string.app_name)+
                    "/save/" +
                    Integer.toString(mYear) +
                    Integer.toString(mMonth +1 ) +
                    Integer.toString(mDay);

            File file = new File(saveCSVFilePath);

            //判斷文件夾"日期目錄"是否存在,如果不存在則建立文件夾
            if (!file.exists()) {
                boolean mkDirs = file.mkdirs();

                if (!mkDirs) {
                    Log.e("Arphic Log","Create save dir error");
                }
            }

        }

        File checkFile = new File(saveCSVFilePath);

        long totalFile = 0;
        //避免存檔檔名重複
        if (checkFile.isDirectory()) {

            File FList[] = checkFile.listFiles();
            totalFile = FList.length;
        } else {
            Log.d("Arphic Log","save dir path is not directory");
        }
        //Log.d("Arphic Log", Long.toString(totalFile));
        saveCountNum = totalFile;

        //typefaceWeight = Typeface.create("multi-weight", Typeface.NORMAL);      //mask by v1.0.1 end
        sensorInit();
        //setFontSize(DEFAULT_FONT_SIZE);       //mask by v1.1.0

        //開啟自動調整亮度
        startAutoBrightness();

        //Typeface tf = Typeface.createFromAsset(getAssets(), mTypefaceTable[0]);      //if use mTypeface[0] will be error  //mask by v1.1.5
        //txt_read.setTypeface(tf);     //mask by v1.1.5

        //啟動觸碰捲動
        //txt_read.setMovementMethod(ScrollingMovementMethod.getInstance());        文章太長不會斷行  //mask by v1.1.5

        txt_fontWidth.setText(fontWightName[0]);        //add by v1.1.0
        txt_showFontWeight.setText(fontWightName[0]);        //add by v1.1.5
        //txt_background.setText(R.string.back_black);        //add by v1.1.0

        //浮動視窗功能初始化
        //popupWindowInit();        //mask by v1.1.5

    }

    private void seekBarInit() {

        //設定最大值
        mSeekBar.setMax(255);

        seekSet();
    }
    //add by v1.1.0 end

    private void sensorInit() {

        // Android 所有的感應器的統一介面
        this.mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        // 取得亮度感應器
        List<Sensor> list = this.mgr.getSensorList(Sensor.TYPE_LIGHT);
        // 我的手機啊，你為什麼不支援亮度感應器
        if (list.isEmpty()) {
            String msg = "不支援亮度感應器";
            Log.e("Arphic Log", msg);
            //txt_read.setText(msg);        //mask by v1.1.5
            return;
        }
        this.sensor = list.get(0);

    }

    /* mask by v1.1.0 begin
    private void sensorCheck(float even) {

        Locale localWeight;     //add by v1.0.1
        if (even == 40) {
            //txt.setTypeface(mTypefaces.get(4));       //mask by v1.0.1

            //add by v1.0.1 begin
            txt_read.setTypeface(typefaceWeight);
            localWeight = new Locale("zh", "tw", "1001");       //weight : Light
            txt_read.setTextLocale(localWeight);
            //add by v1.0.1 end

        } else if (even == 90) {
            //txt.setTypeface(mTypefaces.get(3));       //mask by v1.0.1

            //add by v1.0.1 begin
            txt_read.setTypeface(typefaceWeight);
            localWeight = new Locale("zh", "tw", "1002");       //weight : Regular
            txt_read.setTextLocale(localWeight);
            //add by v1.0.1 end

        } else if (even == 160) {
            //txt.setTypeface(mTypefaces.get(2));       //mask by v1.0.1

            //add by v1.0.1 begin
            txt_read.setTypeface(typefaceWeight);
            localWeight = new Locale("zh", "tw", "1003");       //weight : Medium
            txt_read.setTextLocale(localWeight);
            //add by v1.0.1 end

        } else if (even == 225) {
            //txt.setTypeface(mTypefaces.get(1));       //mask by v1.0.1

            //add by v1.0.1 begin
            txt_read.setTypeface(typefaceWeight);
            localWeight = new Locale("zh", "tw", "1004");       //weight : Bold
            txt_read.setTextLocale(localWeight);
            //add by v1.0.1 end

        } else if (even == 320) {
            //txt.setTypeface(mTypefaces.get(0));       //mask by v1.0.1

            //add by v1.0.1 begin
            txt_read.setTypeface(typefaceWeight);
            localWeight = new Locale("zh", "tw", "1005");       //weight : Black
            txt_read.setTextLocale(localWeight);
            //add by v1.0.1 end

        } else {
            txt_read.setTypeface(Typeface.DEFAULT);
        }
    }
    mask by v1.1.0 end*/

    private void findView(){

        //add by v1.1.0 begin
        //mApp = (RelativeLayout)findViewById(R.id.mApp);       //mask by v1.1.5

        //txt_read = (TextView)findViewById(R.id.txt_show_article);     //mask by v1.1.5
        txt_background = (TextView)findViewById(R.id.txt_show_background);
        txt_defaultLight = (TextView)findViewById(R.id.txt_show_defaultLight);
        txt_fontWidth = (TextView)findViewById(R.id.txt_show_fontWidth);
        //txt_outLux = (TextView)findViewById(R.id.txt_show_outLux);        //mask by v1.1.1
        txt_mSeekBarTitle = (TextView)findViewById(R.id.txt_title_light);       //add by v1.1.1
        txt_defaultLight_note = (TextView)findViewById(R.id.txt_not_defaultLight);      //add by v1.1.1
        txt_showFontWeight = (TextView)findViewById(R.id.txt_showFontWeight);


        //btn_background = (Button)findViewById(R.id.btn_background);       //mask by v1.1.5
        btn_background = (ToggleButton)findViewById(R.id.btn_background);
        btn_addFontWeight = (Button)findViewById(R.id.btn_addFontWeight);
        btn_subFontWeight = (Button)findViewById(R.id.btn_subFontWeight);
        btn_saveInformation = (Button)findViewById(R.id.btn_save);

        mSeekBar = (SeekBar)findViewById(R.id.seekBar);
        //add by v1.1.0 end
        mTogBtn = (ToggleButton)findViewById(R.id.toggleButton);        //add by 1.1.1

        /* mask by v1.1.0 begin
        fontSizeMsg = (TextView)findViewById(R.id.textView2);
        fontSize = (ZoomControls)findViewById(R.id.zoomControls);
        lightValue = (TextView)findViewById(R.id.textView3);
        mask by v1.1.0 end*/

        btn_show = (Button)findViewById(R.id.btn_show);
    }

    private void listener() {
        /* mask by v1.1.0 begin
        fontSize.setOnZoomInClickListener(mFontSizeAddListener);
        fontSize.setOnZoomOutClickListener(mFontSizeSubListener);
        mask by v1.1.0 end */

        //add by v1.1.0 begin
        btn_background.setOnClickListener(mBackgroundListener);
        btn_addFontWeight.setOnClickListener(mAddFontWeight);
        btn_subFontWeight.setOnClickListener(mSubFontWeight);
        btn_saveInformation.setOnClickListener(mSaveInformation);

        mSeekBar.setOnSeekBarChangeListener(mSeekBarListener);
        //add by v1.1.0 end
        mTogBtn.setOnClickListener(mTogBtnListener);        //add by v1.1.1

        btn_show.setOnClickListener(mFontShow);


    }

    @Override
    protected void onResume() {
        super.onResume();

        // 一定要在這註冊
        if (this.sensor != null) {
            this.mgr.registerListener(this, this.sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (checkAutoBringOFF) {
            seekSet();
        }

    }

    @Override
    protected void onPause() {

        super.onPause();
        // 一定要在這解註冊
        if (this.sensor != null) {
            this.mgr.unregisterListener(this, this.sensor);
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView(R.layout.main_sp4);

        //初始化Typeface
        typefacesInit(getAssets());

        findView();

        listener();

        init();

        //loadFile(filePath);       //mask by v1.0.1
        //loadFile();     //add by v1.0.1

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //sensorCheck(event.values[0]);     //mask by v1.1.0
        //txt_outLux.setText(String.valueOf(event.values[0]));      //mask by v1.1.1

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //default add
    }

}

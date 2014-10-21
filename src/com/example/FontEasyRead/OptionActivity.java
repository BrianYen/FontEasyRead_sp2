package com.example.FontEasyRead;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Brian on 2014/10/20.
 */

public class OptionActivity extends Activity {

    private Button btn_addSize;
    private Button btn_subSize;
    private Button btn_yes;
    private Button btn_no;
    private ImageButton btn_nextWeight;
    private ImageButton btn_backWeight;

    private Button.OnClickListener mBtnYesListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
/*
            Intent intent = getIntent();
            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            intent.putExtras(bundle);
            setResult(requestCode, intent); //requestCode需跟A.class的一樣
            B.this.finish();
*/
        }
    };

    private Button.OnClickListener mBtnNoListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            OptionActivity.this.finish();
        }
    };

    private Button.OnClickListener mBtnSubSizeListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    private Button.OnClickListener mBtnAddSizeListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

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

    private void findView(){
        btn_addSize = (Button)findViewById(R.id.btn_addSize);
        btn_subSize = (Button)findViewById(R.id.btn_subSize);
//        btn_yes = (Button)findViewById(R.id.btn_yes);
//        btn_no = (Button)findViewById(R.id.btn_no);
        btn_nextWeight = (ImageButton)findViewById(R.id.btn_nextWeight);
        btn_backWeight = (ImageButton)findViewById(R.id.btn_backWeight);

    }

    private void listener() {

        btn_yes.setOnClickListener(mBtnYesListener);
        btn_no.setOnClickListener(mBtnNoListener);
        btn_addSize.setOnClickListener(mBtnAddSizeListener);
        btn_subSize.setOnClickListener(mBtnSubSizeListener);
        btn_nextWeight.setOnClickListener(mBtnNextWeightListener);
        btn_backWeight.setOnClickListener(mBtnBackWeightListener);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_test1);

        findView();
        listener();
    }

}

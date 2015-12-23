package com.example.administrator.register;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegisterActivity extends Activity {
    private static final String[] CHARS = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };
    private List<String> mList = null;
    private Button mButton_reg;
    private Button mButton_yzm;
    private TextView mTextView1;
    private TextView mTextView2;
    private EditText mText_number;
    private EditText mText_yzm;
    private EditText mText_password;
    private EditText mText_password2;
    private EditText mText_name;
    private CheckBox mCheckBox;
    private String yzm = null;
    private MyAsyncTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mButton_reg = (Button) findViewById(R.id.btn_reg);
        mButton_yzm = (Button) findViewById(R.id.btn_yzm);
        mTextView1 = (TextView) findViewById(R.id.tv);
        mTextView2 = (TextView) findViewById(R.id.tv_title);
        mText_number = (EditText) findViewById(R.id.edit_number);
        mText_yzm = (EditText) findViewById(R.id.edit_yzm);
        mText_password = (EditText) findViewById(R.id.edit_password);
        mText_password2 = (EditText) findViewById(R.id.edit_password2);
        mText_name = (EditText) findViewById(R.id.edit_name);
        mCheckBox =(CheckBox)findViewById(R.id.cb);
        mList = new ArrayList<String>();
        for (int i = 0; i < CHARS.length; i++) {
            mList.add(CHARS[i]);
        }
        mButton_reg.setBackgroundResource(R.drawable.corner_gray);
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCheckBox.isChecked()==true){
                    mButton_reg.setEnabled(true);
                    mButton_reg.setBackgroundResource(R.drawable.selector2);
                    Toast.makeText(getApplicationContext(), "isChecked",
                            Toast.LENGTH_SHORT).show();
                }else{
                    mButton_reg.setEnabled(false);
                    mButton_reg.setBackgroundResource(R.drawable.corner_gray);
                    Toast.makeText(getApplicationContext(), "noChecked",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        mButton_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mText_number.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入手机号",
                            Toast.LENGTH_SHORT).show();
                } else if (mText_number.getText().toString().length() != 11) {
                    Toast.makeText(getApplicationContext(), "手机号位数不为11位",
                            Toast.LENGTH_SHORT).show();
                } else if (mText_yzm.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入验证码",
                            Toast.LENGTH_SHORT).show();
                } else if (mText_password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "请设置登录密码",
                            Toast.LENGTH_SHORT).show();
                } else if (mText_password.getText().toString().length() < 6) {
                    Toast.makeText(getApplicationContext(), "登录密码位置至少6位",
                            Toast.LENGTH_SHORT).show();
                } else if (mText_password2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "请确认登录密码",
                            Toast.LENGTH_SHORT).show();
                } else if (!mText_password2.getText().toString().equals(mText_password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "两次输入的密码不一致",
                            Toast.LENGTH_SHORT).show();
                } else if (mText_name.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "请设置昵称",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "注册成功",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        mButton_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mButton_yzm.getText().equals("获取验证码")) {
                    output();
                    //                    if(mTask!=null){
                    //                        mTask.cancel(true);
                    //                    }
                    mTask = new MyAsyncTask();
                    mTask.execute();
                }
                Toast.makeText(getApplicationContext(), yzm,
                        Toast.LENGTH_SHORT).show();
            }
        });
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "服务条款", Toast.LENGTH_SHORT).show();
            }
        });
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "返回", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 59; i >= 0; i--) {
                publishProgress(i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (values[0] != 0) {
                mButton_yzm.setText(values[0] + "秒后再点");
                yzm = values[0] + "秒后再点";
            } else {
                mButton_yzm.setText("获取验证码");
                //                if(isCancelled()){
                //                    return;
                //                }
            }
        }
    }

    public void output() {
        Random random = new Random();
        yzm = mList.get(random.nextInt(mList.size())) + mList.get(random.nextInt(mList.size())) + mList.get(random.nextInt(mList.size())) + mList.get(random.nextInt(mList.size())) + mList.get(random.nextInt(mList.size())) + mList.get(random.nextInt(mList.size()));
    }


}

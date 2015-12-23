package com.example.administrator.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button zhuce;
    private TextView forget,top;
    private EditText username, password;
    private CheckBox rem_pw, auto_login;
    private ImageButton btnQuit;
    private String userNameValue,passwordValue;
    private SharedPreferences sp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.account);
        password=(EditText)findViewById(R.id.pwdtext);
        rem_pw=(CheckBox)findViewById(R.id.ji);
        top=(TextView)findViewById(R.id.top);
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"返回通道", Toast.LENGTH_LONG).show();

            }
        });
        auto_login=(CheckBox)findViewById(R.id.zidong);
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);

        //判断记住密码多选框的状态
        if(sp.getBoolean("ISCHECK", false))
        {
            //设置默认是记录密码状态
            rem_pw.setChecked(true);
            username.setText(sp.getString("USER_NAME", ""));
            password.setText(sp.getString("PASSWORD", ""));
            //判断自动登陆多选框状态
            if(sp.getBoolean("AUTO_ISCHECK", false))
            {
                //设置默认是自动登录状态
                auto_login.setChecked(true);
                //跳转界面
                Intent intent = new Intent(MainActivity.this, WelcomeAvtivity.class);
                MainActivity.this.startActivity(intent);

            }
        }






        login=(Button)findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameValue = username.getText().toString();
                passwordValue = password.getText().toString();
                if(userNameValue.equals(""))
                {
                    Toast.makeText(MainActivity.this,"账号不能为空", Toast.LENGTH_SHORT).show();
                }else if(passwordValue.equals(""))
                {
                    Toast.makeText(MainActivity.this,"密码不能为空", Toast.LENGTH_SHORT).show();
                }else if(userNameValue.equals("123")&&passwordValue.equals("123"))
                {
                    Toast.makeText(MainActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                    //登录成功和记住密码框为选中状态才保存用户信息
                    if(rem_pw.isChecked())
                    {
                        //记住用户名、密码、
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("USER_NAME", userNameValue);
                        editor.putString("PASSWORD",passwordValue);
                        editor.commit();
                    }
                    //跳转界面
                    Intent intent = new Intent(MainActivity.this,WelcomeAvtivity.class);
                    MainActivity.this.startActivity(intent);
                    //finish();

            }else{

                    Toast.makeText(MainActivity.this,"用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();
                }
            }
        });
        //监听记住密码多选框按钮事件
        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (rem_pw.isChecked()) {

                    System.out.println("记住密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).commit();

                }else {

                    System.out.println("记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();

                }

            }
        });

        //监听自动登录多选框事件
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (auto_login.isChecked()) {
                    System.out.println("自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

                } else {
                    System.out.println("自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });


        zhuce=(Button)findViewById(R.id.button_zhuce);
        zhuce.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"注册",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        forget=(TextView)findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Forgetpassword.class);
                MainActivity.this.startActivity(intent);
                Toast.makeText(getApplicationContext(),"找回密码",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

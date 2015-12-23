package com.example.administrator.register;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.ProgressBar;


public class StartActivity extends Activity{

  private ProgressBar progressBar;
 private Button backButton;
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.start);

    }


}

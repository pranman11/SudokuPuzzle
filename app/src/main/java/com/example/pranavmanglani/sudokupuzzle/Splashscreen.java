package com.example.pranavmanglani.sudokupuzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Pranav Manglani on 24-02-2017.
 */
public class Splashscreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                Intent i=new Intent(getApplicationContext(),Start2Activity.class);
                startActivity(i);
                finish();
    }
}
package com.example.admin.barcodeapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    private Button scanButton;
    private TextView format, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = findViewById(R.id.btnScanButton);
        format = findViewById(R.id.tvScanFormat);
        content = findViewById(R.id.tvScanContent);

        Timber.plant( new Timber.DebugTree());
        Log.wtf(TAG, "onCreate: ");
        Timber.d( "Timber Message.");
    }

    public void buttonPressed(View view) {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //we have a result

            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            format.setText("FORMAT: " + scanningResult.toString());
            content.setText("CONTENT: " + scanContent);
        } else{
            Toast.makeText(this, "No scan data received!", Toast.LENGTH_SHORT).show();
        }
    }
}

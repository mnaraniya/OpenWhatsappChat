package com.example.android.openwhatsappchat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;
import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

public class MainActivity extends AppCompatActivity {



    //private AwesomeValidation mAwesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mAwesomeValidation = new AwesomeValidation(BASIC);
//        // mAwesomeValidation.setContext(this);  // mandatory for UNDERLABEL style
//
//        mAwesomeValidation.addValidation(this, R.id.number, RegexTemplate.TELEPHONE, R.string.err_tel);
        EditText numberText = (EditText)findViewById(R.id.number);
        final String number = numberText.getText().toString();

        ImageButton clickButton = (ImageButton) findViewById(R.id.button);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWhatsappContact(number);
            }
        });


    }
    public void openWhatsappContact(String number) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }
}

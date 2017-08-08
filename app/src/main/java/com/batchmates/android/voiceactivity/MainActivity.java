package com.batchmates.android.voiceactivity;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE=143;
    private static final String TAG = "Test Voice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testTalk(View view) {

        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Where do you want to go");

        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQUEST_CODE:
                if(resultCode==RESULT_OK && data != null)
                {
                    ArrayList<String> voiceIn= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(this,"1"+voiceIn.get(0)+"1",Toast.LENGTH_LONG).show();
                    String theVoice=voiceIn.get(0).toString();
                    Log.d(TAG, "onActivityResult: "+theVoice);
                    if (theVoice.contentEquals("weather"))
                    {
                        Log.d(TAG, "onActivityResult: Content equal Weather");
                    }
                    if (theVoice.contentEquals("calendar"))
                    {
                        Log.d(TAG, "onActivityResult: Content Calender spoken");
                    }
                }
                break;
        }
    }
}

package com.example.mytabspeechtotext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    ConstraintLayout rl;
    TextToSpeech tts;
    private ImageButton btn;
    private final int REQ_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl = (ConstraintLayout) findViewById(R.id.rl);

        tv = (TextView) findViewById(R.id.tv_speechtotextv);
        btn = (ImageButton) findViewById(R.id.button_speechtotext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();

            }
        });

    }

    private void promptSpeechInput()
    {
        // passing an intent of recognize speech
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Gets the default language. In our case, english is selected.
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,getString(R.string.speech_prompt));

        try
        {
            startActivityForResult(i,REQ_CODE);
        }catch (ActivityNotFoundException e)
        {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult( int reqCode, int resCode, Intent data)
    {
        super.onActivityResult(reqCode,resCode,data);
        switch(reqCode)
        {
            case REQ_CODE:
                if(resCode == RESULT_OK && data != null)
                {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tv.setText(result.get(0));

                    // change the color of background when green is spoken
                    if(result.get(0).contains("Hello") || result.get(0).contains("Hey") || result.get(0).contains("Hi")|| result.get(0).contains("Namaste"))
                    {
                        ttS("Hello, I'm a bot, how are you?");
                        rl.setBackgroundColor(Color.GREEN);
                    }
                    if(result.get(0).contains("blue"))
                    {
                        rl.setBackgroundColor(Color.BLUE);
                    }
                    if(result.get(0).contains("normal"))
                    {
                        rl.setBackgroundColor(Color.WHITE);
                    }
                }
                break;
        }
    }

    protected void ttS(final String text)
    {
        tts = new android.speech.tts.TextToSpeech(getApplicationContext(), new android.speech.tts.TextToSpeech.OnInitListener()
        {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if (status != android.speech.tts.TextToSpeech.ERROR)
                {
                    tts.setPitch(0.4f);
                    tts.setSpeechRate(1);
                    tts.setLanguage(Locale.US);
                    tts.speak(text, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null, null);

                }
            }
        });
    }
}
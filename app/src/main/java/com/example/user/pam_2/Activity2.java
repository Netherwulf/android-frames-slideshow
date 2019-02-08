package com.example.user.pam_2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Activity2 extends Activity {
    public static final int STATE_FORWARD = 1;
    public static final int STATE_BACKWARDS = 2;
    ImageView imageView;
    TextView textView;
    ImageButton imgButton1;
    ImageButton imgButton2;
    int currentDrawableResIndex;
    int timerCounter = 0;
    Timer timer;
    boolean playing = false;
    int state = STATE_FORWARD;

    final int[] imageArray = {R.drawable.film1_klatka_0000_min, R.drawable.film1_klatka_0001_min,
            R.drawable.film1_klatka_0002_min, R.drawable.film1_klatka_0003_min,
            R.drawable.film1_klatka_0004_min, R.drawable.film1_klatka_0005_min,
            R.drawable.film1_klatka_0006_min, R.drawable.film1_klatka_0007_min,
            R.drawable.film1_klatka_0008_min, R.drawable.film1_klatka_0009_min,
            R.drawable.film1_klatka_0010_min, R.drawable.film1_klatka_0011_min,
            R.drawable.film1_klatka_0012_min, R.drawable.film1_klatka_0013_min,
            R.drawable.film1_klatka_0014_min, R.drawable.film1_klatka_0015_min,
            R.drawable.film1_klatka_0016_min, R.drawable.film1_klatka_0017_min,
            R.drawable.film1_klatka_0018_min, R.drawable.film1_klatka_0019_min,
            R.drawable.film1_klatka_0020_min, R.drawable.film1_klatka_0021_min,
            R.drawable.film1_klatka_0022_min, R.drawable.film1_klatka_0023_min,
            R.drawable.film1_klatka_0024_min, R.drawable.film1_klatka_0025_min,
            R.drawable.film1_klatka_0026_min, R.drawable.film1_klatka_0027_min,
            R.drawable.film1_klatka_0028_min, R.drawable.film1_klatka_0029_min,
            R.drawable.film1_klatka_0030_min, R.drawable.film1_klatka_0031_min,
            R.drawable.film1_klatka_0032_min, R.drawable.film1_klatka_0033_min,
            R.drawable.film1_klatka_0034_min, R.drawable.film1_klatka_0035_min,
            R.drawable.film1_klatka_0036_min, R.drawable.film1_klatka_0037_min,
            R.drawable.film1_klatka_0038_min, R.drawable.film1_klatka_0039_min,
            R.drawable.film1_klatka_0040_min, R.drawable.film1_klatka_0041_min,
            R.drawable.film1_klatka_0042_min, R.drawable.film1_klatka_0043_min,
            R.drawable.film1_klatka_0044_min, R.drawable.film1_klatka_0045_min,
            R.drawable.film1_klatka_0046_min, R.drawable.film1_klatka_0047_min,
            R.drawable.film1_klatka_0048_min, R.drawable.film1_klatka_0049_min,
            R.drawable.film1_klatka_0050_min, R.drawable.film1_klatka_0051_min,
            R.drawable.film1_klatka_0052_min, R.drawable.film1_klatka_0053_min,
            R.drawable.film1_klatka_0054_min, R.drawable.film1_klatka_0055_min,
            R.drawable.film1_klatka_0056_min, R.drawable.film1_klatka_0057_min,
            R.drawable.film1_klatka_0058_min, R.drawable.film1_klatka_0059_min,
            R.drawable.film1_klatka_0060_min, R.drawable.film1_klatka_0061_min,
            R.drawable.film1_klatka_0062_min, R.drawable.film1_klatka_0063_min,
            R.drawable.film1_klatka_0064_min, R.drawable.film1_klatka_0065_min,
            R.drawable.film1_klatka_0066_min, R.drawable.film1_klatka_0067_min,
            R.drawable.film1_klatka_0068_min, R.drawable.film1_klatka_0069_min,
            R.drawable.film1_klatka_0070_min, R.drawable.film1_klatka_0071_min,
            R.drawable.film1_klatka_0072_min, R.drawable.film1_klatka_0073_min,
            R.drawable.film1_klatka_0074_min, R.drawable.film1_klatka_0075_min,
            R.drawable.film1_klatka_0076_min, R.drawable.film1_klatka_0077_min,
            R.drawable.film1_klatka_0078_min, R.drawable.film1_klatka_0079_min,
            R.drawable.film1_klatka_0080_min, R.drawable.film1_klatka_0081_min,
            R.drawable.film1_klatka_0082_min, R.drawable.film1_klatka_0083_min,
            R.drawable.film1_klatka_0084_min, R.drawable.film1_klatka_0085_min,
            R.drawable.film1_klatka_0086_min, R.drawable.film1_klatka_0087_min,
            R.drawable.film1_klatka_0088_min, R.drawable.film1_klatka_0089_min,
            R.drawable.film1_klatka_0090_min, R.drawable.film1_klatka_0091_min,
            R.drawable.film1_klatka_0092_min, R.drawable.film1_klatka_0093_min,
            R.drawable.film1_klatka_0094_min, R.drawable.film1_klatka_0095_min,
            R.drawable.film1_klatka_0096_min, R.drawable.film1_klatka_0097_min,
            R.drawable.film1_klatka_0098_min, R.drawable.film1_klatka_0099_min,
            R.drawable.film1_klatka_0100_min, R.drawable.film1_klatka_0101_min,
            R.drawable.film1_klatka_0102_min, R.drawable.film1_klatka_0103_min,
            R.drawable.film1_klatka_0104_min, R.drawable.film1_klatka_0105_min,
            R.drawable.film1_klatka_0106_min, R.drawable.film1_klatka_0107_min,
            R.drawable.film1_klatka_0108_min, R.drawable.film1_klatka_0109_min,
            R.drawable.film1_klatka_0110_min, R.drawable.film1_klatka_0111_min,
            R.drawable.film1_klatka_0112_min, R.drawable.film1_klatka_0113_min,
            R.drawable.film1_klatka_0114_min, R.drawable.film1_klatka_0115_min,
            R.drawable.film1_klatka_0116_min, R.drawable.film1_klatka_0117_min,
            R.drawable.film1_klatka_0118_min, R.drawable.film1_klatka_0119_min,
            R.drawable.film1_klatka_0120_min, R.drawable.film1_klatka_0121_min,
            R.drawable.film1_klatka_0122_min, R.drawable.film1_klatka_0123_min,
            R.drawable.film1_klatka_0124_min, R.drawable.film1_klatka_0125_min,
            R.drawable.film1_klatka_0126_min, R.drawable.film1_klatka_0127_min,
            R.drawable.film1_klatka_0128_min, R.drawable.film1_klatka_0129_min,
            R.drawable.film1_klatka_0130_min, R.drawable.film1_klatka_0131_min,
            R.drawable.film1_klatka_0132_min, R.drawable.film1_klatka_0133_min,
            R.drawable.film1_klatka_0134_min, R.drawable.film1_klatka_0135_min,
            R.drawable.film1_klatka_0136_min, R.drawable.film1_klatka_0137_min,
            R.drawable.film1_klatka_0138_min, R.drawable.film1_klatka_0139_min,
            R.drawable.film1_klatka_0140_min, R.drawable.film1_klatka_0141_min,
            R.drawable.film1_klatka_0142_min, R.drawable.film1_klatka_0143_min,
            R.drawable.film1_klatka_0144_min};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        imgButton1 = findViewById(R.id.imgButton1);
        imgButton2 = findViewById(R.id.imgButton2);

        imgButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Porcelana ozdobna - 88.99 zł\nW Europie technologię produkcji porcelany wynalazł w pocz. XVIII w. Ehrenfried Walther von Tschirnhaus. Po jego śmierci prace kontynuował w Dreźnie i Miśni jego uczeń Johann Friedrich Böttger, alchemik elektora saskiego i króla Polski Augusta II Mocnego, kolekcjonera chińskiej porcelany. Böttger wyprodukował " +
                        "twardą białą porcelanę w 1709; od tego czasu datuje się produkcję porcelany europejskiej. Było to osiągnięcie bardzo ważne, także ze względu na rosnące w tamtym czasie zainteresowanie chińszczyzną. orcelanę wyrabianą w Saksonii nazywano „białym złotem”, gdyż zastępowała złoto jako królewski podarunek, osiągając ceny porównywalne do kruszcu.");
            }
        });
        imgButton1.setX(300);
        imgButton1.setY(410);

        imgButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Krzesło + Biurko z kości słoniowej  1345.45 zł\nOd starożytności używana do wytwarzania ozdób i drobnych przedmiotów użytkowych. Stosowanie jej zmalało w okresie renesansu, by powrócić w XVII i XVIII wieku. Wtedy to, obok dotychczasowych zastosowań, zaczęto używać kości słoniowej do inkrustacji i jako podłoże pod malowane miniatury.\n" +
                        "W XIX w. i w pierwszych dekadach XX w. dobrze zachowane ciosy mamutów (zwane błędnie kłami) z Syberii pozyskiwano na dużą skalę jako odmianę kości słoniowej. Na przełomie XIX/XX w. w Rosji skupiono przeszło 30 ton kości słoniowej z mamuta.");
            }
        });
        imgButton2.setX(350);
        imgButton2.setY(460);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int stopTime = preferences.getInt("stopTime", 0);
        int duration = preferences.getInt("duration", 6000);
        double partOfVideo = ((double) stopTime / (double) duration) * 144.0;
        if (partOfVideo > 144) {
            partOfVideo = 144;
        }
        if (partOfVideo < 0) {
            partOfVideo = 0;
        }
        final int slideNumber = Math.round((float) partOfVideo);
        currentDrawableResIndex = slideNumber;
        imageView.setOnTouchListener(new View.OnTouchListener() {
            int oldX = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        oldX = (int) motionEvent.getX();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        Log.d("swipeX", oldX - motionEvent.getX() + "");
                        if (oldX - motionEvent.getX() > 0) {
                            textView.setText("");
                            initializeTimer(STATE_BACKWARDS);
                            break;
                        }
                        if (oldX - motionEvent.getX() < 0) {
                            initializeTimer(STATE_FORWARD);
                            break;
                        }
                    case MotionEvent.ACTION_UP:
                        stopTimer();
                        break;
                }
                return true;
            }
        });
        textView.setMinimumWidth(750);
        textView.setMaxWidth(750);
        imageView.setMinimumWidth(600);
        imageView.setMaxWidth(600);
        imageView.setImageResource(imageArray[slideNumber]);
        imageView.bringToFront();
    }

    public Activity getContext() {
        return this;
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            playing = false;
        }
    }

    private void initializeTimer(int state) {
        if (this.state != state) {
            stopTimer();
        }
        if (!playing) {
            this.state = state;
            timer = new Timer();
            timerCounter = 1;
            if (state == STATE_FORWARD) {
                Log.d("STATE", "FORWARD");
                timer.schedule(new ForwardTickClass(getContext()), 3, 30);
            } else {
                Log.d("STATE", "BACKWARDS");
                timer.schedule(new BackwardTickClass(getContext()), 3, 30);
            }
        }
    }

    protected void onStop() {
        super.onStop();
    }

    public void onBackPressed(){
        super.onBackPressed();

        double stopTimeDouble = ((double)currentDrawableResIndex/144.0) * 6000.0;
        int stopTime = (int) stopTimeDouble;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("stopTime",stopTime);
        editor.apply();
    }

    private class ForwardTickClass extends TimerTask {
        Activity context;

        protected ForwardTickClass(Activity context) {
            this.context = context;
        }

        @Override
        public void run() {
            playing = true;
            Log.d("", timerCounter + "   " + currentDrawableResIndex);
            if (timerCounter > 0) {
                if (currentDrawableResIndex > 53 && currentDrawableResIndex < 68) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imgButton1.bringToFront();
                        }
                    });
                } else {
                    if (currentDrawableResIndex > 112 && currentDrawableResIndex < 127) {
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imgButton2.bringToFront();
                            }
                        });
                    } else {
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.bringToFront();
                            }
                        });
                    }
                }
                if (currentDrawableResIndex >= 0) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("index", currentDrawableResIndex + "");
                            if(currentDrawableResIndex >=0 && currentDrawableResIndex <= 143){
                                imageView.setImageResource(imageArray[currentDrawableResIndex]);
                            }                        }
                    });
                }
                if(currentDrawableResIndex < imageArray.length - 1){
                    currentDrawableResIndex++;
                }else {
                    currentDrawableResIndex = 0;
                }
            } else {
                stopTimer();
            }
        }
    }

    private class BackwardTickClass extends TimerTask {
        Activity context;

        protected BackwardTickClass(Activity context) {
            this.context = context;
        }

        @Override
        public void run() {
            playing = true;
            Log.d("", timerCounter + "   " + currentDrawableResIndex);
            if (timerCounter > 0) {
                if (currentDrawableResIndex > 53 && currentDrawableResIndex < 68) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imgButton1.bringToFront();
                        }
                    });
                } else {
                    if (currentDrawableResIndex > 112 && currentDrawableResIndex < 127) {
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imgButton2.bringToFront();
                            }
                        });
                    } else {
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.bringToFront();
                            }
                        });
                    }
                }
                if (currentDrawableResIndex >= 0) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("index", currentDrawableResIndex + "");
                            if(currentDrawableResIndex >=0 && currentDrawableResIndex <= 143){
                                imageView.setImageResource(imageArray[currentDrawableResIndex]);
                            }
                        }
                    });
                }
                if(currentDrawableResIndex > 0){
                    currentDrawableResIndex--;
                }else {
                    currentDrawableResIndex = imageArray.length - 1;
                }
            } else {
                stopTimer();
            }
        }
    }
}
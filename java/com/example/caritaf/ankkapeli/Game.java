package com.example.caritaf.ankkapeli;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;


public class Game extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ei titteliä
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Koko ruudun näkymän asetus
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Näyttö GamePanel luokka
        setContentView(new GamePanel(this));

    }


}

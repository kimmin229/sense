package com.example.samplemygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    int soundPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView main_img = (ImageView)findViewById(R.id.imageView);
        Glide.with(this).load(R.raw.main).into(main_img);



        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        soundPlay = soundPool.load(this, R.raw.btn_ms, 1);


        Button imageButton = (Button) findViewById(R.id.game3_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Character.class);
                startActivity(intent);
                soundPool.play(soundPlay, 3,3,0,0,1);

            }
        });


        Button imageButton1 = (Button) findViewById(R.id.game1_btn);
        imageButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Game1.class);
                startActivity(intent);
                mediaPlayer.pause();
                soundPool.play(soundPlay, 3,3,0,0,1);
            }
        });

        Button imageButton2 = (Button) findViewById(R.id.game2_btn);
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Game2.class);
                startActivity(intent);
                mediaPlayer.pause();
                soundPool.play(soundPlay, 3,3,0,0,1);
            }
        });

    }

}
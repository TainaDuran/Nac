package br.com.duranduran.nac_tainaduranpaixao_rm76996;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void play (View v){
        Intent telaPlayer = new Intent(this, PlayerActivity.class);
        final MediaPlayer bt = MediaPlayer.create(this, R.raw.button1);
        bt.start();
        startActivity(telaPlayer);
        finish();
    }

    public void about (View v){
        Intent telaAbout = new Intent(this, AboutActivity.class);
        final MediaPlayer bt = MediaPlayer.create(this, R.raw.button1);
        bt.start();
        startActivity(telaAbout);
        finish();
    }
}

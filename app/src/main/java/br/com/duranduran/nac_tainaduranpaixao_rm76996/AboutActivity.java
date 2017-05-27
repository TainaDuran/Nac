package br.com.duranduran.nac_tainaduranpaixao_rm76996;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void back (View v){
        Intent telaMenu = new Intent(this, MenuActivity.class);
        final MediaPlayer bt = MediaPlayer.create(this, R.raw.button2);
        bt.start();
        startActivity(telaMenu);
        finish();
    }
}

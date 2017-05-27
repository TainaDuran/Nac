package br.com.duranduran.nac_tainaduranpaixao_rm76996;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import br.com.duranduran.nac_tainaduranpaixao_rm76996.Roda;
import info.hoang8f.widget.FButton;

public class SlotMachineActivity extends AppCompatActivity {

    private ImageView ivSlot1, ivSlot2, ivSlot3,imProfile;
    private Roda slot1, slot2, slot3;
    private FButton btPlayslot;

    private TextView tvPlayer, tvChips, tvTextChips, tvTimer;

    private int chips;
    private int count = 0;

    public static final Random RANDOM = new Random();
    public static long randomLong(long lower, long upper){
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);

        ivSlot1 = (ImageView) findViewById(R.id.ivSlot1);
        ivSlot2 = (ImageView) findViewById(R.id.ivSlot2);
        ivSlot3 = (ImageView) findViewById(R.id.ivSlot3);

        imProfile = (ImageView) findViewById(R.id.imProfile);

        tvPlayer = (TextView) findViewById(R.id.tvPlayer);
        tvChips = (TextView) findViewById(R.id.tvChips);
        tvTextChips = (TextView) findViewById(R.id.tvTextChips);
        tvTimer = (TextView) findViewById(R.id.tvTimer);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/montalt.ttf");
        tvPlayer.setTypeface(myCustomFont);
        tvChips.setTypeface(myCustomFont);
        tvTextChips.setTypeface(myCustomFont);
        tvTimer.setTypeface(myCustomFont);

        btPlayslot = (FButton) findViewById(R.id.btPlayslot);

        String nome = getIntent().getStringExtra("PLAYER");
        tvPlayer.setText(nome);

        String nFichas = getIntent().getStringExtra("N_FICHAS");
        chips = Integer.parseInt(nFichas);
        tvChips.setText(String.valueOf(chips));

        imProfile = (ImageView) findViewById(R.id.imProfile);

        boolean mal = getIntent().getExtras().getBoolean("MAL");
        boolean fem = getIntent().getExtras().getBoolean("FEM");

        if (mal){
            imProfile.setImageResource(R.drawable.boy);
        }

        if (fem){
            imProfile.setImageResource(R.drawable.girl);
        }

        Timer T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tvTimer.setText("Time: " + count);
                        count++;
                    }
                });
            }
        }, 1000, 1000);


    }

    public void playSlot(View v){

        final MediaPlayer bt = MediaPlayer.create(this, R.raw.slotmachine);
        bt.start();

        if(chips > 0){

            chips--;
            tvChips.setText(String.valueOf(chips));

            rodarSlot1();
            rodarSlot2();
            rodarSlot3();

            btPlayslot.setEnabled(false);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    slot1.paraDeRodar();
                    slot2.paraDeRodar();
                    slot3.paraDeRodar();

                    result();

                    btPlayslot.setEnabled(true);

                }
            }, 3000);

        } else {
            Toast.makeText(this, "The end of chips :(", Toast.LENGTH_LONG).show();

        }

    }

    private void result(){
        if(slot1.indiceAtual == slot2.indiceAtual && slot2.indiceAtual == slot3.indiceAtual){
            Toast.makeText(this, "You win! +5", Toast.LENGTH_SHORT).show();
            chips = chips + 5;
            tvChips.setText(String.valueOf(chips));
        } else if(slot1.indiceAtual == slot2.indiceAtual || slot2.indiceAtual == slot3.indiceAtual || slot1.indiceAtual == slot3.indiceAtual){
            Toast.makeText(this, "Good job! +2", Toast.LENGTH_SHORT).show();
            chips = chips + 2;
            tvChips.setText(String.valueOf(chips));
        } else {
            Toast.makeText(this, "You lose!", Toast.LENGTH_SHORT).show();
        }
    }

    private void rodarSlot1(){
        slot1 = new Roda(new Roda.RodaListener(){
            @Override
            public void novaImagem(final int resourceID){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot1.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong(0, 200));
        slot1.start();
    }

    private void rodarSlot2(){
        slot2 = new Roda(new Roda.RodaListener(){
            @Override
            public void novaImagem(final int resouceID){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot2.setImageResource(resouceID);
                    }
                });
            }
        }, 200, randomLong(150, 400));
        slot2.start();
    }

    private void rodarSlot3(){
        slot3 = new Roda(new Roda.RodaListener(){
            @Override
            public void novaImagem(final int resouceID){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot3.setImageResource(resouceID);
                    }
                });
            }
        }, 200, randomLong(300, 600));
        slot3.start();
    }
}

package br.com.duranduran.nac_tainaduranpaixao_rm76996;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class PlayerActivity extends AppCompatActivity {

    private EditText etPlayer;
    private Spinner spFichas;
    private String gender, nFichas;
    private ImageView ivGender;
    private RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        etPlayer = (EditText)findViewById(R.id.etPlayer);
        spFichas = (Spinner)findViewById(R.id.spFichas);
        ivGender = (ImageView)findViewById(R.id.ivGender);
        rgGender = (RadioGroup)findViewById(R.id.rgGender);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.n_fichas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFichas.setAdapter(adapter);

        spFichas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                nFichas = spFichas.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        nFichas = spFichas.getSelectedItem().toString();
    }

    public void start (View v){
        Intent telaGame = new Intent(this, SlotMachineActivity.class);
        final MediaPlayer bt = MediaPlayer.create(this, R.raw.button1);
        bt.start();
        telaGame.putExtra("PLAYER", etPlayer.getText().toString());

        if (gender == "Male"){
            telaGame.putExtra("MAL", true);
        } else if (gender == "Female"){
            telaGame.putExtra("FEM", true);
        }

        telaGame.putExtra("N_FICHAS", nFichas);

        startActivity(telaGame);
        finish();
    }

    public void onRadioButtonClicked(View v) {

        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()) {
            case R.id.rbMale:
                if (checked)
                    ivGender.setImageResource(R.drawable.boy);
                gender = "Male";

                break;
            case R.id.rbFemale:
                if (checked)

                    ivGender.setImageResource(R.drawable.girl);
                gender = "Female";

                break;
        }
    }

}

package id.sch.smktelkom_mlg.tugas01.xirpl5024.malabox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnGenericMotionListener {

    EditText etNamaPengirim;
    TextView tvHasil, tvSyarat;
    Button bv;
    RadioGroup rgKR;
    RadioButton rbPn, rbPo;
    Spinner spKB;
    CheckBox kp, kb, cbid;
    int Syarat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        etNamaPengirim = (EditText) findViewById(R.id.editTextNama);
        rgKR = (RadioGroup) findViewById(R.id.radioGroupKriteria);
        /*rbPn = (RadioButton) findViewById(R.id.radioButtonPerusahaanerusahaan);
        rbPo = (RadioButton) findViewById(R.id.radioButtonPeroranganerorangan);*/
        cbid = (CheckBox) findViewById(R.id.checkBoxIdentitas);
        kp = (CheckBox) findViewById(R.id.checkBoxPengirim);
        kb = (CheckBox) findViewById(R.id.checkBoxBarang);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        spKB = (Spinner) findViewById(R.id.spinnerKB);
        bv = (Button) findViewById(R.id.buttonValidasi);
        tvSyarat = (TextView) findViewById(R.id.textViewSyarat);

        cbid.setOnCheckedChangeListener(this);
        kp.setOnGenericMotionListener(this);
        kb.setOnCheckedChangeListener(this);


        bv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doClick();
            }
        });
    }


    private void doClick() {

        String syarat = "Jumlah syarat Ketentutan yang telah anda validasi adalah :\n ";
        int startlen = syarat.length();
        if (cbid.isChecked()) syarat += cbid.getText() + "\n";
        if (kp.isChecked()) syarat += kp.getText() + "\n";
        if (kb.isChecked()) syarat += kb.getText() + "\n";

        if (syarat.length() == startlen) syarat += "Tidak Ada pada Pilihan";

        if (isValid()) {
            String nama = etNamaPengirim.getText().toString();

            String hasil = null;
            if (rgKR.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(rgKR.getCheckedRadioButtonId());
                hasil = rb.getText().toString();
            }
                    /*if(rbPn.isChecked())
                    {
                        hasil = rbPn.getText().toString();
                    }
                    else if (rbPn.isChecked())
                    {
                        hasil = rbPn.getText().toString();
                    }
                    else if(rbPo.isChecked()){
                        hasil = rbPo.getText().toString();
                    }*/
            tvHasil.setText("Nama Anda : " + nama + "\n" + "Kriteria Pengirim : " + hasil + "\n" + "Kriteria Barang : " + spKB.getSelectedItem().toString());
        }
    }


    private void doProcess() {
        if (isValid()) {
            String nama = etNamaPengirim.getText().toString();

        }
    }

    private boolean isValid() {
        boolean valid = true;
        String nama = etNamaPengirim.getText().toString();

        if (nama.isEmpty()) {
            etNamaPengirim.setError("Nama Belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNamaPengirim.setError("Nama Minimal 3 Karakter");
            valid = false;
        } else {
            etNamaPengirim.setError(null);
        }
        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) Syarat += 1;
        else Syarat -= 2;

        tvSyarat.setText("Jumlah syarat yang dikumpulkan adalah (" + Syarat + " terpilih)");
    }

    @Override
    public boolean onGenericMotion(View v, MotionEvent event) {
        return false;
    }
}
package com.example.phamvanphong_171210861;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText edtGaDi, edtGaDen, edtGiaTien;
    RadioButton rdoKhuHoi, rdoMotChieu;
    Button btnEdit, btnBack;
    DBHelper dbHelper;
    Ticket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtGaDen = (EditText) findViewById(R.id.edtGaDen);
        edtGaDi = (EditText) findViewById(R.id.edtGaDi);
        edtGiaTien = (EditText) findViewById(R.id.edtGiaTien);
        rdoKhuHoi = (RadioButton) findViewById(R.id.rdoKhuHoi);
        rdoMotChieu = (RadioButton) findViewById(R.id.rdoMotChieu);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnBack = (Button) findViewById(R.id.btnBack);

        dbHelper = new DBHelper(EditActivity.this);
        dbHelper.openDB();

        Intent intent = getIntent();
        ticket = (Ticket) intent.getSerializableExtra("TICKET");

        edtGaDi.setText(ticket.getGaDi());
        edtGaDen.setText(ticket.getGaDen());
        edtGiaTien.setText(ticket.getDonGia() + "");
        if (ticket.isTheLoai() == 1)
        {
            rdoKhuHoi.setChecked(true);
        } else {
            rdoMotChieu.setChecked(true);
        }
        rdoKhuHoi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdoKhuHoi.isChecked()){
                    rdoMotChieu.setChecked(false);
                }else{
                    rdoMotChieu.setChecked(true);
                }
            }
        });
        rdoMotChieu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdoMotChieu.isChecked()){
                    rdoKhuHoi.setChecked(false);
                }else{
                    rdoKhuHoi.setChecked(true);
                }
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getValueString(edtGaDi).equals("") || getValueString(edtGaDen).equals("") || getValueString(edtGiaTien).equals(""))
                {
                    Toast.makeText(EditActivity.this, "error", Toast.LENGTH_SHORT).show();
                } else {
                    long price = (long) (Long.parseLong(getValueString(edtGiaTien)) * 2 * 0.95) ;
                    int t = (rdoKhuHoi.isChecked())?1:0;
                    long s = dbHelper.Update(ticket.getId(), getValueString(edtGaDi), getValueString(edtGaDen), price, t);
                    if (s == 0)
                    {
                        Toast.makeText(EditActivity.this, "error", Toast.LENGTH_SHORT).show();
                    } else {
//                        Intent intent = new Intent(EditActivity.this, MainActivity.class);
//                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private String getValueString(EditText edt)
    {
        return edt.getText().toString().trim();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.closeDB();
    }
}

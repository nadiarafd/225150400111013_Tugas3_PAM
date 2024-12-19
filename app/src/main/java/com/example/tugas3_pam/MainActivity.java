package com.example.tugas3_pam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    double angkaSebelumnya = 0;
    boolean operatorDipilih = false;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView layar = findViewById(R.id.tv_hasil);

        // Button angka
        List<Integer> buttonAngkaIds = Arrays.asList(
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9
        );

        for (int id : buttonAngkaIds) {
            Button btn = findViewById(id);
            btn.setOnClickListener(view -> {
                String input = ((Button) view).getText().toString();
                if (layar.getText().toString().equals("0") || operatorDipilih) {
                    layar.setText(input);
                    operatorDipilih = false;
                } else {
                    layar.append(input);
                }
            });
        }

        // Button operator
        List<Integer> buttonOperasiIds = Arrays.asList(
                R.id.btn_tambah, R.id.btn_kurang, R.id.btn_kali, R.id.btn_bagi
        );

        for (int id : buttonOperasiIds) {
            Button btn = findViewById(id);
            btn.setOnClickListener(view -> {
                angkaSebelumnya = Double.parseDouble(layar.getText().toString());
                operator = ((Button) view).getText().toString();
                operatorDipilih = true;
            });
        }

        // Tombol on dan off
        findViewById(R.id.btn_off).setOnClickListener(view -> layar.setVisibility(View.GONE));
        findViewById(R.id.btn_on).setOnClickListener(view -> {
            layar.setVisibility(View.VISIBLE);
            layar.setText("0");
        });

        // Tombol koma
        findViewById(R.id.btn_dec).setOnClickListener(view -> {
            if (!layar.getText().toString().contains(".")) {
                layar.append(".");
            }
        });

        // Tombol hapus
        findViewById(R.id.btn_hapus).setOnClickListener(view -> {
            String teks = layar.getText().toString();
            if (teks.length() > 1) {
                layar.setText(teks.substring(0, teks.length() - 1));
            } else {
                layar.setText("0");
            }
        });

        // Tombol hitung
        findViewById(R.id.btn_hitung).setOnClickListener(view -> {
            try {
                double angkaKedua = Double.parseDouble(layar.getText().toString());
                double hasil = hitung(angkaSebelumnya, angkaKedua, operator);

                layar.setText(String.valueOf(hasil));
                angkaSebelumnya = hasil;
                operatorDipilih = true;
            } catch (Exception e) {
                layar.setText("Error");
            }
        });

        // Tombol restart
        findViewById(R.id.btn_ac).setOnClickListener(view -> {
            angkaSebelumnya = 0;
            operator = "";
            layar.setText("0");
        });
    }

    private double hitung(double a, double b, String op) {
        switch (op) {
            case "/":
                if (b == 0) {
                    return Double.NaN; // Mengembalikan NaN jika pembagian oleh nol
                }
                return a / b;
            case "X":
                return a * b;
            case "-":
                return a - b;
            case "+":
                return a + b;
            default:
                return 0;
        }
    }
}
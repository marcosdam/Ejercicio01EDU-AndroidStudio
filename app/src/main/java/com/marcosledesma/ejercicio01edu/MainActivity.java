package com.marcosledesma.ejercicio01edu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.marcosledesma.ejercicio01edu.actividades.CrearActivity;
import com.marcosledesma.ejercicio01edu.modelos.Ordenador;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    // Quitamos static de CREAR_ACTIVITY
    private final int CREAR_ACTIVITY = 1;
    /**
     * Crear variables para las vistas
     */
    private TextView txtMarca, txtModelo, txtRam, txtTamPantalla, txtFechaCompra;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaVariables();

        // setOnClickListener debe estar bajo inicializaVariables
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CrearActivity.class);
                startActivityForResult(intent, CREAR_ACTIVITY);
            }
        });

    }

    private void inicializaVariables() {
        txtMarca = findViewById(R.id.txtMarcaMain);
        txtModelo = findViewById(R.id.txtModeloMain);
        txtRam = findViewById(R.id.txtRamMain);
        txtTamPantalla = findViewById(R.id.txtTamPantallaMain);
        txtFechaCompra = findViewById(R.id.txtFechaCompraMain);
        btnCrear = findViewById(R.id.btnCrearMain);
    }

    // Después de recibir el resultcode ok en CrearActivivy, necesitamos onActivityResult en el Main
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREAR_ACTIVITY && resultCode == RESULT_OK){
            if (data != null){
                Ordenador ordenador = data.getExtras().getParcelable("ORDENADOR");
                if(ordenador != null){
                    txtMarca.setText(ordenador.getMarca());
                    txtModelo.setText(ordenador.getModelo());
                    txtRam.setText(String.valueOf(ordenador.getRam()+ "GB"));
                    txtTamPantalla.setText(String.valueOf(ordenador.getTamPantalla()));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    txtFechaCompra.setText(simpleDateFormat.format(ordenador.getFechaCompra()));
                }
            }
        }
    }
}
package com.marcosledesma.ejercicio01edu.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marcosledesma.ejercicio01edu.R;
import com.marcosledesma.ejercicio01edu.modelos.Ordenador;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearActivity extends AppCompatActivity {

    /**
     * Definir variables
     */
    private EditText txtMarca, txtModelo, txtRam, txtTamPantalla, txtFechaCompra;
    private Button btnCancelar, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        inicializaVariables();

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtMarca.getText().toString().isEmpty() &&
                    !txtModelo.getText().toString().isEmpty() &&
                    !txtTamPantalla.getText().toString().isEmpty() &&
                    !txtRam.getText().toString().isEmpty() &&
                    !txtFechaCompra.getText().toString().isEmpty()){
                    // Si tod0 está relleno Puedo crear el Ordenador
                    try {
                        String marca = txtMarca.getText().toString();
                        String modelo = txtModelo.getText().toString();
                        int ram = Integer.parseInt(txtRam.getText().toString());
                        float tamPantalla = Float.parseFloat(txtTamPantalla.getText().toString());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaCompra = simpleDateFormat.parse(txtFechaCompra.getText().toString());
                        Ordenador ordenador = new Ordenador(marca, modelo, ram, tamPantalla, fechaCompra);
                        // Pasarlo al Main
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("ORDENADOR", ordenador);
                        Intent intent = new Intent();
                        intent.putExtras(bundle);
                        // Result_OK y finish activity
                        setResult(RESULT_OK, intent);
                        finish();
                    }catch(Exception ex){
                        Log.e("EXPLOTÓ", ex.getMessage());
                    }
                }
                else{
                    Toast.makeText(CrearActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializaVariables() {
        txtMarca = findViewById(R.id.txtMarcaCrear);
        txtModelo = findViewById(R.id.txtModeloCrear);
        txtRam = findViewById(R.id.txtRamCrear);
        txtTamPantalla = findViewById(R.id.txtTamPantallaCrear);
        txtFechaCompra = findViewById(R.id.txtFechaCompraCrear);
        btnCancelar = findViewById(R.id.btnCancelarCrear);
        btnGuardar = findViewById(R.id.btnGuardarCrear);
    }
}
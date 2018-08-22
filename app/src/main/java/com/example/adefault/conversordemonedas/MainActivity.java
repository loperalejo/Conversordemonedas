package com.example.adefault.conversordemonedas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eCantidad;
    private TextView tValor;
    private Spinner sDesde, sConvertir;
    private Button bConvertir, bInvertir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] moneda = {"Pesos Colombianos(COP)", "Dolar(EE.UU)", "Euro", "Yen(JPY)"};

        eCantidad = findViewById(R.id.eCantidad);
        tValor = findViewById(R.id.tValor);
        sDesde = findViewById(R.id.sDesde);
        sConvertir = findViewById(R.id.sConvertir);
        bConvertir = findViewById(R.id.bConvertir);
        bInvertir = findViewById(R.id.bInvertir);

        sDesde.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner, moneda));
        sConvertir.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner, moneda));
    }

    // Funcion que se activa al presionar el boton "Invertir"
    public void InvertirMoneda(View view) {

        String moneda1, moneda2;

        moneda1 = sDesde.getSelectedItem().toString();
        moneda2 = sConvertir.getSelectedItem().toString();

        sDesde.setSelection(obtenerPosicion(sConvertir,moneda2));
        sConvertir.setSelection(obtenerPosicion(sDesde,moneda1));

    }

    // Funcion que se activa al presionar el boton "Convertir"
    public void ConvertirMoneda(View view) {

        if(eCantidad.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese la cantidad que desea convertir", Toast.LENGTH_SHORT).show();

        } else {
            String cantidad, moneda1, moneda2;
            double monto;

            cantidad = eCantidad.getText().toString();
            monto = Double.parseDouble(cantidad);
            moneda1 = sDesde.getSelectedItem().toString();
            moneda2 = sConvertir.getSelectedItem().toString();

            if (moneda1.equals("Pesos Colombianos(COP)")) {
                if (moneda2.equals("Dolar(EE.UU)")) {
                    monto = monto / 3013.65;
                } else if (moneda2.equals("Euro")) {
                    monto = monto / 3432.79;
                } else if (moneda2.equals("Yen(JPY)")) {
                    monto = monto / 27.22;
                }
            } else if (moneda1.equals("Dolar(EE.UU)")) {
                if (moneda2.equals("Pesos Colombianos(COP)")) {
                    monto = monto * 3013.65;
                } else if (moneda2.equals("Euro")) {
                    monto = monto * 0.88;
                } else if (moneda2.equals("Yen(JPY)")) {
                    monto = monto * 110.74;
                }
            } else if (moneda1.equals("Euro")) {
                if (moneda2.equals("Pesos Colombianos(COP)")) {
                    monto = monto * 3432.79;
                } else if (moneda2.equals("Dolar(EE.UU)")) {
                    monto = monto * 1.14;
                } else if (moneda2.equals("Yen(JPY)")) {
                    monto = monto * 126.18;
                }
            } else if (moneda1.equals("Yen(JPY)")) {
                if (moneda2.equals("Dolar(EE.UU)")) {
                    monto = monto * 0.0090;
                } else if (moneda2.equals("Euro")) {
                    monto = monto * 0.0079;
                } else if (moneda2.equals("Pesos Colombianos(COP)")) {
                    monto = monto * 27.22;
                }
            }

            String resultado = String.valueOf(monto);
            tValor.setText(resultado);
        }
    }


    // Funcion para obtener la posicion de un string de un spinner
    public static int obtenerPosicion(Spinner spinner, String moneda) {

        int i, pos = 0;

        for(i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(moneda)){
                pos = i;
            }
        }

        return pos;
    }
}


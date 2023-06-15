package com.example.holoamundo2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnEntrar(View view){

        EditText txtNombre = (EditText)findViewById(R.id.txtNombre);
        //Toast.makeText(this.getApplicationContext(), txtNombre.getText().toString(), Toast.LENGTH_SHORT).show();

        //Primero se saca la referencia aqui
        EditText txtPassword = (EditText)findViewById(R.id.txtPassword);
        RadioButton rbMascu = (RadioButton)findViewById(R.id.rbMascu);
        Switch swnotificaciones = (Switch)findViewById(R.id.swnotificaciones);
        EditText txtFechaDate = (EditText)findViewById(R.id.txtFechaDate);

        //Despues se procesa aca
        String nombre =txtNombre.getText().toString();
        String Password = txtPassword.getText().toString();
        //Para sacar el genero seleccionado
        String genero = rbMascu.isChecked()?"Masculino":"Femenino";
        //Sacar si recibe o no notificaciones
        String notificaciones = swnotificaciones.isChecked()?"SI":"NO";
        //Sacar la fecha de nacimiento
        String fechaNaci = txtFechaDate.getText().toString();

        Toast.makeText(this.getApplicationContext(),
                "Nombre:" + nombre + "," +
                        "Password: " + Password + "," +
                        "Genero" + genero + "," +
                        "Notificacion" + notificaciones + "," +
                        "FechaNacimiento" + fechaNaci,
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        Bundle b = new Bundle();

        b.putString("Nombre",nombre);
        b.putString("Password",Password);
        b.putString("Genero",genero);
        b.putString("Notifica",notificaciones);
        intent.putExtras(b);

        startActivity(intent);

    }
}
package com.example.holoamundo2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivityLogin extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
    }

    public void btLogin(View view){
        EditText txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        EditText txtContrasena = (EditText) findViewById(R.id.txtContrasena);

        Map<String, String> datos = new HashMap<String, String>();
        datos.put("correo",txtUsuario.getText().toString());
        datos.put("clave",txtContrasena.getText().toString());

        WebService ws= new WebService (
                "https://api.uealecpeterson.net/public/login",
                datos, MainActivityLogin.this, MainActivityLogin.this);
        ws.execute("POST") ;

    }

    @Override
    public void processFinish(String result) throws JSONException {
        Log.i("Resp",result);
        //Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        TextView txtToken = (TextView)findViewById(R.id.txtToken);

        JSONObject respuesta = new JSONObject(result);
        if (respuesta.has("error")){
            Toast.makeText(this, "Error Login" + respuesta.getString("error"), Toast.LENGTH_SHORT).show();
            txtToken.setText("Error: " + respuesta.getString("error"));
        }else{
            Toast.makeText(this, "Bienvenido, Token: " +
                    respuesta.getString("access_token"), Toast.LENGTH_SHORT).show();
            txtToken.setText("Token: " + respuesta.getString("access_token"));
            String Token = respuesta.getString("access_token").toString();

            Intent intent = new Intent(MainActivityLogin.this, MainActivityListaProductos.class);
            Bundle b = new Bundle();
            b.putString("Token",Token);

            intent.putExtras(b);
            startActivity(intent);
        }

    }
}
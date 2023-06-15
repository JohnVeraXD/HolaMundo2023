package com.example.holoamundo2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivityListaBancos extends AppCompatActivity implements Asynchtask {

    TextView txtLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_bancos);

        txtLista = (TextView) findViewById(R.id.txtListaBanco);
        Map<String, String> datos = new HashMap<String, String>();

        WebService ws = new
                WebService("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList",
                datos, MainActivityListaBancos.this,MainActivityListaBancos.this);
        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");

    }

    @Override
    public void processFinish(String result) throws JSONException {

        String lstBancos="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" +
                    banco.getString("code").toString() + " - " +
                    banco.getString("name").toString();
        }

        txtLista.setText("Lista de bancos \n" + lstBancos);
    }
}
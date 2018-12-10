package com.example.manuel.abcc_http_mysql;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controlador.Analizador_JSON;

public class ActivityEliminar extends Activity {

    EditText txtDato;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        txtDato= findViewById(R.id.txtDato);
    }


    public void eliminarAlumnos(View v){
        String nc=txtDato.getText().toString();


        //comprobar conexion de wifi

        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni= cm.getActiveNetworkInfo();//checar los permisos

        //conectar y enviar datos para guardar en MySQL
        if (ni != null && ni.isConnected()){
            new EliminarAlumnos().execute(nc);
        }




    }//METODO AGREGAR ALUMNOS
    class EliminarAlumnos extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... args) {

            Map<String,String> mapDatos=new HashMap<String, String>();
            mapDatos.put("nc",args[0]);

            Analizador_JSON analizador_json = new Analizador_JSON();
            //cambiar el nombre del archivo php, debe de ser el de consulta
            String url = "http://192.168.1.70/Semestre-agosto-diciembre/Sistema_ABCC_MSQL/web_service_http_android/bajas_alumnos.php";


            String metodo="POST";

            JSONObject resultado = analizador_json.peticionHTTP(url,metodo,mapDatos);
            int r=0;
            try {
                r =resultado.getInt("exito");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (r==1){
                Log.i("Msj resultado", "REGISTRO ELIMINADO");

                //  Toast.makeText(getApplicationContext(),"ALUMNO AGREGADO ",Toast.LENGTH_LONG).show();

            }else
                Log.i("Msj resultado", "ERROR EN LA ELIMINACION");


            return null;


        }//eliminar alumnos


    }

}

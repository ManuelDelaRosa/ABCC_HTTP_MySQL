package com.example.manuel.abcc_http_mysql;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import controlador.Analizador_JSON;

public class ActivityConsultas  extends Activity {
    ListView lsl_consulta;
    ArrayAdapter <String> adapter;
    ArrayList<String>arrayList=new ArrayList<>();
    RadioGroup rbGr;
    EditText txtDato;
    RadioButton rbNum,rbNombre,rbPrimerAp;
    volatile String dato;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        lsl_consulta=findViewById(R.id.lsl_consulta);
        rbGr= findViewById(R.id.rbGr);
        rbNum= findViewById(R.id.rbNum);
        rbNombre= findViewById(R.id.rbNombre);
        rbPrimerAp= findViewById(R.id.rbPrimerAp);
        txtDato= findViewById(R.id.txtDato);
        //txtNombre= findViewById(R.id.txtNombre);
        //txtPrimerAp= findViewById(R.id.txtPrimerAp);
       //new MostrarAlumnos().execute();



        rbGr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbNum:
                        dato = txtDato.getText().toString();
                        new MostrarAlumnos().execute(dato);

                        break;
                    case R.id.rbNombre:
                        dato = txtDato.getText().toString();
                        new MostrarAlumnos().execute(dato);
                        break;
                    case R.id.rbPrimerAp:
                        dato = txtDato.getText().toString();
                        new MostrarAlumnos().execute(dato);
                        break;
                }
            }
        });
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        lsl_consulta.setAdapter(adapter);


    }//onCreate
    class MostrarAlumnos extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            Analizador_JSON analizador_json = new Analizador_JSON();
            //cambiar el nombre del archivo php, debe de ser el de consulta
            String url="http://192.168.1.70/Semestre-agosto-diciembre/Sistema_ABCC_MSQL/web_service_http_android/consulta_alumnos.php";
            JSONObject jsonObject= analizador_json.peticionHTTP(url);


            try {
                JSONArray jsonArray = jsonObject.getJSONArray("alumnos");
                String cadena="";

                for (int i=0; i<jsonArray.length();i++){
                    if(strings[0].equals(jsonArray.getJSONObject(i).getString("nc"))){

                        cadena=jsonArray.getJSONObject(i).getString("nc")+"|"+
                                jsonArray.getJSONObject(i).getString("n")+"|"+
                                jsonArray.getJSONObject(i).getString("pa")+"|"+
                                jsonArray.getJSONObject(i).getString("sa")+"|"+
                                jsonArray.getJSONObject(i).getString("e")+"|"+
                                jsonArray.getJSONObject(i).getString("s")+"|"+
                                jsonArray.getJSONObject(i).getString("c");

                        //se agrega String ´por String al array list, esto llena el array lista para despues meterlo al adaptador
                        arrayList.add(cadena);
                    } else if(strings[0].equals(jsonArray.getJSONObject(i).getString("n"))){

                        cadena=jsonArray.getJSONObject(i).getString("nc")+"|"+
                                jsonArray.getJSONObject(i).getString("n")+"|"+
                                jsonArray.getJSONObject(i).getString("pa")+"|"+
                                jsonArray.getJSONObject(i).getString("sa")+"|"+
                                jsonArray.getJSONObject(i).getString("e")+"|"+
                                jsonArray.getJSONObject(i).getString("s")+"|"+
                                jsonArray.getJSONObject(i).getString("c");

                        //se agrega String ´por String al array list, esto llena el array lista para despues meterlo al adaptador
                        arrayList.add(cadena);
                    }else if(strings[0].equals(jsonArray.getJSONObject(i).getString("pa"))){

                        cadena=jsonArray.getJSONObject(i).getString("nc")+"|"+
                                jsonArray.getJSONObject(i).getString("n")+"|"+
                                jsonArray.getJSONObject(i).getString("pa")+"|"+
                                jsonArray.getJSONObject(i).getString("sa")+"|"+
                                jsonArray.getJSONObject(i).getString("e")+"|"+
                                jsonArray.getJSONObject(i).getString("s")+"|"+
                                jsonArray.getJSONObject(i).getString("c");

                        //se agrega String ´por String al array list, esto llena el array lista para despues meterlo al adaptador
                        arrayList.add(cadena);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }//mostrar alumnos
    }
}

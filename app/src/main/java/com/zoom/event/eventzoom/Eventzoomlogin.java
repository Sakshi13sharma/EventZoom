package com.zoom.event.eventzoom;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Eventzoomlogin extends AppCompatActivity {


    EditText edt_email,edt_pass;
    Button btn_login;

    String email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventzoomlogin);

        edt_email = (EditText) findViewById(R.id.log_txt_email);
        edt_pass = (EditText) findViewById(R.id.log_txt_pass);
        btn_login = (Button) findViewById(R.id.log_btn_login);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edt_email.getText().toString();
                pass = edt_pass.getText().toString();
                String loginurl = "https://eventzoom-194221.appspot.com/rest/api/signIn&"+email+"&"+pass;
                new GetData().execute(loginurl);
            }
        });





    }


    public class GetData extends AsyncTask<String,Void,String>
    {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        Log.d("From Post Method",s);

            try {
                JSONObject jb = new JSONObject(s);

                String msg = jb.getString("Message");

                if (msg.matches("successfull"))
                {
                    Toast.makeText(Eventzoomlogin.this,"Successfully Login!",Toast.LENGTH_LONG).show();
                }else if (msg.matches("Info Not Found")){
                    Toast.makeText(Eventzoomlogin.this,"Wrong Credentials!",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Eventzoomlogin.this,"Login Failed!",Toast.LENGTH_LONG).show();
                }




            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        protected String doInBackground(String... strings) {

            String jsonurl = strings[0];

            Httphandler sh = new Httphandler();

            System.out.println("From Cloud Do in backgroud............." + jsonurl);
            String jsonstr = sh.makeServiceCall(jsonurl);



            return jsonstr;
        }
    }
}

package com.example.sharedpreferencesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText login;
    EditText pwd;
    Button cnx;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login= (EditText) findViewById(R.id.login);
        pwd=(EditText)findViewById(R.id.password);
        cnx=(Button) findViewById(R.id.connexion);
        register=(TextView)findViewById(R.id.register);
        final AlertDialog.Builder pDialog = new AlertDialog.Builder(this);
        SharedPreferences prefs=getPreferences(Context.MODE_PRIVATE);
        String emailsp=prefs.getString("email","");
        String psswordsp=prefs.getString("password","");
        if (emailsp.equals("") && psswordsp.equals("")){
            pDialog.setTitle("Bienvenue");
            pDialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            pDialog.show();
        }
        else {
            Intent toHomeActivity= new Intent(MainActivity.this, HomeActivity.class);
            startActivity(toHomeActivity);
            finish();
        }

        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login.getText().toString().equals("student") && pwd.getText().toString().equals("ept"))
                {
                    SharedPreferences sharedPref= getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("email", login.getText().toString());
                    editor.putString("password", pwd.getText().toString());
                    editor.commit();
                    pDialog.setTitle("Connexion avec succés");
                    pDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Intent toHomeActivity = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(toHomeActivity);
                            finish();
                        }
                    });
                    pDialog.show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Vérifier vos paramètres d'accès", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
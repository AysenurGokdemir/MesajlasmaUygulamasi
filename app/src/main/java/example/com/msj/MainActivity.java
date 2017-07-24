package example.com.msj;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername,etPassword;
    TextView tvKayitOl;
    Button btnGiris;

    FirebaseAuth mAuth;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.editTextKullaniciAdi);
        etPassword = (EditText) findViewById(R.id.editTextUserSifre);
        tvKayitOl = (TextView) findViewById(R.id.textViewKayıtOl);
        btnGiris = (Button) findViewById(R.id.buttonGiris);

        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();

        if(fUser != null){
            Intent i = new Intent(this,AnaSayfaActivity.class);
            startActivity(i);
            finish();
        }



        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this,
                        new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(MainActivity.this,AnaSayfaActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewKayıtOl :
                Intent i = new Intent(MainActivity.this,KayitOlActivity.class);
                startActivity(i);
                break;
        }

    }
}

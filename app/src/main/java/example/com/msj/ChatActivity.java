package example.com.msj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    EditText etMesaj;
    Button btnGonder;
    ListView lvMesajlar;
    CustomAdaptor adaptor;

    String konu;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbRef;

    FirebaseUser fUser;

    ArrayList<Mesaj> mesajListe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        fUser = FirebaseAuth.getInstance().getCurrentUser();

        etMesaj = (EditText) findViewById(R.id.editTextMesaj);
        btnGonder = (Button) findViewById(R.id.buttonGonder);

        lvMesajlar = (ListView) findViewById(R.id.listViewMesajlar);
        mesajListe.add(new Mesaj("Yüklwniyor..","",""));
        adaptor = new CustomAdaptor(this,mesajListe);
        lvMesajlar.setAdapter(adaptor);



        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            konu = bundle.getString("konu");

            dbRef = db.getReference("Chats/"+konu+"/mesaj");

        }

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mesajListe.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    mesajListe.add(ds.getValue(Mesaj.class));
                    Log.d("ABC",ds.getValue(Mesaj.class).getMesajText());
                }
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mesajText = etMesaj.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String zaman = sdf.format(new Date());
                dbRef.push().setValue(new Mesaj(mesajText,fUser.getEmail(),zaman));
                adaptor.notifyDataSetChanged();
                etMesaj.setText("");
            }

        });





    }
}
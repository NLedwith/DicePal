package com.bignerdranch.android.ttrpgtracker.ui.parties;

import androidx.appcompat.app.AppCompatActivity;
import com.bignerdranch.android.ttrpgtracker.R;
import com.bignerdranch.android.ttrpgtracker.ui.npcs.CreateCustomNPCActivity;
import com.bignerdranch.android.ttrpgtracker.ui.npcs.CustomNPC;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreatePartyActivity extends AppCompatActivity {
    private String uID;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference rootRef = database.getReference();
    List<EditText> characterNamesEditTextList;
    Button createParty;
    EditText partyNameEditText;
    EditText partyNotesEditText;
    String notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_party);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent extra = getIntent();
        this.uID = extra.getStringExtra("uID");

        partyNotesEditText = findViewById(R.id.partyNotesEditText);
        partyNameEditText = findViewById(R.id.partyNameEditText);
        createParty = findViewById(R.id.createPartyButton);
        characterNamesEditTextList = new ArrayList<EditText>();
        characterNamesEditTextList.add((EditText) findViewById(R.id.player1NameEditText));
        characterNamesEditTextList.add((EditText) findViewById(R.id.player2NameEditText));
        characterNamesEditTextList.add((EditText) findViewById(R.id.player3NameEditText));
        characterNamesEditTextList.add((EditText) findViewById(R.id.player4NameEditText));
        characterNamesEditTextList.add((EditText) findViewById(R.id.player5NameEditText));
        characterNamesEditTextList.add((EditText) findViewById(R.id.player6NameEditText));
        characterNamesEditTextList.add((EditText) findViewById(R.id.player7NameEditText));
        characterNamesEditTextList.add((EditText) findViewById(R.id.player8NameEditText));
        characterNamesEditTextList.add((EditText) findViewById(R.id.player9NameEditText));
        characterNamesEditTextList.add((EditText) findViewById(R.id.player10NameEditText));

        createParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (partyNameEditText.getText().toString().trim().length() == 0)
                    Toast.makeText(CreatePartyActivity.this, "Party name required.",
                            Toast.LENGTH_SHORT).show();
                else {
                    if (partyNotesEditText.getText().toString().trim().length() == 0)
                        notes = "None";
                    else
                        notes = partyNotesEditText.getText().toString();
                    saveParty();
                }
            }
        });
    }

    public void saveParty() {
        try {
            DatabaseReference characterRef = database.getReference("parties");
            int i = 0;
            HashMap players = new HashMap();

            for(EditText thisText : characterNamesEditTextList) {
                if (thisText.getText().toString().trim().length() > 0) {
                    players.put("ID"+Integer.toString(i), thisText.getText().toString());
                    i = i + 1;
                }
            }
            Party newParty = new Party(partyNameEditText.getText().toString(),notes, players);
            DatabaseReference newCustomCharRef = characterRef.push();
            String Id = newCustomCharRef.getKey();
            newCustomCharRef.setValue(newParty);
            HashMap map = new HashMap();
            map.put(Id, "True");
            rootRef.child("users").child(uID).child("parties").updateChildren(map);
        } catch (Exception e) {
            Intent mainIntent = new Intent();
            mainIntent.putExtra("userID", uID);
            finish();
        }
        Intent mainIntent = new Intent();
        mainIntent.putExtra("userID", uID);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent mainIntent = new Intent();
        mainIntent.putExtra("userID", uID);
        finish();
        return true;
    }
}

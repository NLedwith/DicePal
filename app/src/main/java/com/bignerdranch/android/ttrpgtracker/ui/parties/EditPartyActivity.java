package com.bignerdranch.android.ttrpgtracker.ui.parties;

import androidx.appcompat.app.AppCompatActivity;
import com.bignerdranch.android.ttrpgtracker.R;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EditPartyActivity extends AppCompatActivity {
    private String uID, fbKey;
    private Party thisParty;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference rootRef = database.getReference();

    List<EditText> editCharacterNamesEditTextList;
    Button editParty;
    EditText editPartyNameEditText;
    EditText editPartyNotesEditText;
    String notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_party);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent extra = getIntent();
        this.uID = extra.getStringExtra("uID");
        this.thisParty = (Party) extra.getSerializableExtra("thisParty");
        this.fbKey = thisParty.fbKey;

        editPartyNotesEditText = findViewById(R.id.editPartyNotesEditText);
        editPartyNameEditText = findViewById(R.id.editPartyNameEditText);
        editParty = findViewById(R.id.editPartyButton);
        editCharacterNamesEditTextList = new ArrayList<EditText>();
        editCharacterNamesEditTextList.add((EditText) findViewById(R.id.editPlayer1NameEditText));
        editCharacterNamesEditTextList.add((EditText) findViewById(R.id.editPlayer2NameEditText));
        editCharacterNamesEditTextList.add((EditText) findViewById(R.id.editPlayer3NameEditText));
        editCharacterNamesEditTextList.add((EditText) findViewById(R.id.editPlayer4NameEditText));
        editCharacterNamesEditTextList.add((EditText) findViewById(R.id.editPlayer5NameEditText));
        editCharacterNamesEditTextList.add((EditText) findViewById(R.id.editPlayer6NameEditText));
        editCharacterNamesEditTextList.add((EditText) findViewById(R.id.editPlayer7NameEditText));
        editCharacterNamesEditTextList.add((EditText) findViewById(R.id.editPlayer8NameEditText));
        editCharacterNamesEditTextList.add((EditText) findViewById(R.id.editPlayer9NameEditText));
        editCharacterNamesEditTextList.add((EditText) findViewById(R.id.editPlayer10NameEditText));

        editPartyNameEditText.setText(thisParty.getName());
        editPartyNotesEditText.setText(thisParty.getNotes());
        int i = 0;
        Iterator hmIterator = thisParty.getPlayers().entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            editCharacterNamesEditTextList.get(i).setText((String)mapElement.getValue());
             i = i + 1;
        }

        editParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editPartyNameEditText.getText().toString().trim().length() == 0)
                    Toast.makeText(EditPartyActivity.this, "Party name required.",
                            Toast.LENGTH_SHORT).show();
                else {
                    if (editPartyNotesEditText.getText().toString().trim().length() == 0)
                        notes = "None";
                    else
                        notes = editPartyNotesEditText.getText().toString();
                    editParty();
                }
            }
        });

    }

    public void editParty() {
        try {
            DatabaseReference characterRef = database.getReference("parties");
            int i = 0;
            HashMap players = new HashMap();

            for(EditText thisText : editCharacterNamesEditTextList) {
                if (thisText.getText().toString().trim().length() > 0) {
                    players.put("ID"+Integer.toString(i), thisText.getText().toString());
                    i = i + 1;
                }
            }
            Party newParty = new Party(editPartyNameEditText.getText().toString(),notes, players);
            HashMap map = new HashMap();
            map.put(this.fbKey, newParty);
            characterRef.updateChildren(map);
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

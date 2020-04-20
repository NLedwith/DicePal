package com.bignerdranch.android.ttrpgtracker.ui.npcs;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.ttrpgtracker.LoginActivity;
import com.bignerdranch.android.ttrpgtracker.R;
import com.bignerdranch.android.ttrpgtracker.ui.encounters.EnterInitiativeActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;

public class CreateCustomNPCActivity extends AppCompatActivity {

    private String uID;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference rootRef = database.getReference();

    Button createCustomNPC;
    EditText npcNameEditText;
    EditText npcHealthEditText;
    EditText npcNotesEditText;
    Switch blindedSwitch;
    Switch charmedSwitch;
    Switch deafenedSwitch;
    Switch fatiguedSwitch;
    Switch frightenedSwitch;
    Switch grappledSwitch;
    Switch incapacitatedSwitch;
    Switch invisibleSwitch;
    Switch paralyzedSwitch;
    Switch petrifiedSwitch;
    Switch poisonedSwitch;
    Switch proneSwitch;
    Switch restrainedSwitch;
    Switch stunnedSwitch;
    Switch unconsciousSwitch;
    String notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_custom_n_p_c);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent extra = getIntent();
        this.uID = extra.getStringExtra("uID");

        createCustomNPC = findViewById(R.id.createCustomNPCButton);
        npcNameEditText = findViewById(R.id.npcNameEditText);
        npcHealthEditText = findViewById(R.id.maxHPEditText);
        npcNotesEditText = findViewById(R.id.notesEditText);
        blindedSwitch = findViewById(R.id.blindedSwitch);
        charmedSwitch = findViewById(R.id.charmedSwitch);
        deafenedSwitch = findViewById(R.id.deafenedSwitch);
        fatiguedSwitch = findViewById(R.id.fatiguedSwitch);
        frightenedSwitch = findViewById(R.id.frightenedSwitch);
        grappledSwitch = findViewById(R.id.grappledSwitch);
        incapacitatedSwitch = findViewById(R.id.incapacitatedSwitch);
        invisibleSwitch = findViewById(R.id.invisibleSwitch);
        paralyzedSwitch = findViewById(R.id.paralyzedSwitch);
        petrifiedSwitch = findViewById(R.id.petrifiedSwitch);
        poisonedSwitch = findViewById(R.id.poisonedSwitch);
        proneSwitch = findViewById(R.id.proneSwitch);
        restrainedSwitch = findViewById(R.id.restrainedSwitch);
        stunnedSwitch = findViewById(R.id.stunnedSwitch);
        unconsciousSwitch = findViewById(R.id.unconsciousSwitch);


        createCustomNPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(npcNameEditText.getText().toString().trim().length() == 0 || npcHealthEditText.getText().toString().trim().length() == 0)
                    Toast.makeText(CreateCustomNPCActivity.this, "NPC name and maximum health required.",
                            Toast.LENGTH_SHORT).show();
                else {
                    if(npcNotesEditText.getText().toString().trim().length() == 0)
                        notes = "None";
                    else
                        notes = npcNotesEditText.getText().toString();
                    saveCustomNPC();
                }
            }
        });
    }

    public void saveCustomNPC() {
        try {
            DatabaseReference characterRef = database.getReference("custom_npcs");
            CustomNPC newNPC = new CustomNPC(npcNameEditText.getText().toString(), Integer.valueOf(npcHealthEditText.getText().toString()), notes,
                    blindedSwitch.isChecked(), charmedSwitch.isChecked(), deafenedSwitch.isChecked(), fatiguedSwitch.isChecked(),
                    frightenedSwitch.isChecked(), grappledSwitch.isChecked(), incapacitatedSwitch.isChecked(), invisibleSwitch.isChecked(),
                    paralyzedSwitch.isChecked(), petrifiedSwitch.isChecked(), poisonedSwitch.isChecked(), proneSwitch.isChecked(),
                    restrainedSwitch.isChecked(), stunnedSwitch.isChecked(), unconsciousSwitch.isChecked());
            DatabaseReference newCustomCharRef = characterRef.push();
            String Id = newCustomCharRef.getKey();
            newCustomCharRef.setValue(newNPC);
            HashMap map = new HashMap();
            map.put(Id, "True");
            rootRef.child("users").child(uID).child("custom_npcs").updateChildren(map);
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

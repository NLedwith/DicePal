package com.bignerdranch.android.ttrpgtracker.ui.npcs;

import androidx.appcompat.app.AppCompatActivity;
import com.bignerdranch.android.ttrpgtracker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;

public class EditCustomNPCActivity extends AppCompatActivity {

    private String uID, fbKey;
    private CustomNPC thisNPC;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference rootRef = database.getReference();

    Button editCustomNPC;
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
        setContentView(R.layout.activity_edit_custom_n_p_c);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent extra = getIntent();
        this.uID = extra.getStringExtra("uID");
        this.thisNPC = (CustomNPC) extra.getSerializableExtra("thisNPC");
        this.fbKey = thisNPC.fbKey;

        editCustomNPC = findViewById(R.id.editCustomNPCButton);
        npcNameEditText = findViewById(R.id.editNPCNameEditText);
        npcHealthEditText = findViewById(R.id.editMaxHPEditText);
        npcNotesEditText = findViewById(R.id.editNotesEditText);
        blindedSwitch = findViewById(R.id.editBlindedSwitch);
        charmedSwitch = findViewById(R.id.editCharmedSwitch);
        deafenedSwitch = findViewById(R.id.editDeafenedSwitch);
        fatiguedSwitch = findViewById(R.id.editFatiguedSwitch);
        frightenedSwitch = findViewById(R.id.editFrightenedSwitch);
        grappledSwitch = findViewById(R.id.editGrappledSwitch);
        incapacitatedSwitch = findViewById(R.id.editIncapacitatedSwitch);
        invisibleSwitch = findViewById(R.id.editInvisibleSwitch);
        paralyzedSwitch = findViewById(R.id.editParalyzedSwitch);
        petrifiedSwitch = findViewById(R.id.editPetrifiedSwitch);
        poisonedSwitch = findViewById(R.id.editPoisonedSwitch);
        proneSwitch = findViewById(R.id.editProneSwitch);
        restrainedSwitch = findViewById(R.id.editRestrainedSwitch);
        stunnedSwitch = findViewById(R.id.editStunnedSwitch);
        unconsciousSwitch = findViewById(R.id.editUnconsciousSwitch);

        npcNameEditText.setText(thisNPC.getName());
        npcHealthEditText.setText(Integer.toString(thisNPC.getHp()));
        npcNotesEditText.setText(thisNPC.getNotes());
        blindedSwitch.setChecked(thisNPC.isBlinded());
        charmedSwitch.setChecked(thisNPC.isCharmed());
        deafenedSwitch.setChecked(thisNPC.isDeafened());
        fatiguedSwitch.setChecked(thisNPC.isFatigued());
        frightenedSwitch.setChecked(thisNPC.isFrightened());
        grappledSwitch.setChecked(thisNPC.isGrappled());
        incapacitatedSwitch.setChecked(thisNPC.isIncapacitated());
        invisibleSwitch.setChecked(thisNPC.isInvisible());
        paralyzedSwitch.setChecked(thisNPC.isParalyzed());
        petrifiedSwitch.setChecked(thisNPC.isPetrified());
        poisonedSwitch.setChecked(thisNPC.isPoisoned());
        proneSwitch.setChecked(thisNPC.isProne());
        restrainedSwitch.setChecked(thisNPC.isRestrained());
        stunnedSwitch.setChecked(thisNPC.isStunned());
        unconsciousSwitch.setChecked(thisNPC.isUnconscious());

        editCustomNPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(npcNameEditText.getText().toString().trim().length() == 0 || npcHealthEditText.getText().toString().trim().length() == 0)
                    Toast.makeText(EditCustomNPCActivity.this, "NPC name and maximum health required.",
                            Toast.LENGTH_SHORT).show();
                else {
                    if(npcNotesEditText.getText().toString().trim().length() == 0)
                        notes = "None";
                    else
                        notes = npcNotesEditText.getText().toString();
                    saveEditsCustomNPC();
                }
            }
        });
    }

    public void saveEditsCustomNPC() {
        try {
            DatabaseReference characterRef = database.getReference("custom_npcs");
            CustomNPC newNPC = new CustomNPC(npcNameEditText.getText().toString(), Integer.valueOf(npcHealthEditText.getText().toString()), notes,
                    blindedSwitch.isChecked(), charmedSwitch.isChecked(), deafenedSwitch.isChecked(), fatiguedSwitch.isChecked(),
                    frightenedSwitch.isChecked(), grappledSwitch.isChecked(), incapacitatedSwitch.isChecked(), invisibleSwitch.isChecked(),
                    paralyzedSwitch.isChecked(), petrifiedSwitch.isChecked(), poisonedSwitch.isChecked(), proneSwitch.isChecked(),
                    restrainedSwitch.isChecked(), stunnedSwitch.isChecked(), unconsciousSwitch.isChecked());
            HashMap map = new HashMap();
            map.put(this.fbKey, newNPC);
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

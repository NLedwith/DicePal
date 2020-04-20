package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import androidx.appcompat.app.AppCompatActivity;
import com.bignerdranch.android.ttrpgtracker.R;

import android.content.Intent;
import android.os.Bundle;

public class EncounterActivity extends AppCompatActivity {

    String uID;
    Encounter thisEncounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);
        Intent extra = getIntent();
        this.uID = extra.getStringExtra("uID");
        this.thisEncounter = (Encounter) extra.getSerializableExtra("thisEncounter");
        //enter_initiative_rec_view = findViewById(R.id.enterInitiativeRecView);
    }
}

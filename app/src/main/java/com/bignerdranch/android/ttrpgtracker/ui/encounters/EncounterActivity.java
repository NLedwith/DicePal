package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.ttrpgtracker.R;

import android.content.Intent;
import android.os.Bundle;

public class EncounterActivity extends AppCompatActivity {

    String uID;
    Encounter thisEncounter;

    RecyclerView encounter_rec_view;
    EncounterRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);
        Intent extra = getIntent();
        this.uID = extra.getStringExtra("uID");
        this.thisEncounter = (Encounter) extra.getSerializableExtra("thisEncounter");
        encounter_rec_view = findViewById(R.id.encounterRecView);

        recyclerAdapter = new EncounterRecyclerAdapter(this, this.thisEncounter.participants);
        encounter_rec_view.setAdapter(recyclerAdapter);
        for(EncounterParticipant thisParticipant: thisEncounter.participants)
            recyclerAdapter.addItem(thisParticipant);
    }

    public void onResume() {
        super.onResume();
        uID = this.uID;
        for(EncounterParticipant thisParticipant: thisEncounter.participants)
            recyclerAdapter.addItem(thisParticipant);
    }

    public void onStart() {
        super.onStart();
        for(EncounterParticipant thisParticipant: thisEncounter.participants)
            recyclerAdapter.addItem(thisParticipant);
    }
}

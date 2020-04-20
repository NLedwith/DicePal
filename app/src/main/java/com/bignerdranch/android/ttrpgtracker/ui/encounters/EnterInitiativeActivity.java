package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.ttrpgtracker.MainActivity;
import com.bignerdranch.android.ttrpgtracker.R;
import com.bignerdranch.android.ttrpgtracker.ui.npcs.CustomNPC;
import com.bignerdranch.android.ttrpgtracker.ui.npcs.CustomNPCRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EnterInitiativeActivity extends AppCompatActivity
{
    RecyclerView enter_initiative_rec_view;
    EnterInitiativeRecyclerAdapter recyclerAdapter;
    private String uID;
    private Encounter thisEncounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_initiative);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent extra = getIntent();
        this.uID = extra.getStringExtra("uID");
        this.thisEncounter = (Encounter) extra.getSerializableExtra("thisEncounter");
        enter_initiative_rec_view = findViewById(R.id.enterInitiativeRecView);

        recyclerAdapter = new EnterInitiativeRecyclerAdapter(this, this.thisEncounter.participants);
        enter_initiative_rec_view.setAdapter(recyclerAdapter);
        for(EncounterParticipant thisParticipant: thisEncounter.participants)
            recyclerAdapter.addItem(thisParticipant);

        FloatingActionButton configure_encounter_fab = findViewById(R.id.start_encounter_fab);
        configure_encounter_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configureEncounter();
            }
        });
    }

    public void configureEncounter()
    {
        int i = 0;
        Collections.sort(thisEncounter.participants);
        Intent intent = new Intent(this, EncounterActivity.class);
        intent.putExtra("uID", uID);
        intent.putExtra("thisEncounter", thisEncounter);
        finish();
        startActivity(intent);
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

    @Override
    public boolean onSupportNavigateUp(){
        Intent mainIntent = new Intent();
        mainIntent.putExtra("userID", uID);
        finish();
        return true;
    }
}

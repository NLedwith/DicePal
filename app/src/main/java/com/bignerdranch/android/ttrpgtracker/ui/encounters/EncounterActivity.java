package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.ttrpgtracker.R;
import com.bignerdranch.android.ttrpgtracker.ui.parties.EditPartyActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import java.util.Collections;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

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

        recyclerAdapter = new EncounterRecyclerAdapter(this.thisEncounter.participants);
        encounter_rec_view.setAdapter(recyclerAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(encounter_rec_view);

        FloatingActionButton end_encounter_fab = findViewById(R.id.end_encounter_fab);
        end_encounter_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endEncounter();
            }
        });
        FloatingActionButton continue_encounter_fab = findViewById(R.id.continue_encounter_fab);
        continue_encounter_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueEncounter();
            }
        });
    }

    public void continueEncounter()
    {
        if(recyclerAdapter.participants.size() !=  0) {
            EncounterParticipant startNPC = recyclerAdapter.participants.get(0);
            recyclerAdapter.participants.remove(0);
            recyclerAdapter.notifyItemRemoved(0);
            recyclerAdapter.participants.add(startNPC);
            recyclerAdapter.notifyItemInserted(recyclerAdapter.participants.size() - 1);
        }
    }

    public void endEncounter()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(EncounterActivity.this);
        builder1.setMessage("Are you sure you want to end the encounter?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent mainIntent = new Intent();
                        mainIntent.putExtra("userID", uID);
                        finish();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
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

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction)
            {
                case ItemTouchHelper.LEFT:
                    recyclerAdapter.participants.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(EncounterActivity.this, R.color.removeItem))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        }
    };
}

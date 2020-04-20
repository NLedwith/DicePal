package com.bignerdranch.android.ttrpgtracker.ui.npcs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.ttrpgtracker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TabStandardNPCSFragment extends Fragment {
    RecyclerView stand_npc_rec_view;
    StandardNPCRecyclerAdapter recyclerAdapter;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stand_npc_rec_view = view.findViewById(R.id.standard_npc_rec_view);
        retrieveStockNPC();

        recyclerAdapter = new StandardNPCRecyclerAdapter(new ArrayList<StandardNPC>());
        stand_npc_rec_view.setAdapter(recyclerAdapter);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_standard_npcs, container, false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrieveStockNPC();
    }

    public void onStart() {
        super.onStart();
        retrieveStockNPC();
    }

    public void retrieveStockNPC() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("standard_npcs");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            StandardNPC thisNpc = snap.getValue(StandardNPC.class);
                            recyclerAdapter.addItem(thisNpc);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }
}


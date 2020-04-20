package com.bignerdranch.android.ttrpgtracker.ui.parties;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.ttrpgtracker.MainActivity;
import com.bignerdranch.android.ttrpgtracker.R;
import com.bignerdranch.android.ttrpgtracker.ui.npcs.CustomNPC;
import com.bignerdranch.android.ttrpgtracker.ui.npcs.CustomNPCRecyclerAdapter;
import com.bignerdranch.android.ttrpgtracker.ui.npcs.EditCustomNPCActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class PartiesFragment extends Fragment {

    RecyclerView party_rec_view;
    PartyRecyclerAdapter recyclerAdapter;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference rootRef = database.getReference();
    private String userId;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        party_rec_view = view.findViewById(R.id.party_rec_view);
        retrieveParties();

        recyclerAdapter = new PartyRecyclerAdapter(new ArrayList<Party>());
        party_rec_view.setAdapter(recyclerAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(party_rec_view);

        FloatingActionButton add_new_party_fab = view.findViewById(R.id.add_new_parties_fab);
        add_new_party_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewParty();
            }
        });
    }

    void openAddNewParty()
    {
        Intent intent = new Intent(getActivity(), CreatePartyActivity.class);
        intent.putExtra("uID", userId);
        startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = ((MainActivity)getActivity()).uID;
        retrieveParties();
    }

    public void onResume() {
        super.onResume();
        userId = ((MainActivity)getActivity()).uID;
        retrieveParties();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parties, container, false);
    }

    public void retrieveParties(){
        Query npc = rootRef.child("users").child(userId).child("parties");
        ValueEventListener usersEventListener = (new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key;
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    key = data.getKey();
                    retrievePartiesHelper(key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        npc.addValueEventListener(usersEventListener);
    }

    public void retrievePartiesHelper(String key){
        final String finalKey = key;
        Query npcQuery = rootRef.child("parties").child(key);
        ValueEventListener npcEventListener =(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getKey()!=null) {
                    if(dataSnapshot.getValue()!=null) {
                        Party thisParty = dataSnapshot.getValue(Party.class);
                        thisParty.fbKey = finalKey;
                        recyclerAdapter.addItem(thisParty);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        npcQuery.addListenerForSingleValueEvent(npcEventListener);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
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
                    rootRef.child("parties").child(recyclerAdapter.parties.get(position).fbKey).removeValue();
                    rootRef.child("users").child(userId).child("parties").child(recyclerAdapter.parties.get(position).fbKey).removeValue();
                    recyclerAdapter.parties.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                    break;
                case ItemTouchHelper.RIGHT:
                    Intent intent = new Intent(getActivity(), EditPartyActivity.class);
                    intent.putExtra("uID", userId);
                    intent.putExtra("thisParty", recyclerAdapter.parties.get(position));
                    startActivity(intent);
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getActivity(), R.color.removeItem))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(getActivity(), R.color.editItem))
                    .addSwipeRightActionIcon(R.drawable.ic_edit)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        }
    };
}


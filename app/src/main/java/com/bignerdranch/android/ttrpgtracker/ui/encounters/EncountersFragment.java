package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.ttrpgtracker.MainActivity;
import com.bignerdranch.android.ttrpgtracker.R;
import com.bignerdranch.android.ttrpgtracker.ui.npcs.CreateCustomNPCActivity;
import com.bignerdranch.android.ttrpgtracker.ui.npcs.CustomNPC;
import com.bignerdranch.android.ttrpgtracker.ui.npcs.StandardNPC;
import com.bignerdranch.android.ttrpgtracker.ui.parties.EditPartyActivity;
import com.bignerdranch.android.ttrpgtracker.ui.parties.Party;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EncountersFragment extends Fragment implements StandardNPCIFirebaseLoadDone {
    DatabaseReference standNPCRef;
    StandardNPCIFirebaseLoadDone standardNPCIFirebaseLoadDone;

    SearchableSpinner partiesSearchableSpinner;
    SearchableSpinner cNPC1SearchableSpinner;
    SearchableSpinner cNPC2SearchableSpinner;
    SearchableSpinner cNPC3SearchableSpinner;
    SearchableSpinner cNPC4SearchableSpinner;
    SearchableSpinner cNPC5SearchableSpinner;
    SearchableSpinner sNPC1SearchableSpinner;
    SearchableSpinner sNPC2SearchableSpinner;
    SearchableSpinner sNPC3SearchableSpinner;
    SearchableSpinner sNPC4SearchableSpinner;
    SearchableSpinner sNPC5SearchableSpinner;
    List<SearchableSpinner> standardNPCSpinners;
    List<SearchableSpinner> customNPCSpinners;
    Button enterInitiative;

    List<StandardNPC> standardNPCSList;
    List<Party> yourPartiesList;
    List<CustomNPC> customNPCList;
    private String userId;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference rootRef = database.getReference();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        partiesSearchableSpinner = view.findViewById(R.id.partySearchableSpinner);

        cNPC1SearchableSpinner = view.findViewById(R.id.cNPC1SearchableSpinner);
        cNPC2SearchableSpinner = view.findViewById(R.id.cNPC2SearchableSpinner);
        cNPC3SearchableSpinner = view.findViewById(R.id.cNPC3SearchableSpinner);
        cNPC4SearchableSpinner = view.findViewById(R.id.cNPC4SearchableSpinner);
        cNPC5SearchableSpinner = view.findViewById(R.id.cNPC5SearchableSpinner);

        sNPC1SearchableSpinner = view.findViewById(R.id.sNPC1SearchableSpinner);
        sNPC2SearchableSpinner = view.findViewById(R.id.sNPC2SearchableSpinner);
        sNPC3SearchableSpinner = view.findViewById(R.id.sNPC3SearchableSpinner);
        sNPC4SearchableSpinner = view.findViewById(R.id.sNPC4SearchableSpinner);
        sNPC5SearchableSpinner = view.findViewById(R.id.sNPC5SearchableSpinner);

        customNPCSpinners = new ArrayList<SearchableSpinner>();
        customNPCSpinners.add(cNPC1SearchableSpinner);
        customNPCSpinners.add(cNPC2SearchableSpinner);
        customNPCSpinners.add(cNPC3SearchableSpinner);
        customNPCSpinners.add(cNPC4SearchableSpinner);
        customNPCSpinners.add(cNPC5SearchableSpinner);
        standardNPCSpinners = new ArrayList<SearchableSpinner>();
        standardNPCSpinners.add(sNPC1SearchableSpinner);
        standardNPCSpinners.add(sNPC2SearchableSpinner);
        standardNPCSpinners.add(sNPC3SearchableSpinner);
        standardNPCSpinners.add(sNPC4SearchableSpinner);
        standardNPCSpinners.add(sNPC5SearchableSpinner);
        enterInitiative = view.findViewById(R.id.enterInitiativeButton);

        enterInitiative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEncounter();
            }
        });

        standNPCRef = FirebaseDatabase.getInstance().getReference("standard_npcs");
        standardNPCIFirebaseLoadDone = this;
        setParties();
        setCustomNPCS();
        standNPCRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<StandardNPC> standNPCS = new ArrayList<>();
                for(DataSnapshot standNPCSnapshot:dataSnapshot.getChildren())
                {
                    standardNPCSList.add(standNPCSnapshot.getValue(StandardNPC.class));
                }
                //standardNPCIFirebaseLoadDone.onFirebaseLoadSuccess(standNPCS);
                //standardNPCSList = standNPCS;
                List<String> standNPCNameList = new ArrayList<>();
                for(StandardNPC thisStand:standardNPCSList)
                    standNPCNameList.add(thisStand.getName());
                ArrayAdapter<String> standNPCAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, standNPCNameList);
                sNPC1SearchableSpinner.setAdapter(standNPCAdapter);
                sNPC2SearchableSpinner.setAdapter(standNPCAdapter);
                sNPC3SearchableSpinner.setAdapter(standNPCAdapter);
                sNPC4SearchableSpinner.setAdapter(standNPCAdapter);
                sNPC5SearchableSpinner.setAdapter(standNPCAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                standardNPCIFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });
    }
    public void startEncounter()
    {
        List<EncounterParticipant> participants = new ArrayList<EncounterParticipant>();
        Party currentParty = yourPartiesList.get(partiesSearchableSpinner.getSelectedItemPosition());;

        Iterator hmIterator = currentParty.getPlayers().entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            participants.add(new EncounterParticipant((String)mapElement.getValue()));
        }
        for(SearchableSpinner thisCustSpin : customNPCSpinners)
        {
            CustomNPC thisCustNPC = customNPCList.get(thisCustSpin.getSelectedItemPosition());
            participants.add(new EncounterParticipant(thisCustNPC.getName()));
        }
        for(SearchableSpinner thisStandSpin : standardNPCSpinners)
        {
            StandardNPC thisStandNPC = standardNPCSList.get(thisStandSpin.getSelectedItemPosition());
            participants.add(new EncounterParticipant(thisStandNPC.getName()));
        }
        Encounter thisEncounter = new Encounter(participants);
        Intent intent = new Intent(getActivity(), EnterInitiativeActivity.class);
        intent.putExtra("uID", userId);
        intent.putExtra("thisEncounter", thisEncounter);
        startActivity(intent);
    }

    public void setParties(){
        Query npc = rootRef.child("users").child(userId).child("parties");
        ValueEventListener usersEventListener = (new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key;
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    key = data.getKey();
                    Query npcQuery = rootRef.child("parties").child(key);
                    final List<Party> yourParties = new ArrayList<>();
                    ValueEventListener npcEventListener =(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getKey()!=null) {
                                if(dataSnapshot.getValue()!=null) {
                                    yourPartiesList.add(dataSnapshot.getValue(Party.class));
                                }
                            }
                            List<String> partiesNameList = new ArrayList<>();
                            for(Party thisParty:yourPartiesList)
                                partiesNameList.add(thisParty.getName());
                            ArrayAdapter<String> partyAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, partiesNameList);
                            partiesSearchableSpinner.setAdapter(partyAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    npcQuery.addListenerForSingleValueEvent(npcEventListener);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        npc.addValueEventListener(usersEventListener);
    }

    public void setCustomNPCS(){
        Query npc = rootRef.child("users").child(userId).child("custom_npcs");
        ValueEventListener usersEventListener = (new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key;
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    key = data.getKey();
                    Query npcQuery = rootRef.child("custom_npcs").child(key);
                    final List<CustomNPC> yourCustomNPCS = new ArrayList<>();
                    ValueEventListener npcEventListener =(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getKey()!=null) {
                                if(dataSnapshot.getValue()!=null) {
                                    customNPCList.add(dataSnapshot.getValue(CustomNPC.class));
                                }
                            }
                            List<String> customNPCNameList = new ArrayList<>();
                            for(CustomNPC thisNPC:customNPCList)
                                customNPCNameList.add(thisNPC.getName());
                            ArrayAdapter<String> customNPCAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, customNPCNameList);
                            cNPC1SearchableSpinner.setAdapter(customNPCAdapter);
                            cNPC2SearchableSpinner.setAdapter(customNPCAdapter);
                            cNPC3SearchableSpinner.setAdapter(customNPCAdapter);
                            cNPC4SearchableSpinner.setAdapter(customNPCAdapter);
                            cNPC5SearchableSpinner.setAdapter(customNPCAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    npcQuery.addListenerForSingleValueEvent(npcEventListener);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        npc.addValueEventListener(usersEventListener);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_encounters, container, false);
    }

    @Override
    public void onFirebaseLoadSuccess(List<StandardNPC> standNPCList) {
        standardNPCSList = standNPCList;
        List<String> standNPCNameList = new ArrayList<>();
        for(StandardNPC thisStand:standNPCList)
            standNPCNameList.add(thisStand.getName());
        ArrayAdapter<String> standNPCAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, standNPCNameList);
        sNPC1SearchableSpinner.setAdapter(standNPCAdapter);
        sNPC2SearchableSpinner.setAdapter(standNPCAdapter);
        sNPC3SearchableSpinner.setAdapter(standNPCAdapter);
        sNPC4SearchableSpinner.setAdapter(standNPCAdapter);
        sNPC5SearchableSpinner.setAdapter(standNPCAdapter);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = ((MainActivity)getActivity()).uID;
        yourPartiesList = new ArrayList<>();
        customNPCList = new ArrayList<>();
        standardNPCSList = new ArrayList<>();
    }

    public void onResume() {
        super.onResume();
        userId = ((MainActivity)getActivity()).uID;
    }
}

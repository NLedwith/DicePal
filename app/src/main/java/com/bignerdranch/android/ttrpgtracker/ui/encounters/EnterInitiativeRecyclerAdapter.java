package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.ttrpgtracker.R;
import com.bignerdranch.android.ttrpgtracker.ui.encounters.EncounterParticipant;

import java.util.Collections;
import java.util.List;

public class EnterInitiativeRecyclerAdapter extends RecyclerView.Adapter<com.bignerdranch.android.ttrpgtracker.ui.encounters.EnterInitiativeRecyclerAdapter.ViewHolder>{
    List<EncounterParticipant> participants;
    private LayoutInflater layoutInflator;
    EnterInitiativeRecyclerAdapter(Context ctx, List<EncounterParticipant> participants)
    {
        layoutInflator = LayoutInflater.from(ctx);
        this.participants = participants;
    }

    @NonNull
    @Override
    public com.bignerdranch.android.ttrpgtracker.ui.encounters.EnterInitiativeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflator.inflate(R.layout.row_item_enter_initiative, parent, false);
        com.bignerdranch.android.ttrpgtracker.ui.encounters.EnterInitiativeRecyclerAdapter.ViewHolder viewHolder = new com.bignerdranch.android.ttrpgtracker.ui.encounters.EnterInitiativeRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.bignerdranch.android.ttrpgtracker.ui.encounters.EnterInitiativeRecyclerAdapter.ViewHolder holder, int position) {
        holder.participant_name.setText(participants.get(position).getName());
        holder.participant_initiative.setText(participants.get(position).getInitiative());
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    public void addItem(EncounterParticipant p) {
        int result = findIndex(p);
        if(result!=-1){
            participants.set(result,p);
            this.notifyItemChanged(result);
        }
        else{
            participants.add(p);
            this.notifyItemInserted(participants.size()-1);
        }
    }

    public int findIndex(EncounterParticipant character){
        for(int i = 0;i<participants.size();i++){
            if(character.getName().equals(participants.get(i).getName())){
                return i;
            }
        }
        return -1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView participant_name;
        EditText participant_initiative;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            participant_name = itemView.findViewById(R.id.participantNameTextView);
            participant_initiative = itemView.findViewById(R.id.initiativeTextField);
            participant_initiative.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void afterTextChanged(Editable editable) {
                    participants.get(getAdapterPosition()).setInitiative(participant_initiative.getText().toString());
                }
            });
        }
    }


}

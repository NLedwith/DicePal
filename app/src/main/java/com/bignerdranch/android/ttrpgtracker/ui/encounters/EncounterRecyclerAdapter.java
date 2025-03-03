package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.ttrpgtracker.R;

import java.util.List;

public class EncounterRecyclerAdapter extends RecyclerView.Adapter<com.bignerdranch.android.ttrpgtracker.ui.encounters.EncounterRecyclerAdapter.ViewHolder>{
    List<EncounterParticipant> participants;

    EncounterRecyclerAdapter(List<EncounterParticipant> participants)
    {
        this.participants = participants;
    }

    @NonNull
    @Override
    public com.bignerdranch.android.ttrpgtracker.ui.encounters.EncounterRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
        View view = layoutInflator.inflate(R.layout.row_item_encounter, parent, false);
        com.bignerdranch.android.ttrpgtracker.ui.encounters.EncounterRecyclerAdapter.ViewHolder viewHolder = new com.bignerdranch.android.ttrpgtracker.ui.encounters.EncounterRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.bignerdranch.android.ttrpgtracker.ui.encounters.EncounterRecyclerAdapter.ViewHolder holder, int position) {
        holder.participant_name.setText(participants.get(position).getName());
        holder.participant_hp.setText(participants.get(position).getHp());
        //holder.participant_hp.setVisibility(View.GONE);
        boolean isExpanded = participants.get(position).isExpanded();
        /*if(isExpanded)
            holder.expandableLayout.setVisibility(View.VISIBLE);
        else if(!isExpanded)
            holder.expandableLayout.setVisibility(View.GONE);*/
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
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
        EditText participant_hp;
        TableLayout expandableLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            participant_name = itemView.findViewById(R.id.encounterParticipantNameTextView);
            participant_hp = itemView.findViewById(R.id.hpEditText);
            expandableLayout = itemView.findViewById(R.id.encounterExpandableLayout);
            participant_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    EncounterParticipant thisParticipant = participants.get(getAdapterPosition());
                    thisParticipant.setExpanded(!thisParticipant.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            participant_hp.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void afterTextChanged(Editable editable) {
                    participants.get(getAdapterPosition()).setHp(participant_hp.getText().toString());
                }
            });
        }
    }


}

package com.bignerdranch.android.ttrpgtracker.ui.parties;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.ttrpgtracker.R;
import com.bignerdranch.android.ttrpgtracker.ui.parties.Party;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PartyRecyclerAdapter extends RecyclerView.Adapter<com.bignerdranch.android.ttrpgtracker.ui.parties.PartyRecyclerAdapter.ViewHolder>{
    List<Party> parties;

    PartyRecyclerAdapter(List<Party> parties)
    {
        this.parties = parties;
    }

    @NonNull
    @Override
    public com.bignerdranch.android.ttrpgtracker.ui.parties.PartyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
        View view = layoutInflator.inflate(R.layout.row_item_party, parent, false);
        com.bignerdranch.android.ttrpgtracker.ui.parties.PartyRecyclerAdapter.ViewHolder viewHolder = new com.bignerdranch.android.ttrpgtracker.ui.parties.PartyRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.bignerdranch.android.ttrpgtracker.ui.parties.PartyRecyclerAdapter.ViewHolder holder, int position) {
        holder.party_name.setText(parties.get(position).getName());
        holder.party_notes.setText(parties.get(position).getNotes());
        holder.player_names.setText(parties.get(position).getAllPlayers());

        boolean isExpanded = parties.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }
    @Override
    public int getItemCount() {
        return parties.size();
    }

    public void addItem(Party p) {
        int result = findIndex(p);
        if(result!=-1){
            parties.set(result,p);
            this.notifyItemChanged(result);
        }
        else{
            parties.add(p);
            this.notifyItemInserted(parties.size()-1);
        }
    }

    public int findIndex(Party newParty){
        for(int i = 0;i<parties.size();i++){
            if(newParty.getName().equals(parties.get(i).getName())){
                return i;
            }
        }
        return -1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView party_name, player_names, party_notes;
        ConstraintLayout expandableLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            party_name = itemView.findViewById(R.id.partyNameTextView);
            party_notes = itemView.findViewById(R.id.partyNotesTextView);
            player_names = itemView.findViewById(R.id.partyMemberTextView);
            expandableLayout = itemView.findViewById(R.id.partyExpandableLayout);
            party_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    Party clickedParty = parties.get(getAdapterPosition());
                    clickedParty.setExpanded(!clickedParty.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

}

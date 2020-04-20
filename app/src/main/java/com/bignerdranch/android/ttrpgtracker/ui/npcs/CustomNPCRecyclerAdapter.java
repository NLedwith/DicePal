package com.bignerdranch.android.ttrpgtracker.ui.npcs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.ttrpgtracker.R;

import java.util.Collections;
import java.util.List;

public class CustomNPCRecyclerAdapter extends RecyclerView.Adapter<CustomNPCRecyclerAdapter.ViewHolder>{
    List<CustomNPC> custom_npcs;

    CustomNPCRecyclerAdapter(List<CustomNPC> custom_npcs)
    {
        Collections.sort(custom_npcs);
        this.custom_npcs = custom_npcs;
    }

    @NonNull
    @Override
    public CustomNPCRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
        View view = layoutInflator.inflate(R.layout.row_item_custom_npc, parent, false);
        CustomNPCRecyclerAdapter.ViewHolder viewHolder = new CustomNPCRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomNPCRecyclerAdapter.ViewHolder holder, int position) {
        holder.cust_npc_name.setText(custom_npcs.get(position).getName());
        holder.cust_npc_hp.setText(custom_npcs.get(position).getHp().toString());
        holder.cust_npc_ci.setText(custom_npcs.get(position).getAllConditionImmunities());
        holder.cust_npc_notes.setText(custom_npcs.get(position).getNotes());
        boolean isExpanded = custom_npcs.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }
    @Override
    public int getItemCount() {
        return custom_npcs.size();
    }

    public void addItem(CustomNPC p) {
        int result = findIndex(p);
        if(result!=-1){
            custom_npcs.set(result,p);
            this.notifyItemChanged(result);
        }
        else{
            custom_npcs.add(p);
            this.notifyItemInserted(custom_npcs.size()-1);
        }
    }

    public int findIndex(CustomNPC character){
        for(int i = 0;i<custom_npcs.size();i++){
            if(character.getName().equals(custom_npcs.get(i).getName())){
                return i;
            }
        }
        return -1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView cust_npc_name, cust_npc_hp,cust_npc_ci, cust_npc_notes;
        ConstraintLayout expandableLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            cust_npc_name = itemView.findViewById(R.id.customNPCNameTextView);
            cust_npc_hp = itemView.findViewById(R.id.customNPCHpTextView);
            cust_npc_ci = itemView.findViewById(R.id.customNPCCiTextView);
            cust_npc_notes = itemView.findViewById(R.id.customNPCNotesTextView);
            expandableLayout = itemView.findViewById(R.id.customNPCExpandableLayout);
            cust_npc_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    CustomNPC clickedNPC = custom_npcs.get(getAdapterPosition());
                    clickedNPC.setExpanded(!clickedNPC.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

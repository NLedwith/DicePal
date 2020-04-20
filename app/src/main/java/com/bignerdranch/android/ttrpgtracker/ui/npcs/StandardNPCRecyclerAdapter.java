package com.bignerdranch.android.ttrpgtracker.ui.npcs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bignerdranch.android.ttrpgtracker.R;

import java.util.List;

public class StandardNPCRecyclerAdapter extends RecyclerView.Adapter<StandardNPCRecyclerAdapter.ViewHolder>{

    List<StandardNPC> standard_npcs;

    StandardNPCRecyclerAdapter(List<StandardNPC> standard_npcs)
    {
        this.standard_npcs = standard_npcs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
        View view = layoutInflator.inflate(R.layout.row_item_standard_npc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.stand_npc_name.setText(standard_npcs.get(position).getName());
        holder.stand_npc_cr.setText(standard_npcs.get(position).getCr());
        holder.stand_npc_ac.setText(standard_npcs.get(position).getAc());
        holder.stand_npc_hp.setText(standard_npcs.get(position).getHp());
        holder.stand_npc_ci.setText(standard_npcs.get(position).getAllConditionImmunities());

        boolean isExpanded = standard_npcs.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return standard_npcs.size();
    }

    public void addItem(StandardNPC p) {
        int result = findIndex(p);
        if(result!=-1){
            standard_npcs.set(result,p);
            this.notifyItemChanged(result);
        }
        else{
            standard_npcs.add(p);
            this.notifyItemInserted(standard_npcs.size()-1);
        }
    }

    public int findIndex(StandardNPC character){
        for(int i = 0;i<standard_npcs.size();i++){
            if(character.getName().equals(standard_npcs.get(i).getName())){
                return i;
            }
        }
        return -1;
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView stand_npc_name, stand_npc_cr, stand_npc_ac, stand_npc_hp,stand_npc_ci;
        ConstraintLayout expandableLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            stand_npc_name = itemView.findViewById(R.id.standardNPCNameTextView);
            stand_npc_cr = itemView.findViewById(R.id.standardNPCCrTextView);
            stand_npc_ac = itemView.findViewById(R.id.standardNPCAcTextView);
            stand_npc_hp = itemView.findViewById(R.id.standardNPCHpTextView);
            stand_npc_ci = itemView.findViewById(R.id.standardNPCCiTextView);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            stand_npc_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    StandardNPC clickedNPC = standard_npcs.get(getAdapterPosition());
                    clickedNPC.setExpanded(!clickedNPC.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

package com.bignerdranch.android.ttrpgtracker.ui.rolldice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.ttrpgtracker.R;

public class RollDiceFragment extends Fragment {

    private EditText d20NumField;
    private EditText d12NumField;
    private EditText d10NumField;
    private EditText d8NumField;
    private EditText d6NumField;
    private EditText d4NumField;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        d20NumField = view.findViewById(R.id.editText7);
        d12NumField = view.findViewById(R.id.editText8);
        d10NumField = view.findViewById(R.id.editText9);
        d8NumField = view.findViewById(R.id.editText10);
        d6NumField = view.findViewById(R.id.editText11);
        d4NumField = view.findViewById(R.id.editText12);
        Button roll_dice = view.findViewById(R.id.button2);
        roll_dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiceRoller(getDiceValues());
            }
        });
    }

    private void openDiceRoller(int[] numDice) {
        Intent intent = new Intent(getActivity(), RollDiceActivity.class);
        intent.putExtra("num_dice", numDice);
        startActivity(intent);
    }

    private int[] getDiceValues()
    {
        int[] numDice = new int[6];
        if(!d20NumField.getText().toString().equals(""))
            numDice[0] = Integer.parseInt(d20NumField.getText().toString());
        else
            numDice[0] = 0;

        if(!d12NumField.getText().toString().equals(""))
            numDice[1] = Integer.parseInt(d12NumField.getText().toString());
        else
            numDice[1] = 0;

        if(!d10NumField.getText().toString().equals(""))
            numDice[2] = Integer.parseInt(d10NumField.getText().toString());
        else
            numDice[2] = 0;

        if(!d8NumField.getText().toString().equals(""))
            numDice[3] = Integer.parseInt(d8NumField.getText().toString());
        else
            numDice[3] = 0;

        if(!d6NumField.getText().toString().equals(""))
            numDice[4] = Integer.parseInt(d6NumField.getText().toString());
        else
            numDice[4] = 0;

        if(!d4NumField.getText().toString().equals(""))
            numDice[5] = Integer.parseInt(d4NumField.getText().toString());
        else
            numDice[5] = 0;
        return numDice;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rolldice, container, false);
    }
}

package com.bignerdranch.android.ttrpgtracker.ui.rolldice;

import androidx.appcompat.app.AppCompatActivity;
import com.bignerdranch.android.ttrpgtracker.R;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class RollDiceActivity extends AppCompatActivity {

    TextView diceTotal;
    TextView d20Total;
    TextView d12Total;
    TextView d10Total;
    TextView d8Total;
    TextView d6Total;
    TextView d4Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);
        diceTotal = findViewById(R.id.textView22);
        d20Total = findViewById(R.id.textView21);
        d12Total = findViewById(R.id.textView20);
        d10Total = findViewById(R.id.textView19);
        d8Total = findViewById(R.id.textView18);
        d6Total = findViewById(R.id.textView17);
        d4Total = findViewById(R.id.textView16);
        int[] numDice = getIntent().getIntArrayExtra("num_dice");
        System.out.println(numDice);
        int[] diceSums = rollAllDice(numDice);
        int total = 0;
        for(int val : diceSums)
            total += val;
        d4Total.setText(Integer.toString(diceSums[5]));
        d6Total.setText(Integer.toString(diceSums[4]));
        d8Total.setText(Integer.toString(diceSums[3]));
        d10Total.setText(Integer.toString(diceSums[2]));
        d12Total.setText(Integer.toString(diceSums[1]));
        d20Total.setText(Integer.toString(diceSums[0]));
        diceTotal.setText(Integer.toString(total));
    }

    public int[] rollAllDice(int[] numDice)
    {
        Random rand = new Random();
        int i = 0;
        int d20Sum = 0;
        int d12Sum = 0;
        int d10Sum = 0;
        int d8Sum = 0;
        int d6Sum = 0;
        int d4Sum = 0;
        for(i = 0; i < numDice[0]; i++)
            d20Sum += rand.nextInt(20) + 1;
        for(i = 0; i < numDice[1]; i++)
            d12Sum += rand.nextInt(12) + 1;
        for(i = 0; i < numDice[2]; i++)
            d10Sum += rand.nextInt(10) + 1;
        for(i = 0; i < numDice[3]; i++)
            d8Sum += rand.nextInt(8) + 1;
        for(i = 0; i < numDice[4]; i++)
            d6Sum += rand.nextInt(6) + 1;
        for(i = 0; i < numDice[5]; i++)
            d4Sum += rand.nextInt(4) + 1;
        return new int[]{d20Sum, d12Sum, d10Sum, d8Sum, d6Sum, d4Sum};
    }
}

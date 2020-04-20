package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import java.io.Serializable;
import java.util.List;

public class Encounter implements Serializable {
    List<EncounterParticipant> participants;
    public Encounter(List<EncounterParticipant> participants)
    {
        this.participants = participants;
    }

}

package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import com.bignerdranch.android.ttrpgtracker.ui.parties.Party;

import java.util.List;

public interface PartiesIFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<Party> partyList);
    void onFirebaseLoadFailed(String message);
}

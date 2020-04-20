package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import com.bignerdranch.android.ttrpgtracker.ui.npcs.StandardNPC;

import java.util.List;

public interface StandardNPCIFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<StandardNPC> standNPCList);
    void onFirebaseLoadFailed(String message);
}

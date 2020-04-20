package com.bignerdranch.android.ttrpgtracker.ui.parties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Party implements Serializable {
    private String name;
    private String notes;
    private boolean expanded;
    private HashMap<String,String> players;

    public HashMap<String,String> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String,String> players) {
        this.players = players;
    }

    Party()
    {
        this.fbKey = "";
        this.expanded = false;
    }

    public Party(String name, String notes, HashMap<String,String> players)
    {
        this.fbKey = "";
        this.name = name;
        this.notes = notes;
        this.expanded = false;
        this.players = players;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAllPlayers()
    {
        String allPlayers = "";
        Iterator hmIterator = this.getPlayers().entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            allPlayers = allPlayers + mapElement.getValue() + "\n";
        }
        if (allPlayers.trim().length() > 0)
            allPlayers = allPlayers.substring(0,allPlayers.length()-1);
        return allPlayers;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String fbKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFbKey() {
        return fbKey;
    }

    public void setFbKey(String fbKey) {
        this.fbKey = fbKey;
    }
}

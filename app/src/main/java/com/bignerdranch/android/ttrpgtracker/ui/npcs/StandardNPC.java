package com.bignerdranch.android.ttrpgtracker.ui.npcs;

import java.io.Serializable;

public class StandardNPC implements Serializable{
    private String name;
    private String hp;
    private String cr;
    private String ac;
    private boolean expanded;

    private boolean blinded = false;
    private boolean charmed = false;
    private boolean deafened = false;
    private boolean fatigued = false;
    private boolean frightened = false;
    private boolean grappled = false;
    private boolean incapacitated = false;
    private boolean invisible = false;
    private boolean paralyzed = false;
    private boolean petrified = false;
    private boolean poisoned = false;
    private boolean prone = false;
    private boolean restrained = false;
    private boolean stunned = false;
    private boolean unconscious = false;

    StandardNPC()
    {
        this.expanded = false;
    }

    public StandardNPC(String name, String hp, String cr, String ac, boolean blinded, boolean charmed, boolean deafened, boolean fatigued, boolean frightened, boolean grappled, boolean incapacitated, boolean invisible, boolean paralyzed, boolean petrified, boolean poisoned, boolean prone, boolean restrained, boolean stunned, boolean unconscious) {
        this.name = name;
        this.hp = hp;
        this.cr = cr;
        this.ac = ac;
        this.expanded = false;
        this.blinded = blinded;
        this.charmed = charmed;
        this.deafened = deafened;
        this.fatigued = fatigued;
        this.frightened = frightened;
        this.grappled = grappled;
        this.incapacitated = incapacitated;
        this.invisible = invisible;
        this.paralyzed = paralyzed;
        this.petrified = petrified;
        this.poisoned = poisoned;
        this.prone = prone;
        this.restrained = restrained;
        this.stunned = stunned;
        this.unconscious = unconscious;
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getHp()
    {
        return this.hp;
    }
    public void setHp(String hp)
    {
        this.hp = hp;
    }
    public String getCr()
    {
        return this.cr;
    }
    public void setCr(String cr)
    {
        this.cr = cr;
    }
    public String getAc()
    {
        return this.ac;
    }
    public void setAc(String ac)
    {
        this.ac = ac;
    }


    public boolean isBlinded() {
        return blinded;
    }

    public void setBlinded(boolean blinded) {
        this.blinded = blinded;
    }

    public boolean isCharmed() {
        return charmed;
    }

    public void setCharmed(boolean charmed) {
        this.charmed = charmed;
    }

    public boolean isDeafened() {
        return deafened;
    }

    public void setDeafened(boolean deafened) {
        this.deafened = deafened;
    }

    public boolean isFatigued() {
        return fatigued;
    }

    public void setFatigued(boolean fatigued) {
        this.fatigued = fatigued;
    }

    public boolean isFrightened() {
        return frightened;
    }

    public void setFrightened(boolean frightened) {
        this.frightened = frightened;
    }

    public boolean isGrappled() {
        return grappled;
    }

    public void setGrappled(boolean grappled) {
        this.grappled = grappled;
    }

    public boolean isIncapacitated() {
        return incapacitated;
    }

    public void setIncapacitated(boolean incapacitated) {
        this.incapacitated = incapacitated;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isParalyzed() {
        return paralyzed;
    }

    public void setParalyzed(boolean paralyzed) {
        this.paralyzed = paralyzed;
    }

    public boolean isPetrified() {
        return petrified;
    }

    public void setPetrified(boolean petrified) {
        this.petrified = petrified;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    public boolean isProne() {
        return prone;
    }

    public void setProne(boolean prone) {
        this.prone = prone;
    }

    public boolean isRestrained() {
        return restrained;
    }

    public void setRestrained(boolean restrained) {
        this.restrained = restrained;
    }

    public boolean isStunned() {
        return stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    public boolean isUnconscious() {
        return unconscious;
    }

    public void setUnconscious(boolean unconscious) {
        this.unconscious = unconscious;
    }

    public String getAllConditionImmunities() {
        String cond_immunities = "";
        if(this.isBlinded() == true)
            cond_immunities = cond_immunities + "Blinded, ";
        if(this.isCharmed() == true)
            cond_immunities = cond_immunities + "Charmed, ";
        if(this.isDeafened() == true)
            cond_immunities = cond_immunities + "Deafened, ";
        if(this.isFatigued() == true)
            cond_immunities = cond_immunities + "Fatigued, ";
        if(this.isFrightened() == true)
            cond_immunities = cond_immunities + "Frightened, ";
        if(this.isGrappled() == true)
            cond_immunities = cond_immunities + "Grappled, ";
        if(this.isIncapacitated() == true)
            cond_immunities = cond_immunities + "Incapacitated, ";
        if(this.isInvisible() == true)
            cond_immunities = cond_immunities + "Invisible, ";
        if(this.isParalyzed() == true)
            cond_immunities = cond_immunities + "Paralyzed, ";
        if(this.isPetrified() == true)
            cond_immunities = cond_immunities + "Petrified, ";
        if(this.isPoisoned() == true)
            cond_immunities = cond_immunities + "Poisoned, ";
        if(this.isProne() == true)
            cond_immunities = cond_immunities + "Prone, ";
        if(this.isRestrained() == true)
            cond_immunities = cond_immunities + "Restrained, ";
        if(this.isStunned() == true)
            cond_immunities = cond_immunities + "Stunned, ";
        if(this.isUnconscious() == true)
            cond_immunities = cond_immunities + "Unconscious, ";

        if(!cond_immunities.equals(("")))
            cond_immunities = cond_immunities.substring(0,cond_immunities.length()-2);
        else
            cond_immunities = "None";
        return cond_immunities;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}

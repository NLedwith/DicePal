package com.bignerdranch.android.ttrpgtracker.ui.encounters;

import java.io.Serializable;

public class EncounterParticipant implements Serializable, Comparable<EncounterParticipant>{
    private String name;
    private String initiative = "";
    private boolean blindedImmunity = false;
    private boolean charmedImmunity = false;
    private boolean deafenedImmunity = false;
    private boolean fatiguedImmunity = false;
    private boolean frightenedImmunity = false;
    private boolean grappledImmunity = false;
    private boolean incapacitatedImmunity = false;
    private boolean invisibleImmunity = false;
    private boolean paralyzedImmunity = false;
    private boolean petrifiedImmunity = false;
    private boolean poisonedImmunity = false;
    private boolean proneImmunity = false;
    private boolean restrainedImmunity = false;
    private boolean stunnedImmunity = false;
    private boolean unconsciousImmunity = false;
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

    public EncounterParticipant(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitiative() {
        return initiative;
    }

    public void setInitiative(String initiative) {
        this.initiative = initiative;
    }

    public boolean isBlindedImmunity() {
        return blindedImmunity;
    }

    public void setBlindedImmunity(boolean blindedImmunity) {
        this.blindedImmunity = blindedImmunity;
    }

    public boolean isCharmedImmunity() {
        return charmedImmunity;
    }

    public void setCharmedImmunity(boolean charmedImmunity) {
        this.charmedImmunity = charmedImmunity;
    }

    public boolean isDeafenedImmunity() {
        return deafenedImmunity;
    }

    public void setDeafenedImmunity(boolean deafenedImmunity) {
        this.deafenedImmunity = deafenedImmunity;
    }

    public boolean isFatiguedImmunity() {
        return fatiguedImmunity;
    }

    public void setFatiguedImmunity(boolean fatiguedImmunity) {
        this.fatiguedImmunity = fatiguedImmunity;
    }

    public boolean isFrightenedImmunity() {
        return frightenedImmunity;
    }

    public void setFrightenedImmunity(boolean frightenedImmunity) {
        this.frightenedImmunity = frightenedImmunity;
    }

    public boolean isGrappledImmunity() {
        return grappledImmunity;
    }

    public void setGrappledImmunity(boolean grappledImmunity) {
        this.grappledImmunity = grappledImmunity;
    }

    public boolean isIncapacitatedImmunity() {
        return incapacitatedImmunity;
    }

    public void setIncapacitatedImmunity(boolean incapacitatedImmunity) {
        this.incapacitatedImmunity = incapacitatedImmunity;
    }

    public boolean isInvisibleImmunity() {
        return invisibleImmunity;
    }

    public void setInvisibleImmunity(boolean invisibleImmunity) {
        this.invisibleImmunity = invisibleImmunity;
    }

    public boolean isParalyzedImmunity() {
        return paralyzedImmunity;
    }

    public void setParalyzedImmunity(boolean paralyzedImmunity) {
        this.paralyzedImmunity = paralyzedImmunity;
    }

    public boolean isPetrifiedImmunity() {
        return petrifiedImmunity;
    }

    public void setPetrifiedImmunity(boolean petrifiedImmunity) {
        this.petrifiedImmunity = petrifiedImmunity;
    }

    public boolean isPoisonedImmunity() {
        return poisonedImmunity;
    }

    public void setPoisonedImmunity(boolean poisonedImmunity) {
        this.poisonedImmunity = poisonedImmunity;
    }

    public boolean isProneImmunity() {
        return proneImmunity;
    }

    public void setProneImmunity(boolean proneImmunity) {
        this.proneImmunity = proneImmunity;
    }

    public boolean isRestrainedImmunity() {
        return restrainedImmunity;
    }

    public void setRestrainedImmunity(boolean restrainedImmunity) {
        this.restrainedImmunity = restrainedImmunity;
    }

    public boolean isStunnedImmunity() {
        return stunnedImmunity;
    }

    public void setStunnedImmunity(boolean stunnedImmunity) {
        this.stunnedImmunity = stunnedImmunity;
    }

    public boolean isUnconsciousImmunity() {
        return unconsciousImmunity;
    }

    public void setUnconsciousImmunity(boolean unconsciousImmunity) {
        this.unconsciousImmunity = unconsciousImmunity;
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

    @Override
    public int compareTo(EncounterParticipant o) {
        int chk1 = 0;
        int chk2 = 0;
        if(this.getInitiative().trim().length() != 0)
            chk1 = Integer.parseInt(this.getInitiative());
        if(o.getInitiative().trim().length() != 0)
            chk2 = Integer.parseInt(o.getInitiative());
        return chk2 - chk1;
    }
}

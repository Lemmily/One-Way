package com.models.entities;

import com.badlogic.ashley.core.Entity;
import com.utils.Enums;

/**
 * Created by emily on 19/05/15.
 */
public class Attribute extends Entity {

    private Enums.Attributes theType;


    private int theValue;

    public Attribute(Enums.Attributes pType, int pValue) {
        theType = pType;
        theValue = pValue;
    }

    /**
     * @return modifier to things like the skill check or attack/defense rolls etc.
     */
    public int getModifier() {
        if (theValue <= 1)      return -5;
        else if (theValue < 4)  return -4;
        else if (theValue < 6)  return -3;
        else if (theValue < 8)  return -2;
        else if (theValue < 10) return -1;
        else if (theValue < 12) return 0;
        else if (theValue < 14) return 1;
        else if (theValue < 16) return 2;
        else if (theValue < 18) return 3;
        else if (theValue <= 20) return 5;

        else return 6;
    }

    public int getValue() {
        return theValue;
    }
}

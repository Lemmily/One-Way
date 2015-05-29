package com.models.entities;

import com.utils.Enums;

/**
 * Created by emily on 19/05/15.
 */
public class Attribute {

    private Enums.Attributes theType;


    private int theValue;

    public Attribute(Enums.Attributes pType, int pValue) {
        theType = pType;
        theValue = pValue;
    }

    /**
     * @return modifier for things like the skill check or attack/defense rolls etc.
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

    /**
     * @return value of attribute
     */
    public int getValue() {
        return theValue;
    }

    /**
     * @param pNum amount to increase by
     * @return the new value after the change.
     */
    public int increaseBy(int pNum) {
        theValue += Math.abs(pNum);
        return theValue;
    }

    /**
     * @param pNum amount to decrease by
     * @return the new value after the change.
     */
    public int decreaseBy(int pNum) {
        theValue -= Math.abs(pNum);
        return theValue;
    }
}

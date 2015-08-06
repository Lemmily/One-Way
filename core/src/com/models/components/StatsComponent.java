package com.models.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.models.entities.Attribute;
import com.models.entities.StatMod;
import com.utils.Enums;

/**
 * Created by emily on 19/05/15.
 */
public class StatsComponent extends Component{
    /**
     * container for holding the stats data
     */

    private Attribute theStrength;
    private Attribute theDexterity;
    private Attribute theIntelligence;
    private Attribute theConstitution;
    private Attribute theCharisma;
    private Attribute theWisdom;
    private Attribute theLuck;


    private final ArrayMap<Enums.Attributes, Array<StatMod>> theModifiers = new ArrayMap<>();
    private final Array<StatMod> theRemovableModifiers = new Array<>();

    public StatsComponent(Attribute pStrength, Attribute pDexterity, Attribute pIntelligence, Attribute pConstitution, Attribute pCharisma, Attribute pWisdom, Attribute pLuck) {
        theStrength = pStrength;
        theDexterity = pDexterity;
        theIntelligence = pIntelligence;
        theConstitution = pConstitution;
        theCharisma = pCharisma;
        theWisdom = pWisdom;
        theLuck = pLuck;
    }

    public StatsComponent(int pStrength, int pDexterity, int pIntelligence, int pConstitution, int pCharisma, int pWisdom, int pLuck) {
        theStrength = new Attribute(Enums.Attributes.Strength, pStrength);
        theDexterity = new Attribute(Enums.Attributes.Dexterity, pDexterity);
        theIntelligence = new Attribute(Enums.Attributes.Intelligence, pIntelligence);
        theConstitution = new Attribute(Enums.Attributes.Constitution, pConstitution);
        theCharisma = new Attribute(Enums.Attributes.Charisma, pCharisma);
        theWisdom = new Attribute(Enums.Attributes.Wisdom, pWisdom);
        theLuck = new Attribute(Enums.Attributes.Luck, pLuck);
    }


    public Attribute get(Enums.Attributes pType) {
        switch(pType) {
            case Strength:
                return theStrength;
            case Dexterity:
                return theDexterity;
            case Constitution:
                return theConstitution;
            case Intelligence:
                return theIntelligence;
            case Charisma:
                return theCharisma;
            case Wisdom:
                return theWisdom;
            case Luck:
                return theLuck;
            default:
                return null;
        }
    }

    public int getTotal(Enums.Attributes pType) {
        switch (pType) {
            case Strength:
                return theStrength.getValue() + tallyModifiers(pType);
            case Dexterity:
                return theDexterity.getValue() + tallyModifiers(pType);
            case Constitution:
                return theConstitution.getValue() + tallyModifiers(pType);
            case Intelligence:
                return theIntelligence.getValue() + tallyModifiers(pType);
            case Charisma:
                return theCharisma.getValue() + tallyModifiers(pType);
            case Wisdom:
                return theWisdom.getValue() + tallyModifiers(pType);
            case Luck:
                return theLuck.getValue() + tallyModifiers(pType);
            default:
                return 0;
        }
    }

    public void addModifier(StatMod pStatMod) {
        theModifiers.get(pStatMod.getType()).add(pStatMod);
    }

    public void removeModifier(StatMod pStatMod) {
        theModifiers.get(pStatMod.getType()).removeValue(pStatMod, true);
    }


    public int tallyModifiers(Enums.Attributes pType) {
        int lMod = 0;

        for (int i = 0; i < theModifiers.get(pType).size; i++) {
            if (theModifiers.get(pType).get(i).getTurnsLeft() > 0) {
                lMod += theModifiers.get(pType).get(i).getValue();
            } else {
                theRemovableModifiers.add(theModifiers.get(pType).get(i));
            }
        }

        //Todo: think of a better solution for removing dead modifiers.
        for (int i = 0; i < theRemovableModifiers.size; i++) {
            theModifiers.get(pType).removeValue(theRemovableModifiers.get(i), true);
        }

        return lMod;
    }

}

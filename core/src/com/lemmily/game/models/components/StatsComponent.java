package com.lemmily.game.models.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.lemmily.game.models.entities.Attribute;
import com.lemmily.game.models.entities.StatMod;
import com.lemmily.game.utils.Enums;

/**
 *  A container for holding the stats data. Including modifiers to these stats..
 */
public class StatsComponent implements Component {
    private final ArrayMap<Enums.Attributes, Array<StatMod>> theModifiers = new ArrayMap<>();


    private Attribute theStrength;
    private Attribute theDexterity;
    private Attribute theIntelligence;
    private Attribute theConstitution;
    private Attribute theCharisma;
    private Attribute theWisdom;
    private Attribute theLuck;
//    private Array<StatMod> theRemovableModifiers = new Array<>();

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


    public Attribute getValue(Enums.Attributes pType) {
        switch (pType) {
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

    public int getTotalModified(Enums.Attributes pType) {
        switch (pType) {
            case Strength:
                return theStrength.getModifier() + tallyAndGetModifiers(pType);
            case Dexterity:
                return theDexterity.getModifier() + tallyAndGetModifiers(pType);
            case Constitution:
                return theConstitution.getModifier() + tallyAndGetModifiers(pType);
            case Intelligence:
                return theIntelligence.getModifier() + tallyAndGetModifiers(pType);
            case Charisma:
                return theCharisma.getModifier() + tallyAndGetModifiers(pType);
            case Wisdom:
                return theWisdom.getModifier() + tallyAndGetModifiers(pType);
            case Luck:
                return theLuck.getModifier() + tallyAndGetModifiers(pType);
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


    public int tallyAndGetModifiers( Enums.Attributes pType) {
        int lMod = 0;
        Array<StatMod> lRemovableModifiers = new Array<>();
        for (int i = 0; i < theModifiers.get(pType).size; i++) {
            if (theModifiers.get(pType).get(i).getTurnsLeft() == -1) {
                continue; //permanent modifier. (or until it gets explicitly removed.)
            }
            else if (theModifiers.get(pType).get(i).getTurnsLeft() > 0) {
                lMod += theModifiers.get(pType).get(i).getValue();
            }
            else {
                lRemovableModifiers.add(theModifiers.get(pType).get(i));
            }
        }

        //Todo: think of a better solution for removing dead modifiers.
        for (int i = 0; i < lRemovableModifiers.size; i++) {
            theModifiers.get(pType).removeValue(lRemovableModifiers.get(i), true);
        }

        return lMod;
    }

}

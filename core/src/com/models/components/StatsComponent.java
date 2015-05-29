package com.models.components;

import com.badlogic.ashley.core.Component;
import com.models.entities.Attribute;
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

}

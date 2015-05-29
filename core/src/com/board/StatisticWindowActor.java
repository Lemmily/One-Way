package com.board;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.utils.Assets;

/**
 * Created by emily on 28/05/15.
 */
public class StatisticWindowActor extends Window {

    private Label theLabel;

    public StatisticWindowActor(String title) {
        super(title, Assets.menuSkin);
        theLabel = new Label("", Assets.menuSkin);
        add(theLabel);
    }


    public String cutNewLines(String pText, int pLineWidth) {
        String s = "";

//        pText.split()
        if (pText.length() > pLineWidth) {
            int lNum = pText.length() / pLineWidth;
            for (int i = 1; i <= lNum; i++) {
                s += pText.substring((i - 1) * pLineWidth, i * pLineWidth);
                s += "\n";
            }
        } else {
            s = pText;
        }

        return s;
    }

    public void setLabel(String pText) {
        theLabel.setText(cutNewLines(pText, 10));
    }

}

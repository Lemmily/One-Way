package com.board;


import com.badlogic.gdx.scenes.scene2d.Touchable;
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
        this.setTouchable(Touchable.disabled);
    }

    /**
     * crudely cuts text into {@param pLineWidth} by inserting newlines into the text
     *
     * @param pText      text to format
     * @param pLineWidth width of text
     * @return text filled with new lines.
     */
    public String cutNewLines(String pText, int pLineWidth) {
        String s = "";

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
        theLabel.setText(cutNewLines(pText, 18));
    }

}

package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String currentText;
    private List<String> textHistory;

    public SimpleController() {
        this.currentText = null;
        this.textHistory = new ArrayList<>();
    }

    public void setText(String text) {
        if (Objects.isNull(text)) {
            throw new IllegalArgumentException("Text cannot be null!");
        }
        this.currentText = text;
    }

    public String getText() {
        return this.currentText;
    }

    public List<String> getTextHistory() {
        return List.copyOf(this.textHistory);
    }

    public void printText() {
        if (Objects.isNull(this.currentText)) {
            throw new IllegalStateException("Set text is null!");
        }
        System.out.println(this.currentText);
        this.textHistory.add(this.currentText);
    }

}

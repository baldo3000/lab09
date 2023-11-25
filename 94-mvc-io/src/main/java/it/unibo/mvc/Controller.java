package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    public void setText(String text);

    public String getText();

    public List<String> getTextHistory();

    public void printText();
}

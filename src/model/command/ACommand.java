package model.command;

import java.util.ArrayList;
import java.util.List;
import model.pixel.Pixel;

/**
 * Represents a command that is used to process an image.
 */
public abstract class ACommand implements Command {
  protected List<String> fields;
  protected List<Integer> contents;
  protected List<List<Pixel>> imageVals;
  protected List<List<Pixel>> newImageVals;
  protected int params;

  /**
   * Constructs a command with an empty set of fields.
   */
  public ACommand(int params) {
    this.fields = new ArrayList<>();
  }

  /**
   * Sets this command's fields.
   * @param f an ArrayList of strings for the commands to be set to.
   */
  public void setFields(ArrayList<String> f) {
    for(String s : f) {
      fields.add(s);
    }
  }

  public int numOfParams() {
    return params;
  }
}
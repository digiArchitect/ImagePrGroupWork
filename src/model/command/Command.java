package model.command;

import java.util.ArrayList;
import java.util.function.Function;

import model.image.Image;

public interface Command extends Function<Image, Image> {
  void setFields(ArrayList<String> f);
}

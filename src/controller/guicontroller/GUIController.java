package controller.guicontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a controller for the GUI.
 */
public interface GUIController extends ActionListener {
  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  void actionPerformed(ActionEvent e);
}

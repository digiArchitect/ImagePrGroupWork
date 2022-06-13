package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;


import static java.util.Map.entry;

/**
 * Represents an Image Processor Controller, allowing the user to give inputs to the program,
 * which it will exchange for data and computation from the model.
 */
public class ImagePrControllerImplMock implements ImagePrController {
  private final Readable input;
  private final StringBuilder totalLog;
  private final StringBuilder successLog;
  private final StringBuilder failLog;
  private final ArrayList<String> fakeHash;
  public Appendable log ;

  /**
   * Constructs an Image Processor Controller, given an image processor model, a view, and
   * an input source.
   */
  public ImagePrControllerImplMock(Readable input, Appendable log) throws
          IllegalArgumentException {
    this.input = input;
    this.totalLog = new StringBuilder();
    this.successLog = new StringBuilder();
    this.failLog = new StringBuilder();
    this.fakeHash = new ArrayList<>();
    this.log = log;
  }

  /**
   * Opens the image processor for user input.
   */
  @Override
  public void startProcessor() throws IllegalStateException {
    Scanner fetch = new Scanner(input);
    String arf;
    ArrayList<String> fields = new ArrayList<>();
    int full = 1;
    boolean loaded = false;
    boolean fail;
    Map<String, ArrayList<String>> commandsMap = Map.ofEntries(
            entry("load", new ArrayList<>(Arrays.asList("file-path", "img-name"))),
            entry("save", new ArrayList<>(Arrays.asList("file-path", "img-name"))),
            entry("red-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("blue-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("green-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("value-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("luma-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("intensity-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("horizontal-flip", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("vertical-flip", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("brighten", new ArrayList<>(Arrays.asList("int", "img-name", "img-dest"))));


    while (true) {
      while (fields.size() < full) {
        try {

          fail = true;
          arf = fetch.next();
          totalLog.append("input: " + arf + "\n");

          if (arf.equalsIgnoreCase("q") || arf.equalsIgnoreCase("quit")) {
            log.append("total log: ");
            log.append(totalLog);
            log.append("success log: ");
            log.append("-------------");
            log.append(successLog);
            log.append("fail log: ");
            log.append("-------------");
            log.append(failLog);
            fetch.close();
            return;
          }

          if (fields.size() == 0) {
            if (commandsMap.containsKey(arf)) {
              if (loaded || arf.equals("load")) {
                fields.add(arf);
                successLog.append("Command: " + arf + "\n");
                fail = false;
                full = commandsMap.get(arf).size() + 1;
              }
            }
          } else {
            String parentCommand = fields.get(0);
            String inputType = (commandsMap.get(fields.get(0)).get(fields.size() - 1));
            switch (inputType) {
              case ("int"):
                try {
                  Integer.parseInt(arf);
                  fields.add(arf);
                  successLog.append("String-int: " + arf + "\n");
                  fail = false;
                } catch (Exception e) {
                  //ignore
                }
                break;
              case ("img-name"):
                if (fakeHash.contains(arf) || parentCommand.equals("load")) {
                  fields.add(arf);
                  successLog.append("img-name: " + arf + "\n");
                  fail = false;
                }
                break;
              case ("img-dest"):
                fields.add(arf + "\n");
                successLog.append("img-dest: " + arf + "\n");
                fail = false;
                break;
              case ("file-path"):
                File f = new File(arf);
                if (parentCommand.equals("load")) {
                  if (f.isFile()) {
                    fields.add(arf);
                    successLog.append("file-path: " + arf + "\n");
                    fail = false;
                  }
                } else if (parentCommand.equals("save")) {
                  fields.add(arf);
                  successLog.append(arf + "\n");
                  fail = false;
                }
                break;
              default:
                System.out.println("Input type not recognized.");
            }
          }

          if (fail) {
            failLog.append("FAIL INPUT: " + arf + "\n");
          }
        } catch (Exception e) {
          throw new IllegalStateException("there's nothing in the scanner you moron");
        }
      }


      System.out.println("command executed");

      if (fields.get(0).equals("load")) {
        loaded = true;
      }
      fakeHash.add(fields.get(fields.size() - 1));

      full = 1;
      fields.clear();
    }
  }
}








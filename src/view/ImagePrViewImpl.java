package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents the view of the image processor, allowing us to see our image once we've
 * saved it.
 */
public class ImagePrViewImpl implements ImagePrView {
  /**
   * Saves the image to the user's computer.
   */
  @Override
  public void save(String fileLocation, String fileName,
                   List<Integer> contents, List<String> mapList) throws IOException {
    File newFile = new File(fileLocation);
    FileWriter w = new FileWriter(newFile);
    StringBuilder s = new StringBuilder();
    s.append("P3\n");
    s.append(contents.get(0));
    s.append(" ");
    s.append(contents.get(1));
    s.append("\n");
    s.append(contents.get(2));
    s.append("\n");
    int count = 0;
    for (int x = 0;  x < contents.get(1); x++) {
      for (int y = 0; y < contents.get(0); y++) {
        s.append(mapList.get(count));
        count ++;
      }
      s.append("\n");
    }
    s.append("\n");
    w.write(s.toString());
    w.close();
  }
}
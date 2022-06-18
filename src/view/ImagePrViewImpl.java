package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import model.image.FunctionUtils;
import model.image.Image;
import model.image.Pixel;

import static model.image.FunctionUtils.fileTypeSupported;

/**
 * Represents the view of the image processor, allowing us to see our image once we've
 * saved it.
 */
public class ImagePrViewImpl implements ImagePrView {
  /**
   * Saves the image to the user's computer.
   */
  @Override

  public void save(String fileLoc, String fileName, List<Integer> contents, HashMap<String, Image> images) throws IOException {
    String fileType = fileLoc.split("[.]")[1];
    if (fileTypeSupported(fileType)) {
      saveSupported(fileLoc, contents, images.get(fileName).flatten(), fileType);
    } else if (fileType.equals("ppm")) {
      savePPM(fileLoc, contents, FunctionUtils.getFlatten(images, fileName));
    } else {
      throw new IllegalArgumentException();
    }
  }

  private void saveSupported(String fileLocation,
                             List<Integer> contents, List<Pixel> imageVals, String fileType)
          throws IOException {
    BufferedImage b = new BufferedImage(contents.get(0), contents.get(1), BufferedImage.TYPE_INT_RGB);
    int count = 0;
    for (int x = 0; x < contents.get(1); x++) {
      for (int y = 0; y < contents.get(0); y++) {
        b.setRGB(y, x, imageVals.get(count).hashCode());
        count++;
      }
    }
    ImageIO.write(b, fileType, new File(fileLocation));
  }

  private void savePPM(String fileLocation,
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
    for (int x = 0; x < contents.get(1); x++) {
      for (int y = 0; y < contents.get(0); y++) {
        s.append(mapList.get(count));
        count++;
      }
      s.append("\n");
    }
    s.append("\n");
    w.write(s.toString());
    w.close();
  }

}
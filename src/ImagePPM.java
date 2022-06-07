import java.io.File;

public class ImagePPM  {
  Pixel[][] imageVals;
  String name;
  File current;
  int width;
  int height;
  int maxValue;
  public ImagePPM(Pixel[][] imageVals, String name, File current, int width,
                  int height, int maxValue) {
    this.imageVals = imageVals;
    this.name = name;
    this.current = current;
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
  }



}


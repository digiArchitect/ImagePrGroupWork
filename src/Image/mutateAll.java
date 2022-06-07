package Image;

import java.util.function.Function;

public class mutateAll implements Function<Pixel,Pixel>  {
  int constant;

  public mutateAll(int constant) {
    this.constant = constant;
  }

  @Override
  public Pixel apply(Pixel pixel) {
    StringBuilder p = new StringBuilder();
    for(int x = 0; x < 3; x++) {
      int newRgb = pixel.getChannel(x)+constant;
      if(newRgb > 255) {
        newRgb = 255;
      }
      else if(newRgb < 0) {
        newRgb = 0;
      }
      p.append(newRgb);
    }
    return new Pixel(Integer.parseInt(p.toString()));
  }

}

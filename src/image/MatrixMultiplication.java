package image;

import java.util.function.Function;

public class MatrixMultiplication extends AbstractApply implements Function<Pixel,Pixel> {
  Double[][] matrix;

  public MatrixMultiplication(Double[][] matrix) {
    this.matrix = matrix;
  }

  @Override
  public Pixel apply(Pixel pixel) {
    int[] newRgb = new int[3];
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        newRgb[x] += (int)(pixel.getChannel(y) * matrix[x][y]);
      }
      newRgb[x] = clamp(newRgb[x]);

    }
    return properRGB(newRgb);
  }
    
}

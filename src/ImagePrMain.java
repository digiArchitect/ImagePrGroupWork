import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import controller.ImagePrController;
import controller.ImagePrControllerImpl;
import model.ImagePrModel;
import model.ImagePrModelImpl;
import view.ImagePrView;
import view.ImagePrViewImpl;

/**
 * Runs the program, allowing the user to upload PPM images and edit them using the
 * image processor, and then save them.
 */
public class ImagePrMain {
  /**
   * Runs the program.
   * @param args the command line arguments.
   */
  public static void main(String[] args) throws IOException {
    Readable input;

    if (args.length > 0) {
      if(args[0].equals("file")) {
        try {
          Path filePath = Path.of(args[1]);
          String content = Files.readString(filePath);

          input = new StringReader(content);
        }
        catch(IOException e) {
          System.out.println("Cannot read this file. Reading input from keyboard.");
          input = new InputStreamReader(System.in);
        }
      }
      else {
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
          sb.append(s + " ");
        }
        if(!sb.substring(sb.length() - 1).equals("q")) {
          sb.append("q");
        }
        System.out.println(sb);
        input = new StringReader(sb.toString());
      }
    }
    else {
      input = new InputStreamReader(System.in);
    }
    ImagePrView view = new ImagePrViewImpl();
    ImagePrModel model = new ImagePrModelImpl();
    ImagePrController controller = new ImagePrControllerImpl(input, view, model);
    controller.startProcessor();
  }
}
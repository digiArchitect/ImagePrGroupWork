# Jylah and Archie's Enhanced Image Processor

Jylah and Archie's image processor is a text-based interactive image
processor program that runs in java. Details of the changes we made to our design since the
initial assignment are noted at the bottom of this README.

## Installation

Downlad the zip from handins.

## Usage
You can enter user input either through a command line argument, or through entering into the
console with your keyboard.

+ Running a script
  + in the command line of the run configuration, type "file" and then the name of the text file you wish to run. The program will execute all of the commands, and then exit.
+ Keyboard input 
  + Upon opening the program, a welcome message will display, showing you the format
of inputs and the different commands available to you, and then a console scanner will prompt you
to deliver text input.

Correct input format is specified in USEME.md

If a user enters an invalid input, the program will continue reading through their inputs in search
of a valid one. If at any point the user inputs the string "q" or "Q", the program will terminate.

## Complete Parts

Our program is able to successfully load and save images from all the specified file types of the
assignment. Along with this, it can fully and correctly manipulate images using all commands
specified by the assignment. This includes both direct pixel-mutation, and the use of kernel
matrices to change pixels depending on the ones surrounding them. The program can be run either by
running a text script, or by interacting with it with the keyboard.


## Design Changes
+ The way Pixels represent their RGB value was changed from three int variables to one 32-int bit variable, with a translation method added as a util. This made it easier to deal with BufferedImages.
+ Load was moved from the model to the controller. This adheres more closely to the MVC structure.
+ Added an "Image" and "Pixel" interface that ImageImpl and PixelImpl then implement, as opposed to just having an Image and Pixel class. This was done to remove leaks.
+ General reformatting of some helper methods and functions to simplify code.

## License
I took the photo for mangoes.ppm of myself using a filter on the photobooth app on my own computer.
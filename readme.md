# Jylah and Archie's Image Processor

Jylah and Archie's image processor is a text-based interactive image
processor program that runs in java.

## Installation

Downlad the zip from handins

## Usage

Upon opening the program, a console scanner will prompt you
to deliver text input. You can copy and paste the following string
(without the quotation marks) into the scanner input for a demo of
the program's functionalities. Below is a walkthrough of what each
separate command does.
Upon opening the program, a welcome message will display, showing you the format
of inputs and the different commands available to you, and then a console scanner will prompt you
to deliver text input.

Text inputs come in the format of the name of the command,
and then valid inputs for each of its field types, which will be specified.

If a user enters an invalid input, the program will continue reading through their inputs in search
of a valid one. If at any point the user inputs the string "q" or "Q", the program will terminate.

You can copy and paste the following series of commands into the scanner input for a demo of
the program's functionalities.

## Demo Commands

load res/mangoes.ppm foo red-component foo redfoo blue-component foo bluefoo green-component foo
greenfoo value-component foo valuefoo luma-component foo lumafoo intensity-component foo intensityfoo horizontal-flip foo horizontalfoo vertical-flip foo verticalfoo brighten 50 foo brightfoo brighten -50 foo darkfoo save res/mangoes-red.png redfoo save res/mangoes-blue.png bluefoo save res/mangoes-green.png greenfoo save res/mangoes-value.png valuefoo save res/mangoes-luma.png lumafoo save res/mangoes-intenstiy.png intensityfoo save res/mangoes-horizontal-flip.png horizontalfoo save res/mangoes-vertical-flip.png verticalfoo save res/mangoes-brighter.png brightfoo save res/mangoes-darker.png darkfoo q

## Demo Commands Explained
```python
# returns 'words'
load 
# loads my given photobooth selfie to the processor under the name "foo"
load res/mangoes.ppm foo

# returns 'geese'
foobar.pluralize('goose')
# create an image of only the red component of foo, under the name "redfoo"
red-component foo redfoo

# returns 'phenomenon'
foobar.singularize('phenomena')
```
# create an image of only the blue component of foo, under the name "bluefoo"
blue-component foo bluefoo

# create an image of only the green component of foo, under the name "greenfoo"
green-component foo greenfoo

# create an image of only the value component of foo, under the name "valuefoo"
value-component foo valuefoo

# create an image of only the luma component of foo, under the name "lumafoo"
luma-component foo lumafoo

# create an image of only the intensity component of foo, under the name "intensityfoo"
intensity-component foo intensityfoo

# create an image of a horizontal flip of foo, under the anme "horizontalfoo"
horizontal-flip foo horizontalfoo

# create an image of a vertical flip of foo, under the anme "verticalfoo"
vertical-flip foo verticalfoo

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
# create an image of a brightened foo, under the name "brightfoo"
brighten 50 foo brightfoo

Please make sure to update tests as appropriate.
# create an image of a darkened foo, under the name "darkfoo"
brighten -50 foo darkfoo

# save redfoo to the res folder under the file name mangoes-red.png
save res/mangoes-red.png redfoo

# save bluefoo to the res folder under the file name mangoes-blue.png
save res/mangoes-blue.png bluefoo

# save greenfoo to the res folder under the file name mangoes-green.png
save res/mangoes-green.png greenfoo

# save valuefoo to the res folder under the file name mangoes-value.png
save res/mangoes-value.png valuefoo

# save lumafoo to the res folder under the file name mangoes-luma.png
save res/mangoes-luma.png lumafoo

# save intensityfoo to the res folder under the file name mangoes-intensity.png
save res/mangoes-intenstiy.png intensityfoo

# save horizontalfoo to the res folder under the file name mangoes-horizontal.png
save res/mangoes-horizontal-flip.png horizontalfoo

# save verticalfoo to the res folder under the file name mangoes-vertical.png
save res/mangoes-vertical-flip.png verticalfoo

# save brightfoo to the res folder under the file name mangoes-brighter.png
save res/mangoes-brighter.png brightfoo

# save darkfoo to the res folder under the file name mangoes-darker.png
save res/mangoes-darker.png darkfoo

# quits the program
q
```

## License
[MIT](https://choosealicense.com/licenses/mit/)
I took the photo for mangoes.ppm of myself using a filter on the photobooth app on my own computer.
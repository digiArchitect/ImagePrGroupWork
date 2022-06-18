## Features
+ Load Images
  + Load images into the image processor through the controller.
  + Command format:
    + load *file-source* *image-name*
+ Save Images
  + Save images from the image processor through the controller.
  + Command format:
    + save *file-source* *image-name*
+ Greyscale Images
  + Greyscale images through a variety of means.
  + Commands include:
    + greyscale
    + red-component
    + green-component
    + blue-component
    + luma
    + intensity
    + value
  + Command format:
    + *name-of-command* *image-name* *image-destination*
+ Brighten/Darken Images
  + Brighten or darken images by a certain constant integer value.
  + Command format:
    + brighten *amount* *image-name* *image-destination*
+ Flip Images
  + Flip images, vertically or horizontally.
  + Commands include:
    + vertical-flip
    + horizontal-flip
  + Command format:
    + *type*-flip *image-name* *image-destination*
+ Blur Images
  + Blur images.
  + Command format:
    + blur *image-name* *image-destination*
+ Sharpen Images
  + Sharpen images.
  + Command format:
    + sharpen *image-name* *image-destination*
+ Sepia Filter
  + Add a sepia filter over images.
  + Command format:
    + sepia *image-name* *image-destination*

    
## Demo Commands Explained
Attatched in this zip file is a script that contains a series of commands to be executed
in this program. This is every one of those commands, explained.
```python
# loads my given photobooth selfie to the processor under the name "foo"
load res/mangoes.ppm foo

# create an image of only the red component of foo, under the name "redfoo"
red-component foo redfoo

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

# create an image of a darkened foo, under the name "darkfoo"
brighten -50 foo darkfoo

# create an image of a foo with a sepia filter, under the name "sepiafoo"
sepia foo sepiafoo 

# create an image of a foo with a greyscale filter, under the name "greyfoo"
greyscale foo greyfoo 

# create an image of a sharpened foo, under the name "sharpfoo"
sharpen foo sharpfoo 

#create an image of a blurred foo, under the name "blurfoo"
blur foo blurfoo

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

# save sepiafoo to the res folder under the file name mangoes-sepia.png
save res/mangoes-sepia.png sepiafoo

# save greyfoo to the res folder under the file name mangoes-grey.png
save res/mangoes-grey.png greyfoo 

# save sharpfoo to the res folder under the file name mangoes-sharpen.png
save res/mangoes-sharp.png sharpfoo 

# save blurfoo to the res folder under the file name mangoes-blurry.png
save res/mangoes-blurry.png blurfoo

# quits the program
q
```
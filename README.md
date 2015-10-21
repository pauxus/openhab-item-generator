Openhab item generator
======================

This small projects is a proof of concept for my config-dsl library (http://github.com/blackbuild/config-dsl), as well
as a handy generator to create item files for an openhab installation.

## Components 

The System consists of three components:

### The model
 
The model resides along with some helper classes under `src/main/groovy`. It consists of a couple of config-dsl
enhanced classes describing various parts of an openhab system.

### The config file

The config file makes use of the model's dsl features to describe an actual openhab installation (look into
src/test/groovy/SampleConfig.groovy for a very basic example.

### The generators

Generators are groovy scripts that run on an existing OpenHab config instance and create various fragments and
actual config files. Using a common base-class (FileGenerator), generators can write results into config files,
taking care of preserving manually created code. 

# Usage

Writing the config file is currently best done using IntelliJ IDEA, because of the direct support for config-dsl 
(eclipse support is still work in progess). Either clone this project and modify it directly or create a separate
project and include the items-generator and config-dsl in the classpath. I plan to create a shaded version containing
every dependency directly.

Adapt the generators as needed.

Run the generator from your IDE or using the shaded jar from the commandline and supply the necessary three parameters
configFile, generators-directory, output directory.

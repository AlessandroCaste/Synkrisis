# Synkrisis

Synkrisis is a bigraph modelling and engineering toolchain sporting a modular approach aimed at streamlining support for bigraphical model checking.

## How it works

Synkrisis toolchain allows to: 
- Specify Bigraphical Models 
- Produce Labeled Transition Graphs from such Model Specifications
- Translate to external model checker languages  
  

Currently, Synkrisis engine runs on: 
- BigMC (the Bigraphical Model Checker) as Bigraphical Reaction System Interpreter, outputting transition graphs  
(https://github.com/AlessandroCaste/bigmc)

And it allows exporting to the following model checkers:
- SPOT model checker, HOA format (https://spot.lrde.epita.fr/)
- PRISM model checker (https://www.prismmodelchecker.org/)

Further infos on bigraph and the syntax behind Synkrisis can be found in the its Github wiki.  
If you'd like to give Synkrisis a try, there's a ready-to-use Jupyter notebook (and it also supports Prism and Spot from its command line!).

## Installation

Synkrisis was built and tested on UNIX-Linux systems (Ubuntu/Debian/Arch); execution has also been tested in Windows WSL.  
There are two ways to install Synkrisis: 
1. Standard Installation
2. Docker Installation 

### Standard Installation
For a correct installation please follow these steps:  
1. Install BigMC https://github.com/AlessandroCaste/bigmc, be sure to install all dependencies and do not forget to add the binaries in /usr/local/bigmc/bin to your path
2. Be sure to have Java 11+ installed
3. Download the latest release from the release tab  
...and you're done! You just need to run it!  

Note that eventually other model checkers may be used side-by-side with Synkrisis, so BigMC is required only until that moment.

### Docker Installation
Provided you've docker installed, simply pull and run:  
``` 
sudo docker pull alessandrocaste/synkrisis-docker:$TAG
sudo docker run  alessandrocaste/synkrisis-docker:$TAG
``` 
Where the latest tag can be found in the (you guessed it) 
`alessandrocaste/synkrisis-docker` repository  
You will be able to run Synkrisis from the Dockerized environment, together with Spot and Prism instances ready to use

## Running Synkrisis

Synkrisis can be run both via CLI and its interactive shell, you can find more info in the Wiki.  
The standard and simple way of executing Synkrisis is by running its jar:  
``` java -jar /path/to/Synkrisis.jar ```  
That has been already aliased to ``` synkrisis ``` in the dockerized environment.  


## Related Repos
1. Synkrisis for Jupyter Notebook: a sample (and simple) jupyter notebook to test out Synkrisis capabilities  
(https://github.com/AlessandroCaste/SynkrisisJupyter)
2. Synkrisis for Docker  
(https://github.com/AlessandroCaste/Synkrisis-docker)
(https://cloud.docker.com/repository/docker/alessandrocaste/synkrisis-docker/tags)
3. Bigraph specification format for VSCode   
(https://github.com/AlessandroCaste/bigraph-vscode)

SPACE BALLS PROGRAM DESCRIPTION
Chris Pan, 
Brendan DeMilt


The program "Space Balls" is Brendan's creative name for the Barnes Hutt
algorithm. This algorithm is an n-body space simulation that has nlogn complexity.
The algorithm groups nearby bodies and approximates them as a single body.
To tell if 2 nodes can be grouped, use the test s/d < 0.5, s being the width of 
the region represented by internal node, and d is distance between body and the
center of mass.

The data structure we use is the quad-ST-tree, the node has a planet, and its 
corresponding square representing 2d space. The root node represents the whole
plane, with children splitting it up evenly into 4 squares. The symbol table
is utilized as the key stores a square plane where each node value is a planet
body in itself. We chose this program because space is amazing and this is a lot more fun than 
a zooming algorithm


ENJOY 


More information on the barnes hut algorithm:
http://arborjs.org/docs/barnes-hut

For an interactive link, check out:
http://www.stefanom.org/bh-tree/


TO RUN:
compile:
javac SpaceBalls.java

run:
java SpaceBalls < galaxyformation.txt


a good time change value is .1 as any large number will be too quick for you to see

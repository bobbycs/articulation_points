This program finds articulation points and the corresponding biconnected components.

An articulation point is a vertex whose removal would result in the separation of
parts of the graph.
The biconnected components of a graph are the separate parts which would exist if
articulation points were to be removed.

The main method builds an internal graph using an edge list representation. It builds
it from an input file (given as an argument to the program) that represents a graph 
in the following format:

   [number of vertices]
    [vertex 1] [vertex2]
    ...

The first line gives the number of vertices.
All subsequent lines represent edges in the graph.
Every edge is undirected.
Vertices do not contain data.
The vertices are labeled with integer values between 0 and [number of vertices] - 1.
The program assumes that this input file is properly formatted.

Example input file:

16
 1 6
 1 14
 2 4
 2 10
 3 4
 3 15
 4 6
 4 7
 4 10
 5 14
 0 1
 0 5
 0 6
 0 14
 1 5
 6 14
 7 9
 8 9
 8 12
 8 13
 10 15
 11 12
 11 13
 12 13


The corresponding output for this graph would be:

Number of Nodes: 16
Number of Edges: 24
Number of Biconnected Components = 7
Number of Articulation Points: 7
Articulation Points:  4  8  9  7  4  6  0
Component 1: {{2,4}{2,10}{10,4}{10,15}{15,3}{3,4}}
Component 2: {{12,8}{12,11}{11,13}{13,8}}
Component 3: {{8,9}}
Component 4: {{9,7}}
Component 5: {{7,4}}
Component 6: {{4,6}}
Component 7: {{1,6}{6,0}{6,14}{14,5}{5,0}{14,0}{1,0}}
Runtime: 6.730000000000001E-4 seconds

You are given an MXN matrix. Every cell of the matrix had a cost associated. Initially you are (0,0) and you need to reach at (M-1,N-1). Find the minimum cost to reach the destination and also the minimum cost path. You can only move right cell, down cell and diagonal lower cell.

**Input**:

Input1: A string array containing rows matrix as element.

Input2: An integer having number of rows in the input matrix.

**Output**:

The output will be a string containing minimum cost and the path chosen separated by the comma. The output for all the invalid input should be printed on console as NA.

For example: Given the cost matrix

5 7 2 4

1 8 1 3

6 2 9 5

1 6 2 8

Minimum cost path : BDDR 
B - Down cell

D - Diagonal cell

R - Right cell

Hence your output 18,BDDR. Where 18 is the minimum cost to reach (M-1,N-1) along path BDDR.

**Solution**:

Algorithm:

Find a new matrix on the basis of given input matrix which will have minimum cost to reach the respective cell. For above input matrix it will be:

5  12 14 18

6  13 13 16

12 8  17 18

13 14 10 18 

Example: To reach the cell (1,1) the minimum cost is 13 which is addition of 5 and 8. The (M-1,N-1) cell shows the minimum cost to reach the cell, 18. This is your first answer. 

To find the path, You have to start from destination cell (M-1,N-1) and look for minimum cost from neighbourhood. So for 18 it is 10. That is a right move R. 
Similarly 10 to 8 - Diagonal move D
8 to 6 - Diagonal move D
6 to 5 - Down move B

So output is **18,BDDR**.






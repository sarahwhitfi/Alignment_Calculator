	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	Two options are available when running the program. 
	The first option is the general option and it calculates the closest path from a provided node to every node in a digraph. The second option calculates optimal alignment.
	
	TO RUN THE PROGRAM //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
	THe program should be compiled and ran from the terminal with the inputs:
	javac MainDijkstra.java
	java MainDijkstra inputx.txt
	
	The text file's name will not actually be inputx.txt and will vary based on which test case you run. I have created 6 test cases, which are numbered input1.txt, input2.txt, etc.
	Please replace inputx.txt with the name of one of these input files and ensure that the text file is directly accessible by the program. Otherwise, you may need to provide a path to the file.
	When running the alignment case, inputx.txt should be replaced with input_align.txt, which I have provided. input_align.txt is formatted correctly to solve part 2 of the homework.
	I have attached all of these input files in the zip folder.
	
	
	INPUT GUIDELINES //---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	The general option reads a file with the following format (the file should have no blank lines at the beginning; "Alignment?" should start the first line of the file):
	
	Alignment? (1 for yes, 0 for no)
	0
	Number of nodes: 
	8
	Source node:
	4
	Edges (start, end, weight):
	1 0 8
	1 3 1
	2 6 5
	3 1 1
	3 7 2
	4 2 2
	4 6 2
	5 3 6
	6 5 3
	6 7 3
	
	
	For the general format: 
	Alignment represents which option is chosen (general or alignment). It should be 0 for all general format inputs.
	Number of nodes represents the total number of vertices
	Source node represents the starting node (v0 to v(n-1) are possible inputs)
	Edges are represented with three integers. To add an edge enter the starting vertex, the ending vertex, and the weight of the edge. Any amount of edges can be added.
	
	My 6 general input files that were created from the Djikstra algorithm visualizer are attached as examples of correctly formatted inputs. 
	
	
	The alignment option reads a file with the following format (also without a newline at the beginning):
	
	Alignment? (1 for yes, 0 for no) 
	1 
	Strings:
	AGTGTCT ATTACG
	Scoring: (match, mismatch, indel)
	1 4 6
	
	
	For the alignment format:
	Alignment represents which option is chosen (general or alignment). It should be 1 for all alignment format inputs.
	Strings provides the two strings whose alignment will be calculated.
	Scoring is represented by 3 integers, which represent the match, mismatch, and indel values.
	
	
	
	GENERAL INPUT AND OUTPUT VALUES //-----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	Input 1:
	Alignment? (1 for yes, 0 for no)
	0
	Number of nodes: 
	8
	Source node:
	4
	Edges (start, end, weight):
	1 0 8
	1 3 1
	2 6 5
	3 1 1
	3 7 2
	4 2 2
	4 6 2
	5 3 6
	6 5 3
	6 7 3
	
	
	Output 1:
	
		Results with source = v4:
	
			Shortest path to vertex v0 is 20
				Path is: [4, 6, 5, 3, 1, 0]
			Shortest path to vertex v1 is 12
				Path is: [4, 6, 5, 3, 1]
			Shortest path to vertex v2 is 2
				Path is: [4, 2]
			Shortest path to vertex v3 is 11
				Path is: [4, 6, 5, 3]
			Shortest path to vertex v4 is 0
				Path is: [4]
			Shortest path to vertex v5 is 5
				Path is: [4, 6, 5]
			Shortest path to vertex v6 is 2
				Path is: [4, 6]
			Shortest path to vertex v7 is 5
				Path is: [4, 6, 7]
	
	
	
	Input 2:
	Alignment? (1 for yes, 0 for no)
	0
	Number of nodes: 
	8
	Source node:
	5
	Edges (start, end, weight):
	1 0 2
	2 1 7
	2 4 7
	2 6 4
	4 6 5
	4 7 7
	5 6 7
	6 2 5
	6 7 1
	
	
	Output 2:
	
		Results with source = v5:
	
			Shortest path to vertex v0 is 21
				Path is: [5, 6, 2, 1, 0]
			Shortest path to vertex v1 is 19
				Path is: [5, 6, 2, 1]
			Shortest path to vertex v2 is 12
				Path is: [5, 6, 2]
			Vertex v3 is unreachable!
				Path does not exist!
			Shortest path to vertex v4 is 19
				Path is: [5, 6, 2, 4]
			Shortest path to vertex v5 is 0
				Path is: [5]
			Shortest path to vertex v6 is 7
				Path is: [5, 6]
			Shortest path to vertex v7 is 8
				Path is: [5, 6, 7]
	
	
	
	Input 3:
	Alignment? (1 for yes, 0 for no)
	0
	Number of nodes: 
	18
	Source node:
	7
	Edges (start, end, weight):
	0 4 7
	0 14 8
	1 4 9
	2 6 9
	3 2 5
	4 1 1
	5 1 4
	5 4 3
	5 6 4
	5 8 7
	6 3 2
	6 5 3
	7 11 1
	9 6 2
	9 8 8
	9 13 8
	10 9 8
	10 13 4
	11 14 3
	12 9 9
	12 16 8
	13 17 9
	14 15 1
	15 12 7
	15 14 1
	16 12 7
	
	
	Output 3:
	
		Results with source = v7:
	
			Vertex v0 is unreachable!
				Path does not exist!
			Shortest path to vertex v1 is 30
				Path is: [7, 11, 14, 15, 12, 9, 6, 5, 1]
			Shortest path to vertex v2 is 30
				Path is: [7, 11, 14, 15, 12, 9, 6, 3, 2]
			Shortest path to vertex v3 is 25
				Path is: [7, 11, 14, 15, 12, 9, 6, 3]
			Shortest path to vertex v4 is 29
				Path is: [7, 11, 14, 15, 12, 9, 6, 5, 4]
			Shortest path to vertex v5 is 26
				Path is: [7, 11, 14, 15, 12, 9, 6, 5]
			Shortest path to vertex v6 is 23
				Path is: [7, 11, 14, 15, 12, 9, 6]
			Shortest path to vertex v7 is 0
				Path is: [7]
			Shortest path to vertex v8 is 29
				Path is: [7, 11, 14, 15, 12, 9, 8]
			Shortest path to vertex v9 is 21
				Path is: [7, 11, 14, 15, 12, 9]
			Vertex v10 is unreachable!
				Path does not exist!
			Shortest path to vertex v11 is 1
				Path is: [7, 11]
			Shortest path to vertex v12 is 12
				Path is: [7, 11, 14, 15, 12]
			Shortest path to vertex v13 is 29
				Path is: [7, 11, 14, 15, 12, 9, 13]
			Shortest path to vertex v14 is 4
				Path is: [7, 11, 14]
			Shortest path to vertex v15 is 5
				Path is: [7, 11, 14, 15]
			Shortest path to vertex v16 is 20
				Path is: [7, 11, 14, 15, 12, 16]
			Shortest path to vertex v17 is 38
				Path is: [7, 11, 14, 15, 12, 9, 13, 17]
	
	
	
	Input 4:
	Alignment? (1 for yes, 0 for no)
	0
	Number of nodes: 
	8
	Source node:
	5
	Edges (start, end, weight):
	0 1 7
	0 2 8
	1 0 7
	1 3 2
	1 5 2
	2 0 6
	2 6 2
	3 7 7
	4 6 4
	4 7 2
	5 6 7
	5 7 6
	6 4 7
	6 5 2
	
	
	Output 4:
	
		Results with source = v5:
	
			Vertex v0 is unreachable!
				Path does not exist!
			Vertex v1 is unreachable!
				Path does not exist!
			Vertex v2 is unreachable!
				Path does not exist!
			Vertex v3 is unreachable!
				Path does not exist!
			Shortest path to vertex v4 is 14
				Path is: [5, 6, 4]
			Shortest path to vertex v5 is 0
				Path is: [5]
			Shortest path to vertex v6 is 7
				Path is: [5, 6]
			Shortest path to vertex v7 is 6
				Path is: [5, 7]
	
	
	
	Input 5:
	Alignment? (1 for yes, 0 for no)
	0
	Number of nodes: 
	18
	Source node:
	13
	Edges (start, end, weight):
	0 1 1
	0 2 7
	0 4 5
	0 7 1
	0 14 6
	1 0 7
	1 5 3
	2 5 7
	3 6 7
	3 17 9
	5 8 4
	5 9 4
	6 2 1
	6 5 4
	8 5 9
	8 7 9
	8 9 5
	9 5 3
	9 6 2
	9 13 2
	10 3 7
	10 17 4
	11 12 5
	11 15 1
	12 9 4
	12 11 9
	13 12 1
	13 16 4
	14 11 3
	15 12 6
	15 16 7
	16 12 9
	16 15 9
	16 17 9
	17 10 2
	
	
	Output 5:
	
		Results with source = v13:
	
			Vertex v0 is unreachable!
				Path does not exist!
			Vertex v1 is unreachable!
				Path does not exist!
			Shortest path to vertex v2 is 8
				Path is: [13, 12, 9, 6, 2]
			Shortest path to vertex v3 is 22
				Path is: [13, 16, 17, 10, 3]
			Vertex v4 is unreachable!
				Path does not exist!
			Shortest path to vertex v5 is 8
				Path is: [13, 12, 9, 5]
			Shortest path to vertex v6 is 7
				Path is: [13, 12, 9, 6]
			Shortest path to vertex v7 is 21
				Path is: [13, 12, 9, 5, 8, 7]
			Shortest path to vertex v8 is 12
				Path is: [13, 12, 9, 5, 8]
			Shortest path to vertex v9 is 5
				Path is: [13, 12, 9]
			Shortest path to vertex v10 is 15
				Path is: [13, 16, 17, 10]
			Shortest path to vertex v11 is 10
				Path is: [13, 12, 11]
			Shortest path to vertex v12 is 1
				Path is: [13, 12]
			Shortest path to vertex v13 is 0
				Path is: [13]
			Vertex v14 is unreachable!
				Path does not exist!
			Shortest path to vertex v15 is 11
				Path is: [13, 12, 11, 15]
			Shortest path to vertex v16 is 4
				Path is: [13, 16]
			Shortest path to vertex v17 is 13
				Path is: [13, 16, 17]
	
	
	
	Input 6:
	Alignment? (1 for yes, 0 for no)
	0
	Number of nodes: 
	8
	Source node:
	0
	Edges (start, end, weight):
	0 2 7
	0 4 7
	2 0 3
	2 5 3
	2 6 7
	3 1 3
	3 7 2
	5 1 9
	6 4 8
	6 7 8
	7 5 8
	7 6 4
	
	
	Output 6:
	
		Results with source = v0:
	
			Shortest path to vertex v0 is 0
				Path is: [0]
			Shortest path to vertex v1 is 19
				Path is: [0, 2, 5, 1]
			Shortest path to vertex v2 is 7
				Path is: [0, 2]
			Vertex v3 is unreachable!
				Path does not exist!
			Shortest path to vertex v4 is 7
				Path is: [0, 4]
			Shortest path to vertex v5 is 10
				Path is: [0, 2, 5]
			Shortest path to vertex v6 is 14
				Path is: [0, 2, 6]
			Shortest path to vertex v7 is 22
				Path is: [0, 2, 6, 7]
	
	
	ALIGNMENT INPUT AND OUTPUT VALUES //---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	The input of my alignment operation is:
	
	Alignment? (1 for yes, 0 for no) 
	1 
	Strings:
	AGTGTCT ATTACG
	Scoring: (match, mismatch, indel)
	1 4 6
	
	
	The output of my alignment operation is:
	
		In the form (row, column), the optimal alignment path is:
		(0, 0) (1, 1) (2, 2) (3, 3) (4, 4) (4, 5) (5, 6) (6, 7)
	
		The optimal alignment is:
		AGTGTCT
		ATTA-CG
	
		The alignment score is: 21
	
	
	The interpretation of this output is:
	We know that whenever both the column and the row increase, a corresponding character from the both the strings will be added to the alignment strings.
	If only one increases, that one adds a character from its string, and the other adds a gap character.
	
	Therefore:
	(0, 0) represents the start node and not a character.
	(1, 1) represents adding both the character 'A' from the first character of the first string and the character 'A' from the first character of the second.
	(2, 2) represents adding both the character 'G' from the second character of the first string and the character 'T' from the second character of the second.
	(3, 3) represents adding both the character 'T' from the third character of the first string and the character 'T' from the third character of the second.
	(4, 4) represents adding both the character 'G' from the fourth character of the first string and the character 'A' from the fourth character of the second.
	(5, 4) represents adding only the character 'T' from the fifth character of the first string and adding the character '-' to represent a gap in the second.
	(6, 5) represents adding both the character 'C' from the sixth character of the first string and the character 'C' from the fifth character of the second.
	(7, 6) represents adding both the character 'T' from the seventh character of the first string and the character 'G' from the sixth character of the second.
	
	This results in an alignment of:
	AGTGTCT
	ATTA-CG
	
	This interpretation result matches the result reached using the program.
	
	

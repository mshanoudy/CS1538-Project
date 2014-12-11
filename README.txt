========================================
CS 1538 Group Project Information Sheet
----------------------------------------

Name(s):  Steven Ross, Mark Shanoudy, Jeremy McCandlish

Lecture section: Mon Wed 6:00-7:15pm  

Assignment #: Group Project  

Program due date:  12/10/14

Handed in date: 12/10/14

Language: Java

Version:  jdk1.7.0_09

Relevant File name(s):

-data--Contains all data outputed from our simulation experiments and obervations
	- DistributionTest.nb--file with results of checking observable data dists. using Mathematica
	- Experimental-Results.xlsx--file that has the important stat results
	- Experiments.xlsx--file containg the different outputed results from the differen expirment runs
	- Experiments(1hour).xlsx--file containg the expeirments for a one hour time period
	- RAW-DATA.xlsx--File containing our observable data
-src--Contains all of the java source files for the simulation(run the simulation inside this folder)
	- CheckoutServer.java
	- Customer.java
	- MTGServer.java
	- QZServer.java
	- RandomGenerator.java
	- ServerQueue.java
	- SimDriver.java
	- SimulationEngine.java
	
Other Files:

SimProjectReport.docx-location for the report portion of the project

output.txt - location of the results of the simulation(open with excel for the correct format)


Additional comments to the grader:

Compile: javac SimDriver.java

Run: java SimDriver h c m

	h = the number of ours to run the simulation
	c = the number of servers you would like to use for the checkout area
	m = the number of servers you would like to use for the mtg server area



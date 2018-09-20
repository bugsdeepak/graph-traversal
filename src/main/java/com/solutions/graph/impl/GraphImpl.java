package com.solutions.graph.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component("graphImpl")
public class GraphImpl implements Graph {
	
	//A table structure represents the graph
	private List<String> leftArray;
	private List<String> rightArray;
		
	public GraphImpl() {
		leftArray = new ArrayList<String>();
		rightArray = new ArrayList<String>();
	}
	
	/**
	 * Load the pair for cities from the file
	 * @param fileName
	 */
	@Override
	public void loadCitiesFromFile(String fileName) {
		String completeFileName = "src\\main\\resources\\" + fileName;
		Stream<String> stream = null;
		try {
			stream = Files.lines(Paths.get(completeFileName));
			stream.forEach(x -> addCitiesToTable(x));
			stream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
		   if(stream != null) {
			   stream.close();
		   }
		}
	}
	
	private void addCitiesToTable(String strLine) {
		if(strLine!= null && strLine.length()>2 && strLine.contains(",")) {
			String split [] = strLine.split(",");
			leftArray.add(split[0].trim().toLowerCase());
			rightArray.add(split[1].trim().toLowerCase());
		}
	}
	
	/**
	 * Add and a pair of destinations which are connected
	 * The city names can be in all CAPS or small case
	 * @param source
	 * @param destination
	 * 
	 */
	@Override
	public void add(String left, String right) {
		if(left != null && left.length() > 0 && right != null && right.length() >0) {
			//Duplicate ==> 1. Left element found in left array And right element found in right array
			//              2. Right element found in left array And left element found in right array
			if( (leftArray.contains(left) && rightArray.contains(right)) ||
					(leftArray.contains(right) && rightArray.contains(left)) ) {
				System.out.println("Duplicate  = " + left + " : " + right);
			} else {
				leftArray.add(left);
				rightArray.add(right);
			}
		}
	}
	
	/**
	 * Check whether two cities are connected with roads
	 * If origin and destination are same then always returns true
	 * Returns false if the destination cannot be reached from origin
	 * by traversing in between cities
	 * 
	 * @param origin
	 * @param destination
	 * @return boolean True if connected otherwise false
	 */
	@Override
	public boolean isConnected(String left, String right) {
		left = left.toLowerCase();
		right = right.toLowerCase();
		
		String curElement = left;
		int leftArrayIndx = -1;
		int rightArrayIndx = -1;
		
		//create a temporary list - leave the original table untouched
		//this is a overhead in memory but only to find two cities are connected
		List<String> tmpLeftArray = new ArrayList<String>(leftArray);
		List<String> tmpRightArray = new ArrayList<String>(rightArray);
		
		//start from left and traverse till you find right
		while(!curElement.equalsIgnoreCase(right)) {
			
			//if current element found in left array
			if(tmpLeftArray.contains(curElement)) {
				
				//then current element will be the one on its right in table
				leftArrayIndx = tmpLeftArray.indexOf(curElement);
				curElement = tmpRightArray.get(leftArrayIndx);
				
				//and remove that table entry as we have traversed it
				tmpLeftArray.remove(leftArrayIndx);
				tmpRightArray.remove(leftArrayIndx);
				
			
			//if current element found in right array
			} else if (tmpRightArray.contains(curElement)) {
				
				//then current element will be the one on its left in table
				rightArrayIndx = tmpRightArray.indexOf(curElement);
				curElement = tmpLeftArray.get(rightArrayIndx);
				
				//and remove that table entry as we have traversed it
				tmpLeftArray.remove(rightArrayIndx);
				tmpRightArray.remove(rightArrayIndx);
				
			// element not found in left array as well as right array to proceed	
			} else {
				return false;
			}
		}
		//while condition failed - right element found in traversal
		return true;
	}
}

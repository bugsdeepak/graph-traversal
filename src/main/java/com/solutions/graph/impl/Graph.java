package com.solutions.graph.impl;

/**
 * 
 * @author Antony
 * interface that represents a Graph and its operation.
 */
public interface Graph {
	
	/**
	 * Add and a pair of destinations which are connected
	 * The city names can be in all CAPS or small case
	 * @param source
	 * @param destination
	 * 
	 */
	public void add(String source, String destination);
	
	/**
	 * Load the pair for cities from the file which contains each row 
	 * with comma separated values
	 * @param fileName
	 */
	public void loadCitiesFromFile(String fileName);
	
	/**
	 * Check whether two cities are connected with roads
	 * If origin and destination are same then always returns true
	 * Returns false if the destination cannot be reached from origin
	 * by traversing in between cities
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public boolean isConnected(String origin, String destination);

}

package com.solutions.graph.model;

public class GraphTraversal {
	
	private final String origin;
	private final String destination;
	private final boolean isConnected;
	
	public GraphTraversal(String origin, String destination, boolean isConnected) {
		this.origin = origin;
		this.destination = destination;
		this.isConnected = isConnected;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public boolean isConnected() {
		return isConnected;
	}
}

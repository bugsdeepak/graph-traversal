package com.solutions.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.solutions.graph.impl.Graph;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraphTraversalApplicationTests {
	
	@Autowired
	private Graph graphImpl;

	@Before
	public void contextLoads() {
		graphImpl.loadCitiesFromFile("cities.txt");
	}
	
	@Test
	public void testConnectedCities() {
		assertTrue(graphImpl.isConnected("Chennai", "Delhi"));
	}
	
	@Test
	public void testConnectedCitiesInReverse() {
		assertTrue(graphImpl.isConnected("Delhi", "Chennai"));
	}
	
	@Test
	public void testConnectedSameCities() {
		assertTrue(graphImpl.isConnected("Delhi", "Delhi"));
	}
	
	@Test
	public void testNotConnectedCities() {
		assertFalse(graphImpl.isConnected("Chennai", "GOA"));
	}
	
	@Test
	public void testNonExistingCities() {
		assertFalse(graphImpl.isConnected("Mumbai", "karachi"));
	}
	
	@Test
	public void testTwoNonExistingCities() {
		assertFalse(graphImpl.isConnected("Kabul", "karachi"));
	}

}

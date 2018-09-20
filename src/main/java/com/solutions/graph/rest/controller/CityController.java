package com.solutions.graph.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solutions.graph.impl.Graph;
import com.solutions.graph.model.GraphTraversal;

@RestController
public class CityController implements ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(CityController.class);
	private static final String LOGGER_STRING = "Request Received : Orighin - %s, Destination - %s"; 
	private final static String ERROR_PATH = "/error";
	private final static String CONNECTED_PATH = "/connected";
	
	@Autowired
	Graph graphImpl ;
	
    @RequestMapping(value = CONNECTED_PATH)
    public GraphTraversal findConnected(@RequestParam(required=true, value="origin") String origin,
    		@RequestParam(required=true, value="destination") String destination) {
    	
    	logger.info(String.format(LOGGER_STRING, origin, destination));
    	graphImpl.loadCitiesFromFile("cities.txt");
        return new GraphTraversal(origin, destination, graphImpl.isConnected(origin, destination));
    }

	@Override
	@RequestMapping(value = ERROR_PATH)
	public String getErrorPath() {
		return "<B> You Are Seeing this error Page. Because You Are Requesting a Non Existing Resource"
				+ " OR Mandatory Paramenter Are not Passed with /connected <B>";
	}
}

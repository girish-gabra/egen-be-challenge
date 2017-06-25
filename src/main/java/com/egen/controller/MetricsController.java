package com.egen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.model.Metric;
import com.egen.services.MetricsService;

@RestController
@RequestMapping(value="/metrics")
public class MetricsController {
	@Autowired
	private MetricsService service;
	@RequestMapping(value="/create", method = RequestMethod.POST)		
	public ResponseEntity<String> createMetric(@RequestBody Metric metric)
	{
		
		long timestamp = metric.getTimeStamp();
		int value = metric.getValue();
		service.createMetric(metric);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public ResponseEntity<List<Metric>> getAllMetrics()
	{
		List<Metric> allMetrics = service.getAllMetrics();
		// check if there are no records
		if(allMetrics.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Metric>>(allMetrics,HttpStatus.OK);
	}
}

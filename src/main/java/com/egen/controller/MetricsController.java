package com.egen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egen.model.Metric;
import com.egen.services.MetricsService;

@RestController
@RequestMapping(value="/metrics")
public class MetricsController {
	@Autowired
	private MetricsService service;
	@RequestMapping(value="/create")		
	public ResponseEntity<?> createMetric(@RequestBody Metric metric)
	{
		//System.out.println("inside metric controller");
		
		String timestamp = metric.getTimeStamp();
		int value = metric.getValue();
		service.createMetric(metric);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
}

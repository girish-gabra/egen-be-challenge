package com.egen.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egen.model.Metric;

@RestController
@RequestMapping(value="/metrics")
public class MetricsController {

	@RequestMapping(value="/create")		
	public ResponseEntity<?> createMetric(@RequestBody Metric metric)
	{
		System.out.println("inside metric controller");
		System.out.println(metric.getValue());
		System.out.println(metric.getTimeStamp());
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
}

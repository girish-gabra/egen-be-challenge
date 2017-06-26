package com.egen.controller;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.model.Metric;
import com.egen.services.MetricsService;

@RestController
@RequestMapping(value="/metrics")
public class MetricsController {
	
	int baseWeight;
	@Autowired
	private MetricsService service;
	@RequestMapping(value="/create", method = RequestMethod.POST)		
	public ResponseEntity<String> createMetric(@RequestBody Metric metric)
	{
		
		long timestamp = metric.getTimeStamp();
		int value = metric.getValue();
		
		if(baseWeight==0){
			baseWeight=value;
		}
		
		service.createMetric(metric);
		
		// check for alerts
		//Rules rules = new Rules();
		OverWeightRule overWeightRule = new OverWeightRule(metric,baseWeight);
		UnderWeightRule underWeightRule = new UnderWeightRule(metric,baseWeight);
		RulesEngine alertRulesEngine = RulesEngineBuilder.aNewRulesEngine().build();
		alertRulesEngine.registerRule(overWeightRule);
		alertRulesEngine.registerRule(underWeightRule);
		alertRulesEngine.fireRules();
		
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
	
	@RequestMapping(value="/readByTimeRange", method = RequestMethod.GET)
	public ResponseEntity<List<Metric>> getMetricsByTimeRange(@RequestParam long fromTimeStamp, @RequestParam long toTimeStamp)
	{
		List<Metric> metrics = service.getMetricsByRange(fromTimeStamp, toTimeStamp);
		// check if there are no records
		if(metrics.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Metric>>(metrics,HttpStatus.OK);
	}
}

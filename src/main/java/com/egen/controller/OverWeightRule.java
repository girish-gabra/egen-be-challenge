package com.egen.controller;



import org.bson.Document;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egen.model.Metric;
import com.egen.services.AlertService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Rule(name = "over weight rule", description = "if value is greater than 10% of base weight create an alert" )
public class OverWeightRule {

	private Metric metric;
	private int baseWeight;
	
	OverWeightRule(Metric metric,int baseWeight)
	{
		
		this.metric = metric;
		this.baseWeight=baseWeight;
	}
	
	@Condition
	public boolean isOverWeight()
	{
		int current = this.metric.getValue();
		return (current > this.baseWeight+0.1*this.baseWeight);
	}
	
	@Action
	public void addOverweightAlert()
	{
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase database = mongoClient.getDatabase("egen");
		MongoCollection<Document> collection = database.getCollection("alerts");
		Document document = new Document("timestamp",this.metric.getTimeStamp())
								.append("value", this.metric.getValue())
								.append("type", "Overweight");	
		collection.insertOne(document);
		mongoClient.close();
	}
}

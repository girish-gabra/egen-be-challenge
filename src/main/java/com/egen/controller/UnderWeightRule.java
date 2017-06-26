package com.egen.controller;
import org.bson.Document;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import com.egen.model.Metric;
import com.egen.services.AlertService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Rule(name = "under weight rule", description = "if value is greater than 10% of base weight create an alert" )
public class UnderWeightRule {

	@Autowired
	private AlertService alertService;
	
	private Metric metric;
	private int baseWeight;
	
	UnderWeightRule(Metric metric,int baseWeight)
	{
		this.metric = metric;
		this.baseWeight=baseWeight;
	}
	
	@Condition
	public boolean isOverWeight()
	{
		int current = this.metric.getValue();
		return (current < this.baseWeight - 0.1*this.baseWeight);
	}
	
	@Action
	public void addOverweightAlert()
	{
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase database = mongoClient.getDatabase("egen");
		MongoCollection<Document> collection = database.getCollection("alerts");
		Document document = new Document("timestamp",this.metric.getTimeStamp())
								.append("value", this.metric.getValue())
								.append("type", "Underweight");	
		collection.insertOne(document);
		mongoClient.close();
	}
}

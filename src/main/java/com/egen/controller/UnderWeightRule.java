package com.egen.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.bson.Document;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import com.egen.model.Metric;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Rule(name = "under weight rule", description = "if value is greater than 10% of base weight create an alert" )
public class UnderWeightRule {
	
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
	public void addUnderWeightAlert()
	{
		MongoDatabase database = null;
		MongoClient mongoClient = null;
		try
		{
			Properties properties = new Properties();
			String propFileName = "db.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if(inputStream!=null){
				properties.load(inputStream);
			}
			String DB_NAME = properties.getProperty("DB_NAME");
			String DB_URL = properties.getProperty("DB_URL");
			int DB_PORT = Integer.parseInt(properties.getProperty("DB_PORT"));
			mongoClient = new MongoClient( DB_URL , DB_PORT );
			database = mongoClient.getDatabase(DB_NAME);
			MongoCollection<Document> collection = database.getCollection("alerts");
			Document document = new Document("_id",this.metric.getTimeStamp())
									.append("value", this.metric.getValue())
									.append("type", "Underweight");	
			collection.insertOne(document);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(mongoClient!=null){
				mongoClient.close();
			}
		}
	}
}

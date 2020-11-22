package com.backend.notificationengine.services;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
    public MongoClient mongoClient;
    private static final String dbName = "notification";

    MongoService() {
        try {
            mongoClient = MongoClients.create(
                    "mongodb+srv://newuser:newuser@cluster0.7l3am.mongodb.net/notification?retryWrites=true&w=majority");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getCount(String collectionName, Bson filter) {
        return mongoClient.getDatabase(dbName).getCollection(collectionName).countDocuments(filter);
    }

    public FindIterable<Document> getData(String collectionName, Bson filter) {
        return mongoClient.getDatabase(dbName).getCollection(collectionName).find(filter);
    }

}

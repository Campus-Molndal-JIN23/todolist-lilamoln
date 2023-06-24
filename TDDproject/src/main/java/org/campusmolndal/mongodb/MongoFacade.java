package org.campusmolndal.mongodb;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class MongoFacade {
    MongoCollection<Document> collection;
    public MongoFacade(String collectionName) {
        MongoSettings mongoSettings = new MongoSettings();
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoSettings.getConnectionString()))
                .serverApi(serverApi)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase(mongoSettings.getCluster());
        database.runCommand(new Document("ping",1));
        this.collection = database.getCollection(collectionName);
    }
    public Document get(String id) {
        return collection.find(new Document("_id",new ObjectId(id))).first();
    }
    public Document insert(Document document) {
        try {
            ObjectId objectId = collection.insertOne(document).getInsertedId().asObjectId().getValue();
            document.put("_id",objectId);
            return document;
        } catch (MongoException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    public Document update(Document document) {
        try {
            long changed = collection.replaceOne(new Document("_id",document.get("_id")),document).getModifiedCount();
            if(changed == 0) return null;
            return document;
        } catch (MongoException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    public Document delete(Document document) {
        try {
            if(collection.deleteOne(new Document("_id",document.get("_id"))).wasAcknowledged())
                return document;
            return null;
        } catch (MongoException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    public List<Document> list() {
        List<Document> docs = new ArrayList<>();
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            docs.add(document);
        }
        return docs;
    }
    public List<Document> findTodoByUserId (String userId) {
        List<Document> docs = new ArrayList<>();
        FindIterable<Document> documents = collection.find(new Document("assigned_to",userId));
        for (Document document : documents) {
            docs.add(document);
        }
        return docs;
    }
}

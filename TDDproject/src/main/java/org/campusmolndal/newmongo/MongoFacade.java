package org.campusmolndal.newmongo;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class MongoFacade {
    MongoCollection<Document> collection;
    public MongoFacade(String collection) {
        MongoDatabase database;
        MongoSettings mongoSettings = new MongoSettings();
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoSettings.getConnectionString()))
                .serverApi(serverApi)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(mongoSettings.getCluster());
            database.runCommand(new Document("ping",1));
        this.collection = database.getCollection(collection);
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
            collection.replaceOne(new Document("_id",document.get("_id")),document);
            return document;
        } catch (MongoException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    public Document delete(Document document) {
        try {
            collection.deleteOne(new Document("_id",document.get("_id")));
            return document;
        } catch (MongoException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    public List<Document> list() {
        List<Document> docs = new ArrayList<>();
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> cursor = documents.iterator();
        while(cursor.hasNext()) {
            docs.add(cursor.next());
        }
        return docs;
    }
    public List<Document> findTodoByUserId (String userId) {
        List<Document> docs = new ArrayList<>();
        FindIterable<Document> documents = collection.find(new Document("assigned_to",userId));
        MongoCursor<Document> cursor = documents.iterator();
        while(cursor.hasNext()) {
            docs.add(cursor.next());
        }
        return docs;
    }
}

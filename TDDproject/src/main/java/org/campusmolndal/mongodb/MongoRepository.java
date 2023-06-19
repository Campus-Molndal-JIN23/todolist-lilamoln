package org.campusmolndal.mongodb;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.campusmolndal.interfaces.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

abstract class MongoRepository<T> implements Repository<T> {
    private MongoCollection<T> collection;
    MongoRepository(String collectionName, Class<T> tClass) {
        MongoDatabase db;
        try {
            db = getDb(getConnectionString(), getCluster());
        } catch (Exception e) {
            db = getDb("mongodb://localhost:27017", "Cluster0");
        }
        this.setCollection(db.getCollection(collectionName, tClass));
    }
    public void create(T t) {
        getCollection().insertOne(t);
    }
    public T read(String id) {
        return getCollection().find(new Document("_id",new ObjectId(id))).first();
    }
    public void update(T t) {
        getCollection().findOneAndReplace(new Document("_id",t),t);
    }
    public void delete(T t) {
        getCollection().findOneAndDelete(new Document("_id",t));
    }
    public List<T> list() {
        List<T> ts = new ArrayList<>();
        for(T t : getCollection().find()) {
            ts.add(t);
        }
        return ts;
    }
    public List<T> search(String field, String term) {
        List<T> result = new ArrayList<>();
        for(T t : getCollection().find(Filters.eq(field,term))) {
            result.add(t);
        }
        return result;
    }
    private MongoDatabase getDb(String connectionString, String cluster) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojoCodecRegistry);
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .codecRegistry(codecRegistry)
                .build();
        try {
            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase(cluster);
            database.runCommand(new Document("ping",1));
            return database;
        } catch (MongoException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }
    private String getCluster() {
        try {
            return getProperty("cluster");
        } catch (Exception e) {
            return "Cluster0";
        }
    }
    private String getConnectionString() {
        try {
            return getProperty("connectionString");
        } catch (Exception e) {
            return "mongodb://localhost:27017";
        }
    }
    private String getProperty(String property) throws IOException {
        Properties properties = new Properties();
        try {
            FileReader fr = new FileReader("mongo.config");
            properties.load(fr);
            return properties.getProperty(property);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "MongoRepository{" +
                "collection=" + getCollection() +
                '}';
    }

    public MongoCollection<T> getCollection() {
        return collection;
    }

    public void setCollection(MongoCollection<T> collection) {
        this.collection = collection;
    }
}
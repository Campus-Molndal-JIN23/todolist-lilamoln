package org.campusmolndal.mongodb;

import java.io.FileReader;
import java.util.Properties;

public class MongoSettings {
    private String connectionString;
    private String cluster;
    public MongoSettings() {
        this.connectionString = getSetting("connectionString");
        if (this.connectionString == null) {
            this.connectionString = "mongodb://localhost:27017";
        }
        this.cluster = getSetting("cluster");
        if (this.cluster == null) {
            this.cluster = "ClusterTodo";
        }
    }
    private String getSetting(String key) {
        try {
            FileReader reader = new FileReader("mongo.config");
            Properties p = new Properties();
            p.load(reader);
            return p.getProperty(key);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String getConnectionString() {
        return this.connectionString;
    }

    public String getCluster() {
        return this.cluster;
    }
}

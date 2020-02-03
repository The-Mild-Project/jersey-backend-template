package server;

import java.util.Arrays;

import com.mongodb.client.MongoDatabase;
import db.mongo.MongoDatabaseFactory;
import db.mongo.util.MongoDatabaseType;
import db.mongo.util.MongoEnv;

public class ServerTester {
    public static void main(String[] args) {
        final MongoEnv env = new MongoEnv();
        final MongoDatabaseFactory factory = new MongoDatabaseFactory(env.getMongoUser(), env.getMongoPass());

        Arrays.stream(MongoDatabaseType.values())
              .forEach(type -> factory.addDatabase(type, env.getMongoCluster()));

        final MongoDatabase database = factory.getDatabase(MongoDatabaseType.NULL);
    }
}
package infra.database;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDatabaseClient {

    private MongoClient client;
    private MongoDatabase root;

    private MongoDatabaseClient() {

        final Properties prop = new Properties();
        InputStream input = null;

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("api.entities").build();

        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider));

        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();

        try {
            
            input = new FileInputStream("BOOT-INF/classes/");
            prop.load(input);

            this.client = new MongoClient(new ServerAddress(prop.getProperty("database.host"), Integer.valueOf(prop.getProperty("database.port"))), options);
            this.root = this.client.getDatabase(prop.getProperty("database.name"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MongoDatabaseClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MongoDatabaseClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static MongoDatabaseClient INSTANCE = new MongoDatabaseClient();

    public static MongoDatabaseClient getInstance() {
        return INSTANCE;
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getRoot() {
        return root;
    }
}

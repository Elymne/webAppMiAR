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

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class MongoDatabaseClient implements EnvironmentAware {

    static Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        MongoDatabaseClient.environment = environment;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static String getDatabaseHost() {
        System.out.println(environment.getProperty("database.host"));
        return environment.getProperty("database.host");
    }

    public static String getDatabasePort() {
        System.out.println(environment.getProperty("database.port"));
        return environment.getProperty("database.port");
    }

    public static String getDatabaseName() {
        System.out.println(environment.getProperty("database.name"));
        return environment.getProperty("database.name");
    }

    private MongoClient client;
    private MongoDatabase root;

    private MongoDatabaseClient() {

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("api.entities").build();

        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider));

        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();

        this.client = new MongoClient(new ServerAddress(getDatabaseHost(), Integer.valueOf(getDatabasePort())), options);
        this.root = this.client.getDatabase(getDatabaseName());

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

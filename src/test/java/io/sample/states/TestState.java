package io.sample.states;

import io.sample.CayenneTutorialModule;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.configuration.server.ServerRuntimeBuilder;
import org.apache.cayenne.datasource.DataSourceBuilder;
import org.openjdk.jmh.annotations.*;

@State(Scope.Thread)
public class TestState {

    @Param("jdbc:stub://localhost:9999/1111")
    String url;

    @Param("io.bootique.jdbc.driver.stub.Driver")
    String driver;

    @Param("root")
    String username;

    @Param("password")
    String password;

    @Param("true")
    Boolean isDefault;

    public ObjectContext context;


    @Setup(Level.Trial)
    public void initialize() {

        System.out.println("url: " + url);
        System.out.println("driver: " + driver);
        System.out.println("username: " + username);
        System.out.println("password: " + password);

        ServerRuntimeBuilder builder = ServerRuntime.builder();

        if (isDefault) {
            builder.addModule(new CayenneTutorialModule());
        }

        ServerRuntime cayenneRuntime = builder
                .dataSource(DataSourceBuilder
                        .url(url)
                        .driver(driver)
                        .userName(username)
                        .password(password)
                        .pool(10, 20)
                        .build())
                .addConfig("cayenne-project.xml")
                .build();
        context = cayenneRuntime.newContext();
    }
}
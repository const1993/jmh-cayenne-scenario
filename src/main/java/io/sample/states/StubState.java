package io.sample.states;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import io.sample.CayenneTutorialModule;

@State(Scope.Thread)
public class StubState {
    public ObjectContext context;

    @Setup(Level.Trial)
    public void initialize() {
        ServerRuntime cayenneRuntime = ServerRuntime.builder()
                .addModule(new CayenneTutorialModule())
//                .dataSource(DataSourceBuilder
//                        .url("jdbc:stub://127.0.0.1:3306/cayenne_demo")
//                        .driver("io.bootique.jdbc.driver.stub.Driver")
//                        .userName("user")
//                        .password("password")
//                        .pool(10, 20)
//                        .build())
                .addConfig("cayenne-project.xml")
                .build();
        context = cayenneRuntime.newContext();
    }
}

package org.sample.states;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.datasource.DataSourceBuilder;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class MysqlState {
    public ObjectContext context;


    @Setup(Level.Trial)
    public void initialize() {

        ServerRuntime cayenneRuntime = ServerRuntime.builder()
                .dataSource(DataSourceBuilder
                        .url("jdbc:mysql://192.168.99.100:3306/cayenne_demo?useSSL=false")
                        .driver("com.mysql.cj.jdbc.Driver")
                        .userName("root")
                        .password("password")
                        .pool(10, 20)
                        .build())
                .addConfig("cayenne-project.xml")
                .build();
        context = cayenneRuntime.newContext();
    }
}

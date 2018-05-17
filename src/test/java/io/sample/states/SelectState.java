package io.sample.states;

import io.sample.CayenneTutorialModule;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.configuration.server.ServerRuntimeBuilder;
import org.apache.cayenne.datasource.DataSourceBuilder;
import org.apache.cayenne.datasource.PoolingDataSource;
import org.apache.cayenne.test.jdbc.DBHelper;
import org.apache.cayenne.test.jdbc.TableHelper;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;

@State(Scope.Thread)
public class SelectState {

    @Param("jdbc:stub://localhost:9999/cayenne_demo")
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

        PoolingDataSource dataSource = DataSourceBuilder
                .url(url)
                .driver(driver)
                .userName(username)
                .password(password)
                .pool(1, 1)
                .build();
        ServerRuntime cayenneRuntime = builder
                .dataSource(dataSource)
                .addConfig("cayenne-project.xml")
                .build();
        context = cayenneRuntime.newContext();

        try {
            cleanupTables(dataSource);
            generateRows(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void cleanupTables(DataSource dataSource) throws SQLException {
        DBHelper dbHelper = new DBHelper(dataSource);
            new TableHelper(dbHelper, "PAINTING").deleteAll();
            new TableHelper(dbHelper, "ARTIST").deleteAll();
            new TableHelper(dbHelper, "GALLERY").deleteAll();
            System.out.println("Cleaned!!");
    }

    private void generateRows(DataSource dataSource) throws SQLException {
        DBHelper dbHelper = new DBHelper(dataSource);

        TableHelper gallery = new TableHelper(dbHelper, "GALLERY");
        gallery.setColumns("ID", "NAME");
        gallery.insert(1, "Armitage");
        TableHelper artist = new TableHelper(dbHelper, "ARTIST");
        artist.setColumns("ID", "NAME", "DATE_OF_BIRTH");

        TableHelper painting = new TableHelper(dbHelper, "PAINTING");
        painting.setColumns("ID", "NAME", "ARTIST_ID", "GALLERY_ID");


        for (int i = 1; i <= 10; i++) {
            artist.insert(i, "a" + i, new Date());
            for (int j = 1; j <= 10; j++) {
                int id = (i - 1) * 10 +j;
                painting.insert(id, "b"+j, i, 1);
            }
        }
    }
}
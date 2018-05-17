package io.sample.insert;

import io.sample.persistent.Artist;
import io.sample.persistent.Gallery;
import io.sample.persistent.Painting;
import io.sample.states.MysqlState;
import io.sample.states.TestState;
import org.apache.cayenne.ObjectContext;
import org.openjdk.jmh.annotations.*;

import java.util.Properties;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Measurement(batchSize = 10, time = 2, timeUnit = MICROSECONDS)
@Warmup(batchSize = 10, time = 2, timeUnit = MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class TestInsert {

    @Benchmark()
    public void test_insert_one(TestState state) {

        ObjectContext context = state.context;
        Artist artist = context.newObject(Artist.class);
        artist.setName("Picasso");
        context.commitChanges();
    }

    @Benchmark()
    public void test_insert_batch10x(TestState state) {
        ObjectContext context = state.context;

        for (int i = 0; i < 10; i++) {
            Gallery gallery = context.newObject(Gallery.class);
            gallery.setName("Armitage"+i);
        }
        context.commitChanges();
    }

    @Benchmark()
    public void test_insert_batch100x(TestState state) {
        ObjectContext context = state.context;

        for (int i = 0; i < 100; i++) {
            Painting painting = context.newObject(Painting.class);
            painting.setName("Black Squere"+i);
        }
        context.commitChanges();
    }
}
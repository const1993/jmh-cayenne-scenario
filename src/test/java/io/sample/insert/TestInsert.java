package io.sample.insert;

import io.sample.persistent.Artist;
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
            Artist artist = context.newObject(Artist.class);
            artist.setName("Picasso"+i);
        }
        context.commitChanges();
    }

    @Benchmark()
    public void test_insert_batch100x(TestState state) {
        ObjectContext context = state.context;

        for (int i = 0; i < 100; i++) {
            Artist artist = context.newObject(Artist.class);
            artist.setName("Picasso"+i);
        }
        context.commitChanges();
    }
}
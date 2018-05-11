package org.sample.insert;

import org.apache.cayenne.ObjectContext;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.sample.persistent.Artist;
import org.sample.states.MysqlState;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Measurement(batchSize = 10, time = 2, timeUnit = MICROSECONDS, iterations = 5)
@Warmup(batchSize = 10, time = 2, timeUnit = MICROSECONDS, iterations = 5)
@BenchmarkMode(Mode.AverageTime)
public class SimpleInsert {

    @Benchmark()
    public void test_insert_one(MysqlState state) {
        ObjectContext context = state.context;
        Artist artist = context.newObject(Artist.class);
        artist.setName("Picasso");
        context.commitChanges();
    }

    @Benchmark()
    public void test_insert_batch10x(MysqlState state) {
        ObjectContext context = state.context;

        for (int i = 0; i < 10; i++) {
            Artist artist = context.newObject(Artist.class);
            artist.setName("Picasso"+i);
        }
        context.commitChanges();
    }

    @Benchmark()
    public void test_insert_batch100x(MysqlState state) {
        ObjectContext context = state.context;

        for (int i = 0; i < 100; i++) {
            Artist artist = context.newObject(Artist.class);
            artist.setName("Picasso"+i);
        }
        context.commitChanges();
    }
}

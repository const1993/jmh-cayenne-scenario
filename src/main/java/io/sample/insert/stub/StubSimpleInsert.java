package io.sample.insert.stub;

import io.sample.states.StubState;
import org.apache.cayenne.ObjectContext;
import org.openjdk.jmh.annotations.*;
import io.sample.persistent.Artist;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Fork(1)
@Measurement(batchSize = 10, time = 2, timeUnit = MICROSECONDS, iterations = 5)
@Warmup(batchSize = 10, time = 2, timeUnit = MICROSECONDS, iterations = 5)
@BenchmarkMode(Mode.AverageTime)
public class StubSimpleInsert {

    @Benchmark()
    public void test_insert_one(StubState state) {
        ObjectContext context = state.context;
        Artist artist = context.newObject(Artist.class);
        artist.setName("Picasso");
        context.commitChanges();
    }

    @Benchmark()
    public void test_insert_batch10x(StubState state) {
        ObjectContext context = state.context;

        for (int i = 0; i < 10; i++) {
            Artist artist = context.newObject(Artist.class);
            artist.setName("Picasso"+i);
        }
        context.commitChanges();
    }

    @Benchmark()
    public void test_insert_batch100x(StubState state) {
        ObjectContext context = state.context;
        for (int i = 0; i < 100; i++) {
            Artist artist = context.newObject(Artist.class);
            artist.setName("Picasso"+i);
        }
        context.commitChanges();
    }
}

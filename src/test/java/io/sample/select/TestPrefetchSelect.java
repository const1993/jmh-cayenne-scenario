package io.sample.select;

import io.sample.persistent.Artist;
import io.sample.states.MysqlState;
import io.sample.states.TestState;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.openjdk.jmh.annotations.*;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Measurement(time = 2, timeUnit = MICROSECONDS, iterations = 5)
@Warmup(time = 2, timeUnit = MICROSECONDS, iterations = 5)
@BenchmarkMode(Mode.AverageTime)
public class TestPrefetchSelect {


    @Benchmark()
    public void test_select_prefetch_one(TestState state) {
        ObjectContext context = state.context;
        ObjectSelect.query(Artist.class).prefetch(Artist.PAINTINGS.joint()).localCache().select(context);
    }


    @Benchmark()
    public void test_select_where_one(TestState state) {
        ObjectContext context = state.context;

        ObjectSelect.query(Artist.class)
                .where( Artist.NAME.eq("Picasso"))
                .select(context);
    }

    @Benchmark()
    public void test_select_prefetch_batch10x(TestState state) {
        ObjectContext context = state.context;
        for (int i = 0; i< 10; i++) {
            ObjectSelect.query(Artist.class).prefetch(Artist.PAINTINGS.joint()).localCache().select(context);
        }
    }

    @Benchmark()
    public void test_select_where_batch10x(TestState state) {
        ObjectContext context = state.context;
        for (int i = 0; i< 10; i++) {

            ObjectSelect.query(Artist.class)
                    .where(Artist.NAME.eq("Picasso"))
                    .select(context);
        }
    }


}

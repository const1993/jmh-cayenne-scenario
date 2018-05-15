package io.sample.select;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.openjdk.jmh.annotations.*;
import io.sample.persistent.Artist;
import io.sample.states.MysqlState;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Measurement(time = 2, timeUnit = MICROSECONDS, iterations = 5)
@Warmup(time = 2, timeUnit = MICROSECONDS, iterations = 5)
@BenchmarkMode(Mode.AverageTime)
public class SimpleSelect {

    @Benchmark()
    public void select_one(MysqlState state) {
        ObjectContext context = state.context;

        ObjectSelect.query(Artist.class).select(context);
    }

    @Benchmark()
    public void select_batch10x(MysqlState state) {
        ObjectContext context = state.context;

        for(int i = 0; i < 10; i++) {
            ObjectSelect.query(Artist.class).select(context);
        }
    }
}

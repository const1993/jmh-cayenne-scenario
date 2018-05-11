package org.sample.select;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.sample.persistent.Artist;
import org.sample.states.MysqlState;

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

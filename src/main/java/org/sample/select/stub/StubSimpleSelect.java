package org.sample.select.stub;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.openjdk.jmh.annotations.*;
import org.sample.persistent.Artist;
import org.sample.states.MysqlState;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Fork(1)
@Measurement(time = 2, timeUnit = MICROSECONDS, iterations = 5)
@Warmup(time = 2, timeUnit = MICROSECONDS, iterations = 5)
@BenchmarkMode(Mode.AverageTime)
public class StubSimpleSelect {

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

package io.sample.select;

import io.sample.persistent.Artist;
import io.sample.persistent.Gallery;
import io.sample.persistent.Painting;
import io.sample.states.SelectState;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

import java.util.List;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Measurement(time = 2, timeUnit = MICROSECONDS, iterations = 5)
@Warmup(time = 2, timeUnit = MICROSECONDS, iterations = 5)
@BenchmarkMode(Mode.AverageTime)
public class TestWhereSelect {

    @Benchmark()
    public void test_select_where_one(SelectState state) {
        ObjectContext context = state.context;

        ObjectSelect.query(Gallery.class)
                .where(Gallery.NAME.eq("Armitage"))
                .select(context);
    }

    @Benchmark()
    public void test_select_where_batch10x(SelectState state) {
        ObjectContext context = state.context;

        ObjectSelect.query(Artist.class)
                .where(Painting.NAME.startsWith("a"))
                .select(context);
    }

    @Benchmark()
    public void test_select_where_batch100x(SelectState state) {
        ObjectContext context = state.context;

        ObjectSelect.query(Painting.class)
                .where(Painting.NAME.startsWith("b"))
                .select(context);
    }
}

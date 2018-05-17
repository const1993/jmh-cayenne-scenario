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
public class TestSimpleSelect {

//    @Benchmark()
//    public void select_one(SelectState state) {
//        ObjectContext context = state.context;
//
//        ObjectSelect.query(Gallery.class).select(context);
//    }
//
//    @Benchmark()
//    public void select_batch10x(SelectState state) {
//        ObjectContext context = state.context;
//
//        ObjectSelect.query(Artist.class).select(context);
//    }

    @Benchmark()
    public void select_batch100x(SelectState state) {
        ObjectContext context = state.context;

        List<Painting> select = ObjectSelect.query(Painting.class).select(context);
        System.out.println(select.size());

    }
}

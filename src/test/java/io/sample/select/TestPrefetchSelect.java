package io.sample.select;

import io.sample.persistent.Artist;
import io.sample.persistent.Gallery;
import io.sample.persistent.Painting;
import io.sample.states.SelectState;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.openjdk.jmh.annotations.*;


import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Measurement(time = 2, timeUnit = MICROSECONDS, iterations = 5)
@Warmup(time = 2, timeUnit = MICROSECONDS, iterations = 5)
@BenchmarkMode(Mode.AverageTime)
public class TestPrefetchSelect {


    @Benchmark()
    public void test_select_prefetch_one(SelectState state) {
        ObjectContext context = state.context;
//        List<Gallery> select =
                ObjectSelect.query(Gallery.class).prefetch(Gallery.PAINTINGS.joint()).select(context);
//        System.out.println(TestPrefetchSelect.class.getName() + ".test_select_prefetch_one "+ Gallery.class.getName()+" " + select.size());
    }

    @Benchmark()
    public void test_select_prefetch_batch10x(SelectState state) {
        ObjectContext context = state.context;
//        List<Artist> select =
                ObjectSelect.query(Artist.class).prefetch(Artist.PAINTINGS.joint()).select(context);
//        System.out.println(TestPrefetchSelect.class.getName() + ".test_select_prefetch_batch10x "+ Artist.class.getName()+" " + select.size());

    }

    @Benchmark()
    public void test_select_prefetch_batch100x(SelectState state) {
        ObjectContext context = state.context;
//        List<Painting> select =
                ObjectSelect.query(Painting.class).prefetch(Painting.ARTIST.joint()).select(context);
//        System.out.println(TestPrefetchSelect.class.getName() + ".test_select_prefetch_batch100x "+ Painting.class.getName()+" " + select.size());

    }


}

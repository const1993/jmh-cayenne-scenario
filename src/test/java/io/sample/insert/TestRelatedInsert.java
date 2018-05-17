package io.sample.insert;

import io.sample.persistent.Artist;
import io.sample.persistent.Gallery;
import io.sample.persistent.Painting;
import io.sample.states.TestState;
import org.apache.cayenne.ObjectContext;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Measurement(batchSize = 10, time = 2, timeUnit = MICROSECONDS, iterations = 5)
@Warmup(batchSize = 10, time = 2, timeUnit = MICROSECONDS, iterations = 5)
@BenchmarkMode(Mode.AverageTime)
public class TestRelatedInsert {

    @Benchmark()
    public void test_insert_one(TestState state) {
        ObjectContext context = state.context;
        Artist artist = context.newObject(Artist.class);
        artist.setName("Picasso");

        Gallery gallery = context.newObject(Gallery.class);
        gallery.setName("faraday");

        Painting painting = context.newObject(Painting.class);
        painting.setName("asdasd");
        painting.setArtist(artist);
        painting.setGallery(gallery);

        context.commitChanges();
    }

    @Benchmark()
    public void test_insert_batch10x(TestState state) {
        ObjectContext context = state.context;

        Artist artist = context.newObject(Artist.class);
        artist.setName("Picasso");

        Gallery gallery = context.newObject(Gallery.class);
        gallery.setName("faraday");

        for (int i = 0; i < 10; i++) {

            Painting painting = context.newObject(Painting.class);
            painting.setName("asdasd");
            painting.setArtist(artist);
            painting.setGallery(gallery);
        }
        context.commitChanges();
    }

    @Benchmark()
    public void test_insert_batch100x(TestState state) {
        ObjectContext context = state.context;
        Artist artist = context.newObject(Artist.class);
        artist.setName("Picasso");

        Gallery gallery = context.newObject(Gallery.class);
        gallery.setName("faraday");

        for (int i = 0; i < 1000; i++) {

            Painting painting = context.newObject(Painting.class);
            painting.setName("asdasd");
            painting.setArtist(artist);
            painting.setGallery(gallery);
        }
        context.commitChanges();
    }
}
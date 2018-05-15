package io.sample.insert;

import org.apache.cayenne.ObjectContext;
import org.openjdk.jmh.annotations.*;
import io.sample.persistent.Artist;
import io.sample.persistent.Gallery;
import io.sample.persistent.Painting;
import io.sample.states.MysqlState;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Measurement(batchSize = 10, time = 2, timeUnit = MICROSECONDS, iterations = 5)
@Warmup(batchSize = 10, time = 2, timeUnit = MICROSECONDS, iterations = 5)
@BenchmarkMode(Mode.AverageTime)
public class RelatedInsert {


    @Benchmark()
    public void test_insert_one(MysqlState state) {
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
    public void test_insert_batch10x(MysqlState state) {
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
    public void test_insert_batch100x(MysqlState state) {
        ObjectContext context = state.context;
        Artist artist = context.newObject(Artist.class);
        artist.setName("Picasso");

        Gallery gallery = context.newObject(Gallery.class);
        gallery.setName("faraday");

        for (int i = 0; i < 100; i++) {

            Painting painting = context.newObject(Painting.class);
            painting.setName("asdasd");
            painting.setArtist(artist);
            painting.setGallery(gallery);
        }
        context.commitChanges();
    }

}

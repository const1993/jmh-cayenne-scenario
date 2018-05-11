package org.sample.insert;

import org.apache.cayenne.ObjectContext;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.sample.persistent.Artist;
import org.sample.persistent.Gallery;
import org.sample.persistent.Painting;
import org.sample.states.MysqlState;

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

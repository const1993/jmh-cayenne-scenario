package io.sample.persistent;

import io.sample.persistent.auto._Gallery;

public class Gallery extends _Gallery {

    private static final long serialVersionUID = 1L;

    public String toString() {
        return "Gallery {" +
                "name=" + name +
                " paintings=" + paintings.toString() +
                '}';
    }

}

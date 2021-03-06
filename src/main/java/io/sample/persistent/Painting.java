package io.sample.persistent;


import io.sample.persistent.auto._Painting;

public class Painting extends _Painting {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Painting{" +
                "name=" + name +
                " artist=" + artist.toString() +
                " gallery=" + gallery.toString() +
                '}';
    }
}

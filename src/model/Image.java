package model;

import java.io.InputStream;

public interface Image {
    String name();
    InputStream stream();
    int[] size();
    Image next();
    Image prev();
}
package com.example.nataliia.popularmovies.utils;

public class ImageUriUtils {

    private final static String BASE_URL = "https://image.tmdb.org/t/p/";
    private final static String SIZE = "w185/";

    public static String getImageStringUrl(String path) {
       return BASE_URL + SIZE + path;
    }
}

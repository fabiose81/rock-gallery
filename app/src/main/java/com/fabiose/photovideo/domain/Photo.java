package com.fabiose.photovideo.domain;

/**
 * Created by fabioestrela on 18-04-11.
 */

public class Photo {

    private String name;

    public Photo(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

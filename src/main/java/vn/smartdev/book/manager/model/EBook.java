package vn.smartdev.book.manager.model;

import java.io.Serializable;

public class EBook implements Serializable{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

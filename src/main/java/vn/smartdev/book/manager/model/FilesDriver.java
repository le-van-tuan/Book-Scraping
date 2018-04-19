package vn.smartdev.book.manager.model;

import java.io.Serializable;

public class FilesDriver implements Serializable{

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FilesDriver{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

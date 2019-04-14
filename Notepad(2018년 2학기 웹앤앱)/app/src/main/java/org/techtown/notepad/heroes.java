package org.techtown.notepad;

public class heroes {
    String _id;
    String id;
    String name;

    public heroes(String _id, String id, String name) {
        this._id = _id;
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return String.format("ID = %s \n Title = %s \n Content = %s",id,name);
    }
}
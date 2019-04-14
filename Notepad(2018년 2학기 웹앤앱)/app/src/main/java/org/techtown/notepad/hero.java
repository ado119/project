package org.techtown.notepad;

public class hero {
    String id;
    String name;

    public hero(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return String.format("%s. %s", id, name);
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }
}
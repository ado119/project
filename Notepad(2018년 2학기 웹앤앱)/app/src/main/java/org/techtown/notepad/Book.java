package org.techtown.notepad;

public class Book {
    int id;
    String name;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return String.format("ID = %s \n Content = %s",id,name);
    }
}

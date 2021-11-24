package ru.mirea.services;

import ru.mirea.entity.Genre;

public class GenreService  {

    public int sortByFirstLetter(Genre o1, Genre o2) {
        if (o1.getGenreName().charAt(0) < o2.getGenreName().charAt(0)) return -1;
        else if (o1.getGenreName().charAt(0) > o2.getGenreName().charAt(0)) return 1;
        else return 0;
    }
}

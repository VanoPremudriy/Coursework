package ru.mirea.services;

import ru.mirea.entity.Band;

public class BandService {
    public int sortByFirstLetter(Band o1, Band o2) {
        if (o1.getBandName().charAt(0) < o2.getBandName().charAt(0)) return -1;
        else if (o1.getBandName().charAt(0) > o2.getBandName().charAt(0)) return 1;
        else return 0;
    }

}

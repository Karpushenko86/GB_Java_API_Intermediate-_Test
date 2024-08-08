package com.geekbrains;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();

        store.generateLaptops(10);

        store.displayAllLaptops();

        Map<String, Object> criteria = store.getCriteriaFromUser();

        Set<Laptop> filteredLaptops = store.filterLaptops(criteria);
        store.displayFilteredLaptops(filteredLaptops);
    }
}
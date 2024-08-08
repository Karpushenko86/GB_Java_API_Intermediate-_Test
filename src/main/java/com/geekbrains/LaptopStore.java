package com.geekbrains;

import java.util.*;

public class LaptopStore {
    private Set<Laptop> laptops;
    private Random random;
    boolean choiceMatched;

    public LaptopStore() {
        laptops = new HashSet<>();
        random = new Random();
        choiceMatched = false;
    }

    public void generateLaptops(int count) {
        String[] modelsOptions = {"HP", "Lenovo", "Asus", "Huawei", "MSI", "Apple"};
        int[] ramOptions = {4, 8, 16, 32, 64, 128};
        int[] hddOptions = {128, 256, 512, 1024, 2048};
        String[] osOptions = {"Без ОС", "Windows 10", "Windows 11", "Linux"};
        String[] colorOptions = {"Black", "White", "Gray", "Dark Gray", "Blue", "Dark Blue"};

        for (int i = 0; i < count; i++) {
            String model = modelsOptions[random.nextInt(modelsOptions.length)] + "-" + (i + 1);
            int ram = ramOptions[random.nextInt(ramOptions.length)];
            int hddMemory = hddOptions[random.nextInt(hddOptions.length)];
            String os = osOptions[random.nextInt(osOptions.length)];
            String color = colorOptions[random.nextInt(colorOptions.length)];

            laptops.add(new Laptop(model, ram, hddMemory, os, color));
        }
    }

    public void displayAllLaptops() {
        System.out.println("Все доступные ноутбуки в магазине(" + laptops.size() + "):");
        for (Laptop laptop : laptops) {
            System.out.println(laptop);
        }
    }

    public Map<String, Object> getCriteriaFromUser() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> criteria = new HashMap<>();

        while (true) {
            System.out.println("\nДоступные критерии для сортировки: ");
            System.out.println("1 - ОЗУ (от 4 до 128)");
            System.out.println("2 - Объем ЖД (от 128 до 2048)");
            System.out.println("3 - Операционная система (Без ОС, Windows 10, Windows 11, Linux)");
            System.out.println("4 - Цвет (Black, White, Gray, Dark Gray, Blue, Dark Blue)");
            System.out.println("5 - Модель (HP, Lenovo, Asus, Huawei, MSI, Apple)");
            System.out.println("0 - Завершить ввод критериев.");
            System.out.print("Введите цифру, соответствующую необходимому критерию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.println();

            if (choice == 0) {
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Введите минимальное значение ОЗУ: ");
                    int ram = scanner.nextInt();
                    scanner.nextLine();
                    criteria.put("ram", ram);
                    break;
                case 2:
                    System.out.print("Введите минимальное значение объема ЖД: ");
                    int hdd = scanner.nextInt();
                    scanner.nextLine();
                    criteria.put("hdd", hdd);
                    break;
                case 3:
                    System.out.print("Введите операционную систему: ");
                    String os = scanner.nextLine();
                    criteria.put("os", os);
                    break;
                case 4:
                    System.out.print("Введите цвет: ");
                    String color = scanner.nextLine();
                    criteria.put("color", color);
                    break;
                case 5:
                    System.out.print("Введите модель: ");
                    String model = scanner.nextLine();
                    criteria.put("model", model);
                    break;
                default:
                    System.out.println("Неверный выбор!");
                    break;
            }
        }
        return criteria;
    }

    public Set<Laptop> filterLaptops(Map<String, Object> filters) {
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);

        for (Laptop laptop : laptops) {
            boolean matches = true;

            if (filters.containsKey("ram") && laptop.getRam() < (int) filters.get("ram")) {
                matches = false;
            }
            if (filters.containsKey("hdd") && laptop.getHdd() < (int) filters.get("hdd")) {
                matches = false;
            }
            if (filters.containsKey("os") && !laptop.getOs().equalsIgnoreCase((String) filters.get("os"))) {
                matches = false;
            }
            if (filters.containsKey("color") && !laptop.getColor().equalsIgnoreCase((String) filters.get("color"))) {
                matches = false;
            }
            if (filters.containsKey("model") && !laptop.getModel().equalsIgnoreCase((String) filters.get("model"))) {
                matches = false;
            }

            if (matches) {
                choiceMatched = true;
            } else {
                filteredLaptops.remove(laptop);
            }
        }
        return filteredLaptops;
    }

    public void displayFilteredLaptops(Set<Laptop> filteredLaptops) {
        if (!choiceMatched) {
            System.out.println("Ноутбуки, ссоответствующие критериям - отсутсвуют.");
        } else {
            System.out.println("Ноутбуки, соответствующие критериям: ");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop);
            }
        }
    }
}
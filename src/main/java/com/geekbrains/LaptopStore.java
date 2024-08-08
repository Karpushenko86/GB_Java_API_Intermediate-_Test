package com.geekbrains;

import java.util.*;

public class LaptopStore {
    private Set<Laptop> laptops;

    public LaptopStore() {
        laptops = new HashSet<>();
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public Map<String, Object> getCriteriaFromUser() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> criteria = new HashMap<>();

        while (true) {
            System.out.println("Введите цифру, соответствующую необходимому критерию: ");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("5 - Модель");
            System.out.println("6 - Завершить ввод критериев");

            int criterion = scanner.nextInt();
            scanner.nextLine();

            if (criterion == 6) {
                break;
            }

            switch (criterion) {
                case 1:
                    System.out.print("Введите минимальное значение ОЗУ: ");
                    criteria.put("ram", scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Введите минимальное значение объема ЖД: ");
                    criteria.put("hdd", scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Введите операционную систему: ");
                    criteria.put("os", scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Введите цвет: ");
                    criteria.put("color", scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Введите модель: ");
                    criteria.put("model", scanner.nextLine());
                    break;
                default:
                    System.out.println("Неверный выбор");
                    break;
            }
        }

        return criteria;
    }

    public Set<Laptop> filterLaptops(Map<String, Object> filters) {
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);
        for (Laptop laptop : laptops) {
            if (filters.containsKey("ram") && laptop.getRam() < (int) filters.get("ram")) {
                filteredLaptops.remove(laptop);
            }
            if (filters.containsKey("hdd") && laptop.getHdd() < (int) filters.get("hdd")) {
                filteredLaptops.remove(laptop);
            }
            if (filters.containsKey("os") && !laptop.getOs().equalsIgnoreCase((String) filters.get("os"))) {
                filteredLaptops.remove(laptop);
            }
            if (filters.containsKey("color") && !laptop.getColor().equalsIgnoreCase((String) filters.get("color"))) {
                filteredLaptops.remove(laptop);
            }
            if (filters.containsKey("model") && !laptop.getModel().equalsIgnoreCase((String) filters.get("model"))) {
                filteredLaptops.remove(laptop);
            }
        }
        return filteredLaptops;
    }

    public void displayFilteredLaptops(Set<Laptop> laptops) {
        System.out.println("Ноутбуки, соответствующие критериям: ");
        for (Laptop laptop : laptops) {
            System.out.println(laptop);
        }
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();

        store.addLaptop(new Laptop("MSI Katana", 8, 256, "Windows", "Black"));
        store.addLaptop(new Laptop("Lenovo Legion 5", 16, 512, "Linux", "Silver"));
        store.addLaptop(new Laptop("Apple MacBook Pro 2", 32, 1024, "MacOS", "White"));
        store.addLaptop(new Laptop("Asus Tuf Gaming A15", 8, 256, "Windows", " Dark Gray"));

        Map<String, Object> criteria = store.getCriteriaFromUser();

        Set<Laptop> filteredLaptops = store.filterLaptops(criteria);
        store.displayFilteredLaptops(filteredLaptops);
    }
}
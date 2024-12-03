package com.zerotoismail.fakestoreapi.controllers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductControllerTest {

    @Test
    void testGetString() {
        String str = """
                {
                  "name": "John Doe",
                  "age": 45,
                  "address": "Doe Street, 23, Java Town"
                }
                """;

        System.out.println(str);
        System.out.println(str.substring(30, 40));
    }

    @Test
    void testNewString() {
        String sql = """
                SELECT id, firstName, lastName s\
                FROM Employee
                WHERE departmentId = "IT" \
                ORDER BY lastName, firstName""";
        System.out.println(sql);
    }


    @Test
    void testDefaultSwitch() {
        String day = "Monday";
        String result = switch (day) {
            case "Monday" -> "This is MONDAY";
            case "Tuesday" -> "This is TUESDAY";
            case "Wednesday" -> "This is WEDNESDAY";
            case "Thursday" -> "This is THURSDAY";
            case "Friday" -> "This is FRIDAY";
            case "Saturday" -> "This is SATURDAY";
            case "Sunday" -> "This is SUNDAY";
            default -> "THIS IS INVALID DAY";
        };

        System.out.println(result);
    }


    @Test
    void testMoreSwitch() {
        String fruit = "BANANA";
        String sql = switch (fruit) {
            case "APPLE", "PEAR" -> {
                System.out.println("the given fruit was: " + fruit);
                yield "Common fruit";
            }
            case "ORANGE", "AVOCADO" -> "Exotic fruit";
            case "BANANA" -> {
                System.out.println("BANANA");
                yield "BANANA";
            }
            default -> "Undefined fruit";
        };
        System.out.println(sql);
    }


    record User(String name, int age, String address) {
    }

    @Test
    void testRecords() {
        User user1 = new User("John Doe", 45, "Doe Street, 23, Java Town");
        User user2 = new User("John Doe", 45, "Doe Street, 23, Java Town");

        User user3 = new User("Michal Jordon", user1.age, user1.address);
        System.out.println("User1 == User2: " + (user1 == user2));
        System.out.println("User1.equals(User2): " + user1.equals(user2));

        System.out.println("UserName: " + user1.name);
        System.out.println("UserAge: " + user1.age);
        System.out.println("UserAddress: " + user1.address);

        System.out.println(user1);


    }

    @Test
    void testFindMaxInArray(){
       List<Integer> arr = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
       Optional<Integer> max = arr.stream()
               .max(Integer::compareTo);
        System.out.println("Max: " + max.get());
        System.out.println("Max: ${max.get()}");
    }


    record Employee(String name, int salary){}
    @Test
    void testFindHigestSalaryOfEmployee(){
        List<Employee> employees = Arrays.asList(
                new Employee("John", 50000),
                new Employee("Jane", 60000),
                new Employee("Mark", 55000),
                new Employee("Sophia", 75000),
                new Employee("Alex", 40000)
        );

        Optional<Employee> max = employees.stream()
                .max(Comparator.comparingDouble(Employee::salary));
        System.out.println("Max Employee Salary: " + max.get());
    }

    @Test
    void testConcatAllStringArray(){
        List<String> names = Arrays.asList("John", "Jane", "Mark", "Sophia", "Alex");
        String result = names.stream()
                .collect(Collectors.joining(", "));
        System.out.println("Joining: " + result);
    }
}
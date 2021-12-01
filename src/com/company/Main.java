package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> catalog = new ArrayList<>(); // Справочник подразделений

        inputFromFile(catalog);

        checkContainment(catalog);
        sortListByReverse(catalog);

        printList(catalog);
        saveToFile(catalog);

    }

    //Проверка наличия кода подразделения в исходном массиве
    // modifiedList - хранит коды отсутствующих подразделений
    // substring - хранит код подразделения, который может отсутствовать в исх. массиве
    // isModified - были ли внесены изменения в modifiedList

    static private void checkContainment(ArrayList<String> checkList) {
        List<String> modifiedList = new ArrayList<>();
        String substring;
        boolean isModified = false;
        for (String code : checkList) {
            if (code.contains("\\")) {
                for (int i = 0; i != code.length(); i++) {
                    i = code.indexOf("\\", i);
                    if (i == -1) {
                        break;
                    }
                    substring = code.substring(0, i).concat("”");

                    if (!checkList.contains(substring) && !modifiedList.contains(substring)) {
                        modifiedList.add(substring);
                        isModified = true;
                    }
                }
            }
        }
        if (isModified) {
            checkList.addAll(modifiedList);
        }

    }

    //Сортировка массива по убыванию
    static private void sortListByReverse(ArrayList<String> list) {
        checkContainment(list);
        Collections.sort(list, Collections.reverseOrder());
    }

    //Сортировка массива по возрастанию
    static private void sortListDirectly(ArrayList<String> list) {
        checkContainment(list);
        Collections.sort(list);
    }

    //Ввод данных из файла input.txt
    static private void inputFromFile(ArrayList<String> inputToList) {
        try(Scanner scanner = new Scanner(new File("input.txt"))) {
            while (scanner.hasNextLine()) {
                inputToList.add(scanner.nextLine());
            }
        }
        catch (IOException e) {
            System.out.println("Error =(!");
            e.printStackTrace();
        }
    }

    //Сохранение массива в файл output.txt
    static private void saveToFile(ArrayList<String> listToSave) {
        try (FileWriter outputFile = new FileWriter("output.txt")) {
            for (String item : listToSave) {
                outputFile.write(item + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error =(!");
            e.printStackTrace();
        }
    }

    // Вывод элементов массива на экран
    static private void printList(ArrayList<String> listToPrint) {
        for (String item : listToPrint) {
            System.out.println(item);
        }
    }

}
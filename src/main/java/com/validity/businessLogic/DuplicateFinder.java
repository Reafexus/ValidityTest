package com.validity.businessLogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DuplicateFinder {

    public DuplicateDisplay findDupes() {
        DuplicateDisplay display = new DuplicateDisplay();
        display.setDuplicates(new ArrayList<String>());
        display.setSingles(new ArrayList<String>());
        File file = new File(getClass().getClassLoader().getResource("normal.csv").getFile());
        Map<Integer, LineData> data = new HashMap<>();
        String line;
        int lineNumber = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //skip headers
            line = reader.readLine();
            lineNumber++;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",(?=[a-zA-Z0-9\",])");
                LineData lineData = new LineData();
                lineData.setId(Integer.parseInt(split[0]));
                lineData.setFirstName(split[1]);
                lineData.setLastName(split[2]);
                lineData.setCompany(split[3]);
                lineData.setEmail(split[4]);
                lineData.setAddress1(split[5]);
                lineData.setAddress2(split[6]);
                lineData.setZip(split[7]);
                lineData.setCity(split[8]);
                lineData.setStateLong(split[9]);
                lineData.setState(split[10]);
                lineData.setPhone(split[11]);
                lineData.setFullLine(line);
                data.put(lineNumber, lineData);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < lineNumber; i++) {
            boolean dupes = false;
            for (int j = i + 1; j < lineNumber; j++) {
                dupes = areEntriesSimilar(data.get(i), data.get(j));
                if (dupes) {
                    data.get(i).setWasDupe(true);
                    data.get(j).setWasDupe(true);
                    break;
                }
            }
        }
        for (int i = 1; i < lineNumber; i++) {
            if(data.get(i).isWasDupe()){
                display.getDuplicates().add(data.get(i).getFullLine());
            } else{
                display.getSingles().add(data.get(i).getFullLine());
            }
        }
        return display;
    }

    private boolean areEntriesSimilar(LineData data1, LineData data2) {
        int similar = 0;
        int missingData = 0;
        if (data1.getCity().equals(data2.getCity())) {
            similar++;
        }
        if (isBlank(data1.getCity()) || isBlank(data2.getCity())) {
            missingData++;
        }
        if (data1.getStateLong().equals(data2.getStateLong())) {
            similar++;
        }
        if (isBlank(data1.getStateLong()) || isBlank(data2.getStateLong())) {
            missingData++;
        }
        if (data1.getState().equals(data2.getState())) {
            similar++;
        }
        if (isBlank(data1.getState()) || isBlank(data2.getState())) {
            missingData++;
        }
        if (data1.getZip().equals(data2.getZip())) {
            similar++;
        }
        if (isBlank(data1.getZip()) || isBlank(data2.getZip())) {
            missingData++;
        }
        if (data1.getPhone().equals(data2.getPhone())) {
            similar++;
        }
        if (isBlank(data1.getPhone()) || isBlank(data2.getPhone())) {
            missingData++;
        }
        if (similar == 0 && missingData == 0) {
            if (data1.getId() == data2.getId()) {
                System.out.println("Not Duplicate: " + data1.getId() + " and " + data2.getId());
            }
            return false;
        }
        int similarFirstName = 0;
        boolean startFirstSame = false;
        boolean missingFirstNames = false;
        if (!isBlank(data1.getFirstName()) && !isBlank(data2.getFirstName())) {
            String[] firstName1 = data1.getFirstName().split("");
            String[] firstName2 = data2.getFirstName().split("");
            if (firstName1[0].equals(firstName2[0])) {
                startFirstSame = true;
            }
            int shorterName = firstName1.length > firstName2.length ? firstName2.length : firstName1.length;
            for (int i = 0; i < shorterName; i++) {
                if (firstName1[i].equals(firstName2[i])) {
                    similarFirstName++;
                }
            }
            if (similarFirstName >= 2) {
                similar++;
            }
        } else {
            missingFirstNames = true;
            missingData++;
        }
        int similarLastName = 0;
        boolean startLastSame = false;
        boolean missingLastNames = false;
        if (!isBlank(data1.getLastName()) && !isBlank(data2.getLastName())) {
            String[] lastName1 = data1.getLastName().split("");
            String[] lastName2 = data2.getLastName().split("");
            if (lastName1[0].equals(lastName2[0])) {
                startLastSame = true;
            }
            int shorterName = lastName1.length > lastName2.length ? lastName2.length : lastName1.length;
            for (int i = 0; i < shorterName; i++) {
                if (lastName1[i].equals(lastName2[i])) {
                    similarLastName++;
                }
            }
            if (similarLastName >= 2) {
                similar++;
            }
        } else {
            missingData++;
            missingLastNames = true;
        }
        if ((!missingFirstNames && !startFirstSame && similarFirstName < 2) || (!missingLastNames && !startLastSame && similarLastName < 2)) {
            if (data1.getId() == data2.getId()) {
                System.out.println("Not Duplicate: " + data1.getId() + " and " + data2.getId());
            }
            return false;
        }
        int similarCompany = 0;
        if (!isBlank(data1.getCompany()) && !isBlank(data2.getCompany())) {
            String[] company1 = data1.getCompany().split(" ");
            String[] company2 = data2.getCompany().split(" ");
            for (String s1 : company1) {
                String nameNoPunctuation1 = s1.replaceAll(",", "");
                if (!ignoreCommon(nameNoPunctuation1)) {
                    for (String s : company2) {
                        String nameNoPunctuation2 = s.replaceAll(",", "");
                        if (nameNoPunctuation1.equals(nameNoPunctuation2)) {
                            similarCompany++;
                        }
                    }
                }
            }
            if (similarCompany > 0) {
                similar++;
            }
        } else {
            missingData++;
        }
        int similarEmail = 0;
        if (!isBlank(data1.getEmail()) && !isBlank(data2.getEmail())) {
            String[] email1 = data1.getEmail().split("[@.]");
            String[] email2 = data2.getEmail().split("[@.]");
            similarEmail += email1[0].equals(email2[0]) ? 1 : 0;
            similarEmail += email1[1].equals(email2[1]) ? 1 : 0;
            similarEmail += email1[2].equals(email2[2]) ? 1 : 0;
            if (similarEmail >= 2) {
                similar++;
            }
        } else {
            missingData++;
        }
        int similarAddress = 0;
        if (!isBlank(data1.getAddress1()) && !isBlank(data2.getAddress1())) {
            String[] address1 = data1.getAddress1().split(" ");
            String[] address2 = data2.getAddress1().split(" ");
            for (int i = 0; i < 3; i++) {
                if (address1.length - 1 <= i) {
                    if (address2.length - 1 <= i) {
                        similarAddress += address1[i].equals(address2[i]) ? 1 : 0;
                    } else {
                        break;
                    }

                }
            }
            if (similarAddress >= 2) {
                similar++;
            }
            if (similarCompany > 0) {
                similar++;
            }
        } else {
            missingData++;
        }
        similar += data1.getAddress2().equals(data2.getAddress2()) ? 1 : 0;
        if (similar + missingData >= 8) {

            return true;
        }
        if (data1.getId() == data2.getId()) {
            System.out.println("Not Duplicate: " + data1.getId() + " and " + data2.getId());
        }
        return false;
    }

    private boolean ignoreCommon(String word) {
        return word.equals("The") || word.equals("and") || word.equals("Group") || word.equals("Company") || word.equals("Inc") || word.equals("LLC");
    }

    private boolean isBlank(String str){
        return "".equals(str);
    }
}

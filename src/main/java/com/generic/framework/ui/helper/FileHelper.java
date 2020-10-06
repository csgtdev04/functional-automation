package com.generic.framework.ui.helper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHelper {
    public List<String> retrieveArrayList(String filePath) {
        List<String> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

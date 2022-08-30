package com.oyd;

import java.io.File;
import java.io.FileInputStream;

public class iostreamPath {
    public static void main(String[] args) {

        System.out.println(new File(".").getAbsolutePath());    //D:\Program\Git\GitRepo\MyMall_Learning\.
        System.out.println(new File("/").getAbsolutePath());    //D:\


    }
}

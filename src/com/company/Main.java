package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException,InterruptedException {
         transferFile();
        }

    public static void transferFile() throws IOException, InterruptedException {
        File file = new File("C:/Users/root/Desktop/Busygin/Peredatchik/123.txt");
        BufferedReader Freader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = Freader.readLine()) != null) {
            String[] SubStr;
            SubStr = line.split(" ");
                for (int i = 0; i <SubStr.length; i++) {            //считываю каждое слово из массива
                    System.out.println(SubStr[i]+"передаю");
                        String word = SubStr[i];
                    System.out.println(word);
                        for (int i1 = 0; i1 < word.length(); i1++) {    //считываю каждый символ из слова
                                System.out.println("начинаю передачу символа");
                                int code = word.codePointAt(i1);
                                System.out.println(code);
                                String binary =String.format("%7s",Integer.toBinaryString(code)).replaceAll(" ","0");        //символ переведен в UTF-16
                            System.out.println(binary+"not converted");
                                System.out.println(binary +"UTF CODE");
                                for (int i2 = 0; i2 < binary.length(); i2++) {
                                   try( ServerSocket swch = new ServerSocket(5353)){//рукопожатие на передачу символа
                                   // System.out.println("5353 connected");
                                    try(Socket incoming1= swch.accept()) {
                                        try(ServerSocket bit1 = new ServerSocket(1900)) {
                                            if (binary.substring(i2, i2 + 1).equals("1")) {
                                                System.out.println("1 - передал");
                                                try (Socket bit01 = bit1.accept()) {
                                                    //Thread.sleep(200);
                                                }
                                            } else {
                                                System.out.println("0 - передал");
                                                bit1.close();
                                                //Thread.sleep(1000);
                                            }
                                        }
                                        swch.close();
                                    }
                                    }
                                }
                                System.out.println("закончил передачу символа");
                        }
                    }
                }
        }

    }

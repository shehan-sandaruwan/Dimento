package com.example.shehandinuka.alwaysalive;


import java.io.BufferedReader;
import java.io.InputStreamReader;


public class PythonCaller {


     static String line = "";

        public static void callToPython(){
            try {

                Runtime rt = Runtime.getRuntime();
                Process pr = rt.exec("python Test.py");

                // retrieve output from python script
                BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line = "";
                while((line = bfr.readLine()) != null) {
                    // display each output line form python script
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



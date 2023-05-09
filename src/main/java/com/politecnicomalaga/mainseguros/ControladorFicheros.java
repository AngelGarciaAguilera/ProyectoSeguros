/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainseguros;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author mint
 */
public class ControladorFicheros {

    public static boolean writeText(String fileName, String data) {

        PrintWriter out = null;
        
        try{
            out = new PrintWriter(new FileWriter(fileName));
            out.print(data); 
            out.flush(); //Aseguramos que todo pasa a disco
        }catch(IOException io){  
            System.out.println(io.getMessage());
            return false;
        }finally{
            if(out != null){
                out.close();
            }
        }
        return true;
    }

    public static String readText(String fileName) {  

        Scanner sc = null;
        String texto = "";
        
        try{
            sc = new Scanner(new FileReader (fileName));
            
            while(sc.hasNext()){
                texto += "\n" + sc.nextLine();
            }
        }catch (IOException e) {
            return e.getMessage();
        }finally{
            if(sc != null){
                sc.close();
            }            
        }
        return texto;
    }
}

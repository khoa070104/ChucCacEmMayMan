/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 *
 * @author NCPC
 */
public class TextNomalizer {
    
    // Mục tiêu: Đọc các dữ liệu có trong file input.txt vào Java
    public String readFile(String fileName){
        String content = ""; // Abc EFG 
        try{
            Scanner sc = new Scanner(new File(fileName));
            while(sc.hasNextLine()){
                String line = sc.nextLine().trim(); //EFG  
                if(!line.isEmpty()){ // Bỏ qua dòng trống
                    content += line +" " ;
                }
            }
            sc.close();
        }catch(Exception e){
            System.out.println("File not found!");
        }
        return content;
    }
    
    public String nomalize(String text){
        text = text.toLowerCase();
        text = text.replaceAll("\\s+", " ");
        
        text = text.replaceAll("\\s*([,.:])\\s*", "$1 ");
        text = text.replaceAll("\\s*\"\\s*", "\""); 
        text = xuLiSauDauCham(text);
        text = text.trim();
        if(!text.isEmpty()){
            text = text.substring(0,1).toUpperCase()+text.substring(1);
        }
        
        if(text.endsWith(".") != true){
            text += ".";
        }
        
        return text;
    }
    
    public void writeFile(String fileName, String content) {
        try {
            FileWriter fw = new FileWriter(new File(fileName));
            fw.write(content);
            fw.close();
        } catch (IOException ex) {
        }
        
    }

    private String xuLiSauDauCham(String text) {
        String[] sentences = text.split("\\.");

        String res = "";
        
        for (String sentence : sentences) {
            sentence = sentence.trim(); //Anh
            if(sentence.isEmpty() == false){
               sentence = sentence.substring(0, 1).toUpperCase()+sentence.substring(1);
               res +=sentence+". ";
            }
        }
        return res.trim();
    }
    
    public static void main(String[] args) {
        TextNomalizer test = new TextNomalizer();
        
        String content = test.readFile("input.txt");
        content = test.nomalize(content);
        test.writeFile("output.txt", content);
    }
     
}

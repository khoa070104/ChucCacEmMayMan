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
        if(text == null || text.trim().isEmpty()){
            return "";
        }
        
        text = text.toLowerCase();
        text = text.replaceAll("\\s+", " ");
        
        // Fix dấu câu: thêm space sau dấu câu
        text = text.replaceAll("\\s*([,.:])\\s*", "$1 ");
        text = text.replaceAll("\\s*\"\\s*", "\" ");
        
        // Xử lý viết hoa sau dấu chấm
        text = xuLiSauDauCham(text);
        text = text.trim();
        
        // Viết hoa chữ cái đầu
        if(!text.isEmpty()){
            text = text.substring(0,1).toUpperCase()+text.substring(1);
        }
        
        // Đảm bảo kết thúc bằng dấu chấm
        if(!text.endsWith(".")){
            text += ".";
        }
        
        return text;
    }
    
    public void writeFile(String fileName, String content) {
        try {
            FileWriter fw = new FileWriter(new File(fileName));
            fw.write(content);
            fw.close();
            System.out.println("File written successfully: " + fileName);
        } catch (IOException ex) {
            System.out.println("Error writing file: " + ex.getMessage());
        }
    }

    private String xuLiSauDauCham(String text) {
        String[] sentences = text.split("\\.");
        String res = "";
        
        for (String sentence : sentences) {
            sentence = sentence.trim();
            if(!sentence.isEmpty()){
                // Viết hoa chữ cái đầu của câu
                sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
                res += sentence + ". ";
            }
        }
        return res.trim();
    }
    
    public static void main(String[] args) {
        TextNomalizer test = new TextNomalizer();
        
        System.out.println("Reading input file...");
        String content = test.readFile("input.txt");
        
        if(content.isEmpty()){
            System.out.println("Input file is empty or not found!");
            return;
        }
        
        System.out.println("Original text: " + content);
        System.out.println("Normalizing text...");
        content = test.nomalize(content);
        System.out.println("Normalized text: " + content);
        
        test.writeFile("output.txt", content);
    }
     
}

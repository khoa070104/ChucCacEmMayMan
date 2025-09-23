/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Student;

/**
 *
 * @author NCPC
 */
public class StudentController {
    ArrayList<Student> listStd = new ArrayList<>();
    
    public Student getStudentById(String studentId){
        for (Student student : listStd) {
            if(student.getId().equals(studentId)){
                return student;
            }
        }
        return null;
    }
    
    public void createStudent(){
        while(true){
            String id, name, semester,course;
            do{
                id = Inputter.input("Enter Student ID: ");
            }while(getStudentById(id) != null);
            name = Inputter.input("Enter name: ");
            semester = Inputter.input("Enter semester: ");
            course = Inputter.input("Enter course: ");
            
            listStd.add(new Student(id, name, semester, course));
            
            if(listStd.size()>= 10){
                String choice = Inputter.inputRequired("Do you want to continue (Y/N)?", Inputter.YES_NO_VALIDATE);
                if(choice.equalsIgnoreCase("N")){
                    break;
                }
            }
        }
    }
    
    public void findAndSort(){
        String name = Inputter.input("Enter Student Name:");
        
        ArrayList<Student> res = new ArrayList<>();
        
        for (Student student : listStd) {
            if(student.getName().contains(name)){
                res.add(student);
            }
        }
        
        Collections.sort( res,  Comparator.comparing(Student::getName));
        for (Student r : res) {
            System.out.println(r.getId()+"|"+r.getName()+"|"+r.getCourse()+"|"+r.getSemester());
        }
    }
}

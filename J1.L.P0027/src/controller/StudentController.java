/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import model.Mountain;
import model.Student;

public class StudentController {
    List<Student> listStudent = new ArrayList<>();
    List<Mountain> listMountain = new ArrayList<>();

    public List<Student> getListStudent() {
        return listStudent;
    }
    
    

    // Use Validator's scanner via prompt helpers
    
    // Đầu tiên nhập gì, và cho nhập mấy lần
    
    // --- Function
    
    public Student getStudentById(String studentId){
        for (Student student : listStudent) {
            if(student.getStudenId().equals(studentId)){
                return student;
            }
        }
        return null;
    }
    
    public Mountain getMountainById(String moutainId){
        for (Mountain mountain : listMountain) {
            if(mountain.getCode().equals(moutainId)){
                return mountain;
            }
        }
        return null;
    }
    
    public void readMountainList(String fileName){
        try {
            Scanner rf = new Scanner( new File(fileName) );
            // bỏ qua dòng đầu tiên trong file
            if(rf.hasNext()){
                rf.nextLine();
            }
            
            while(rf.hasNext()){
                String[] lines = rf.nextLine().split(",");
                Mountain mountain = new Mountain(lines[0], lines[1], lines[2], lines[3]);
                listMountain.add(mountain);
            }
            rf.close();
        } catch (Exception e) {
            System.out.println("File invalid!");
        }
    }
    
    public void addNewRegistration(){
        String  studentId, name, phoneNumber, email, mountainCode;
        double tutionFee = 6000000;
        do{
            studentId = Inputter.inputRequired("Enter student id:" , Inputter.STUDENT_ID_VALIDATE );
        }while(getStudentById(studentId) != null);
        name = Inputter.inputRequired("Enter name:", Inputter.NAME_VALIDATE);
        phoneNumber = Inputter.inputRequired("Enter phone number: ", Inputter.PHONE_NUMBER_VALIDATE);
        email = Inputter.inputRequired("Enter email: ", Inputter.EMAIL_VALIDATE);
        do{
            mountainCode = Inputter.inputRequired("Enter mountain code: ", Inputter.MOUNTAIN_CODE_VALIDATE);
        }while( getMountainById(mountainCode) == null);
        if( phoneNumber.matches(Inputter.TUTION_FEE_VALIDATE) ){
            tutionFee = tutionFee - 35/100 * tutionFee;
        }
        Student newStudent = new Student(studentId, name, phoneNumber, email, mountainCode, tutionFee);
        listStudent.add(newStudent);
    }
    
    // Update -> Record tồn tại 
    public void updateRegistration(){
        String studentId, name, phone, email, moutainCode;
        double tutionFee = 6000000;
        studentId = Inputter.inputRequired("Enter student id:" , Inputter.STUDENT_ID_VALIDATE );
        Student std = getStudentById(studentId);
        if(std == null){
            System.out.println("This student has not registered yet.");
            return ;
        } else {
            // Ko bắt buộc ngta điền 
            name = Inputter.inputOptional("Enter new name:", Inputter.NAME_VALIDATE);
            phone = Inputter.inputOptional("Enter new phone:", Inputter.PHONE_NUMBER_VALIDATE);
            email = Inputter.inputOptional("Enter new email:", Inputter.EMAIL_VALIDATE);
            
            while(true){
                moutainCode = Inputter.inputOptional("Enter new mountain code: ", Inputter.MOUNTAIN_CODE_VALIDATE);
                if(moutainCode.isEmpty() || moutainCode == "" ) 
                    break;
                if(getMountainById(moutainCode) != null) break;
            }
            
            if( name.isEmpty() == false || name != ""){
                std.setName(name);
            }
            if(phone.isEmpty() == false || phone != "" ){
                std.setPhoneNumber(phone);
                if(phone.matches(Inputter.TUTION_FEE_VALIDATE)){
                    tutionFee = tutionFee - 35/100 * tutionFee;
                    std.setTutioneFee(tutionFee);
                }
            }
            if(email.isEmpty() == false || email != "" ){
                std.setEmail(email);
            }
            if(moutainCode != null && moutainCode.isEmpty() == false || moutainCode != ""){
                std.setMountainCode(moutainCode);
            }
            System.out.println("Updated success!");    
        }
    }
    
    public static void main(String[] args) {
        StudentController controller = new StudentController();
        controller.readMountainList("MountainList .csv");

        controller.listStudent.add(new Student("QE190000", "ALIBBA", "0322222222", "email@gmail.com", "1", 6000000))  ;
        controller.listStudent.add(new Student("QE190001", "ALIBBA", "0322222222", "email@gmail.com", "1", 600000))  ;
        controller.listStudent.add(new Student("QE190002", "ALIBBA", "0322222222", "email@gmail.com", "2", 600000))  ;
        controller.listStudent.add(new Student("DE190000", "ALIBBA", "0322222222", "email@gmail.com", "1", 600000))  ;
//        controller.deleteRegister();
        //System.out.println(controller.listStudent.get(0).getName().startsWith("QE")); 
       //List<Student> listSearch = controller.filterByCampus();
        //controller.printList(listSearch, "No One" );
        controller.saveToFile("student.dat");
    
    }
    
    
    // Hàm in thông tin
    // input: nhận 1 list 
    // Và trả về 1 cái bảng thông
    
    public void printList(List<Student> listStudent, String label){
        if(listStudent.size() == 0){
            System.out.println(label);
        } else {
            System.out.printf("-------------------------------------------------------------------------\n");
            System.out.printf("Student ID  | Name          | Phone        | Peak Code | Fee\n");
            System.out.printf("-------------------------------------------------------------------------\n");
            //** TT HỌC SINH
            for (Student student : listStudent) {
                System.out.printf("%-12s| %-14s| %-13s| %-10s| %,-10.1f\n", student.getStudenId(),
                                                                                 student.getName(),
                                                                                 student.getPhoneNumber(),
                                                                                 student.getMountainCode(),
                                                                                 student.getTutioneFee());
            }
            System.out.printf("-------------------------------------------------------------------------\n");
        }
    }
    
    
    public void deleteRegister(){
        String studentId;
        studentId = Inputter.inputRequired("Enter student id to remove: ", Inputter.STUDENT_ID_VALIDATE);
        Student std = getStudentById(studentId);
        if(std == null){
            System.out.println("This student has not registered yet.");
        } else{
            System.out.println("Student Detail: ");
            System.out.println("----------------------------------");
            System.out.println("Student ID: "+ std.getStudenId());
            System.out.println("Name: "+ std.getName());
            System.out.println("Phone: "+ std.getPhoneNumber());
            String moutain = convertMountainCode(std.getMountainCode());
            
            System.out.println("Mountain :"+ moutain);
            System.out.println("Fee:"+ std.getTutioneFee());
            System.out.println("----------------------------------");
            String choice = Inputter.inputRequired("Are you sure you want to delete this registration? (Y/N):", Inputter.YES_NO_VALIDATE);
            if(choice.equals("Y")){
                listStudent.remove(std);
                System.out.println("The registration has been successfully deleted.");
            } 
        }
    }
    
    private String convertMountainCode(String mtCode){
        String moutain ="MT";
        if( Integer.parseInt(mtCode) >= 10){
            moutain+=mtCode; 
        } 
        else{
                moutain+="0"+mtCode;
        }
        return moutain;
    }
    
    
    public List<Student> searchByName(){
        String name = Inputter.inputRequired("Enter name to search: ", Inputter.NAME_VALIDATE);
        List<Student> resultList = new ArrayList<>();
        
        for (Student student : listStudent) {
            if(student.getName().contains(name)){
                resultList.add(student);
            }
        }
        return resultList;
    }
    
    public List<Student> filterByCampus(){
        String code = Inputter.input("Enter Campus Code: ");
        List<Student> resultList = new ArrayList<>();
        for (Student student : listStudent) {
            if(student.getStudenId().startsWith(code)){
                resultList.add(student);
            }
        }
        return resultList;
    }
    
    public void printStatist(){
        // 1. Lọc ra các cái Code có trong list Student
        Set<String> mountainCodeList = new HashSet<>();
        for (Student std : listStudent) {
            mountainCodeList.add(std.getMountainCode());
        }
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-10s | %-22s | %-15s%n", "Peak Name", "Number of Participants", "Total Cost");
        System.out.println("--------------------------------------------------------------");
        for (String code : mountainCodeList) {
            int numberOfParticipant = 0;
            double totalCost = 0;
            for (Student std : listStudent) {
                if(std.getMountainCode().equals(code)){
                    numberOfParticipant++;
                    totalCost+= std.getTutioneFee();
                }
            }
            
            System.out.printf("%-10s | %-22d | %,-15.0f%n", convertMountainCode(code) , numberOfParticipant , totalCost);
        }
        System.out.println("--------------------------------------------------------------");
    }
    
    public void saveToFile(String fileName){
        try {
            FileWriter fw = new FileWriter(new File(fileName));
            for (Student student : listStudent) {
                fw.append(student.getStudenId()+", "
                        + student.getName()+", "
                + student.getPhoneNumber()+", "
                + student.getEmail()+", "
                + student.getMountainCode()+", "
                + student.getTutioneFee());
                fw.append("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("File invalid!");
        }
    }
}

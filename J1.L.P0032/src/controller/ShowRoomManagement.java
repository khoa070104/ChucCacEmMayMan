/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import model.Brand;
import model.Car;

/**
 *
 * @author NCPC
 */
public class ShowRoomManagement {
    List<Brand> listBrand = new ArrayList<>();
    List<Car> listCar = new ArrayList<>();
    public void readBrandFile(String fileName){
        try {
            Scanner rf = new Scanner(new File(fileName));
            while(rf.hasNextLine()){
                String[] lines = rf.nextLine().split(",");
                if(lines.length == 3){
                    String[] soundAndPrice = lines[2].split(":");
                    // B7-2018, BMW 730Li (2018)
                    //  Harman Kardon, 3.749B
                    double price = nomalizePrice(soundAndPrice[1]);
                    Brand newBrand = new Brand(lines[0], lines[1], soundAndPrice[0], price);
                    listBrand.add(newBrand);
                }
            }
            rf.close();
        } catch (Exception e) {
        }
    }
    
    public Brand getBrandById(String brandId){
        for (Brand brand : listBrand) {
            if(brand.getBrandId().equals(brandId)){
                return brand;
            }
        }
        return null;
    }
    
    public void readCarFile(String fileName){
        try {
            Scanner rf = new Scanner(new File(fileName));
            while(rf.hasNextLine()){
                String[] lines = rf.nextLine().split(",");
                
                if(lines.length == 5){
                    Brand existedBrand = getBrandById(lines[1].trim());
                    //System.out.println(existedBrand);
                    if(existedBrand != null){
                        Car newCar = new Car(lines[0],existedBrand,lines[2],lines[3],lines[4]);
                        listCar.add(newCar);
                    }
                }
            }
            rf.close();
        } catch (Exception e) {
        }
    }

    private Double nomalizePrice(String price) {
        String res = price.replaceAll("[.]", "");
        res = res.replaceAll("[B]", "");
        return Double.parseDouble(res);
    }
    
    public List<Brand> getBrandByPrice(){
        double price = Double.parseDouble( Inputter.inputRequired("Enter price to search: "));
        List<Brand> result = new ArrayList<>();
        
        for (Brand brand : listBrand) {
            if(brand.getPrice() <= price){
               result.add(brand);
            }
        }
        return result;
    }
    
    public void printCarSortByBrandName(){
        List<Car> sorted = listCar;        
        sorted.sort(  Comparator.comparing(c -> c.getBrand().getBrandName()));
        
        // -> lambda expression
        // c : Tham số đầu vào, mỗi chữ c đại diện cho 1 object có trong listCar
        for (Car car : sorted) {
            System.out.println(car.toString()); 
        }
//        for (int i = sorted.size()-1; i >= 0; i--) {
//            System.out.println(sorted.get(i));
//        }
    }
    
    public Car getCarById(String carId){
        for (Car car : listCar) {
            if(car.getCarId().equals(carId)){
                return car;
            }
        }
        return null;
    }
    
    public void addNewCar(){
        String id, brandId, engineId, frameId, color;
        do{
            id = Inputter.inputRequired("Enter ID: ");
        }while(getCarById(id) != null);
        Brand existed;
        do{
            brandId = Inputter.inputRequired("Enter Brand Id: ");
            existed = getBrandById(brandId); 
        }while (existed == null);
        engineId = Inputter.inputRequired("Enter Engine Id:", Inputter.VALIDATE_ENGINE_ID);
        frameId = Inputter.inputRequired("Enter Frame Id:", Inputter.VALIDATE_FRAME_ID);
        color = Inputter.inputRequired("Enter Color:");
        
        Car newCar = new Car(id, existed, color, frameId, engineId);
        listCar.add(newCar);
    }
    
    public boolean removeCar(){
        String id = Inputter.inputOptional("Enter Car Id (Enter if cancel):");
        if(id==""|| id.isEmpty())
            return false;
        Car removeCar = getCarById(id);
        listCar.remove(removeCar);
        return true;
    }
    
    public void updateCar(){
        String id, brandId, engineId, frameId, color;
        Car existedCar;
        do{
            id = Inputter.inputRequired("Enter ID: ");
            existedCar = getCarById(id);
        }while(existedCar == null);
        Brand existed;
        do{
            brandId = Inputter.inputOptional("Enter Brand Id (Enter if cancel): ");
            if(brandId == "" || brandId.isEmpty()){
                break;
            }
            existed = getBrandById(brandId); 
            existedCar.setBrand(existed);
        }while (existed == null);
        engineId = Inputter.inputOptional("Enter Engine Id (Enter if cancel):", Inputter.VALIDATE_ENGINE_ID);
        frameId = Inputter.inputOptional("Enter Frame Id (Enter if cancel):", Inputter.VALIDATE_FRAME_ID);
        color = Inputter.inputOptional("Enter Color (Enter if cancel):");
        
        if(engineId !=""&& !engineId.isEmpty()){
            existedCar.setEngineId(engineId);
        }
        if(frameId !=""&& !frameId.isEmpty()){
            existedCar.setFrameId(engineId);
        }
        if(color !=""&& !color.isEmpty()){
            existedCar.setColor(engineId);
        }
    }
    
    
    
    public static void main(String[] args) {
        ShowRoomManagement test = new ShowRoomManagement();
        test.readBrandFile("brands.txt");
        //System.out.println(test.getBrandById(test.listBrand.get(0).getBrandId()));
        test.readCarFile("cars.txt");
//        for (Car arg : test.listCar) {
//            System.out.println(arg);
//        }
        test.printCarSortByBrandName();
        
    }

    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import model.Brand;
import model.Car;
import common.Messages;
import common.Constants;

/**
 *
 * @author NCPC
 */
public class ShowRoomManagement {
    List<Brand> listBrand = new ArrayList<>();
    List<Car> listCar = new ArrayList<>();

    private boolean brandsChanged = false;
    private boolean carsChanged = false;
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

    private String formatPrice(double storedPrice){
        // storedPrice like 3749 -> 3.749B
        double b = storedPrice / 1000.0;
        return String.format("%.3fB", b);
    }

    public void listAllBrands(){
        System.out.println("ID        | Brand Name                          | Sound           | Price");
        System.out.println("--------------------------------------------------------------------------------");
        for (Brand b : listBrand) {
            System.out.printf("%-9s | %-35s | %-15s | %s\n",
                    b.getBrandId().trim(),
                    b.getBrandName().trim(),
                    b.getBrandSound().trim(),
                    formatPrice(b.getPrice()));
        }
    }

    private boolean isUniqueBrandId(String brandId){
        return getBrandById(brandId) == null;
    }

    public void addNewBrand(){
        String brandId;
        do{
            brandId = Inputter.inputRequired("Enter Brand ID: ");
            if(!isUniqueBrandId(brandId)){
                System.out.println(Messages.ERR_DUP_BRAND_ID);
            }
        }while(!isUniqueBrandId(brandId));

        String name;
        do{
            name = Inputter.inputRequired("Enter Brand Name: ");
            if(name.trim().isEmpty()) System.out.println(Messages.ERR_EMPTY_BRAND_NAME);
        }while(name.trim().isEmpty());

        String sound;
        do{
            sound = Inputter.inputRequired("Enter Sound Brand: ");
            if(sound.trim().isEmpty()) System.out.println(Messages.ERR_EMPTY_SOUND);
        }while(sound.trim().isEmpty());

        Double price = null;
        while(price == null){
            String priceStr = Inputter.inputRequired("Enter Price (billions, e.g., 3.749): ");
            try{
                double p = Double.parseDouble(priceStr);
                if(p <= 0) {
                    System.out.println(Messages.ERR_PRICE_POSITIVE);
                    continue;
                }
                // store as thousands of billions (e.g., 3.749 -> 3749)
                price = (double) Math.round(p * 1000.0);
            }catch(Exception ex){
                System.out.println(Messages.ERR_PRICE_POSITIVE);
            }
        }

        Brand nb = new Brand(brandId.trim(), name.trim(), sound.trim(), price);
        listBrand.add(nb);
        brandsChanged = true;
        System.out.println(Messages.MSG_ADDED_SUCCESS);
    }
    
    public List<Brand> getBrandByPrice(){
        double inputBillions;
        while(true){
            try{
                String s = Inputter.inputRequired("Enter price (<=) in billions: ");
                inputBillions = Double.parseDouble(s);
                if(inputBillions < 0){
                    System.out.println(Messages.ERR_PRICE_POSITIVE);
                    continue;
                }
                break;
            }catch(Exception ex){
                System.out.println(Messages.ERR_PRICE_POSITIVE);
            }
        }
        double threshold = Math.round(inputBillions * 1000.0);
        List<Brand> result = new ArrayList<>();
        for (Brand brand : listBrand) {
            if(brand.getPrice() <= threshold){
               result.add(brand);
            }
        }
        return result;
    }

    public void printBrandsByPrice(){
        List<Brand> result = getBrandByPrice();
        if(result.isEmpty()) return;
        System.out.println("ID        | Brand Name                          | Sound           | Price");
        System.out.println("--------------------------------------------------------------------------------");
        for (Brand b : result) {
            System.out.printf("%-9s | %-35s | %-15s | %s\n",
                    b.getBrandId().trim(), b.getBrandName().trim(), b.getBrandSound().trim(), formatPrice(b.getPrice()));
        }
    }

    public void searchBrandById(){
        String id = Inputter.inputRequired("Enter Brand ID: ");
        Brand b = getBrandById(id);
        if(b == null){
            System.out.println(Messages.ERR_BRAND_NOT_FOUND);
        }else{
            System.out.printf("%-9s | %-35s | %-15s | %s\n",
                b.getBrandId().trim(), b.getBrandName().trim(), b.getBrandSound().trim(), formatPrice(b.getPrice()));
        }
    }

    public void updateBrandById(){
        String id = Inputter.inputRequired("Enter Brand ID: ");
        Brand b = getBrandById(id);
        if(b == null){
            System.out.println(Messages.ERR_BRAND_NOT_FOUND);
            return;
        }
        String name = Inputter.inputOptional("Enter Brand Name (empty to skip): ");
        String sound = Inputter.inputOptional("Enter Sound Brand (empty to skip): ");
        String priceStr = Inputter.inputOptional("Enter Price in billions (empty to skip): ");

        if(name != null && !name.isEmpty()) b.setBrandName(name.trim());
        if(sound != null && !sound.isEmpty()) b.setBrandSound(sound.trim());

        if(priceStr != null && !priceStr.isEmpty()){
            try{
                double p = Double.parseDouble(priceStr);
                if(p > 0){
                    double stored = Math.round(p * 1000.0);
                    b.setPrice(stored);
                }else{
                    System.out.println(Messages.ERR_PRICE_POSITIVE);
                }
            }catch(Exception ex){
                System.out.println(Messages.ERR_PRICE_POSITIVE);
            }
        }
        brandsChanged = true;
        System.out.println(Messages.MSG_UPDATED_SUCCESS);
    }
    
    public void printCarSortByBrandName(){
        List<Car> sorted = new ArrayList<>(listCar);
        sorted.sort(
            Comparator.comparing((Car c) -> c.getBrand().getBrandName().trim())
                      .thenComparing((Car c) -> -c.getBrand().getPrice())
        );
        for (Car car : sorted) {
            System.out.println(car.toString());
        }
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
        do{
            engineId = Inputter.inputRequired("Enter Engine Id:", Inputter.VALIDATE_ENGINE_ID);
            if(!isUniqueEngineId(engineId)) System.out.println(Messages.ERR_DUP_ENGINE_ID);
        }while(!isUniqueEngineId(engineId));
        do{
            frameId = Inputter.inputRequired("Enter Frame Id:", Inputter.VALIDATE_FRAME_ID);
            if(!isUniqueFrameId(frameId)) System.out.println(Messages.ERR_DUP_FRAME_ID);
        }while(!isUniqueFrameId(frameId));
        color = Inputter.inputRequired("Enter Color:");
        
        Car newCar = new Car(id, existed, color, frameId, engineId);
        listCar.add(newCar);
        carsChanged = true;
    }
    
    public boolean removeCar(){
        String id = Inputter.inputOptional("Enter Car Id (Enter if cancel):");
        if(id==""|| id.isEmpty())
            return false;
        Car removeCar = getCarById(id);
        if(removeCar == null){
            System.out.println(Messages.ERR_CAR_NOT_FOUND);
            return false;
        }
        listCar.remove(removeCar);
        carsChanged = true;
        System.out.println(Messages.MSG_REMOVED_SUCCESS);
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
            if(existed != null) existedCar.setBrand(existed);
        }while (existed == null);
        engineId = Inputter.inputOptional("Enter Engine Id (Enter if cancel):", Inputter.VALIDATE_ENGINE_ID);
        frameId = Inputter.inputOptional("Enter Frame Id (Enter if cancel):", Inputter.VALIDATE_FRAME_ID);
        color = Inputter.inputOptional("Enter Color (Enter if cancel):");
        
        if(engineId !=""&& !engineId.isEmpty()){
            if(isUniqueEngineIdForUpdate(existedCar, engineId)){
                existedCar.setEngineId(engineId);
            }else{
                System.out.println(Messages.ERR_DUP_ENGINE_ID);
            }
        }
        if(frameId !=""&& !frameId.isEmpty()){
            if(isUniqueFrameIdForUpdate(existedCar, frameId)){
                existedCar.setFrameId(frameId);
            }else{
                System.out.println(Messages.ERR_DUP_FRAME_ID);
            }
        }
        if(color !=""&& !color.isEmpty()){
            existedCar.setColor(color);
        }
        carsChanged = true;
    }

    private boolean isUniqueFrameId(String frameId){
        for (Car c : listCar) if(c.getFrameId().equals(frameId)) return false;
        return true;
    }

    private boolean isUniqueEngineId(String engineId){
        for (Car c : listCar) if(c.getEngineId().equals(engineId)) return false;
        return true;
    }

    private boolean isUniqueFrameIdForUpdate(Car current, String newFrame){
        for (Car c : listCar){
            if(c == current) continue;
            if(c.getFrameId().equals(newFrame)) return false;
        }
        return true;
    }

    private boolean isUniqueEngineIdForUpdate(Car current, String newEngine){
        for (Car c : listCar){
            if(c == current) continue;
            if(c.getEngineId().equals(newEngine)) return false;
        }
        return true;
    }

    public void listCarsByColor(){
        String color = Inputter.inputRequired("Enter color: ");
        for (Car c : listCar){
            if(c.getColor().equalsIgnoreCase(color.trim())){
                System.out.println(c.toString());
            }
        }
    }

    public void searchCarsByBrandNameLike(){
        String keyword = Inputter.inputRequired("Enter partial brand name: ");
        String kw = keyword.trim().toLowerCase();
        for (Car c : listCar){
            if(c.getBrand().getBrandName().toLowerCase().contains(kw)){
                System.out.println(c.toString());
            }
        }
    }

    public void listAllCarsSorted(){
        printCarSortByBrandName();
    }

    public void saveAll(){
        boolean any = false;
        if(brandsChanged){
            saveBrandsToFile(Constants.BRAND_FILE);
            any = true;
        }
        if(carsChanged){
            saveCarsToFile(Constants.CAR_FILE);
            any = true;
        }
        if(!any) System.out.println(Messages.MSG_NOTHING_TO_SAVE);
    }

    public void saveBrandsToFile(String fileName){
        try(PrintWriter pw = new PrintWriter(new FileWriter(new File(fileName)))){
            for (Brand b : listBrand) {
                pw.printf("%s, %s, %s:%s\n",
                        b.getBrandId().trim(),
                        b.getBrandName().trim(),
                        b.getBrandSound().trim(),
                        formatPrice(b.getPrice()));
            }
            brandsChanged = false;
            System.out.println(Messages.MSG_SAVED_OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveCarsToFile(String fileName){
        try(PrintWriter pw = new PrintWriter(new FileWriter(new File(fileName)))){
            for (Car c : listCar) {
                pw.printf("%s, %s, %s, %s, %s\n",
                        c.getCarId().trim(),
                        c.getBrand().getBrandId().trim(),
                        c.getColor().trim(),
                        c.getFrameId().trim(),
                        c.getEngineId().trim());
            }
            carsChanged = false;
            System.out.println(Messages.MSG_SAVED_OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
    

    
    
    
}

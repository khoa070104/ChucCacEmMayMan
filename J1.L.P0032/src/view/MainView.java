package view;

import controller.Inputter;
import controller.ShowRoomManagement;
import common.Constants;
import common.Messages;

public class MainView {
    public static void main(String[] args) {
        ShowRoomManagement app = new ShowRoomManagement();
        app.readBrandFile(Constants.BRAND_FILE);
        app.readCarFile(Constants.CAR_FILE);

        while(true){
            System.out.println("\n==== Michael BMW Showroom ====");
            System.out.println("1. List all brands");
            System.out.println("2. Add a new brand");
            System.out.println("3. Search brand by ID");
            System.out.println("4. Update brand by ID");
            System.out.println("5. List brands with price <= input");
            System.out.println("6. List all cars (brand ASC, price DESC)");
            System.out.println("7. Search cars by partial brand name");
            System.out.println("8. Add a new car");
            System.out.println("9. Remove a car by ID");
            System.out.println("10. Update a car by ID");
            System.out.println("11. List all cars by color");
            System.out.println("12. Save data to files");
            System.out.println("13. Quit program");
            String choice = Inputter.inputRequired("Choose [1-13]: ");
            switch(choice){
                case "1": app.listAllBrands(); break;
                case "2": app.addNewBrand(); break;
                case "3": app.searchBrandById(); break;
                case "4": app.updateBrandById(); break;
                case "5": app.printBrandsByPrice(); break;
                case "6": app.listAllCarsSorted(); break;
                case "7": app.searchCarsByBrandNameLike(); break;
                case "8": app.addNewCar(); break;
                case "9": app.removeCar(); break;
                case "10": app.updateCar(); break;
                case "11": app.listCarsByColor(); break;
                case "12": app.saveAll(); break;
                case "13":
                    app.saveAll();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}



package view;

import controller.ShowroomManager;
import controller.Inputter;
import enums.Car;
import enums.Day;
import enums.Color;
import exception.ExceptionCar;
import common.Constants;
import common.Messages;

public class MainView {
    private ShowroomManager showroomManager;

    public MainView() {
        this.showroomManager = new ShowroomManager();
    }

    public void displayTitle() {
        System.out.println(Constants.PROGRAM_TITLE);
    }

    public void run() {
        displayTitle();
        
        while (true) {
            try {
                // Get car name
                String carName = Inputter.inputRequired(Messages.PROMPT_CAR_NAME);
                Car car = Car.getCar(carName);
                
                // Get color
                String colorName = Inputter.inputRequired(Messages.PROMPT_COLOR);
                Color color = Color.getColor(colorName);
                
                // Get price
                String priceStr = Inputter.inputRequired(Messages.PROMPT_PRICE);
                
                // Get day
                String dayName = Inputter.inputRequired(Messages.PROMPT_DAY);
                Day day = Day.getDay(dayName);
                
                // Validate and process
                try {
                    Car result = showroomManager.checkCar(car, colorName, day, priceStr);
                    System.out.println(Constants.SUCCESS_MESSAGE);
                } catch (ExceptionCar e) {
                    System.out.println("Can't sell Car — " + e.getMessage());
                }
                
                // Ask if user wants to continue
                String continueChoice = Inputter.inputOptional("Do you want to continue? (y/n): ");
                if (continueChoice.equalsIgnoreCase("n") || continueChoice.equalsIgnoreCase("no")) {
                    break;
                }
                
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        
        System.out.println(Messages.MSG_PROGRAM_EXIT);
    }
}

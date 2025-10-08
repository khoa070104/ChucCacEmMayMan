package view;

import java.util.List;
import controller.Inputter;
import controller.ManageEastAsiaCountries;
import model.EastAsiaCountries;
import common.Messages;
import common.Constants;

public class MainView {
    private ManageEastAsiaCountries manager;

    public MainView() {
        this.manager = new ManageEastAsiaCountries();
    }

    public void displayMenu() {
        System.out.println("\n===============================================");
        System.out.println("    MANAGE GEOGRAPHIC INFORMATION");
        System.out.println("===============================================");
        System.out.println("1. Input the information of 11 countries in East Asia");
        System.out.println("2. Display the information of countries you've just input");
        System.out.println("3. Search the information of country by user-entered name");
        System.out.println("4. Display the information of countries sorted by name in ascending order");
        System.out.println("5. Exit");
        System.out.println("===============================================");
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = Inputter.inputInt("Please select an option: ");
            
            switch (choice) {
                case 1:
                    inputCountryInformation();
                    break;
                case 2:
                    displayRecentlyEnteredInformation();
                    break;
                case 3:
                    searchCountryByName();
                    break;
                case 4:
                    displaySortedCountries();
                    break;
                case 5:
                    System.out.println(Messages.MSG_PROGRAM_EXIT);
                    return;
                default:
                    System.out.println(Messages.ERR_INVALID_CHOICE);
            }
        }
    }

    private void inputCountryInformation() {
        if (!manager.canAddMore()) {
            System.out.println("Maximum number of countries (" + Constants.MAX_COUNTRIES + ") has been reached!");
            return;
        }

        System.out.println("\n--- Input Country Information ---");
        String countryCode = Inputter.inputRequired("Enter code of country: ");
        String countryName = Inputter.inputRequired("Enter name of country: ");
        
        float totalArea;
        do {
            totalArea = Inputter.inputFloat("Enter total Area: ");
            if (totalArea <= 0) {
                System.out.println(Messages.ERR_TOTAL_AREA_INVALID);
            }
        } while (totalArea <= 0);
        
        String countryTerrain = Inputter.inputRequired("Enter terrain of country: ");

        try {
            EastAsiaCountries country = new EastAsiaCountries(countryCode, countryName, totalArea, countryTerrain);
            manager.addCountryInformation(country);
            System.out.println(Messages.MSG_COUNTRY_ADDED_SUCCESS);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayRecentlyEnteredInformation() {
        if (!manager.hasCountries()) {
            System.out.println(Messages.ERR_NO_COUNTRIES_ADDED);
            return;
        }

        System.out.println("\n--- Recently Entered Information ---");
        EastAsiaCountries recent = manager.getRecentlyEnteredInformation();
        if (recent != null) {
            displayTableHeader();
            recent.display();
        }
    }

    private void searchCountryByName() {
        if (!manager.hasCountries()) {
            System.out.println(Messages.ERR_NO_COUNTRIES_ADDED);
            return;
        }

        System.out.println("\n--- Search Country by Name ---");
        String searchName = Inputter.inputRequired("Enter the name you want to search for: ");
        
        EastAsiaCountries[] results = manager.searchInformationByName(searchName);
        
        if (results.length == 0) {
            System.out.println(Messages.ERR_COUNTRY_NOT_FOUND);
        } else {
            System.out.println(Messages.MSG_SEARCH_RESULTS);
            displayTableHeader();
            for (EastAsiaCountries country : results) {
                country.display();
            }
        }
    }

    private void displaySortedCountries() {
        if (!manager.hasCountries()) {
            System.out.println(Messages.ERR_NO_COUNTRIES_ADDED);
            return;
        }

        System.out.println("\n--- Countries Sorted by Name ---");
        EastAsiaCountries[] sortedCountries = manager.sortInformationByAscendingOrder();
        
        System.out.println(Messages.MSG_SORTED_RESULTS);
        displayTableHeader();
        for (EastAsiaCountries country : sortedCountries) {
            country.display();
        }
    }

    private void displayTableHeader() {
        System.out.println(Constants.TABLE_HEADER);
        System.out.println(Constants.TABLE_SEPARATOR);
    }
}

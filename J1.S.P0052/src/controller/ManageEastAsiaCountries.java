package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.EastAsiaCountries;
import common.Messages;
import common.Constants;

public class ManageEastAsiaCountries {
    private List<EastAsiaCountries> countries = new ArrayList<>();
    private EastAsiaCountries recentlyEnteredCountry;

    public void addCountryInformation(EastAsiaCountries country) {
        if (country.getTotalArea() <= 0) {
            throw new IllegalArgumentException(Messages.ERR_TOTAL_AREA_INVALID);
        }
        countries.add(country);
        recentlyEnteredCountry = country;
    }

    public EastAsiaCountries getRecentlyEnteredInformation() {
        return recentlyEnteredCountry;
    }

    public EastAsiaCountries[] searchInformationByName(String name) {
        List<EastAsiaCountries> results = new ArrayList<>();
        String searchName = name.toLowerCase().trim();
        
        for (EastAsiaCountries country : countries) {
            if (country.getCountryName().toLowerCase().contains(searchName)) {
                results.add(country);
            }
        }
        
        return results.toArray(new EastAsiaCountries[0]);
    }

    public EastAsiaCountries[] sortInformationByAscendingOrder() {
        List<EastAsiaCountries> sortedList = new ArrayList<>(countries);
        sortedList.sort(Comparator.comparing(EastAsiaCountries::getCountryName));
        return sortedList.toArray(new EastAsiaCountries[0]);
    }

    public List<EastAsiaCountries> getAllCountries() {
        return new ArrayList<>(countries);
    }

    public boolean hasCountries() {
        return !countries.isEmpty();
    }

    public int getCountryCount() {
        return countries.size();
    }

    public boolean canAddMore() {
        return countries.size() < Constants.MAX_COUNTRIES;
    }
}

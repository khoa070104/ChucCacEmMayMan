package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.FeastMenu;
import repository.MenuRepository;
import util.AppLogger;

public class MenuService {
    private final List<FeastMenu> menus;

    public MenuService(String fileName) {
        List<FeastMenu> loadedMenus;
        try {
            loadedMenus = new MenuRepository(fileName).load();
        } catch (IOException | RuntimeException exception) {
            AppLogger.log("Cannot load feast menu data", exception);
            loadedMenus = new ArrayList<>();
        }
        menus = loadedMenus;
    }

    public FeastMenu findByCode(String code) {
        if (code == null) return null;
        return menus.stream().filter(menu -> menu.getCode().equalsIgnoreCase(code.trim()))
                .findFirst().orElse(null);
    }

    public List<FeastMenu> getSortedMenus() {
        return menus.stream().sorted(Comparator.comparing(FeastMenu::getPrice)).toList();
    }

    public boolean isEmpty() {
        return menus.isEmpty();
    }
}

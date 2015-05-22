package edu.chl.blastinthepast.model.menu;

/**
 * Created by Shif on 22/05/15.
 */
public class InGameMenuModel {

    private int currentItem;
    private String[] menuItems;

    public InGameMenuModel() {
        currentItem = 0;
        menuItems = new String[]{"Continue", "Save game", "Options", "Exit to main menu"};
    }

    public int getCurrentItem() {
        return currentItem;
    }

    public String[] getMenuItems() {
        return menuItems;
    }

    public void moveUp() {
        if (currentItem > 0) {
            currentItem--;
        } else {
            currentItem = menuItems.length-1;
        }
    }

    public void moveDown() {
        if(currentItem < menuItems.length-1) {
            currentItem++;
        } else {
            currentItem = 0;
        }
    }

    public void resetCurrentItem() {
        currentItem = 0;
    }

}

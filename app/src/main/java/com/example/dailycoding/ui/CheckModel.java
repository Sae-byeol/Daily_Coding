package com.example.dailycoding.ui;

import java.io.Serializable;

public class CheckModel implements Serializable {
    private int itemName;
    private boolean isSelected;


    public CheckModel(int itemName, boolean isSelected) {
        this.itemName = itemName;
        this.isSelected = isSelected;

    }

    public int getItemName() {
        return itemName;
    }

    public void setItemName(int itemName) {
        this.itemName = itemName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

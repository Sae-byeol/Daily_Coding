package com.example.dailycoding.ui;

import java.io.Serializable;

public class CheckModel implements Serializable {
    private int itemName;
    private boolean isSelected;
    private int content;

    public CheckModel(int itemName, boolean isSelected,int content) {
        this.itemName = itemName;
        this.isSelected = isSelected;
        this.content=content;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
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

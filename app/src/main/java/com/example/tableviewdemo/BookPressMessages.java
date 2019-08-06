package com.example.tableviewdemo;

import java.util.List;

/**
 * Author    zhengchengbin
 * Describe：
 * Data:      2019/7/30 18:02
 * Modify by:
 * Modification date：
 * Modify content：
 */
public class BookPressMessages {

    private String pressName;

    private List<DevelopProgress> developProgressList;

    public BookPressMessages() {}

    public BookPressMessages(String pressName, List<DevelopProgress> developProgressList) {
        this.pressName = pressName;
        this.developProgressList = developProgressList;
    }

    public String getPressName() {
        return pressName;
    }

    public void setPressName(String pressName) {
        this.pressName = pressName;
    }

    public List<DevelopProgress> getDevelopProgressList() {
        return developProgressList;
    }

    public void setDevelopProgressList(List<DevelopProgress> developProgressList) {
        this.developProgressList = developProgressList;
    }
}

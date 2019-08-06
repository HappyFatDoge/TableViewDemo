package com.example.tableviewdemo;

import java.util.List;

/**
 * Author    zhengchengbin
 * Describe：
 * Data:      2019/7/30 17:59
 * Modify by:
 * Modification date：
 * Modify content：
 */
public class BookMessagesBean {

    // 书本开发进度信息
    private List<BookPressMessages> bookPressMessages;
    // 说明
    private String description;

    public BookMessagesBean(){}

    public BookMessagesBean(List<BookPressMessages> bookPressMessages, String description) {
        this.bookPressMessages = bookPressMessages;
        this.description = description;
    }

    public List<BookPressMessages> getBookPressMessages() {
        return bookPressMessages;
    }

    public void setBookPressMessages(List<BookPressMessages> bookPressMessages) {
        this.bookPressMessages = bookPressMessages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

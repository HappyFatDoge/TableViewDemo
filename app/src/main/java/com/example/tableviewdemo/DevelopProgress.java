package com.example.tableviewdemo;

/**
 * Author    zhengchengbin
 * Describe：
 * Data:      2019/7/29 9:34
 * Modify by:
 * Modification date：
 * Modify content：
 */
public class DevelopProgress {

    // 版本
    private String version;
    // 年级（开发进度）
    private String developProgress;

    public DevelopProgress() {}

    public DevelopProgress(String version, String developProgress){
        this.version = version;
        this.developProgress = developProgress;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDevelopProgress() {
        return developProgress;
    }

    public void setDevelopProgress(String developProgress) {
        this.developProgress = developProgress;
    }
}

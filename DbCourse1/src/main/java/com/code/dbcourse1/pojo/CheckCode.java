package com.code.dbcourse1.pojo;

public class CheckCode {
    private String checkCodeId;
    private byte[] image;


    public String  getCheckCodeId() {
        return checkCodeId;
    }

    public void setCheckCodeId(String checkCodeId) {
        this.checkCodeId = checkCodeId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

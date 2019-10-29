package com.ddq.pojo;

public class Source {
    /*
        定义一个bean，用于对桔汁进行抽象（建模）

     */
    private String fruit; // 类型
    private String sugar; // 糖
    private String size; // 大小杯

    public void setFruit(String fruit){
        this.fruit = fruit;
    }

    public String getFruit(){
        return fruit;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getSugar(){
        return sugar;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize(){
        return size;
    }
}

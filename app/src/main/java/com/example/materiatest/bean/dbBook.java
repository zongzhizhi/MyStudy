package com.example.materiatest.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class dbBook extends LitePalSupport {

    private int id;

    /*
    * 标记Name是否唯一 默认值为"书名" 当unique为true添加相同数据会报异常  添加失败
    * */
    @Column(unique = false, defaultValue = "书名")
    private String name;

    /*
    * 标记是否忽略author字段，忽略不创建此字段 true表示忽略
    * */
    @Column(ignore = false)
    private String author;

    /*
    * 标记是否为空
    * */
    @Column(nullable = false)
    private double price;

    private int pages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}

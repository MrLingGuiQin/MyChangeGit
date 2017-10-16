package com.linguiqing.mychanage.ui.db.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * ***************************************
 * statement: 书店(创建数据库的表名与字段)
 * auther: lingguiqin
 * date created : 2017/10/14 0014
 * ***************************************
 */

@Entity
public class Book {

    // Id不能用int类型，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
    @Id(autoincrement = true)
    private Long id; // 主键 图书id

    // 书名
    @Unique //该属性值必须在数据库中是唯一值
    private String name;
    //价格
    private String price;
    // 作者
    private String author;
    // 销量
    private int shell_num;
    //图片url
    private String image_url;

    @Generated(hash = 1046275600)
    public Book(Long id, String name, String price, String author, int shell_num,
            String image_url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.shell_num = shell_num;
        this.image_url = image_url;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", author='" + author + '\'' +
                ", shell_num=" + shell_num +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getShell_num() {
        return this.shell_num;
    }

    public void setShell_num(int shell_num) {
        this.shell_num = shell_num;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

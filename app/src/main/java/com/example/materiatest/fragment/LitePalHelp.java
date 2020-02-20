package com.example.materiatest.fragment;


import android.util.Log;
import android.widget.TextView;

import com.example.materiatest.bean.dbBook;

import org.litepal.LitePal;

import java.util.List;

/*
 *
 * LitePal框架操作数据库帮助类
 * 这是为了区分SQLiteDatabase操作
 * */
public class LitePalHelp {

    private static final String TAG = "LitePalHelp";

    private TextView tv_data;

    /*
     * 数据库添加数据
     * */
    public void addData() {

        dbBook dbBook = new dbBook();
        dbBook.setName("第一行代码");
        dbBook.setAuthor("郭霖");
        dbBook.setPages(453);
        dbBook.setPrice(23.8);
        dbBook.save();
        /*异步线程同步*/
//        dbBook.saveAsync();
        retrieve();
    }


    /*
     * 数据库删除数据
     * */
    public void deleteData() {
        /*
         * 第一种写法
         * 删除dbBook表中所有数据
         * */
//        LitePal.deleteAll(dbBook.class);

        /*
         * 第二种写法
         * 删除数据库dbBook表中id为1的记录
         * */
//        LitePal.delete(dbBook.class,1);

        /*
         * 第三种写法
         * 删除数据中价格大于10的数据
         * */
        LitePal.deleteAll(dbBook.class, "price > ?", "10");
        retrieve();
    }

    /*
     * 数据库数据修改
     * */
    public void change() {

        dbBook db;
        /*
         * 方法一
         * 修改ID为1行的数据
         * */
//        db = LitePal.find(com.example.materiatest.bean.dbBook.class, 1);
//        db.setPrice(0.01);
//        db.setPrice(0.01);
//        db.save();
        /*
         * 方法二
         * 直接更新id为1行的数据
         * */
//        db = new dbBook();
//        db.setPages(400);
//        db.setPrice(0.01);
//        db.update(1);
        /*
         * 方法三
         *将name=第一行代码",并且author="郭霖"的列数据修改为
         * db数据
         * */
        db = new dbBook();
        db.setAuthor("任玉刚");
        db.setName("Android艺术探索");
        db.setPages(233);
        db.setPrice(0.01);
        db.updateAll("name = ? and author = ?", "第一行代码", "郭霖");
        /*
         * 第四种方法
         * 设置数据的默认值
         * */
//        db = new dbBook();
//        db.setToDefault("pages");
//        db.updateAll();

        retrieve();
    }

    /*
     * 查询数据库数据
     * */
    public void retrieve() {
        //①：查询表中所有的记录，返回的是泛型为dbBook的List集合
        List<dbBook> dbBookList = LitePal.findAll(dbBook.class);
        StringBuilder builder = new StringBuilder();
        for (dbBook db : dbBookList) {
            builder
                    .append(" Nmae = ").
                    append(db.getName())
                    .append(" pages = ")
                    .append(db.getPages())
                    .append(" Price = ")
                    .append(db.getPrice())
                    .append(" author = ")
                    .append(db.getAuthor())
                    .append("\n");
        }
        tv_data.setText(builder.toString());
//        //②：查找表id为1的记录
//        dbBook dbBook = LitePal.find(dbBook.class, 1);
//
//        //③：获取表中的第一条数据与最后一条数据
//        dbBook firstNews = LitePal.findFirst(dbBook.class);
//        dbBook latNews = LitePal.findLast(dbBook.class);
//
//        //④：查询表中的第5、10、15条数据
//        List<dbBook> newsList1 = LitePal.findAll(dbBook.class, 5, 10, 15);
//        //或者定义一个数组
//        long[] ids = new long[]{5, 10, 15};
//        List<dbBook> newsList2 = LitePal.findAll(dbBook.class, ids);
//
//        //⑤：查找title为张三的记录,并且以时长作排序(按时间desc倒序  asc 正序)，where()方法接收任意个字符串参数，
//        //其中第一个参数用于进行条件约束，从第二个参数开始，都是用于替换第一个参数中的占位符的。那这个where()方法就对应了一条SQL语句中的where部分。
//        List<dbBook> movies = LitePal.where("title = ?", "张三").
//                order("time desc").find(dbBook.class);
//        //将查询出的新闻按照发布的时间倒序排列，只要title和content这两列数据，即最新发布的新闻放在最前面，那就可以这样写：
//        List<dbBook> newsList = LitePal.select("title", "content")
//                .where("title > ?", "张三")
//                .order("time desc").find(dbBook.class);
//        //设置查询的数量与偏移量
//        List<dbBook> dbBooks = LitePal.select("title", "content")
//                .where("title > ?", "张三")
//                .order("time desc")
//                .limit(30)//只查询前面30条
//                .offset(20)//分页查询，每次查20条
//                .find(dbBook.class);
    }

    public void setTextView(TextView tv_data) {
        this.tv_data = tv_data;
    }
}

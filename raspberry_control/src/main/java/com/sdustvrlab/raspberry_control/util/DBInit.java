package com.sdustvrlab.raspberry_control.util;

import java.io.InputStream;
import java.util.Properties;

public class DBInit {
    public static Properties initDB(){
        Properties pro=new Properties();
        try {
            Class c=DBInit.class;
            ClassLoader cl=c.getClassLoader();//类加载器
            InputStream in=cl.getResourceAsStream("application.properties");
            pro.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

//public static void main(String[] args){
//Properties pro=DBInit.initDB();
//String value=pro.getProperty("url");
//System.out.println(value);
//}
}

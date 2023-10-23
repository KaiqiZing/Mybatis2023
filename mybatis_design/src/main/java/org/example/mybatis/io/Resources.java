package org.example.mybatis.io;

import java.io.InputStream;

public class Resources {
    /**
     * 根据传入的参数，获取一个字节输入流
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
        /*
        *  //Resources.class 获取到类的字节码
        //Resources.class.getClassLoader() 获取字节码的类的加载器
        //Resources.class.getClassLoader().getResourceAsStream(filePath) 根据类加载获取到配置信息
        * */
    }
}

/*
getResourceAsStream() 方法是Java中的一个系统方法，它的作用是从jar包中读取资源文件并返回一个输入流。

这个方法的第一个参数是资源文件的名称，第二个参数是资源文件的类型。
资源文件的名称可以是相对路径，也可以是绝对路径。
资源文件的类型可以是以下几种：
    "java.io.InputStream"：表示资源文件是一个输入流。
    "java.lang.String"：表示资源文件是一个字符串。
    "java.util.Properties"：表示资源文件是一个属性文件。

 */

// IMyAidlInterface.aidl
package com.czy.binderstudy;

// 这里应用必须要有包名
import com.czy.binderstudy.Book;

interface IMyAidlInterface {

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     * 一些基本类型
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    // 不能重载方法, 不然编译不通过
    // void basicTypes(int anInt);

    int getPid();

    void addBook(in Book book);

    Book getBook();
}

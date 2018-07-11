package test.com.mylibrary.entity;

/**
 * Created by 任聪 on 2018/7/11.
 */

public class Param {
    public String key;
    public String value;
    public Param(){}

    public Param(String key, String value) {
        this.key = key;
        this.value = value!=null?value:"";
    }
}

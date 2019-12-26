package com.github.houbb.checksum.model;

import com.github.houbb.checksum.annotation.CheckField;
import com.github.houbb.checksum.annotation.Checksum;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class User {

    @CheckField
    private String name;

    @CheckField
    private String password;

    private String address;

    @Checksum
    private String checksum;

    public String name() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", checksum='" + checksum + '\'' +
                '}';
    }


    /**
     * 构建示例对象
     * @return 构建示例对象
     */
    public static User buildUser() {
        User user = new User();
        user.setName("ryo");
        user.setPassword("1234");
        user.setAddress("china");

        return user;
    }
}

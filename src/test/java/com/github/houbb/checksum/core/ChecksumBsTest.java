package com.github.houbb.checksum.core;

import com.github.houbb.checksum.model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class ChecksumBsTest {

    @Test
    public void checksumTest() {
        User user = User.buildUser();
        final String checksum = ChecksumBs
                .newInstance(user)
                .checksum();

        Assert.assertEquals("8D62F2BC49A9AB51280C8F42A483ED54", checksum);
    }

    @Test
    public void fillTest() {
        User user = User.buildUser();
        ChecksumBs.newInstance(user).fill();

        Assert.assertEquals("User{name='ryo', password='1234', address='china', checksum='8D62F2BC49A9AB51280C8F42A483ED54'}",
                user.toString());
    }

}

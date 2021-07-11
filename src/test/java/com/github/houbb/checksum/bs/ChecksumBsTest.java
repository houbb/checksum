package com.github.houbb.checksum.bs;

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
                .newInstance()
                .target(user)
                .checkValue();

        Assert.assertEquals(user.buildChecksum(), checksum);
        System.out.println(checksum);
    }

    @Test
    public void fillTest() {
        User user = User.buildUser();
        ChecksumBs.newInstance().target(user).fill();

        Assert.assertEquals("User{name='ryo', password='1234', address='china', checksum='5A2A21025E5232FBFD8BFB2A08DE2A01'}",
                user.toString());
    }

}

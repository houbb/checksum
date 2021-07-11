package com.github.houbb.checksum.util;

import com.github.houbb.checksum.model.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class ChecksumHelperTest {

    @Test
    public void checksumTest() {
        User user = User.buildUser();
        final String checksum = ChecksumHelper.checkValue(user);

        Assert.assertEquals(user.buildChecksum(), checksum);
        System.out.println(checksum);
    }

    @Test
    public void fillTest() {
        User user = User.buildUser();
        ChecksumHelper.fill(user);

        Assert.assertEquals("User{name='ryo', password='1234', address='china', checksum='5A2A21025E5232FBFD8BFB2A08DE2A01'}",
                user.toString());
    }

}

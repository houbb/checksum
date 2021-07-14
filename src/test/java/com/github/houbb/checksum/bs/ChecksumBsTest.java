package com.github.houbb.checksum.bs;

import com.github.houbb.checksum.model.User;
import com.github.houbb.checksum.support.cache.CheckFieldListCache;
import com.github.houbb.checksum.support.cache.CheckValueCache;
import com.github.houbb.checksum.support.checksum.DefaultChecksum;
import com.github.houbb.hash.core.hash.Hashes;
import com.github.houbb.sort.core.api.Sorts;
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
                .charset("UTF-8")
                .checkSum(new DefaultChecksum())
                .sort(Sorts.quick())
                .hash(Hashes.md5())
                .times(1)
                .salt(null)
                .checkFieldListCache(new CheckFieldListCache())
                .checkValueCache(new CheckValueCache())
                .checkValue();

        Assert.assertEquals(user.buildCheckValue(), checksum);
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

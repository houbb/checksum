package com.github.houbb.checksum.benchmark;

import com.github.houbb.checksum.bs.ChecksumBs;
import com.github.houbb.checksum.model.User;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.2
 */
@Ignore
public class BenchmarkTest {

    /**
     * 反射设置
     * @since 0.0.2
     * v0.0.1 Cost Mills: 3350
     * v0.0.2 Cost Mills: 2927
     */
    @Test
    public void fillTest() {
        long startMills = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            User user = User.buildUser();
            ChecksumBs.newInstance().target(user).fill();
        }
        long endMills = System.currentTimeMillis();
        System.out.println("Cost Mills: " + (endMills - startMills));
    }

    /**
     * 手动设置
     *
     * Cost Mills: 2505
     * @since 0.0.2
     */
    @Test
    public void handTest() {
        long startMills = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            User user = User.buildUser();
            setCheckSum(user);
        }
        long endMills = System.currentTimeMillis();
        System.out.println("Cost Mills: " + (endMills - startMills));
    }

    /**
     * 设置校验
     * @param user 用户信息
     * @since 0.0.2
     */
    private void setCheckSum(User user) {
        String checksum =  user.buildCheckValue();
        user.setChecksum(checksum);
    }

}

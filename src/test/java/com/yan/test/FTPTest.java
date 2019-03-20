package com.yan.test;

import com.yan.util.Utils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Package ：com.yan.test
 * Description：
 * date： 2019/3/13 下午9:34
 * author： Li KaiYan
 */

public class FTPTest {
    @Test
    public void testFTP() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/Users/yan/Downloads/Cool-Gifts.gif");
        Utils.uploadFile("myPic", fileInputStream);
    }
}

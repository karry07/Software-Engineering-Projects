package com.utils;

import java.util.Random;

public class CommonUtil {
    /**
     * 获取随机字符串
     * @param num 指定生成的随机字符串的长度
     * @return 返回生成的随机字符串
     */
    public static String getRandomString(Integer num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789"; // 可选字符集
        Random random = new Random(); // 创建随机对象
        StringBuffer sb = new StringBuffer(); // 创建字符串缓冲区对象
        for (int i = 0; i < num; i++) { // 循环指定的次数
            int number = random.nextInt(base.length()); // 生成随机索引
            sb.append(base.charAt(number)); // 在字符集中获取字符并添加到字符串缓冲区
        }
        return sb.toString(); // 返回生成的随机字符串
    }
}

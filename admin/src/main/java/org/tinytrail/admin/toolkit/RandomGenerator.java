package org.tinytrail.admin.toolkit;

import java.security.SecureRandom;

/**
 * 分组ID 随机生成器
 */
public final class RandomGenerator {

    // 定义可以使用的字符集合
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    // 私有化构造方法，防止实例化工具类
    private RandomGenerator() {}

    /**
     * 生成指定长度的随机字符串
     *
     * @param length 生成ID 长度
     * @return 分组ID
     */
    public static String generateRandom(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    // 生成默认长度为6的随机字符串
    public static String generateRandom() {
        return generateRandom(6);
    }
}

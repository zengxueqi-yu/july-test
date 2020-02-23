package com.july.test.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.july.test.config.Constants;
import com.july.test.entity.UserInfo;
import org.apache.commons.codec.binary.Hex;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zqk
 * @since 2020/1/22
 */
public class ToolUtil {

    private static SecureRandom random = new SecureRandom();
    private static final String SHA1 = "SHA-1";

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    public static void entryptPassword(String password) {
        byte[] salt = generateSalt(Constants.SALT_SIZE);
        byte[] hashPassword = sha1(password.getBytes(), salt, Constants.HASH_INTERATIONS);
        System.out.println("salt===> " + encodeHex(salt) + ",password===> " + encodeHex(hashPassword));
    }

    /**
     * 生成随机的Byte[]作为salt.
     *
     * @param numBytes byte数组的大小
     */
    public static byte[] generateSalt(int numBytes) {
        //Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, SHA1, salt, iterations);
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     */
    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw unchecked(e);
        }
    }

    /**
     * 将CheckedException转换为UncheckedException.
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * Hex编码.
     */
    public static String encodeHex(byte[] input) {
        return new String(Hex.encodeHex(input));
    }

    private static final String TOKEN_STR = "token";
    private static final String SIGNAL_HEAD = "Bearer ";

    /**
     * 生成Token
     * @param user
     * @return java.lang.String
     * @author zqk
     * @since 2020/2/7 1:33 下午
     */
    public static String getToken(UserInfo user) {
        Map<String,String> map = new HashMap<>();
        map.put("id",user.getId().toString());
        map.put("username",user.getUsername());
        String token="";
        token= JWT.create().withAudience(JSON.toJSONString(map))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public static String decodeToken(String token) {
        String jwtStr = token.replace(SIGNAL_HEAD, "").trim();
        DecodedJWT decodedJWT = JWT.decode(jwtStr);
        Map<String, Claim> claims = decodedJWT.getClaims();
        if (decodedJWT == null) {
            throw new BnException(-1000, "加密解析失败");
        }
        Claim claim = decodedJWT.getClaims().get(TOKEN_STR);
        if (claims == null) {
            throw new BnException(-1000, "加密解析失败");
        }
        return claims.get("aud").asString();
    }

}

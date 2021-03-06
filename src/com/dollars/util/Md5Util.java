package com.dollars.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5����
 * @author tom
 * 2015.12.15
 */
public class Md5Util {

	public static String md5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            //����
        }
        return result;
    }
}

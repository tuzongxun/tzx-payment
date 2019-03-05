package cn.tzxcode.pay.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * md5加解密
 */
@Slf4j
public class MD5 {
		 
	/**
	 * 将字节数组算出16进制MD5串
	 * @param origin 字节数组
	 * @return 16进制MD5字符串
	 */
	public static String encode16(byte[] origin)
	{
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString =BytesToString.byteArrayToHexString(md.digest(origin));
		}
		catch (Exception e) {
			log.error("{}",e);
		}
		return resultString;
	}
	
}


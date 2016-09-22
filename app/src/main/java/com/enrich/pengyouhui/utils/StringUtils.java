package com.enrich.pengyouhui.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	private static DecimalFormat decimalFormat;
	/**
	 * 将Map<String,String>类型转化为 JSON字符串
	 * 
	 * @param map
	 * @return
	 */
	public static String parseMapToJSONStr(Map<String, String> map) {
		if (map != null) {
			String jsonStr = "{";
			for (Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				jsonStr += "\"" + key + "\":\"" + value + "\",";
			}
			if (jsonStr.length() > 1) {
				jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
			}
			return jsonStr + "}";
		} else {
			return "";
		}
	}
	
	 /**
     * @param aStr
     * @param bStr
     * @return 判断字符串是否相等
     */
    public static boolean isEqual(String aStr, String bStr) {
        if (aStr == null || bStr == null) {
            return false;
        }
        return (aStr.compareTo(bStr) == 0);
    }

    /**
     * @param inStr
     * @return 判断是否是空的字符串
     */
    public static boolean isEmptyString(String inStr) {
        return (inStr == null) || (inStr.length() == 0);
    }

	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean isPhoneNumberValid(String phoneNumber) {
		if (phoneNumber == null || isEmpty(phoneNumber))
			return false;
		if (phoneNumber.trim().length() != 11 || !phoneNumber.startsWith("1")) {
			return false;
		}
		return true;
	}

	public static boolean isMobileNumber(String mobiles) {
		Pattern p = Pattern
				.compile("[1][358]\\d{9}");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static Boolean isNotEmpty(String contant) {
		if (contant == null || contant.equals("")) {
			return false;
		}
		return true;
	}
	
	public static boolean isEmpty(CharSequence str){
		return TextUtils.isEmpty(str) || String.valueOf(str).trim().length() == 0;
	}

	public static boolean emailFormat(String email) {
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	public static String md5(String password) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
					password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}
	
	
	public static String pudgeScore(int score){
		int level  = 0 ;
		int num = 0 ;
		if (score<2191) {
			level = 1 ;
		}else if (score < 6571) {
			level = 2 ;
		}else {
			level = 3 ;
		}
		
		switch (level) {
		case 1:
			if (score <= 438) {
				num = 1;
			}else if (score <= 877) {
				num = 2;
			}else if (score <= 1315) {
				num = 3;
			}else if (score <= 1753) {
				num = 4;
			}else {
				num = 5;
			}
			
			break;
		case 2:
			
			if (score <= 3067) {
				num = 1;
			}else if (score <= 3943) {
				num = 2;
			}else if (score <= 4819) {
				num = 3;
			}else if (score <= 5695) {
				num = 4;
			}else {
				num = 5;
			}
			break;
		case 3:
	
			if (score <= 7885) {
				num = 1;
			}else if (score <= 9199) {
				num = 2;
			}else if (score <= 10513) {
				num = 3;
			}else if (score <= 11827) {
				num = 4;
			}else {
				num = 5;
			}
			break;

		default:
			break;
		}
		
		return level + "_" +num;
		
	}

	public static String splitString(String source, int length){
		StringBuffer sb = new StringBuffer();
		if (source.length() < length){
			sb.append(source);
			for (int i = 0; i < length - source.length(); i ++){
				sb.append("0");
			}
			return sb.toString();
		} else if (source.length() == length){
			return source;
		} else {
			return source.substring(0, 10);
		}
	}

	/**
	 * 格式化小数位
	 * @param number 要格式化的数
	 * @param decimal 保留的小数位
	 * @return
	 */
	public static String decimalString(double number, int decimal) {
		if (decimalFormat == null)
			decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(decimal);
		return decimalFormat.format(number);
	}

}

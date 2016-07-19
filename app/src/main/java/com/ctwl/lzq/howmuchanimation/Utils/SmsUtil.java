package com.ctwl.lzq.howmuchanimation.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsUtil {

	/**
	 * 从短信字符窜提取验证码
	 * 
	 * @param body
	 *            短信内容
	 * @param YZMLENGTH
	 *            验证码的长度 一般6位或者4位
	 * @return 接取出来的验证码
	 */
	public static String getyzm(String body, int YZMLENGTH) {
		// 首先([a-zA-Z0-9]{YZMLENGTH})是得到一个连续的六位数字字母组合
		// (?<![a-zA-Z0-9])负向断言([0-9]{YZMLENGTH})前面不能有数字
		// (?![a-zA-Z0-9])断言([0-9]{YZMLENGTH})后面不能有数字出现

		// 获得数字字母组合
		// Pattern p = Pattern .compile("(?<![a-zA-Z0-9])([a-zA-Z0-9]{" +
		// YZMLENGTH + "})(?![a-zA-Z0-9])");

		// 获得纯数字
		Pattern p = Pattern.compile("(?<![0-9])([0-9]{" + YZMLENGTH
				+ "})(?![0-9])");

		Matcher m = p.matcher(body);
		if (m.find()) {
			System.out.println(m.group());
			return m.group(0);
		}
		return null;
	}

	/**
	 * 判断手机号是否正确
	 * 
	 * @param phoneNumber
	 * @return 是否正确
	 */
	public static boolean patternPhoneNumber(String phoneNumber) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(14[0-9])|(17[0-9]))\\d{8}$");
		Matcher m = p.matcher(phoneNumber);

		return m.matches();
	}
}

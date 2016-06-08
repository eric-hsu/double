/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 */

package com.kingdee.ebank.util.sign;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public final class Base64 {
	public static String encode(byte[] bytes) {
        return (new BASE64Encoder()).encodeBuffer(bytes);
    }
    
    public static byte[] decode(String str) throws Exception{
    	return (new BASE64Decoder()).decodeBuffer(str);
    }
}

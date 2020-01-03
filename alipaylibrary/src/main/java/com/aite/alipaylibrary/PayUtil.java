package com.aite.alipaylibrary;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PayUtil {
    /**
     * 获取随机字符串
     *
     * @return 2016年8月4日 上午9:26:07
     * @author Xuehao
     */
    public static String create_nonce_str() {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.replaceAll("\\-", "").toUpperCase();
    }

    /**
     * 获取时间戳
     *
     * @return 2016年8月4日 上午9:26:20
     * @author Xuehao
     */
    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


    /**
     * 构造签名
     *
     * @param params
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        return temp.toString();
    }

    /**
     * 构造签名
     *
     * @param params
     * @param paternerKey
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String Sign(Map<String, String> params, String paternerKey) throws UnsupportedEncodingException {
        String string1 = createSign(params, false);
        String stringSignTemp = string1 + "&key=" + paternerKey;
        //String signValue = MD5Tools.encode(stringSignTemp).toUpperCase();

        String signValue = MD5Util.MD5Encode(stringSignTemp,"UTF-8").toUpperCase();
        return signValue;
    }
}

package com.example.app;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * 创建时间 2019/12/27 17:27
 * 描述:
 */
public class Constants {

    public static String BASE_URL = "http://zhongbyi.aitecc.com/";

    public static int PAGE_SIZE = 10;

    public static String LANG = "zh_cn";


    public static String DoctorType1 = "主任医师";

    public static String DoctorType2 = "副主任医师";

    public static String DoctorType3 = "主治医师";

    public static String DoctorType4 = "医师";

    public static String TEXTHINT = "1、自医生回复起咨询有效期为24小时，在一次咨询周期中可与医生进行不限条数的交流\n" +
            "2、自成功购买起24小时内，如医生未回复自动全额退款。";

    private static final int OPTIONS_TEXT = 1407;

    private static final int OPTIONS_EDIT = 1408;

    private static final int OPTIONS_IMAGE = 1409;

    private static final int OPTIONS_CHIP = 1410;

    private static final int OPTIONS_CHECK = 1411;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({OPTIONS_TEXT,OPTIONS_EDIT,OPTIONS_IMAGE,OPTIONS_CHIP,OPTIONS_CHECK})
    public @interface OptionsType{
        int TEXT = OPTIONS_TEXT;
        int EDIT = OPTIONS_EDIT;
        int IMAGE = OPTIONS_IMAGE;
        int CHIP = OPTIONS_CHIP;
        int CHECK = OPTIONS_CHECK;
    }

}

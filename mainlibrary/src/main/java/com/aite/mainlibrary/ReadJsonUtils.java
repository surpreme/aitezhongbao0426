package com.aite.mainlibrary;

import android.content.Context;

import com.aite.mainlibrary.Mainbean.ConsultBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：Administrator
 * 时间 : 2019/7/4 0004
 * 描述 ：解析本地 json文件
 */
public class ReadJsonUtils {


    public static String getJson(Context context, String jsonNanm) {
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(context.getAssets().open(jsonNanm), "UTF-8");

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;

            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<ConsultBean> getBean(String json, String iconName) {
        JSONObject jsonObject = null;
        List<ConsultBean> data = new ArrayList<>();
        try {
            jsonObject = new JSONObject(json);
            JSONArray students = jsonObject.getJSONArray("testData");
            for (int i = 0; i < students.length(); i++) {
                ConsultBean bean = new ConsultBean();
                bean.setPic(iconName + i);
                bean.setType(students.getJSONObject(i).getString("type"));
                bean.setPrice(students.getJSONObject(i).getString("price"));
                bean.setConsultTime(students.getJSONObject(i).getString("consultTime"));
                data.add(bean);
            }

            return data;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

package com.aite.mainlibrary.Mainbean;

import com.google.gson.annotations.SerializedName;
import com.lzy.basemodule.bean.ErrorBean;

import java.io.Serializable;

public class TwoSuccessCode2Bean extends ErrorBean implements Serializable {

   private String url;

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }
}

package com.aite.mainlibrary.activity.allsetting.editSosUser;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import me.goldze.mvvmhabit.base.BaseModel;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-11
 * @desc:
 */
public final class MvvMViewAdapter extends BaseModel {

    @BindingAdapter(value = {"imageUrl", "imagePlaceholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholderRes))
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        }
    }

}

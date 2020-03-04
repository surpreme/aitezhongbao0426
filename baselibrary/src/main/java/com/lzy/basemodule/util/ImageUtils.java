package com.lzy.basemodule.util;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import com.lzy.basemodule.BaseConstant.BaseConstant;
import com.lzy.basemodule.logcat.LogUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    private static ImageUtils mInstance;

    public static ImageUtils getmInstance() {
        if (mInstance == null) {
            synchronized (ImageUtils.class) {
                if (mInstance == null) {
                    mInstance = new ImageUtils();
                }
            }
        }
        return mInstance;
    }

    public static void photoClip(Activity activity, Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        /**
         * 上述方法中，裁剪后的图片通过Intent的putExtra("return-data",true)方法进行传递，miui系统问题就出在这里，return-data的方式只适用于小图，miui系统默认的裁剪图片可能裁剪得过大，或对return-data分配的资源不足，造成return-data失败。
         * ————————————————
         */
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE_CLIP);
    }

    public static void zoomPhoto(File inputFile, File outputFile,Activity activity) {
        File parentFile = outputFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setDataAndType(getImageContentUri(activity, inputFile), "image/*");
        } else {
            intent.setDataAndType(Uri.fromFile(inputFile), "image/*");
        }
        intent.putExtra("crop", "true");

        //设置剪裁图片宽高比
        intent.putExtra("mAspectX", 1);
        intent.putExtra("mAspectY", 1);

        //设置剪裁图片大小
        intent.putExtra("mOutputX", 200);
        intent.putExtra("mOutputY", 200);

        //  是否返回uri
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        activity.startActivityForResult(intent,  BaseConstant.RESULT_CODE.REQUEST_CODE_CHOOSE_IMAGE_CLIP);
    }
    /**
     * 安卓7.0裁剪根据文件路径获取uri
     */
    private static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=?  ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
    public static void saveBitmap(Context context, Bitmap bitmap, String name) {
        File file = new File(Environment.getExternalStorageDirectory() + "/" + name + ".png");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            //压缩图片，如果要保存png，就用Bitmap.CompressFormat.PNG，要保存jpg就用Bitmap.CompressFormat.JPEG,质量是100%，表示不压缩
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogUtils.e("保存bitmp--路径找不到" + e);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e("保存bitmp--错误" + e);

        }
        //通知相册更新
        MediaStore.Images.Media.insertImage(context.getContentResolver(),
                bitmap, file.toString(), null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        LogUtils.e("保存bitmp--成功");


    }
}

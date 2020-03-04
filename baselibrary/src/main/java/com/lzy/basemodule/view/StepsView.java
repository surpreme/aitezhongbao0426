package com.lzy.basemodule.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lzy.basemodule.R;
import com.lzy.basemodule.util.SystemUtil;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * description: 自定义签到View.
 */
public class StepsView extends View {

    private int width, height;
    private int monthDays = 31;//本月有31天
    private Paint paint;
    private RectF oval = new RectF();
    private float strokeWidth = 10;
    private Bitmap checkBitmap, uncheckBitmap, closeGiftBitmap, openGiftBitmap;
    /**
     * 中间线 以及背景线
     */
    private int backColor = Color.parseColor("#DDDDDD"),
            rashColor = Color.parseColor("#DDDDDD"),
            textColor = Color.parseColor("#EEEEEE");
    private List<Bitmap> bitmapList = new LinkedList<>();
    private int signInCount = 9;

    public StepsView(Context context) {
        this(context, null);
    }

    public StepsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setAntiAlias(true);
        //控制线的宽度
        strokeWidth = SystemUtil.dip2px(context, 15);
        checkBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.sign_2x);
        uncheckBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.sign_un2x);
        closeGiftBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.sign_un2x);
        openGiftBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.sign_2x);
        //控制图的大小
//        checkBitmap = BitmapUtils.setBitmapSize(new WeakReference<Context>(context), R.mipmap.sign_2x, 31, 31, false);
//        uncheckBitmap = BitmapUtils.setBitmapSize(new WeakReference<Context>(context), R.mipmap.sign_un2x, 31, 31, false);
//         = BitmapFactory.decodeResource();
//        closeGiftBitmap = BitmapUtils.setBitmapSize(new WeakReference<Context>(context), R.mipmap.sign_un2x, 31, 31, false);

//        closeGiftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.superthings);
//        openGiftBitmap = BitmapUtils.setBitmapSize(new WeakReference<Context>(context), R.mipmap.sign_2x, 31, 31, false);

//        openGiftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fly);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设置本月天数
     *
     * @param monthDays
     */
    public void setMonthDays(int monthDays) {
        this.monthDays = monthDays;
        if (monthDays == 0) {
            this.monthDays = 31;
        }
        postInvalidate();
    }

    /**
     * 设置一共签到了几天
     *
     * @param days
     */
    public void setProgress(int days) {
        this.signInCount = days;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //一行有7个
        paint.setColor(backColor);
        paint.setStrokeWidth(strokeWidth);
        int rowCount = (monthDays % 5 == 0 ? monthDays / 5 : monthDays / 5 + 1);
        int rowHeigh = height / (rowCount);
        int startX = 0 + rowHeigh / 2;
        int endX = width - rowHeigh / 2;
        int days = 0;

        for (int a = 0; a < rowCount; a++) {
            if (a + 1 == rowCount) {
                endX = (endX - startX) / 5 * (monthDays % 5 == 0 ? 5 : (monthDays % 5)) + checkBitmap.getWidth() / 2;
            }
            paint.setStrokeWidth(strokeWidth);
            int y = rowHeigh * a + rowHeigh / 2;
            canvas.drawLine(startX, y, endX, y, paint);

            paint.setColor(rashColor);
            paint.setStrokeWidth(1);
            canvas.drawLine(startX, y, endX, y, paint);
            // 这里是来判断，是否需要画出左半边还是右半边的半圆弧度？
            if (a % 2 != 0) {
                if (a + 1 != rowCount) {
                    drawLeftOrRightArc(true, canvas, 0 + strokeWidth, y, 0 + rowHeigh + strokeWidth, y + rowHeigh);
                }
            } else {
                if (a + 1 != rowCount) {
                    drawLeftOrRightArc(false, canvas, endX - rowHeigh / 2 - strokeWidth, y, endX + rowHeigh / 2 - strokeWidth, y + rowHeigh);
                }
            }

            // 这里是来判断，本次这根线上画出的礼物的点，以及顺序是顺画，还是倒画出。
            bitmapList.clear();
            int lastDay = (monthDays % 5) == 0 ? 5 : (monthDays % 5);
//            for (int b = 0; b < (a + 1 == rowCount ? (lastDay) : 7); b++) {
//                days++;
//                if (days <= signInCount) {
//                    if (days == 3 || days == 8 || days == 14 || days == 21 || days == monthDays) {
//                        bitmapList.add(a % 2 != 0 ? 0 : bitmapList.size(), openGiftBitmap);
//                    } else {
//                        bitmapList.add(a % 2 != 0 ? 0 : bitmapList.size(), checkBitmap);
//                    }
//                } else {
//                    if (days == 3 || days == 8 || days == 14 || days == 21 || days == monthDays) {
//                        bitmapList.add(a % 2 != 0 ? 0 : bitmapList.size(), closeGiftBitmap);
//                    } else {
//                        bitmapList.add(a % 2 != 0 ? 0 : bitmapList.size(), uncheckBitmap);
//                    }
//                }
//            }
            for (int b = 0; b < (a + 1 == rowCount ? (lastDay) : 5); b++) {
                days++;
                if (days <= signInCount) {
                    if (days == 20) {
                        bitmapList.add(a % 2 != 0 ? 0 : bitmapList.size(), openGiftBitmap);
                    } else {
                        bitmapList.add(a % 2 != 0 ? 0 : bitmapList.size(), checkBitmap);
                    }
                } else {
                    if (days == 20) {
                        bitmapList.add(a % 2 != 0 ? 0 : bitmapList.size(), closeGiftBitmap);
                    } else {
                        bitmapList.add(a % 2 != 0 ? 0 : bitmapList.size(), uncheckBitmap);
                    }
                }
            }

            drawImgs(bitmapList, startX, endX, y, canvas);
        }
        super.onDraw(canvas);
    }

    /**
     * 画出的按路线上的图片，勾选，礼物
     *
     * @param bitmapList
     * @param startX
     * @param endX
     * @param y
     * @param canvas
     */
    private void drawImgs(List<Bitmap> bitmapList, float startX, float endX, float y, Canvas canvas) {
        if (!bitmapList.isEmpty()) {
            startX = startX - bitmapList.get(0).getWidth() / 2;
            int count = bitmapList.size();
            float bitmap_width = (endX - startX) / (count - 1);
            for (int a = 0; a < count; a++) {
                Bitmap bitmap = bitmapList.get(a);
                canvas.drawBitmap(bitmap, startX + (bitmap_width * a), y - bitmap.getHeight() / 2, paint);
            }
        }
    }

    /**
     * 这里画出左边半圆弧，还是右边半圆弧
     *
     * @param isLeft
     * @param canvas
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    private void drawLeftOrRightArc(boolean isLeft, Canvas canvas, float left, float top, float right, float bottom) {
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(backColor);

        if (isLeft) {
            paint.setStyle(Paint.Style.STROKE);
            oval.setEmpty();
            oval.set(left, top, right, bottom);
            canvas.drawArc(oval, 90, 180, false, paint);
            paint.setStrokeWidth(6);
            paint.setColor(rashColor);
            canvas.drawArc(oval, 90, 180, false, paint);
        } else {
            paint.setStyle(Paint.Style.STROKE);
            oval.setEmpty();
            oval.set(left, top, right, bottom);
            canvas.drawArc(oval, 270, 180, false, paint);

            paint.setStrokeWidth(6);
            paint.setColor(rashColor);

            canvas.drawArc(oval, 270, 180, false, paint);
        }
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(backColor);
    }
}

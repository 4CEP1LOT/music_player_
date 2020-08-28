package com.kotlin.lib_common_ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.util.AttributeSet;
import android.view.View;

import com.kotlin.lib_common_ui.R;

import java.util.ArrayList;
import java.util.List;

public class SpreadView extends View {

    private Paint centerPaint; //中心圆paint
    private int radius = 100; //中心圆半径
    private Paint spreadPaint; //扩散圆paint
    private float centerX;//圆心x
    private float centerY;//圆心y
    private int distance = 5; //每次圆递增间距
    private int maxRadius = 80; //最大圆半径
    private int delayMilliseconds = 33;//扩散延迟间隔，越大扩散越慢
    private List<Integer> spreadRadius = new ArrayList<>();//扩散圆层级数，元素为扩散的距离
    private List<Integer> alphas = new ArrayList<>();//对应每层圆的透明度

    public SpreadView(Context context) {
        this(context, null, 0);
    }

    public SpreadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpreadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //通过TypeArray初始化属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SpreadView, defStyleAttr, 0);
        radius = a.getInt(R.styleable.SpreadView_spread_radius, radius);
        maxRadius = a.getInt(R.styleable.SpreadView_spread_max_radius, maxRadius);
        int centerColor = a.getColor(R.styleable.SpreadView_spread_center_color,
                ContextCompat.getColor(context, android.R.color.holo_red_dark));
        int spreadColor = a.getColor(R.styleable.SpreadView_spread_spread_color,
                ContextCompat.getColor(context, R.color.color_F71816));
        distance = a.getInt(R.styleable.SpreadView_spread_distance, distance);
        a.recycle();
        centerPaint = new Paint();              //定义一个中心画笔
        centerPaint.setColor(centerColor);          //为中心画笔设定颜色
        centerPaint.setAntiAlias(true);                 //打开抗锯齿，要不然会很丑
        alphas.add(255);                                //最开始透明度为不透明
        spreadRadius.add(0);                            //最开始扩散度为0

        spreadPaint = new Paint();                      //设置一个扩散效果的画笔
        spreadPaint.setStyle(Paint.Style.STROKE);       //设置为一个空心圆
        spreadPaint.setAntiAlias(true);                 //设置一个抗锯齿
        spreadPaint.setAlpha(255);                       //一开始不扩散透明度为255
        spreadPaint.setColor(spreadColor);              //设置颜色为spreadColor



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {            //获取圆心
        //中心园画在画布正中心位置
         centerX = w / 2;
         centerY = h / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制所有圆，先遍历所有圆
        for(int i = 0; i< spreadRadius.size(); i++){
            //遍历alphas，取出透明度
            int alpha =  alphas.get(i);
            //赋值给透明度
            spreadPaint.setAlpha(alpha);
            //取出圆的半径
            int width = spreadRadius.get(i);
            /**
             * 绘制一个扩散的圆形
             * @params centerX,centerY  圆心
             * @params width
             * @params 扩散的半径+原本的半径
             * @params 画笔
             */
            canvas.drawCircle(centerX,centerY,width + radius,spreadPaint);                  //画圆的过程


            /**
             *
             * 当圆的透明度大于0并且半径小于300
             * 如果大于就返回alpha - distance
             * 否则就返回1，即透明度为1
             * 生成了新的园后，放入alphas数组里，
             * 每画完一个圆都要对当前圆进行更新
             */

            if(alpha > 0 && width < 300) {                                                          //园的透明度和半径
                alpha = alpha - distance > 0 ? alpha - distance : 1;
                alphas.add(alpha);
                spreadRadius.set(i, width+distance);                     //半径越来越宽，透明度越来越弱

            }


        }
        //重置
        if(spreadRadius.get(spreadRadius.size() - 1)> maxRadius){               //当圆的层级数大于最大半径
            alphas.add(255);                                                    //重置圆的透明度
            spreadRadius.add(0);                                                //圆的半径也重置
        }
        //移除多余的圆
        if (spreadRadius.size() >= 8){                                          //当圆的总数大于8时
            alphas.remove(0);                                            //移除最初的圆的透明度
            spreadRadius.remove(0);                                         //移除最初的圆的半径
        }
        canvas.drawCircle(centerX,centerY,radius,spreadPaint);                     //中心园，即不扩散的圆
        postInvalidateDelayed(delayMilliseconds);                                   //设置每一次出现圆的刷新间隔
    }

}


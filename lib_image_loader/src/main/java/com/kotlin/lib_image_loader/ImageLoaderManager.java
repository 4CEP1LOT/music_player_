package com.kotlin.lib_image_loader;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.lifecycle.GenericLifecycleObserver;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.kotlin.lib_image_loader.Utils;

import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * 图片加载类，外界唯一通信类
 * 支持为各种view,notification，widget加载图片
 *
 */


public class ImageLoaderManager {

    public ImageLoaderManager(){

    }

    public void displayImageForCircle(ImageView imageView, String albumPic) {
    }

    private static class SingleHolder{
        private static ImageLoaderManager instance =  new ImageLoaderManager();

    }

    public static ImageLoaderManager getInstance(){
        return SingleHolder.instance;
    }

    /**
     * 为ImageView加载图片
     * 完成简单图片加载只需要用load，apply
     * @param imageView
     * @param url
     */
    public void displayImageForView(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .asBitmap()                                           //因为过渡动画是drawable类型的，所以需要转换成bitmap类型的才能生效
                .load(url)
                .apply(initCommonRequestOption())                       //加载图片的Options
                .transition(BitmapTransitionOptions.withCrossFade())                                               //加载过渡动画效果
                .into(imageView);
    }

    /**
     * 为imageView加载圆形图片
     * @param imageView
     * @param url
     */

    public void displayImageForCircleView(final ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .into(new BitmapImageViewTarget(imageView){                 //bitmapTarget可以为任意对象添加图片
                    //将imageView包装成target
                    @Override
                    protected void setResource(final Bitmap resource) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory
                                .create(imageView.getResources(), resource);            //调用此方法画圆
                        drawable.setCircular(true);
                        imageView.setImageDrawable(drawable);
                    }
                });
    }
    public void displayImagefForViewGroup(final ViewGroup group,String url) {        // 为ViewGroup也就是容器加载图片（设置背景等）
        Glide.with(group.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .into(new SimpleTarget<Bitmap>() {
                    //最简单的Target
                    @Override
                    public void onResourceReady(@NonNull final Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        final Bitmap res = resource;
                        //将Bitmap模糊处理并转成Drawable
                        Observable.just(resource).map(new Function<Bitmap, Drawable>() {            //发射resource图片
                            @Override
                            public Drawable apply(Bitmap bitmap) throws Exception {
                                /**
                                 *  将毛玻璃进行模糊处理并得到一个新的Bitmap转换成一个drawable
                                 */
                                Drawable drawable = new BitmapDrawable(Utils.doBlur(resource, 100, true));      //毛玻璃特效，进行模糊处理
                                return drawable;
                            }
                        }).subscribeOn(Schedulers.io())                             //在子线程执行
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Drawable>() {                       //在Consumer中直接设置ViewGroup中的背景
                                    @SuppressLint("CheckResult")
                                    @Override
                                    public void accept(Drawable drawable) throws Exception {
                                        group.setBackground(drawable);

                                     }
                                });
                    }
                });
    }
/**
 *     为非View加载图片
 */

    private void displayImageForTarget(Context context,Target target,String url){
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .transition(BitmapTransitionOptions.withCrossFade())
                .fitCenter()
                .into(target);
    }

    /**
     *          为notification中的id控件加载图片`
     * @param context
     * @param rv             RemoteView即notification的布局
     * @param id             加载图片的控件id
     * @param notification          notification对象
     * @param NOTIFICATION_ID       notification 本身的id
     * @param url                   图片的地址
     */

    public void displayImageForNotification(Context context, RemoteViews rv, int id, Notification notification, int NOTIFICATION_ID, String url){
            this.displayImageForTarget(context,initNotificationTarget(context,  rv,  id,  notification, NOTIFICATION_ID,url),url);

    }
    //构造一个notification target
    private NotificationTarget initNotificationTarget(Context context, RemoteViews rv, int id, android.app.Notification notification, int NOTIFICATION_ID, String url){
        NotificationTarget target = new NotificationTarget(context,id,rv,notification,NOTIFICATION_ID);
        return target;
    }


    @SuppressLint("CheckResult")
    private RequestOptions initCommonRequestOption(){                   //加载图片的公共的Options
        RequestOptions options = new RequestOptions();

        options .error(android.R.drawable.ic_search_category_default)            //设定错误图示
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)         //设定缓存策略
                .skipMemoryCache(false)                                 //使用内存缓存，true代表不使用，false代表使用
                .priority(Priority.NORMAL);                             //图片下载的优先级

        return options;

    }

}

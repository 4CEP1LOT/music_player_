//package com.kotlin.musiclearning.view.friend;
//
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.kotlin.lib_audio.MediaPlayer.model.AudioBean;
//import com.kotlin.lib_audio.MediaPlayer.view.MusicPlayerActivity;
//import com.kotlin.lib_audio.app.AudioHelper;
//import com.kotlin.lib_common_ui.MultiImageViewLayout;
//import com.kotlin.lib_common_ui.recyclerview.MultiItemTypeAdapter;
//import com.kotlin.lib_common_ui.recyclerview.base.ItemViewDelegate;
//import com.kotlin.lib_common_ui.recyclerview.base.ItemViewDelegateManager;
//import com.kotlin.lib_common_ui.recyclerview.base.ViewHolder;
//import com.kotlin.lib_image_loader.ImageLoaderManager;
//import com.kotlin.lib_video.videoplayer.core.VideoAdContex;
//import com.kotlin.musiclearning.R;
//import com.kotlin.lib_base.model.FriendBodyValue;
//import com.kotlin.lib_base.login.LoginActivity;
//import com.kotlin.lib_base.model.user.UserManager;
//
//
//import java.util.List;
//
//public class FriendRecyclerAdapter extends MultiItemTypeAdapter {
//    private static final int MUSIC_TYPE = 0x01;                         //音乐类型
//
//    private static final int VIDEO_TYPE = 0x01;                          //视频类型
//
//
//    public FriendRecyclerAdapter(Context context, List datas) {
//        super(context, datas);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        addItemViewDelegate(MUSIC_TYPE,new MusicItemDelegate());
//        addItemViewDelegate(VIDEO_TYPE,new VideoItemDelegate());
//    }
//
//
//
//    private class MusicItemDelegate implements ItemViewDelegate<FriendBodyValue>{                           //音乐类型的布局
//        @Override
//        public int getItemViewLayoutId() {
//            return R.layout.item_friend_list_picture_layout;
//        }
//
//        @Override
//        public boolean isForViewType(FriendBodyValue item, int position) {
//            return item.type == FriendRecyclerAdapter.MUSIC_TYPE;                               //如果是这个类型，返回到getItemViewLayoutId页面加载
//        }
//
//        @Override
//        public void convert(ViewHolder holder, final FriendBodyValue recommandBodyValue, int position) {
//
//            //为viewholder绑定数据
//            holder.setText(R.id.name_view,recommandBodyValue.name+ " ");
//            holder.setText(R.id.fansi_view,recommandBodyValue.fans+" ");
//            holder.setText(R.id.zan_view,recommandBodyValue.zan);
//            holder.setText(R.id.message_view,recommandBodyValue.msg);
//            holder.setText(R.id.audio_author_view,recommandBodyValue.audioBean.author);
//            holder.setText(R.id.audio_name_view,recommandBodyValue.audioBean.name);
//            holder.setOnClickListener(R.id.album_layout,new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AudioHelper.addAudio((Activity) mContext,recommandBodyValue.audioBean);                         //调用addAudio来播放音乐；
//                }
//            });
//            holder.setOnClickListener(R.id.guanzhu_view, new View.OnClickListener() {                           //关注按钮初始化并设定监听事件
//                @Override
//                public void onClick(View v) {
//                    LoginActivity.start(mContext);                                                          //如果没有登录的话就去登录
//                }
//            });
//
//            ImageView avatar = holder.getView(R.id.photo_view);                                                             //初始化头像
//            ImageLoaderManager.getInstance().displayImageForView(avatar,recommandBodyValue.avatr);                          //为头像设定图片
//            ImageView albumView = holder.getView(R.id.album_view);                                                          //初始化专辑封面
//            ImageLoaderManager.getInstance().displayImageForView(albumView,recommandBodyValue.audioBean.albumPic);          //为专辑设定图片
//            //多重自动布局
//            MultiImageViewLayout imageViewLayout = holder.getView(R.id.image_layout);
//            imageViewLayout.setList(recommandBodyValue.pics);
//
//        }
//
//
//    }
//
//    private class VideoItemDelegate implements ItemViewDelegate<FriendBodyValue>{
//
//        @Override
//        public int getItemViewLayoutId() {
//            return R.layout.item_friend_list_video_layout;
//        }
//
//        @Override
//        public boolean isForViewType(FriendBodyValue item, int position) {
//            return item.type == FriendRecyclerAdapter.VIDEO_TYPE;
//        }
//
//        @Override
//        public void convert(ViewHolder holder, FriendBodyValue recommandBodyValue, int position) {
//            RelativeLayout videoGroup = holder.getView(R.id.video_layout);                                                  //初始化视频播放界面
//            VideoAdContex mAdsdkContext = new VideoAdContex(videoGroup,recommandBodyValue.videoUrl);                            //创建视频播放器，通过调用videoSlot
//            holder.setText(R.id.fansi_view,recommandBodyValue.fans + "粉丝");
//            holder.setText(R.id.name_view,recommandBodyValue.fans + "分享视频");
//            holder.setText(R.id.text_view,recommandBodyValue.text);
//            holder.setOnClickListener(R.id.guanzhu_view,new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(!UserManager.getInstance().hasLogined()){
//                        LoginActivity.start(mContext);
//                    }
//                }
//            });
//            ImageView avatar =  holder.getView(R.id.photo_view);                                            //设置头像
//            ImageLoaderManager.getInstance().displayImageForCircleView(avatar,recommandBodyValue.avatr);
//
//
//
//
//
//
//        }
//    }
//}

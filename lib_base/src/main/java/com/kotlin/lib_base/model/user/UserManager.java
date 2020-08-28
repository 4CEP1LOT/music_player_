package com.kotlin.lib_base.model.user;


import com.kotlin.lib_base.model.search.SearchAll;

//单例模式管理用户信息
public class UserManager {

    private static UserManager mInstance;
    private User mUser;
    private SearchAll.Result.UserProfile mProfile;


//两个if判断的单例方法叫做双检查机制

    public static  UserManager getInstance(){
        if(mInstance == null){
            synchronized (UserManager.class){                       //字节码锁，因为我们这个类的字节码锁是唯一的
                if(mInstance == null){
                    mInstance = new UserManager();
                }

            }
        }
        return mInstance;
    }

    /**
     * 保存用户信息到内存
     * @param user      user为用户信息
     *
     */
    public void saveUser(User user){
        mUser = user;
        saveLocal(user);
    }

    /**
     * 保存到本地数据库，持久化信息
     * @param user
     */
    private void saveLocal (User user){

    }

    /**
     * 获取用户信息
     * @return
     */

    public User getUser(){
        return mUser;
    }

    /**
     * 从本地获取
     * @return
     */

    private User getLocal(){
        return  null;
    }

    /**
     * 判断是否登陆过
     * @return
     */

    public boolean hasLogined(){
        return getUser() != null;
    }

    /**
     * 删除用户信息
     */
    public void removeUser(){
        mUser = null;
        removeLocal();
    }

    private void removeLocal() {


    }

}

package com.kotlin.lib_connection.okHttp.listener;

import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;

import java.util.ArrayList;

/**
 * 当需要专门处理Cookie时创建此回调接口
 */

public interface DisposeHandleCookieListener extends DisposeDataListener {

    void onCookie(ArrayList<String> cookieStrLists);

}

package com.timo.timolib;

/**
 * 常量类
 */
public class BaseConstancts {
    /**
     * log信息
     */
    public static boolean log = true;
    public static String TAG = "timo";

    /**
     * 退出时设置：
     * 应用退出时是否进入后台
     * 按返回键应用相应退出的时间-毫秒
     */
    public static boolean exit_to_back = false;
    public static int exit_time = 2000;

    /**
     * sp保存名称
     * http缓存文件夹名称
     * 定义一个参数常量值
     * 开启相机的请求Code
     */
    public static final String BASE_SHARE_NAME = "base_share_name";
    public static final String http_cache = "http_cache";
    public static final String Params = "Params";
    public static final String BASE_PASSWORD = "base_password";
    public static final int requestCode_camera = 7001;
    public static final int requestCode_phone = 7002;
    public static final int get_camera_permission_request = 9746;
}


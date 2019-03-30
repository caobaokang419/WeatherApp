package com.gary.weatherdemo.push;

import android.app.Notification;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

import com.gary.weatherdemo.R;
import com.gary.weatherdemo.constant.Constants;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * Created by GaryCao on 2018/10/25.
 */
public class UmPushManager {
    private static final String TAG = "UmPushManager";

    /*私有构造*/
    private UmPushManager() {

    }

    public static void register(Context context) {
        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
        // 参数一：当前上下文context；
        // 参数二：应用申请的Appkey（需替换）；
        // 参数三：渠道名称；
        // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
        // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
        UMConfigure.init(
                context,
                Constants.UmDefConfig.UM_PUSH_APP_KEY,
                Constants.UmDefConfig.UM_PUSH_CHANNEL_NAME,
                UMConfigure.DEVICE_TYPE_PHONE,
                Constants.UmDefConfig.UM_PUSH_MESSAGE_SECRET_KEY);

        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(context);

        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(mIUmengRegisterCallback);
        mPushAgent.setMessageHandler(mMessageHandler);
        mPushAgent.setNotificationClickHandler(mNotificationClickHandler);
    }

    static IUmengRegisterCallback mIUmengRegisterCallback = new IUmengRegisterCallback() {

        @Override
        public void onSuccess(String deviceToken) {
            //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
            //注册成功：deviceToken：-------->  AvMK_CTS9yPTiSuvEqQ20I7JLvqM5gBho_RFCISzBU50
            Log.i(TAG, "注册成功：deviceToken：-------->  " + deviceToken);
        }

        @Override
        public void onFailure(String s, String s1) {
            Log.e(TAG, "注册失败：-------->  " + "s:" + s + ",s1:" + s1);
        }
    };

    /**
     * 自定义通知栏样式的回调方法
     */
    static UmengMessageHandler mMessageHandler = new UmengMessageHandler() {
        @Override
        public Notification getNotification(Context context, UMessage msg) {
            switch (msg.builder_id) {
                case 1:
                    Notification.Builder builder = new Notification.Builder(context);
                    RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                            R.layout.notification_um_push_view);
                    myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                    myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                    myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                    myNotificationView.setImageViewResource(R.id.notification_small_icon,
                            getSmallIconId(context, msg));
                    builder.setContent(myNotificationView)
                            .setSmallIcon(getSmallIconId(context, msg))
                            .setTicker(msg.ticker)
                            .setAutoCancel(true);

                    return builder.getNotification();
                default:
                    //默认为0，若填写的builder_id并不存在，也使用默认。
                    return super.getNotification(context, msg);
            }
        }
    };

    static UmengNotificationClickHandler mNotificationClickHandler = new UmengNotificationClickHandler() {
        @Override
        public void dealWithCustomAction(Context context, UMessage msg) {
            Log.e(TAG, "click");
        }

    };
}
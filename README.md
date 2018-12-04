### 基于MVVM框架的高德天气APP：
-------

### 功能点实现说明：
- [高德天气API：](https://lbs.amap.com/api/webservice/guide/api/weatherinfo/)
实现情况：Get&Post方式可以正常返回查询数据并UI显示；

- MVVM框架框架和组件实现：
1. ViewModel：实现View和Model数据解耦。
2. Room：实现Database模块封装。
3. LiveData：实现View关联数据动态更新。
4. DataBinding：实现xml文件直接绑定数据。
5. WorkManager：实现后台任务、定时任务、链表等不同类型任务管理。

- 网络请求：实现异步网络请求框架封装。

- AdMob&Firebase：实现广告载入和统计分析。

- 高德配置文件：解析存储本地存储高德adcode和城市信息对照表，用于本地天气动态查询api调用。

- CommonUI公共控件：实现公共控件自定义、差异化配置、和控件复用。

-------

### Android AdMob document：
- [AdMob（Google移动广告）SDK指南](https://developers.google.com/admob/android/quick-start?hl=zh-CN#import_the_mobile_ads_sdk)
- [Banner(横幅广告)](https://developers.google.com/admob/android/banner?hl=zh-CN)
- [Interstitial（插页广告）](https://developers.google.com/admob/android/interstitial?hl=zh-CN)
- [Native（原生广告）](https://developers.google.com/admob/android/native-unified?hl=zh-CN)
- [Rewarded Video（应用内购买广告）](https://developers.google.com/admob/android/rewarded-video?hl=zh-CN)


-------

### Android Firebase document：
- [Firebase 集成指南](https://developers.google.com/firebase/docs/android/setup?hl=zh-CN)
- [Analytics(分析)](https://firebase.google.com/docs/analytics/?hl=zh-CN)
- [Crash Report(报错上报)](https://firebase.google.com/docs/crashlytics/?hl=zh-CN)
- [Remote Config(远程配置)](https://firebase.google.com/docs/cloud-messaging/?hl=zh-CN)
- [Cloud Message(云消息)](https://firebase.google.com/docs/remote-config/?hl=zh-CN)
- [Auth(身份认证)](https://firebase.google.com/docs/auth/?hl=zh-CN)

-------
### Android Architecture Components document：
- [Room](https://developer.android.google.cn/topic/libraries/architecture/room)
- [Lifecycle components](https://developer.android.google.cn/topic/libraries/architecture/lifecycle)
- [ViewModels](https://developer.android.google.cn/topic/libraries/architecture/viewmodel)
- [LiveData](https://developer.android.google.cn/topic/libraries/architecture/livedata) :
- [Paging](https://developer.android.google.cn/topic/libraries/architecture/paging/)
- [Navigation](https://developer.android.google.cn/topic/libraries/architecture/navigation/)
- [WorkManager](https://developer.android.google.cn/topic/libraries/architecture/workmanager/) 

-------
### Screenshots：
![](https://github.com/caobaokang419/WeatherApp/blob/master/screenshots/admob_banner_screenshot.bmp)

-------
### License
部分业务机制借鉴网络资源，不能用于商业用途，转载请注明出处，谢谢！ 

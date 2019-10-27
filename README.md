### Mvvm Amap Weather App：
- MMVM: using the MVVM architecture which is the newest app architecture and also recommended by android.
- Amap Weahter: using amap apis to access the weather datas provided by amap server.

-------
### Screenshots：
![](https://github.com/caobaokang419/WeatherApp/blob/master/screenshots/city_weather_screenshot.jpg)


-------
### Functions list as follows: 
- [Amap weather](https://lbs.amap.com/api/webservice/guide/api/weatherinfo/): using both Get and Post methods to query amap weather data;
- City configs file: using Xutils3 to download and save [Aamp weather city config file](http://a.amap.com/lbs/static/file/AMap_adcode_citycode.xlsx.zip)
- Global data apis： using ContentProvider and SharedPreference, provided data access apis both weather db datas and app configs.
--Data cache manager: provided the implement of the disc and memory data cache function;
--Async task manager: 1. provided the wrapper of the ThreadPoolExecutor and the implementation of TaskExecutor;
--Data filter manager: provided the implement of the chain to filter data. 


-------
### Uis list as follows: 
--View pager: using ViewPager to implement to change ui pages when LTR and RTL slide.
--Common UIs: implement common customer ui views using for code reusing.
--RecycleView Wrapper: RecyclerView is recommended by android  and better than ListView;
--City search:auto search the item from the city list when input search key word;

-------
### MVVM architecture compents list as follows: 
- [Lifecycle components](https://developer.android.google.cn/topic/libraries/architecture/lifecycle)
- [ViewModels](https://developer.android.google.cn/topic/libraries/architecture/viewmodel)
- [LiveData](https://developer.android.google.cn/topic/libraries/architecture/livedata) 
- [Room](https://developer.android.google.cn/topic/libraries/architecture/room)
- [WorkManager](https://developer.android.google.cn/topic/libraries/architecture/workmanager/) 
- [Data-binding](https://developer.android.google.cn/topic/libraries/data-binding//) 
- [Paging](https://developer.android.google.cn/topic/libraries/architecture/paging/):TODO
- [Navigation](https://developer.android.google.cn/topic/libraries/architecture/navigation/):TODO

-------
### Network architecture compents list as follow:
- [Retrofit 2.0](https://blog.csdn.net/carson_ho/article/details/73732076)
- [OkHttp3](https://blog.csdn.net/xx326664162/article/details/77714126)
- [Gson](https://baijiahao.baidu.com/s?id=1607221675455152057&wfr=spider&for=pc)
- [RxJava Android](https://gank.io/post/560e15be2dca930e00da1083)：
- [Xutils3](https://github.com/wyouflf/xUtils3)
- [Glide](https://www.jianshu.com/p/7ce7b02988a4)
- [DiskLruCache](https://github.com/JakeWharton/DiskLruCache)
- [ThreadPoolExecutor](https://www.jianshu.com/p/4d4634c92253)
- [AdMob](https://developers.google.com/admob/android/quick-start?hl=zh-CN#import_the_mobile_ads_sdk)：move to [FirebaseApp](https://github.com/caobaokang419/FirebaseApp)
- [Firebase](https://developers.google.com/firebase/docs/android/setup?hl=zh-CN)：move to [FirebaseApp](https://github.com/caobaokang419/FirebaseApp)
- [EventBus](https://www.jianshu.com/p/7ce7b02988a4)：


-------
### Android Architecture Components：
![](https://developer.android.google.cn/topic/libraries/architecture/images/final-architecture.png)


-------
### Thanks
- https://github.com/tangmin1010/appcomponent
- https://github.com/youth5201314/banner
- https://github.com/scwang90/SmartRefreshLayout
- https://github.com/hongyangAndroid/base-diskcache;

-------
### Contact with Me:
Wechat: lovelisa323 
QQ: 302634630
Mail: caobaokang86@gmail.com 
csdn: https://cbk419323.blog.csdn.net/


-------
### License
Copyright (C) 2019 GaryCao
Copyright (C) 2013 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

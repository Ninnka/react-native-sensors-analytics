package com.ninnka.sa;

import android.text.TextUtils;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Callback;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by yang on 2017/4/5
 * <p>
 * 参数类型在@ReactMethod注明的方法中，会被直接映射到它们对应的JavaScript类型
 * String -> String
 * ReadableMap -> Object
 * Boolean -> Bool
 * Integer -> Number
 * Double -> Number
 * Float -> Number
 * Callback -> function
 * ReadableArray -> Array
 */

public class SensorsAnalyticsModule extends ReactContextBaseJavaModule {

    public SensorsAnalyticsModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    private static final String MODULE_NAME = "RNSensorsAnalytics";
    private static final String MODULE_VERSION = "1.0.1";
    private static final String LOGTAG = "SA.RN";

    /**
     * 返回一个字符串名字，这个名字在 JavaScript (RN)端标记这个模块。
     */
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    /**
     * ReadableMap 转换成 JSONObject
     */
    private JSONObject convertToJSONObject(ReadableMap properties) {
        if (properties == null) {
            return null;
        }

        JSONObject json = new JSONObject();
        ReadableNativeMap map = (ReadableNativeMap) properties;
        HashMap<String, Object> hashMap = map.toHashMap();
        for (String key : hashMap.keySet()) {
            if (key != null) {
                Object value = hashMap.get(key);
                if (value != null) {
                    try {
                        json.put(key, hashMap.get(key));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return json;
    }

    /**
     * 参数类型在@ReactMethod注明的方法中，会被直接映射到它们对应的JavaScript类型
     * String -> String
     * ReadableMap -> Object
     * Boolean -> Bool
     * Integer -> Number
     * Double -> Number
     * Float -> Number
     * Callback -> function
     * ReadableArray -> Array
     * <p>
     * 导出 track 方法给 RN 使用.
     *
     * @param eventName  事件名称
     * @param properties 事件的具体属性
     *                   <p>
     *                   RN 中使用示例：（记录 RN_AddToFav 事件，事件属性 "ProductID":123456,"UserLevel":"VIP"）
     *                   <Button
     *                   title="Button"
     *                   onPress={()=>
     *                   SensorsDataAPI_Android.track("RN_AddToFav",{"ProductID":123456,"UserLevel":"VIP"})}>
     *                   </Button>
     */
    @ReactMethod
    public void track(String eventName, ReadableMap properties) {
        try {
            SensorsDataAPI.sharedInstance().track(eventName, convertToJSONObject(properties));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 trackTimerBegin 方法给 RN 使用.
     * <p>
     * 初始化事件的计时器，默认计时单位为毫秒(计时开始).
     *
     * @param eventName 事件的名称.
     *                  <p>
     *                  RN 中使用示例：（计时器事件名称 viewTimer ）
     *                  <Button
     *                  title="Button"
     *                  onPress={()=>
     *                  SensorsDataAPI_Android.trackTimerBegin("viewTimer")}>
     *                  </Button>
     */
    @ReactMethod
    public void trackTimerBegin(String eventName) {
        try {
            SensorsDataAPI.sharedInstance().trackTimerBegin(eventName);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 trackTimerEnd 方法给 RN 使用.
     * <p>
     * 初始化事件的计时器，默认计时单位为毫秒(计时结束，并触发事件)
     *
     * @param eventName 事件的名称.
     *                  <p>
     *                  RN 中使用示例：（计时器事件名称 viewTimer ）
     *                  <Button
     *                  title="Button"
     *                  onPress={()=>
     *                  SensorsDataAPI_Android.trackTimerEnd("viewTimer",{"ProductID":123456,"UserLevel":"VIP"})}>
     *                  </Button>
     */
    @ReactMethod
    public void trackTimerEnd(String eventName, ReadableMap properties) {
        try {
            SensorsDataAPI.sharedInstance().trackTimerEnd(eventName, convertToJSONObject(properties));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }


    /**
     * 导出 clearTrackTimer 方法给 RN 使用.
     * <p>
     * 清除所有事件计时器
     * <p>
     * RN 中使用示例：（保存用户的属性 "sex":"男"）
     * <Button
     * title="Button"
     * onPress={()=>
     * SensorsDataAPI_Android.clearTrackTimer()}>
     * </Button>
     */
    @ReactMethod
    public void clearTrackTimer() {
        try {
            SensorsDataAPI.sharedInstance().clearTrackTimer();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }


    /**
     * 导出 login 方法给 RN 使用.
     *
     * @param loginId RN 中使用示例：
     *                <Button
     *                title="Button"
     *                onPress={()=>
     *                SensorsDataAPI_Android.login("developer@sensorsdata.cn")}>
     *                </Button>
     */
    @ReactMethod
    public void login(String loginId) {
        try {
            SensorsDataAPI.sharedInstance().login(loginId);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 logout 方法给 RN 使用.
     * <p>
     * RN 中使用示例：
     * <Button
     * title="Button"
     * onPress={()=>
     * SensorsDataAPI_Android.logout()}>
     * </Button>
     */
    @ReactMethod
    public void logout() {
        try {
            SensorsDataAPI.sharedInstance().logout();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 trackInstallation 方法给 RN 使用.
     * <p>
     * 用于记录首次安装激活、渠道追踪的事件.
     *
     * @param eventName  事件名.
     * @param properties 事件属性.
     *                   <p>
     *                   RN 中使用示例：（ 这里事件名为 AppInstall ,事件的渠道属性 "$utm_source":"渠道A","$utm_campaign":"广告A" ）
     *                   <Button
     *                   title="Button"
     *                   onPress={()=>
     *                   SensorsDataAPI_Android.trackInstallation("AppInstall",{"$utm_source":"渠道A","$utm_campaign":"广告A"})}>
     *                   </Button>
     */
    @ReactMethod
    public void trackInstallation(String eventName, ReadableMap properties) {
        try {
            SensorsDataAPI.sharedInstance().trackInstallation(eventName, convertToJSONObject(properties));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 trackViewScreen 方法给 RN 使用.
     * <p>
     * 此方法用于 RN 中 Tab 切换页面的时候调用，用于记录 $AppViewScreen 事件.
     *
     * @param url        页面的 url  记录到 $url 字段中(如果不需要此属性，可以传 null ).
     * @param properties 页面的属性.
     *                   <p>
     *                   注：为保证记录到的 $AppViewScreen 事件和 Auto Track 采集的一致，
     *                   需要传入 $title（页面的title） 、$screen_name （页面的名称，即 包名.类名）字段.
     *                   <p>
     *                   RN 中使用示例：
     *                   <Button
     *                   title="Button"
     *                   onPress={()=>
     *                   SensorsDataAPI_Android.trackViewScreen(null,{"$title":"RN主页","$screen_name":"cn.sensorsdata.demo.RNHome"})}>
     *                   </Button>
     */
    @ReactMethod
    public void trackViewScreen(String url, ReadableMap properties) {
        try {
            SensorsDataAPI.sharedInstance().trackViewScreen(url, convertToJSONObject(properties));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 profileSet 方法给 RN 使用.
     *
     * @param properties 用户属性
     *                   <p>
     *                   RN 中使用示例：（保存用户的属性 "sex":"男"）
     *                   <Button
     *                   title="Button"
     *                   onPress={()=>
     *                   SensorsDataAPI_Android.profileSet({"sex":"男"})}>
     *                   </Button>
     */
    @ReactMethod
    public void profileSet(ReadableMap properties) {
        try {
            SensorsDataAPI.sharedInstance().profileSet(convertToJSONObject(properties));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 profileSetOnce 方法给 RN 使用.
     * <p>
     * 首次设置用户的一个或多个 Profile.
     * 与profileSet接口不同的是，如果之前存在，则忽略，否则，新创建.
     *
     * @param properties 属性列表
     *                   <p>
     *                   RN 中使用示例：（保存用户的属性 "sex":"男"）
     *                   <Button
     *                   title="Button"
     *                   onPress={()=>
     *                   SensorsDataAPI_Android.profileSetOnce({"sex":"男"})}>
     *                   </Button>
     */
    @ReactMethod
    public void profileSetOnce(ReadableMap properties) {
        try {
            SensorsDataAPI.sharedInstance().profileSetOnce(convertToJSONObject(properties));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }


    /**
     * 导出 profileIncrement 方法给 RN 使用.
     * <p>
     * 给一个数值类型的Profile增加一个数值. 只能对数值型属性进行操作，若该属性
     * 未设置，则添加属性并设置默认值为0.
     *
     * @param property 属性名称
     * @param value    属性的值，值的类型只允许为 Number .
     *                 <p>
     *                 RN 中使用示例：
     *                 <Button
     *                 title="Button"
     *                 onPress={()=>
     *                 SensorsDataAPI_Android.profileIncrement("money",10)}>
     *                 </Button>
     */
    @ReactMethod
    public void profileIncrement(String property, Double value) {
        try {
            SensorsDataAPI.sharedInstance().profileIncrement(property, value);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 profileAppend 方法给 RN 使用.
     * <p>
     * 给一个列表类型的 Profile 增加一个元素.
     *
     * @param property 属性名称.
     * @param value    新增的元素.
     *                 <p>
     *                 RN 中使用示例：
     *                 <Button
     *                 title="Button"
     *                 onPress={()=>
     *                 SensorsDataAPI_Android.profileAppend("VIP","Gold")}>
     *                 </Button>
     */
    @ReactMethod
    public void profileAppend(String property, String value) {
        try {
            SensorsDataAPI.sharedInstance().profileAppend(property, value);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 profileUnset 方法给 RN 使用.
     * <p>
     * 删除用户的一个 Profile.
     *
     * @param property 属性名称.
     *                 <p>
     *                 RN 中使用示例：
     *                 <Button
     *                 title="Button"
     *                 onPress={()=>
     *                 SensorsDataAPI_Android.profileUnset("sex")}>
     *                 </Button>
     */
    @ReactMethod
    public void profileUnset(String property) {
        try {
            SensorsDataAPI.sharedInstance().profileUnset(property);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 profileDelete 方法给 RN 使用.
     * <p>
     * 删除用户所有 Profile.
     * <p>
     * RN 中使用示例：
     * <Button
     * title="Button"
     * onPress={()=>
     * SensorsDataAPI_Android.profileDelete()}>
     * </Button>
     */
    @ReactMethod
    public void profileDelete() {
        try {
            SensorsDataAPI.sharedInstance().profileDelete();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    /**
     * 导出 getDistinctId 方法给 RN 使用.
     * <p>
     * 获取当前的 DistinctId.
     * <p>
     * successCallback 优先返回 mLoginId ，否则返回 mAnonymousId
     * <p>
     * RN 中使用示例：
     * <Button
     * title="Button"
     * onPress={()=>
     * SensorsDataAPI_Android.getDistinctId(success=>{
     * console.log(success)
     * },
     * error=>{
     * console.log(error)
     * })
     * }>
     * </Button>
     */
    @ReactMethod
    public void getDistinctId(Callback successCallback, Callback errorCallback) {
        try {
            String mLoginId = SensorsDataAPI.sharedInstance().getLoginId();
            if (!TextUtils.isEmpty(mLoginId)) {
                successCallback.invoke(mLoginId);
            } else {
                successCallback.invoke(SensorsDataAPI.sharedInstance().getAnonymousId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
            errorCallback.invoke(e.getMessage());
        }
    }
    
    /**
     * 追踪 app crash 事件
     */
    @ReactMethod
    public void trackCrash() {
        try {
            SensorsDataAPI.sharedInstance().trackAppCrash();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }
}

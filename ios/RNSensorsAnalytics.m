//
//  RNSensorsAnalytics.m
//  hunheDemo
//
//  Created by 肖彦敏 on 2017/4/14.
//  Copyright © 2017年 Facebook. All rights reserved.
//

#import "RNSensorsAnalytics.h"
#import <React/RCTBridge.h>
#import <React/RCTEventDispatcher.h>
#import "SensorsAnalyticsSDK/SensorsAnalyticsSDK.h"

#define MODULE_VERSION @"1.0.0"

@implementation RNSensorsAnalytics

RCT_EXPORT_MODULE(RNSensorsAnalytics)

/**
 * 导出 track 方法给 RN 使用.
 *
 * @param event  事件名称
 * @param propertyDict 事件的具体属性
 *
 * RN 中使用示例：
 *     <Button
 *            title="Button"
 *            onPress={()=>
 *            RNSensorsAnalytics.track("RN_AddToFav",{"ProductID":123456,"UserLevel":"VIP"})}>
 *     </Button>
 */

RCT_EXPORT_METHOD(track:(NSString *)event withProperties:(NSDictionary *)propertyDict){
    @try {
      NSMutableDictionary *mutDict = [NSMutableDictionary dictionaryWithDictionary:propertyDict];
      [propertyDict enumerateKeysAndObjectsUsingBlock:^(id  _Nonnull key, id  _Nonnull obj, BOOL * _Nonnull stop) {
        if ([obj isKindOfClass:NSArray.class]) {
          [mutDict setObject:[NSSet setWithArray:obj] forKey:key];
        }
      }];
      [[SensorsAnalyticsSDK sharedInstance] track:event withProperties:mutDict];
    } @catch (NSException *exception) {
       NSLog(@"[RNSensorsAnalytics] error:%@",exception);
    }
}
/**
 * 导出 trackTimerBegin 方法给 RN 使用.
 *
 * 初始化事件的计时器，默认计时单位为毫秒(计时开始).
 * @param eventName 事件的名称.
 *
 *  RN 中使用示例：（计时器事件名称 viewTimer ）
 *     <Button
 *            title="Button"
 *            onPress={()=>
 *            RNSensorsAnalytics.trackTimerBegin("viewTimer")}>
 *     </Button>
 */
RCT_EXPORT_METHOD(trackTimerBegin:(NSString *)event){
  @try {
    [[SensorsAnalyticsSDK sharedInstance] trackTimerBegin:event];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}
/**
 * 导出 trackTimerEnd 方法给 RN 使用.
 *
 * 初始化事件的计时器，默认计时单位为毫秒(计时结束并触发事件).
 * @param eventName 事件的名称.
 *
 *  RN 中使用示例：（计时器事件名称 viewTimer ）
 *     <Button
 *            title="Button"
 *            onPress={()=>
 *            RNSensorsAnalytics.trackTimerEnd("viewTimer",{"ProductID":123456,"UserLevel":"VIP"})}>
 *     </Button>
 */
RCT_EXPORT_METHOD(trackTimerEnd:(NSString *)event withProperties:(NSDictionary *)propertyDict){
    @try {
        [[SensorsAnalyticsSDK sharedInstance] trackTimerEnd:event withProperties:propertyDict];
    } @catch (NSException *exception) {
        NSLog(@"[RNSensorsAnalytics] error:%@",exception);
    }
}
/**
 * 导出 clearTrackTimer 方法给 RN 使用.
 * <p>
 * 清除所有事件计时器
 * <p>
 * RN 中使用示例：
 *      <Button
 *                 title="Button"
 *                 onPress={()=>
 *                 RNSensorsAnalytics.clearTrackTimer()}>
 *      </Button>
 */
RCT_EXPORT_METHOD(clearTrackTimer){
  @try {
    [[SensorsAnalyticsSDK sharedInstance] clearTrackTimer];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}
/**
 * 导出 trackInstallation 方法给 RN 使用.
 *
 * 用于记录首次安装激活、渠道追踪的事件.
 * @param event  事件名.
 * @param propertyDict 事件属性.
 *
 *  RN 中使用示例：
 *     <Button
 *            title="Button"
 *            onPress={()=>
 *            const date = new Date();
 *            this.year = date.getFullYear();
 *            this.month = date.getMonth() + 1;
 *            this.date = date.getDate();
 *            this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
 *            this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
 *            this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
 *            var currentTime =  this.year + "-" + this.month + "-" + this.date + " " + this.hour
 *                               + ":" + this.minute + ":" + this.second;
 *            RNSensorsAnalytics.trackInstallation("AppInstall",{"FirstUseTime":currentTime})}>
 *     </Button>
 */
RCT_EXPORT_METHOD(trackInstallation:(NSString *)event withProperties:(NSDictionary *)propertyDict){
  @try {
    [[SensorsAnalyticsSDK sharedInstance] trackInstallation:event withProperties:propertyDict];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}
/**
 * 导出 login 方法给 RN 使用.
 *
 * @param loginId 用户唯一下登录ID
 *
 * RN 中使用示例：
 *     <Button
 *            title="Button"
 *            onPress={()=>
 *            RNSensorsAnalytics.login("developer@sensorsdata.cn")}>
 *     </Button>
 */
RCT_EXPORT_METHOD(login:(NSString *)loginId){
  @try {
    [[SensorsAnalyticsSDK sharedInstance] login:loginId];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}
/**
 * 导出 logout 方法给 RN 使用.
 *
 * RN 中使用示例：
 *     <Button
 *            title="Button"
 *            onPress={()=>
 *            RNSensorsAnalytics.logout()}>
 *     </Button>
 */
RCT_EXPORT_METHOD(logout){
  @try {
    [[SensorsAnalyticsSDK sharedInstance] logout];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}
/**
 * 导出 trackViewScreen 方法给 RN 使用.
 *
 * 此方法用于 RN 中 Tab 切换页面的时候调用，用于记录 $AppViewScreen 事件.
 *
 * @param url        页面的 url  记录到 $url 字段中(如果不需要此属性，可以传 null ).
 * @param properties 页面的属性.
 *
 * 注：为保证记录到的 $AppViewScreen 事件和 Auto Track 采集的一致，
 *    需要传入 $title（页面的title） 、$screen_name （页面的名称，即 包名.类名）字段.
 *
 * RN 中使用示例：
 *     <Button
 *            title="Button"
 *            onPress={()=>
 *            RNSensorsAnalytics.trackViewScreen(null,{"$title":"RN主页","$screen_name":"cn.sensorsdata.demo.RNHome"})}>
 *     </Button>
 *
 *
 */
RCT_EXPORT_METHOD(trackViewScreen:(NSString *)url withProperties:(NSDictionary *)properties){
  @try {
    [[SensorsAnalyticsSDK sharedInstance] trackViewScreen:url withProperties:properties];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
  
}
/**
 * 导出 set 方法给 RN 使用.
 *
 * @param profileDict 用户属性
 *
 * RN 中使用示例：（保存用户的属性 "sex":"男"）
 *     <Button
 *            title="Button"
 *            onPress={()=>
 *            RNSensorsAnalytics.set({"sex":"男"})}>
 *     </Button>
 */
RCT_EXPORT_METHOD(set:(NSDictionary *)profileDict){
  @try {
    [[[SensorsAnalyticsSDK sharedInstance] people] set:profileDict];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}
/**
 * 导出 setOnce 方法给 RN 使用.
 *
 * 首次设置用户的一个或多个 Profile.
 * 与set接口不同的是，如果之前存在，则忽略，否则，新创建.
 *
 * @param profileDict 属性列表
 *
 *  RN 中使用示例：（保存用户的属性 "sex":"男"）
 *     <Button
 *            title="Button"
 *            onPress={()=>
 *            RNSensorsAnalytics.setOnce({"sex":"男"})}>
 *     </Button>
 */
RCT_EXPORT_METHOD(setOnce:(NSDictionary *)profileDict){
  @try {
    [[[SensorsAnalyticsSDK sharedInstance] people] setOnce:profileDict];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}
/**
 * 导出 unset 方法给 RN 使用.
 * <p>
 * 删除用户的一个 Profile.
 *
 * @param property 属性名称.
 *                 <p>
 *                 RN 中使用示例：
 *                 <Button
 *                 title="Button"
 *                 onPress={()=>
 *                 RNSensorsAnalytics.unset("sex")}>
 *                 </Button>
 */
RCT_EXPORT_METHOD(unset:(NSString *) profile){
  @try {
    [[[SensorsAnalyticsSDK sharedInstance] people] unset:profile];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}
/**
 * 导出 increment 方法给 RN 使用.
 *
 * 给一个数值类型的Profile增加一个数值. 只能对数值型属性进行操作，若该属性
 * 未设置，则添加属性并设置默认值为0.
 *
 * @param property 属性名称
 * @param value    属性的值，值的类型只允许为 Number .
 *
 * RN 中使用示例：
 *     <Button
 *            title="Button"
 *            onPress={()=>
 *            RNSensorsAnalytics.increment("money",10)}>
 *     </Button>
 */
RCT_EXPORT_METHOD(increment:(NSString *)profile by:(NSNumber *)amount){
  @try {
    [[[SensorsAnalyticsSDK sharedInstance] people] increment:profile by:amount];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}
/**
 * 导出 append 方法给 RN 使用.
 * <p>
 * 向一个<code>NSSet</code>类型的value添加一些值
 *
 * @param property 属性名称.
 * @param value    新增的元素.
 *                 <p>
 * RN 中使用示例：
 *      <Button
 *                 title="Button"
 *                 onPress={()=>{
 *                   var list = ["Sicario","Love Letter"];
 *                   RNSensorsAnalytics.append("Move",list);}>
 *     </Button>
 */
RCT_EXPORT_METHOD(append:(NSString *)profile by:(NSArray *)content){
  @try {
    NSSet *setCntent = [NSSet setWithArray:content];
    [[[SensorsAnalyticsSDK sharedInstance] people] append:profile by:setCntent];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}
/**
 * 导出 deleteUser 方法给 RN 使用.
 * <p>
 * 删除当前这个用户的所有记录.
 * <p>
 * RN 中使用示例：
 *      <Button
 *                title="Button"
 *                onPress={()=>
 *                RNSensorsAnalytics.deleteUser()}>
 *      </Button>
 */
RCT_EXPORT_METHOD(deleteUser){
  @try {
    [[[SensorsAnalyticsSDK sharedInstance] people] deleteUser];
  } @catch (NSException *exception) {
    NSLog(@"[RNSensorsAnalytics] error:%@",exception);
  }
}

RCT_EXPORT_BLOCKING_SYNCHRONOUS_METHOD(getDistinctId){
    @try {
        NSString *bestId = [SensorsAnalyticsSDK sharedInstance].loginId;
        if (bestId == nil) {
            bestId = [SensorsAnalyticsSDK sharedInstance].distinctId;
        }
        if (bestId == nil) {
            [[SensorsAnalyticsSDK sharedInstance] resetAnonymousId];
            bestId = [SensorsAnalyticsSDK sharedInstance].anonymousId;
        }
        return bestId;
    } @catch (NSException *exception) {
        NSLog(@"[RNSensorsAnalytics] error:%@",exception);
        return nil;
    }
    return nil;
}
@end

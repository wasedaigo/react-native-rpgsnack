#import "RNRuntimeViewManager.h"
#import "RuntimeView.h"
#import <React/RCTUIManager.h>
#import <React/RCTBridge.h>

@implementation RNRuntimeViewManager

RCT_EXPORT_MODULE();

@synthesize bridge = _bridge;

- (UIView *)view
{
    return [[RuntimeView alloc] init];
}

// finishPurchase
RCT_EXPORT_METHOD(finishPurchase:(nonnull NSNumber *)reactTag withData:(nonnull NSString *) purchases) {
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        UIView *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[RuntimeView class]]) {
            RCTLog(@"expecting UIView, got: %@", view);
        }
        else {
            [(RuntimeView *)view finishPurchase: purchases];
        }
    }];
}

// finishInterstitialAds
RCT_EXPORT_METHOD(finishInterstitialAds:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        UIView *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[RuntimeView class]]) {
            RCTLog(@"expecting UIView, got: %@", view);
        }
        else {
            [(RuntimeView *)view finishInterstitialAds];
        }
    }];
}

// finishRewardedAds
RCT_EXPORT_METHOD(finishRewardedAds:(nonnull NSNumber *)reactTag withResult: (BOOL) rewarded) {
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        UIView *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[RuntimeView class]]) {
            RCTLog(@"expecting UIView, got: %@", view);
        }
        else {
            [(RuntimeView *)view finishRewardedAds: rewarded];
        }
    }];
}

// finishAchivementUnlock
RCT_EXPORT_METHOD(finishAchievementUnlock:(nonnull NSNumber *)reactTag withData:(nonnull NSString *) achievements) {
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        UIView *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[RuntimeView class]]) {
            RCTLog(@"expecting UIView, got: %@", view);
        }
        else {
            [(RuntimeView *)view finishAchievementUnlock: achievements];
        }
    }];
}

// finishProgressSave
RCT_EXPORT_METHOD(finishProgressSave:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        UIView *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[RuntimeView class]]) {
            RCTLog(@"expecting UIView, got: %@", view);
        }
        else {
            [(RuntimeView *)view finishProgressSave];
        }
    }];
}


- (dispatch_queue_t)methodQueue
{
    return self.bridge.uiManager.methodQueue;
}

RCT_EXPORT_VIEW_PROPERTY(gamedata, NSString);
RCT_EXPORT_VIEW_PROPERTY(progress, NSString);
RCT_EXPORT_VIEW_PROPERTY(purchases, NSString);
RCT_EXPORT_VIEW_PROPERTY(achievements, NSString);
RCT_EXPORT_VIEW_PROPERTY(width, int);
RCT_EXPORT_VIEW_PROPERTY(height, int);

RCT_EXPORT_VIEW_PROPERTY(onRuntimeInit, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onInterstitialAdsShown, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onRewardedAdsShown, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onAchievementUnlocked, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onProgressSaved, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPurchaseStarted, RCTBubblingEventBlock)

@end


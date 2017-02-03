//
//  RuntimeView.h
//  RNRPGSnackRuntime
//
//  Created by Daigo Sato on 1/25/17.
//  Copyright © 2017 Daigo Sato. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <GLKit/GLkit.h>
#import <React/RCTComponent.h>
#import <React/RCTEventDispatcher.h>

@class RCTEventDispatcher;

@interface RuntimeView : UIView<GLKViewDelegate>

- (void)finishPurchase:(NSString*) purchases;
- (void)finishInterstitialAds;
- (void)finishRewardedAds:(BOOL) rewarded;
- (void)finishAchievementUnlock:(NSString*) achievements;
- (void)finishProgressSave;

@property (nonatomic, assign) int width;
@property (nonatomic, assign) int height;
@property (nonatomic, copy) NSString *gamedata;
@property (nonatomic, copy) NSString *progress;
@property (nonatomic, copy) NSString *purchases;
@property (nonatomic, copy) NSString *achievements;

@property (nonatomic, copy) RCTBubblingEventBlock onRuntimeInit;
@property (nonatomic, copy) RCTBubblingEventBlock onInterstitialAdsShown;
@property (nonatomic, copy) RCTBubblingEventBlock onRewardedAdsShown;
@property (nonatomic, copy) RCTBubblingEventBlock onAchievementUnlocked;
@property (nonatomic, copy) RCTBubblingEventBlock onProgressSaved;
@property (nonatomic, copy) RCTBubblingEventBlock onPurchaseStarted;
@end

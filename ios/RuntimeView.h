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
@property (nonatomic, assign) int width;
@property (nonatomic, assign) int height;
@property (nonatomic, copy) NSString *gamedata;

@property (nonatomic, copy) RCTBubblingEventBlock onRuntimeInit;
@end

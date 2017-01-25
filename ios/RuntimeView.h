//
//  RuntimeView.h
//  RNRPGSnackRuntime
//
//  Created by Daigo Sato on 1/25/17.
//  Copyright © 2017 Daigo Sato. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <GLKit/GLkit.h>

@interface RuntimeView : UIView <GLKViewDelegate>
@property (nonatomic, copy) NSString *gamedata;
@end

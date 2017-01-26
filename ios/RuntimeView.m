//
//  RuntimeView.m
//  RNRPGSnackRuntime
//
//  Created by Daigo Sato on 1/25/17.
//  Copyright © 2017 Daigo Sato. All rights reserved.
//

#import "RuntimeView.h"
#import "RPGSnackRuntime/Mobile.h"

@implementation RuntimeView

- (GLKView*)glkView {
    return (GLKView*)[self viewWithTag:100];
}

- (void)willMoveToSuperview:(UIView *)superview {
    [[self glkView] setFrame: self.frame];
    if (!MobileIsRunning()) {
        NSError* err = nil;
        double scaleX = (double)self.frame.size.width / (double)MobileScreenWidth();
        double scaleY = (double)self.frame.size.height / (double)MobileScreenHeight();
        double scale = MAX(1, MIN(scaleX, scaleY));
        MobileStart(scale, &err);
        if (err != nil) {
            NSLog(@"Error: %@", err);
        }
    }
}

- (void)drawFrame{
    [[self glkView] setNeedsDisplay];
}

- (void)glkView:(GLKView *)view drawInRect:(CGRect)rect {
    NSError* err = nil;
    MobileUpdate(&err);
    if (err != nil) {
        NSLog(@"Error: %@", err);
    }
}

- (void)updateTouches:(NSSet*)touches {
    for (UITouch* touch in touches) {
        if (touch.view != [self glkView]) {
            continue;
        }
        CGPoint location = [touch locationInView:[self glkView]];
        MobileUpdateTouchesOnIOS(touch.phase, (int64_t)touch, location.x, location.y);
    }
}

- (void)touchesBegan:(NSSet*)touches withEvent:(UIEvent*)event {
    [self updateTouches:touches];
}

- (void)touchesMoved:(NSSet*)touches withEvent:(UIEvent*)event {
    [self updateTouches:touches];
}

- (void)touchesEnded:(NSSet*)touches withEvent:(UIEvent*)event {
    [self updateTouches:touches];
}

- (void)touchesCancelled:(NSSet*)touches withEvent:(UIEvent*)event {
    [self updateTouches:touches];
}

@end

#import "RNRuntimeViewManager.h"
#import "RuntimeView.h"

#import <React/RCTBridge.h>

@implementation RNRuntimeViewManager

RCT_EXPORT_MODULE();

@synthesize bridge = _bridge;

- (UIView *)view
{
    return [[RuntimeView alloc] initWithFrame:CGRectMake(0, 0, 200, 200)];
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_VIEW_PROPERTY(gamedata, NSString);
            
@end


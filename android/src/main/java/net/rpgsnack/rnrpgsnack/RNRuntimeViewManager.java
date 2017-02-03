package com.rpgsnack.rnrpgsnack;

import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

import java.util.Map;

public class RNRuntimeViewManager extends SimpleViewManager<EbitenGLSurfaceView> {

    public static final int COMMAND_FINISH_PURCHASE = 1;
    public static final int COMMAND_FINISH_ACHIEVEMENT_UNLOCK = 2;
    public static final int COMMAND_FINISH_PROGRESS_SAVE = 3;
    public static final int COMMAND_FINISH_INTERSTITIAL_ADS = 4;
    public static final int COMMAND_FINISH_REWARDED_ADS = 5;

    public static final String REACT_CLASS = "RNRuntimeView";
    public enum Events {
        EVENT_ON_INIT("onRuntimeInit"),
        EVENT_ON_PURCHASE_STARTED("onPurchaseStarted"),
        EVENT_ON_ACHIEVEMENT_UNLOCKED("onAchievementUnlocked"),
        EVENT_PROGRESS_SAVED("onProgressSaved"),
        EVENT_ON_INTERSTITIAL_ADS_SHOWN("onInterstitialAdsShown"),
        EVENT_ON_REWARDED_ADS_SHOWN("onRewardedAdsShown");

        private final String mName;

        Events(final String name) {
            mName = name;
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    @ReactProp(name = "width", defaultInt = 320)
    public void setWidth(EbitenGLSurfaceView view, int width) {
        view.SetWidth(width);
    }

    @ReactProp(name = "height", defaultInt = 480)
    public void setHeight(EbitenGLSurfaceView view, int height) {
        view.SetHeight(height);
    }

    @ReactProp(name = "gamedata")
    public void setGameData(EbitenGLSurfaceView view, String data) {
        view.setData(data.getBytes());
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected EbitenGLSurfaceView createViewInstance(ThemedReactContext themedReactContext) {
        final EbitenGLSurfaceView view = new EbitenGLSurfaceView(themedReactContext);

        final RCTEventEmitter eventEmitter = themedReactContext.getJSModule(RCTEventEmitter.class);
        view.setOnLoaded(new EbitenGLSurfaceView.Callback() {
            @Override
            public void invoke(String data) {
                eventEmitter.receiveEvent(view.getId(), Events.EVENT_ON_INIT.toString(), null);
            }
        });

        view.setOnPurchaseStarted(new EbitenGLSurfaceView.Callback() {
            @Override
            public void invoke(String productId) {
                WritableMap event = Arguments.createMap();
                event.putString("productId", productId);
                eventEmitter.receiveEvent(view.getId(), Events.EVENT_ON_PURCHASE_STARTED.toString(), event);
            }
        });

        view.setOnAchievementUnlocked(new EbitenGLSurfaceView.Callback() {
            @Override
            public void invoke(String achievementId) {
                WritableMap event = Arguments.createMap();
                event.putString("productId", achievementId);
                eventEmitter.receiveEvent(view.getId(), Events.EVENT_ON_ACHIEVEMENT_UNLOCKED.toString(), event);
            }
        });

        view.setOnProgressSaved(new EbitenGLSurfaceView.Callback() {
            @Override
            public void invoke(String progress) {
                WritableMap event = Arguments.createMap();
                event.putString("productId", progress);
                eventEmitter.receiveEvent(view.getId(), Events.EVENT_PROGRESS_SAVED.toString(), event);
            }
        });

        view.setOnInterstitialAdsShown(new EbitenGLSurfaceView.Callback() {
            @Override
            public void invoke(String data) {
                eventEmitter.receiveEvent(view.getId(), Events.EVENT_ON_INTERSTITIAL_ADS_SHOWN.toString(), null);
            }
        });

        view.setOnRewardedAdsShown(new EbitenGLSurfaceView.Callback() {
            @Override
            public void invoke(String data) {
                eventEmitter.receiveEvent(view.getId(), Events.EVENT_ON_REWARDED_ADS_SHOWN.toString(), null);
            }
        });

        return view;
    }

    @Override
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder<String, Object> builder = MapBuilder.builder();
        for (Events event : Events.values()) {
            builder.put(event.toString(), MapBuilder.of("registrationName", event.toString()));
        }
        return builder.build();
    }


    @Override
    public void receiveCommand(
            EbitenGLSurfaceView view,
            int commandType,
            @Nullable ReadableArray args) {
        Assertions.assertNotNull(view);
        Assertions.assertNotNull(args);
        switch (commandType) {
            case COMMAND_FINISH_PURCHASE: {
                view.finalizePurchase(args.getString(0));
                return;
            }
            case COMMAND_FINISH_ACHIEVEMENT_UNLOCK: {
                view.finishAchievementUnlock(args.getString(0));
                return;
            }
            case COMMAND_FINISH_PROGRESS_SAVE: {
                view.finishProgressSave();
                return;
            }
            case COMMAND_FINISH_INTERSTITIAL_ADS: {
                view.finishInterstitialAds();
                return;
            }
            case COMMAND_FINISH_REWARDED_ADS: {
                view.finishRewardedAds(args.getString(0) == "true");
                return;
            }
            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandType,
                        getClass().getSimpleName()));
        }
    }

    @Override
    public Map<String,Integer> getCommandsMap() {
        return MapBuilder.of(
                "finishPurchase",
                COMMAND_FINISH_PURCHASE,
                "finishAchievementUnlock",
                COMMAND_FINISH_ACHIEVEMENT_UNLOCK,
                "finishProgressSave",
                COMMAND_FINISH_PROGRESS_SAVE,
                "finishInterstitialAds",
                COMMAND_FINISH_INTERSTITIAL_ADS,
                "finishRewardedAds",
                COMMAND_FINISH_REWARDED_ADS
        );
    }
}
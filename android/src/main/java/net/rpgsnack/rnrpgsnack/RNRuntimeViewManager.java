package net.rpgsnack.rnrpgsnack;

import android.opengl.GLSurfaceView;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

import java.util.Map;

public class RNRuntimeViewManager extends SimpleViewManager<GLSurfaceView> {

    public static final String REACT_CLASS = "RNRuntimeView";
    public enum Events {
        EVENT_ONLOAD("onRuntimeInit");

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
    protected GLSurfaceView createViewInstance(ThemedReactContext themedReactContext) {
        final EbitenGLSurfaceView view = new EbitenGLSurfaceView(themedReactContext);

        final RCTEventEmitter eventEmitter = themedReactContext.getJSModule(RCTEventEmitter.class);
        view.setOnLoaded(new EbitenGLSurfaceView.Callback() {
            @Override
            public void invoke() {
                eventEmitter.receiveEvent(view.getId(), Events.EVENT_ONLOAD.toString(), null);
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
}

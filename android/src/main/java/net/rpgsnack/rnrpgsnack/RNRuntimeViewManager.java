package net.rpgsnack.rnrpgsnack;

import android.opengl.GLSurfaceView;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

public class RNRuntimeViewManager extends SimpleViewManager<GLSurfaceView> {

  public static final String REACT_CLASS = "RNRuntimeView";

  @ReactProp(name = "width", defaultInt = 320)
  public void setWidth(GLSurfaceView view, int width) {
      ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
      if (layoutParams == null) {
          layoutParams = new ViewGroup.LayoutParams(0, 0);
      }
      layoutParams.width = width;
      view.setLayoutParams(layoutParams);
  }
  
  @ReactProp(name = "height", defaultInt = 480)
  public void setHeight(GLSurfaceView view, int height) {
      ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
      if (layoutParams == null) {
          layoutParams = new ViewGroup.LayoutParams(0, 0);
      }
      layoutParams.height = height;
      view.setLayoutParams(layoutParams);
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
    return new EbitenGLSurfaceView(themedReactContext);
  }
}

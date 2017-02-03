import {
  NativeModules,
  requireNativeComponent,
  findNodeHandle
} from 'react-native';

const RNRuntimeViewManager = NativeModules.RNRuntimeViewManager;
const finishPurchase = RNRuntimeViewManager.finishPurchase;
const finishAchievementUnlock = RNRuntimeViewManager.finishAchievementUnlock;
const finishProgressSave = RNRuntimeViewManager.finishProgressSave;
const finishInterstitialAds = RNRuntimeViewManager.finishInterstitialAds;
const finishRewardedAds = RNRuntimeViewManager.finishRewardedAds;

const RNRuntimeView = requireNativeComponent('RNRuntimeView', null);

class RuntimeView extends RNRuntimeView {
  finishPurchase(purchases) {
      return new Promise((resolve, reject) => {
          finishPurchase(
              findNodeHandle(this), JSON.stringify(purchases)
          );
      });
  }

  finishAchievementUnlock(achievements) {
      return new Promise((resolve, reject) => {
          finishAchievementUnlock(
              findNodeHandle(this), JSON.stringify(achievements)
          );
      });
  }

  finishProgressSave() {
      return new Promise((resolve, reject) => {
          finishProgressSave(
              findNodeHandle(this)
          );
      });
  }

  finishInterstitialAds() {
      return new Promise((resolve, reject) => {
          finishInterstitialAds(
              findNodeHandle(this)
          );
      });
  }

  finishRewardedAds(rewarded) {
      return new Promise((resolve, reject) => {
          finishRewardedAds(
              findNodeHandle(this), rewarded ? 'true' : 'false'
          );
      });
  }
}

export default RuntimeView;
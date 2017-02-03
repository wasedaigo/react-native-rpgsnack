import {
  NativeModules,
  requireNativeComponent,
  findNodeHandle
} from 'react-native';

import {finishPurchase, finishAchievementUnlock, finishProgressSave, finishInterstitialAds, finishRewardedAds} from "./RNRuntimeView.methods";

const RNRuntimeView = requireNativeComponent('RNRuntimeView', null);

class RuntimeView extends RNRuntimeView {
  finishPurchase(purchases) {
      return new Promise((resolve, reject) => {
          finishPurchase(
              findNodeHandle(this), purchases
          );
      });
  }

  finishAchievementUnlock(achievements) {
      return new Promise((resolve, reject) => {
          finishAchievementUnlock(
              findNodeHandle(this), achievements
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
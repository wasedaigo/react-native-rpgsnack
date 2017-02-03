import { NativeModules } from "react-native";
const { RNRuntimeViewManager } = NativeModules;

export const finishPurchase = (handle, purchases) => RNRuntimeViewManager.finishPurchase(handle, purchases);
export const finishAchievementUnlock = (handle, achievements) => RNRuntimeViewManager.finishAchievementUnlock(handle, achievements);
export const finishProgressSave = (handle) => RNRuntimeViewManager.finishProgressSave(handle);
export const finishInterstitialAds = (handle) => RNRuntimeViewManager.finishInterstitialAds(handle);
export const finishRewardedAds = (handle, rewarded) => RNRuntimeViewManager.finishRewardedAds(handle, rewarded);
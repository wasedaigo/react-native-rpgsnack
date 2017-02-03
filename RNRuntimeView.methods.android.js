import {NativeModules} from "react-native";
const {UIManager} = NativeModules;
const {RNRuntimeView} = UIManager;
const {Commands} = RNRuntimeView;

console.log('Commands.finishInterstitialAds', Commands.finishInterstitialAds);
console.log('Commands', Commands);

export const finishPurchase = (handle, purchases) => UIManager.dispatchViewManagerCommand(handle, Commands.finishPurchase, [ purchases ]);
export const finishAchievementUnlock = (handle, achievements) => UIManager.dispatchViewManagerCommand(handle, Commands.finishAchievementUnlock, [ achievements ]);
export const finishProgressSave = (handle) => UIManager.dispatchViewManagerCommand(handle, Commands.finishProgressSave, []);
export const finishInterstitialAds = (handle) => UIManager.dispatchViewManagerCommand(handle, Commands.finishInterstitialAds, []);
export const finishRewardedAds = (handle, rewarded) => UIManager.dispatchViewManagerCommand(handle, Commands.finishRewardedAds, [rewarded]);
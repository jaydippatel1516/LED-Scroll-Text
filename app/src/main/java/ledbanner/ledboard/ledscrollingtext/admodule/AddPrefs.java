package ledbanner.ledboard.ledscrollingtext.admodule;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AddPrefs {
    public SharedPreferences prefs;
    public Editor editor;

    public AddPrefs(Context context) {
        prefs = context.getSharedPreferences("USER PREFS", Context.MODE_PRIVATE);
        editor = this.prefs.edit();
    }

    public void setAdmAppOpenId(String sid) {
        editor.putString("admappid", sid).commit();
    }

    public String getAdmAppOpenId() {
        return prefs.getString("admappid", "ca");
    }

    public void setAdmBannerId(String sid) {
        editor.putString("admbannerid", sid).commit();
    }

    public String getAdmBannerId() {
        return prefs.getString("admbannerid", "ca");
    }

    public void setAdmInterId(String sid) {
        editor.putString("adminterid", sid).commit();
    }

    public String getAdmInterId() {
        return prefs.getString("adminterid", "ca");
    }

    public void setSecAdmInterId(String sid) {
        editor.putString("adminterid2", sid).commit();
    }

    public String getSecAdmInterId() {
        return prefs.getString("adminterid2", "ca");
    }

    public void setAdmNativeId(String sid) {
        editor.putString("admnativeid", sid).commit();
    }

    public String getAdmNativeId() {
        return prefs.getString("admnativeid", "ca");
    }

    public void setsecAdmNativeId(String sid) {
        editor.putString("admnativeid2", sid).commit();
    }

    public String getsecAdmNativeId() {
        return prefs.getString("admnativeid2", "ca");
    }

    public String getRewardId() {
        return prefs.getString("rewardId", "ca");
    }

    public void setRewardId(String sid) {
        editor.putString("rewardId", sid).commit();
    }

    public void setFBbannerId(String sid) {
        editor.putString("fbbannerid", sid).commit();
    }

    public String getFBbammerId() {
        return prefs.getString("fbbannerid", "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_I");
    }

    public void setFBinterId(String sid) {
        editor.putString("fbinterid", sid).commit();
    }

    public String getFBinterId() {
        return prefs.getString("fbinterid", "CAROUSEL_IMG_SQUARE_APP_INSTALL#YOUR_PLACEMENT_I");
    }

    public void setFBnativeBannerId(String sid) {
        editor.putString("fbnativebannerid", sid).commit();
    }

    public String getFBnativeBannerId() {
        return prefs.getString("fbnativebannerid", "CAROUSEL_IMG_SQUARE_APP_INSTALL#YOUR_PLACEMENT_I");
    }


    public void setAdmShowclick(int num) {
        editor.putInt("AdmShowclick", num).commit();
    }

    public int getAdmShowclick() {
        return prefs.getInt("AdmShowclick", 1);
    }



    public void setFBnativeId(String sid) {
        editor.putString("fbnativeid", sid).commit();
    }

    public String getFBnativeId() {
        return prefs.getString("fbnativeid", "CAROUSEL_IMG_SQUARE_APP_INSTALL#YOUR_PLACEMENT_I");
    }

    public void setAlternative(int num) {
        editor.putInt("Alternativ", num).commit();
    }

    public int getAlternativ() {
        return prefs.getInt("Alternativ", 1);
    }


    public int getBackInter() {
        return prefs.getInt("BackInter", 1);
    }

    public void setButtonColor(String sid) {
        editor.putString("addButtonColor", sid).commit();
    }

    public String getButtonColor() {
        return prefs.getString("addButtonColor", "#2F9E33");
    }

    public void setCustomInterstial(int sid) {
        editor.putInt("customInterstial", sid).commit();
    }

    public int getCustomInterstial() {
        return prefs.getInt("customInterstial", 0);
    }
    public int getNativeAdmShowclick() {
        return prefs.getInt("NativeAdmShowclick", 1);
    }

    public void setNativeAdmShowclick(int num) {
        editor.putInt("NativeAdmShowclick", num).commit();
    }

    public boolean getGuideView() {
        return prefs.getBoolean("guideView", false);
    }

    public void setGuideView(boolean sid) {
        editor.putBoolean("guideView", sid).commit();
    }

    public void setExitNative(String sid) {
        editor.putString("exitNative", sid).commit();
    }

    public String getExitNative() {
        return prefs.getString("exitNative", "0");
    }

    public void setAdsOnOff(int sid) {
        editor.putInt("ads_on_off", sid).commit();
    }

    public int getAdsOnOff() {
        return prefs.getInt("ads_on_off", 0);
    }

}

package com.kimisi.hb_fix;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

class GameManager {
    private static String name = "";

    static boolean train = false;
    static int drug = 0;

    static boolean fastFinish = false;
    static boolean theSecondFinish = false;
    static boolean SecretFinish = false;

    public static void setFull(Activity activity){
        // Fullscreen mode
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public static void setName(String name){
        GameManager.name = name;
    }

    public static String getName(){
        return name;
    }

}

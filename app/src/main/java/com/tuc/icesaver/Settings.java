package com.tuc.icesaver;

import android.content.Context;

public class Settings {

    public static boolean isFirstRun = false;
    public static Context ctx;

    public static int age = 0;
    public static boolean car = false;
    public static boolean beef = false;
    public static int beefFrequency = -1;
    public static boolean milk = false;
    public static void save() {
        FileUtil.writeKey("age",""+age,ctx);
        FileUtil.writeKey("car",""+car,ctx);
        FileUtil.writeKey("beef",""+beef,ctx);
        if(beef)
            FileUtil.writeKey("beefFrequency",""+beefFrequency,ctx);
        FileUtil.writeKey("milk",""+milk,ctx);

    }

    public static void load() {
        age = Integer.parseInt(FileUtil.readKey("age"));
        car = Boolean.parseBoolean(FileUtil.readKey("car"));
        beef = Boolean.parseBoolean(FileUtil.readKey("beef"));
        if(beef)
            beefFrequency = Integer.parseInt(FileUtil.readKey("beefFrequency"));
        milk =  Boolean.parseBoolean(FileUtil.readKey("milk"));
    }

}

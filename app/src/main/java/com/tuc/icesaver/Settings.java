package com.tuc.icesaver;

import android.content.Context;

class Settings {

    static boolean isFirstRun = false;
    static Context ctx;
    static long lastTime = 0;
    static long pengiunsDied = 0;
    static double lastIceMelt1 = 0;
    static double lastIceMeltAll = 0;
    static int age = 0;
    static boolean car = false;
    static boolean beef = false;
    static int beefFrequency = -1;
    static boolean milk = false;

    static void save() {
        FileUtil.writeKey("age", "" + age, ctx);
        FileUtil.writeKey("car", "" + car, ctx);
        FileUtil.writeKey("beef", "" + beef, ctx);
        if (beef)
            FileUtil.writeKey("beefFrequency", "" + beefFrequency, ctx);
        FileUtil.writeKey("milk", "" + milk, ctx);
        FileUtil.writeKey("iceAll", "" + lastIceMeltAll, ctx);
        FileUtil.writeKey("lastTime", "" + lastTime, ctx);
        FileUtil.writeKey("penguins", "" + pengiunsDied, ctx);
        FileUtil.writeKey("iceMelt",""+lastIceMelt1,ctx);
    }

    static void load() {
        age = Integer.parseInt(FileUtil.readKey("age"));
        car = Boolean.parseBoolean(FileUtil.readKey("car"));
        beef = Boolean.parseBoolean(FileUtil.readKey("beef"));
        if (beef)
            beefFrequency = Integer.parseInt(FileUtil.readKey("beefFrequency"));
        milk = Boolean.parseBoolean(FileUtil.readKey("milk"));
        lastIceMeltAll = Double.parseDouble(FileUtil.readKey("iceAll"));
        lastTime = Long.parseLong(FileUtil.readKey("lastTime"));
        pengiunsDied = Long.parseLong(FileUtil.readKey("penguins"));
        lastIceMelt1 = Double.parseDouble(FileUtil.readKey("iceMelt"));
    }

}

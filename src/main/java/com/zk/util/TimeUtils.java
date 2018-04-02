package com.zk.util;

public class TimeUtils {
    public static String intervalToString(long createdTimestamp)
    {
        long timeInterval = System.currentTimeMillis() - createdTimestamp;
        if(timeInterval < 1_000)
            return "less than one second";
        if(timeInterval < 60_000)
            return (timeInterval / 1_000) + " seconds";
        return "about " + (timeInterval / 60_000) + " minutes";
    }
}

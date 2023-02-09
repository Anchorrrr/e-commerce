package utils;


import java.util.UUID;

//获取uuid
public class UidUtils {

    public static String getUid() {
        return UUID.randomUUID().toString();
    }
}

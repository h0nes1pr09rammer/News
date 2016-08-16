package com.ctwl.lzq.howmuchanimation.Utils;

/**
 * Created by h0nes1pr09rammer on 2016/8/16.
 */
public class UpdataUtils {

    public static native int patch(String oldApkPath, String newApkPath,
                                   String patchPath);

    public static void loadLib(){
        System.loadLibrary("ApkPatchLibrary");
    }

}

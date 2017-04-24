package com.thundercomm.libimageloader;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by yuanml0715 on 2017/4/24.
 */

public final class CloseUtils {
    private CloseUtils(){};
    public static void closeQuietly(Closeable closeable) {
        if (null != closeable)
        {
            try {
                closeable.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

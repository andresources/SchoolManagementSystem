package com.imgzoom;

import java.io.File;

public interface SaveFileListener {

    void onSuccess(File file);

    void onFail(Exception exception);

}

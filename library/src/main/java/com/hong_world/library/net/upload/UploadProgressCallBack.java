package com.hong_world.library.net.upload;

/**
 * Date: 2018/11/22. 10:34
 * Author: hong_world
 * Description:
 * Version:
 */
public interface UploadProgressCallBack {
    /**
     * 回调进度
     *
     * @param bytesWritten  当前读取响应体字节长度
     * @param contentLength 总长度
     * @param done          是否读取完成
     */
    void onResponseProgress(long bytesWritten, long contentLength, boolean done);
}

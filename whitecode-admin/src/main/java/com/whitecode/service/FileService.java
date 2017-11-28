package com.whitecode.service;

import com.whitecode.dto.MultipartFileParam;

/**
 * Created by White on 2017/11/27.
 */
public interface FileService {
    // 文件分片上传
    void uploadFileRandomAccessFile(MultipartFileParam param) throws Exception;

    // 检查分片是否完成
    boolean checkAndSetUploadProgerss(MultipartFileParam param, String uploadDirPath) throws Exception;
}

package com.whitecode.service.impl;

import com.whitecode.service.FileService;
import com.whitecode.dto.MultipartFileParam;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by White on 2017/11/27.
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private static final String FINAL_DIR_PATH = "E:/webuploader/";
    private static final long CHUNK_SIZE = 5 * 1024 *1024;
    @Override
    public void uploadFileRandomAccessFile(MultipartFileParam param) throws Exception {
        String fileName = param.getName();
        String finalDirPath = FINAL_DIR_PATH;
        String tempDirPath = finalDirPath + param.getId();
        String tempFileName = fileName + "_tmp";
        File tmpDir = new File(tempDirPath);
        File tmpFile = new File(tempDirPath, tempFileName);
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }

        RandomAccessFile accessTmpFile = new RandomAccessFile(tmpFile, "rw");
        long offset = CHUNK_SIZE * param.getChunk();
        //定位到该分片的偏移量
        accessTmpFile.seek(offset);
        //写入该分片数据
        accessTmpFile.write(param.getFile().getBytes());
        accessTmpFile.close();

        if (checkAndSetUploadProgerss(param,tempDirPath)) {
            renameFile(tmpFile,fileName);
        }
    }

    @Override
    public boolean checkAndSetUploadProgerss(MultipartFileParam param, String uploadDirPath) throws Exception {
        File confFile = new File(uploadDirPath, param.getName() + ".conf");
        RandomAccessFile accessConfFile = new RandomAccessFile(confFile, "rw");
        accessConfFile.setLength(param.getChunks());
        accessConfFile.seek(param.getChunk());
        accessConfFile.write(Byte.MAX_VALUE);
        //completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
        byte[] completeList = FileUtils.readFileToByteArray(confFile);
        byte isComplete = Byte.MAX_VALUE;
        for (int i = 0; i < completeList.length && isComplete==Byte.MAX_VALUE; i++) {
            //与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
            isComplete = (byte)(isComplete & completeList[i]);
            logger.info("check part " + i + " complete?:" + completeList[i]);
        }
        accessConfFile.close();

        if (isComplete == Byte.MAX_VALUE) {
           logger.info("upload complete !!");
           return true;
        }
        return false;
    }

    // 重命名
    public boolean renameFile(File toBeRenamed, String toFileNewName) {
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
            return false;
        }
        String p = toBeRenamed.getParent();
        logger.info("p:"+p + File.separatorChar + toFileNewName);
        File newFile = new File(p + File.separatorChar + toFileNewName);
        //修改文件名
        return toBeRenamed.renameTo(newFile);
    }
}

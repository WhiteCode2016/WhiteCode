package com.whitecode.dto;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * WebUploader参数列表
 * Created by White on 2017/11/27.
 */
public class MultipartFileParam {
    // 文件Id
    private String id;
    // 总分片数量
    private int chunks;
    // 当前为第几块分片
    private int chunk;
    // 文件大小
    private long size = 0L;
    // 文件名
    private String name;
    // MD5
    private String md5;
    // 分片对象
    private MultipartFile file;

    //请求中附带的自定义参数
    private HashMap<String, String> param = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getChunks() {
        return chunks;
    }

    public void setChunks(int chunks) {
        this.chunks = chunks;
    }

    public int getChunk() {
        return chunk;
    }

    public void setChunk(int chunk) {
        this.chunk = chunk;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public HashMap<String, String> getParam() {
        return param;
    }

    public void setParam(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "MultipartFileParam{" +
                "id='" + id + '\'' +
                ", chunks=" + chunks +
                ", chunk=" + chunk +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", md5='" + md5 + '\'' +
                ", file=" + file +
                ", param=" + param +
                '}';
    }
}

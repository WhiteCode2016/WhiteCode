package com.whitecode.tools;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.SocketException;

/**
 * FTP文件上传/下载工具类
 * Created by White on 2017/10/27.
 */
public class FTPUtils {
    private static final Logger logger = LoggerFactory.getLogger(FTPUtils.class);

    private static final String FTP_HOST = "192.168.109.130";
    private static final int FTP_PORT = 21;

    /**
     * 获取FTPClient连接
     * @param host
     * @param port
     * @param userName
     * @param password
     * @return
     */
    private static FTPClient getFTPClient(String host, int port, String userName, String password) {
        FTPClient ftpClient = new FTPClient();
        try {
            // 连接FTP服务器
            ftpClient.connect(host,port);
            // 登录FTP服务器
            ftpClient.login(userName,password);
            // 是否成功登录FTP服务器
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("未连接到FTP服务器，用户名或密码错误。");
                ftpClient.disconnect();
            }
        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }


    /**
     * FTP文件上传
     * @param host
     * @param port
     * @param userName
     * @param password
     * @param ftpPath
     * @param filePath
     */
    public static void uploadFile(String host, int port, String userName,
                                  String password, String ftpPath, String filePath) {
        try {
            FTPClient ftpClient = getFTPClient(host,port,userName,password);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.enterLocalPassiveMode();
//            ftpClient.makeDirectory(ftpPath);
            ftpClient.changeWorkingDirectory(ftpPath);
            InputStream inputStream = new FileInputStream(filePath);
            ftpClient.storeFile(ftpPath,inputStream);
            inputStream.close();
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FTP文件下载（获取某目录下的所有文件）
     * @param host
     * @param port
     * @param userName
     * @param password
     * @param ftpPath
     * @param filePath
     */
    public static void downloadFile(String host, int port, String userName,
                                    String password, String ftpPath, String filePath) {
        try {
            FTPClient ftpClient = getFTPClient(host,port,userName,password);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            // 获取ftp服务器目录下的文件
            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile ff : fs) {
                File outFile = new File(filePath + "/" + ff.getName());
                OutputStream outputStream = new FileOutputStream(outFile);
                boolean c =ftpClient.retrieveFile(ff.getName(),outputStream);
                outputStream.close();
            }
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        uploadFile(FTP_HOST, FTP_PORT,"anonymous","", "/upload/w.txt","E:/temp/hello.txt");
//        downloadFile(FTP_HOST, FTP_PORT,"anonymous","","/upload","E:/temp");
//        downloadFile(FTP_HOST, FTP_PORT,"anonymous","","/upload","E:/temp");
        uploadFile(FTP_HOST, FTP_PORT,"anonymous","", "/upload/1.jpg","C:/Users/White/Downloads/006PFMvoly1fke7jdlnb6j30e2cmuhdv.jpg");
    }
}

package com.cqy.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UpLoadAction extends ActionSupport {
    private Map<String, Object> dataMap;
    private Map<String, Object> data;
    private Map<String, Object> data2;

    /**
     * 构造方法
     */
    public UpLoadAction() {
        //初始化Map对象
        dataMap = new HashMap<String, Object>();
        data = new HashMap<String, Object>();
        data2 = new HashMap<String, Object>();
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }



    /**
     * 上传文件
     */
    public String upLoadImage() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
        String path= request.getSession().getServletContext().getRealPath("upload");
        UploadedFile file = wrapper.getFiles("file")[0];
        String fileName = wrapper.getFileNames("file")[0];
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        InputStream in = new FileInputStream((File) file.getContent());
        File uploadFile = new File(path, newFileName);
        OutputStream out = new FileOutputStream(uploadFile);
        byte[] buffer = new byte[1024 * 1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        String truePath=request.getContextPath() + "/" + "upload" + "/" + newFileName;
        System.out.println("现在是上传文件名为"+newFileName+"到"+path);
        in.close();
        out.close();
        data2.put("filePath",truePath);
        data.put("data", data2);
        dataMap.put("results", data);
        dataMap.put("errorNo", "0");
        return SUCCESS;
    }
}
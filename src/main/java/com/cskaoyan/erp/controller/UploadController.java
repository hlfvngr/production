package com.cskaoyan.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UploadController {

    //上传图片
    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map<String,Object> picUpload(MultipartFile uploadFile, HttpSession session){
        //{"error":0,"url":"/pic/1544982091683080.jpeg"}
        Map<String,Object> result = new HashMap<>();
        String originalFilename = uploadFile.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String savePath = UploadController.class.getClassLoader().getResource("../pic").getPath() + newFileName;
        File file = new File(savePath);
        try {
            uploadFile.transferTo(file);
            result.put("error",0);
            result.put("url","/pic/" + newFileName);

            String imgs = (String) session.getAttribute("imgs");
            if(imgs == null || imgs.isEmpty()){
                imgs = "";
                imgs += "/pic/" + newFileName ;
            }
            else {
                imgs +=",";
                imgs += "/pic/" + newFileName ;
            }

            session.setAttribute("imgs",imgs);
        } catch (IOException e) {
            e.printStackTrace();
            result.put("error",1);
            result.put("url",null);
        }

        return result;
    }

    //删除图片
    @RequestMapping("/pic/delete")
    @ResponseBody
    public Map<String,Object> picDelete(String picName,HttpSession session){
        //{"data":"success"}
        Map<String,Object> result = new HashMap<>();

        //从session中取(增加时)
        String imgs = (String) session.getAttribute("imgs");
        if(imgs == null){
            //从数据库中进行查找(编辑时)
            String deleteImg = (String) session.getAttribute("deleteImg");
            if(deleteImg == null || deleteImg.isEmpty()){
                deleteImg = "";
                deleteImg += "/pic/" + picName ;
            } else {
                deleteImg +=",";
                deleteImg += "/pic/" + picName ;
            }
            session.setAttribute("deleteImg",deleteImg);
            result.put("data","success");
            return result;
        }

        if(imgs.startsWith(picName)){
            imgs.replace(picName,"");
        }else {
            imgs.replace("," + picName,"");
        }
        result.put("data","success");
        return result;
    }

    //上传附件
    @RequestMapping("/file/upload")
    @ResponseBody
    public Map<String,Object> fileUpload(MultipartFile file, HttpSession session){
        //{"error":0,"url":"/file/D:\\upload\\temp\\file\\\\taskkill.txt"}
        Map<String,Object> result = new HashMap<>();
        String originalFilename = file.getOriginalFilename();
        String savePath = UploadController.class.getClassLoader().getResource("../file").getPath() + originalFilename;
        File f = new File(savePath);
        try {
            file.transferTo(f);
            result.put("error",0);
            result.put("url","/file/" + originalFilename);

            String files = (String) session.getAttribute("files");
            if(files == null || files.isEmpty()){
                files = "";
                files += "/file/" + originalFilename ;
            }
            else {
                files +=",";
                files += "/file/" + originalFilename ;
            }
            session.setAttribute("files",files);
        } catch (IOException e) {
            e.printStackTrace();
            result.put("error",1);
            result.put("url",null);
        }


        return result;
    }

    //删除附件
    @RequestMapping("/file/delete")
    @ResponseBody
    public Map<String,Object> fileDelete(String fileName,HttpSession session){
        //{"data":"success"}
        Map<String,Object> result = new HashMap<>();
        String files = (String) session.getAttribute("files");

        //从session中取(增加时)
        if(files == null){
            //从数据库中进行查找(编辑时)
            String deleteFile = (String) session.getAttribute("deleteFile");
            if(deleteFile == null || deleteFile.isEmpty()){
                deleteFile = "";
                deleteFile += "/file/" + fileName ;
            } else {
                deleteFile +=",";
                deleteFile += "/file/" + fileName ;
            }
            session.setAttribute("deleteFile",deleteFile);
            result.put("data","success");
            return result;
        }

        if(files.startsWith(fileName)){
            files.replace(fileName,"");
        }else {
            files.replace("," + fileName,"");
        }
        result.put("data","success");
        return result;
    }
}

package com.how2java.springboot.web;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/9/30 下午10:54
 *  File        :   UploadController.java
 *  Description :
 */

// 上传文件，还要在配置文件设置上传文件大小。
@Controller
public class UploadController {
    @RequestMapping(value = "/uploadPage")
    public String uploadPage() {
        return "uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest req,
                         @RequestParam("file") MultipartFile file,
                         Model m) {
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String destFileName = req.getServletContext().getRealPath("") + "uploaded" + fileName;

        try {
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败，" + e.getMessage();
        }
        return "showImg";
    }
}

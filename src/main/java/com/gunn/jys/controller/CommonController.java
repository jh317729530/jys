package com.gunn.jys.controller;

import com.gunn.jys.annotation.Anon;
import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.InfoResult;
import com.gunn.jys.bo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController()
@RequestMapping("/common")
public class CommonController extends BaseController {

    @Anon
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        InfoResult result = new InfoResult();
        if (!file.isEmpty()) {
            String path = request.getServletContext().getRealPath("/");
            String fileName = file.getOriginalFilename();
            File filePath = new File(path, fileName);
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdir();
            }
            file.transferTo(new File(path + File.separator + fileName));
            result.setInfo(fileName);
            return result;
        } else {
            return new Result();
        }
    }

    @Anon
    @RequestMapping("/download")
    public void download(@RequestParam String fileName,HttpServletResponse response,HttpServletRequest request) {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            String path = request.getServletContext().getRealPath("/");
            bis = new BufferedInputStream(new FileInputStream(new File(path + File.separator + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

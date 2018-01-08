package com.orange.onlinetest.controller;

import com.orange.onlinetest.utils.ImageCheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 10:20
 */
@Controller
@RequestMapping(value = "util")
public class UtilController {
    @RequestMapping(value = "getImageCheck",method = RequestMethod.GET)
    public void getImageCheck(HttpServletResponse response, HttpSession session){
        Object[] obj = ImageCheckUtil.createImage();
        session.setAttribute("imageCode",obj[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) obj[1];
        response.setContentType("image/png");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

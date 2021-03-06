package com.syj.olb.user.web;

import com.syj.olb.common.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/verifyCode")
public class VerifyCodeController {
    @RequestMapping("/verifyCode")
    public  void getVerifyCode(HttpServletRequest request, HttpServletResponse response)throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();//获取一次性验证码图片
        // 该方法必须在getImage()方法之后来调用
//		System.out.println(vc.getText());//获取图片上的文本
        VerifyCode.output(image, response.getOutputStream());//把图片写到指定流中

        // 把文本保存到session中，为LoginServlet验证做准备
        request.getSession().setAttribute("vCode", vc.getText());
    }
}

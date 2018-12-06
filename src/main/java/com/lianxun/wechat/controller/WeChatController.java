package com.lianxun.wechat.controller;

import com.lianxun.wechat.Util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @author taorun
 * 2018/12/5 下午2:25
 */

@RestController
public class WeChatController {

    private static Logger logger = LoggerFactory.getLogger(WeChatController.class);

    /**
     * 微信公众号URL、Token验证接口
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/wx/token")
    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.error("WeChatController   ----   WeChatController");

        System.out.println("========WeChatController========= ");
        logger.info("请求进来...");

        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = request.getParameter(name);
            // out.print(name + "=" + value);

            String log = "name =" + name + "     value =" + value;
            logger.error(log);
        }

        String signature = request.getParameter("signature");/// 微信加密签名
        String timestamp = request.getParameter("timestamp");/// 时间戳
        String nonce = request.getParameter("nonce"); /// 随机数
        String echostr = request.getParameter("echostr"); // 随机字符串
        PrintWriter out = response.getWriter();

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
//        out = null;

    }

}

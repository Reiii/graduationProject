package com.yan.util;

import com.sun.mail.util.MailSSLSocketFactory;
import com.yan.domain.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;

/**
 * Package ：com.yan.util
 * Description：
 * date： 2019/1/15 下午11:45
 * author： Li KaiYan
 */

public class Utils {
    private static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static final String mail = "510602825@qq.com";
    private static final String Authorization_code = "mqprmgmmhdchbhgc";
    private static final String mail_reg = "请打开以下链接激活你的账号：\nlocalhost:8080/active?uid=%s&code=%s";
    private static final String mail_pwd = "请打开以下链接修改密码：\nlocalhost:8080/changePasswd?id=%s&Active_Code=%s";


    /**
     * 判断email格式是否合法
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        Pattern regex = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    /**
     * 发送激活码 type1为激活账号 type2为修改密码
     * @param user
     * @param type
     */
    public static void sendActive_Code(User user, int type) {
        try {
            Properties props = new Properties();
            // 开启debug调试
            props.setProperty("mail.debug", "true");
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置邮件服务器主机名
            props.setProperty("mail.host", "smtp.qq.com");
            // 发送邮件协议名称
            props.setProperty("mail.transport.protocol", "smtp");

            //qq邮箱开启ssl 需要设置ssl
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getInstance(props);

            //邮件内容
            StringBuilder text = new StringBuilder();
            String greeting = "您好，欢迎使用会议室预约系统！\n";
            String content = "";
            text.append(greeting);
            if (type == 0) {
                content = String.format(mail_reg, user.getUid(), user.getActive_code());
            } else {
                content = String.format(mail_pwd, user.getUid(), user.getActive_code());
            }
            text.append(content);
            Message msg = new MimeMessage(session);
            msg.setSubject("会议室预约系统");
            msg.setText(text.toString());
            msg.setFrom(new InternetAddress(mail));

            Transport transport = session.getTransport();
            transport.connect("smtp.qq.com", mail, Authorization_code);

            transport.sendMessage(msg, new Address[]{new InternetAddress(user.getEmail())});
            transport.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

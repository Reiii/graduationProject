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

    private static final String str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static final String mail = "510602825@qq.com";
    private static final String Authorization_code = "dbuzjwsaqhefbggb";
    private static final String mail_reg = "请打开以下链接激活你的账号：\nlocalhost:8080/user/active?uid=%s&code=%s";
    private static final String mail_pwd = "请打开以下链接修改密码：\nlocalhost:8080/user/setPassword/verify?uid=%s&code=%s";


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

    public static String getActiveCode(){
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        for(int i = 0; i < 32; i++){
            sb.append(str.charAt(rd.nextInt(62)));
        }
        return sb.toString();
    }

    /**
     * 检测是否经过md5加密
     *
     * @param msg
     * @return
     */
    public static boolean isValidMessageAudio(String msg) {
        int cnt = 0;
        for (int i = 0; i < msg.length(); ++i) {
            switch (msg.charAt(i)) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                    ++cnt;
                    if (32 <= cnt) return true;
                    break;
                case '/':
                    if ((i + 10) < msg.length()) {// "/storage/"
                        char ch1 = msg.charAt(i + 1);
                        char ch2 = msg.charAt(i + 8);
                        if ('/' == ch2 && ('s' == ch1 || 'S' == ch1)) return true;
                    }
                default:
                    cnt = 0;
                    break;
            }
        }
        return false;
    }
}

package com.yan.controller;

import com.yan.constant.loginRequire;
import com.yan.domain.Post;
import com.yan.domain.Subject_area;
import com.yan.domain.Theme_sticker;
import com.yan.domain.User;
import com.yan.exception.ForumException;
import com.yan.service.impl.ForumServiceImpl;
import com.yan.util.Page;
import com.yan.util.Status;
import com.yan.util.StatusMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Package ：com.yan.controller
 * Description：
 * date： 2019/1/22 上午10:45
 * author： Li KaiYan
 */

@RestController
@RequestMapping(value = "/forum")
public class ForumController {

    @Autowired
    private ForumServiceImpl forumService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        return new ModelAndView("forumHome");
    }

    @RequestMapping(value = "/getAllSubjectArea", method = RequestMethod.GET)
    public Subject_area[] getAllSubjectArea(){
        Subject_area[] areas = forumService.getAllSubject_area();
        return areas;
    }

    @RequestMapping(value = "/getSubjectArea", method = RequestMethod.GET)
    public ModelAndView getSubjectArea(@Param("subject_id") String subject_id, HttpSession session){
        session.setAttribute("subject_id", subject_id);
        return new ModelAndView("SubjectArea");
    }

    @RequestMapping(value = "/getAreaDetail", method = RequestMethod.GET)
    public Subject_area getAreaDetail(HttpSession session){
        String subject_id = (String)session.getAttribute("subject_id");
        session.removeAttribute("subject_id");
        Subject_area subject_area = new Subject_area();
        subject_area.setSubject_id(subject_id);
        Subject_area area = forumService.getSubject_areaById(subject_area);
        return area;
    }

    @RequestMapping(value = "/getStickers", method = RequestMethod.GET)
    public Page<Map<String, Object>> getStickers(@RequestParam(value = "subject_id") String subject_id, @RequestParam(value = "page", defaultValue = "1") String page) throws ForumException {
        Subject_area subject_area = new Subject_area();
        subject_area.setSubject_id(subject_id);
        Page<Map<String, Object>> stickers = forumService.getTheme_stickersBySubject_id(subject_area, page);
        return stickers;
    }

    @RequestMapping(value = "/getStickerDetail", method = RequestMethod.GET)
    public ModelAndView getStickerDetail(@Param("theme_id") String theme_id, HttpSession session){
        session.setAttribute("theme_id", theme_id);
        return new ModelAndView("ThemeSticker");
    }

    @RequestMapping(value = "/getThemeSticker", method = RequestMethod.GET)
    public Theme_sticker getThemeSticker(HttpSession session){
        String theme_id = (String)session.getAttribute("theme_id");
        Theme_sticker theme_sticker = new Theme_sticker();
        theme_sticker.setTheme_id(theme_id);
        Theme_sticker sticker_in_db = forumService.getTheme_stickerById(theme_sticker);
        return sticker_in_db;
    }

    @RequestMapping(value = "/getPosts", method = RequestMethod.GET)
    public Page<Map<String, Object>> getPosts(@Param("theme_id") String theme_id, @RequestParam(value = "page", defaultValue = "1") String page) throws ForumException{
        Theme_sticker sticker = new Theme_sticker();
        sticker.setTheme_id(theme_id);
        Page<Map<String, Object>> PostPage = forumService.getPostsByTheme_sticker(sticker, page);
        return PostPage;
    }

    @loginRequire
    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public Status addPost(HttpSession session, @Param("theme_id") String theme_id, @Param("content") String content){
        Status status = new Status();
        User user = (User)session.getAttribute("user");
        Post post = new Post();
        post.setTheme_id(theme_id);
        post.setContent(content);
        post.setType("0");
        post.setStatus("0");
        if(forumService.addPost(post, user)){
            status.setStatus(StatusMsg.ADD_SUCCESS);
        }else{
            status.setStatus(StatusMsg.ADD_FAILED);
        }
        return status;
    }

    @RequestMapping(value = "/getStickersByClass", method = RequestMethod.GET)
    public Page<Map<String, Object>> getStickerByClass(@Param("subject_id") String subject_id, @Param("classification") String classification, @RequestParam(value = "page", defaultValue = "1") String page) throws ForumException{
        Theme_sticker theme_sticker = new Theme_sticker();
        theme_sticker.setSubject_id(subject_id);
        theme_sticker.setClassification(classification);
        Page<Map<String, Object>> stickerPage = forumService.getTheme_stickerByClassification(theme_sticker, page);
        return stickerPage;
    }

    @RequestMapping(value = "/getStickersByTitle", method = RequestMethod.GET)
    public Page<Map<String, Object>> getStickerByTitle(@Param("title") String title, @RequestParam(value = "page", defaultValue = "1") String page) throws ForumException{
        Theme_sticker theme_sticker = new Theme_sticker();
        theme_sticker.setTitle(title);
        Page<Map<String, Object>> stickerPage = forumService.getTheme_stickerByTitle(theme_sticker, page);
        return stickerPage;
    }

    @loginRequire
    @RequestMapping(value = "/addThemeSticker", method = RequestMethod.POST)
    public Status addSticker(@RequestParam("title") String title, @RequestParam("classification") String classification, @RequestParam("content") String content, @RequestParam("subject_id") String subject_id, HttpSession session){
        Status status = new Status();
        User user = (User)session.getAttribute("user");
        Theme_sticker theme_sticker = new Theme_sticker();
        theme_sticker.setTitle(title);
        theme_sticker.setClassification(classification);
        theme_sticker.setSubject_id(subject_id);
        boolean isSuccess = forumService.addTheme_sticker(theme_sticker, user);
        if(isSuccess){
            Post post = new Post();
            post.setTheme_id(theme_sticker.getTheme_id());
            post.setContent(content);
            post.setType("0");
            post.setStatus("0");
            forumService.addPost(post, user);
            status.setStatus(StatusMsg.ADD_SUCCESS);
        }else{
            status.setStatus(StatusMsg.ADD_FAILED);
        }
        return status;
    }

    @loginRequire
    @RequestMapping(value = "/myStickers", method = RequestMethod.GET)
    public Theme_sticker[] myStickers(HttpSession session){
        User user = (User)session.getAttribute("user");
        Theme_sticker[] stickers = forumService.getTheme_stickerByUser(user);
        return stickers;
    }

    @RequestMapping(value = "/getClassification", method = RequestMethod.GET)
    public List<String> getClassification() {
        return forumService.getClassification();
    }


    @loginRequire
    @RequestMapping(value = "/releaseSticker", method = RequestMethod.GET)
    public ModelAndView releaseSticker(){
        return new ModelAndView("releaseSticker");
    }

}

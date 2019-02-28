package com.yan.controller;

import com.yan.domain.Subject_area;
import com.yan.domain.Theme_sticker;
import com.yan.exception.ForumException;
import com.yan.service.impl.ForumServiceImpl;
import com.yan.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
        return new ModelAndView("subjectArea");
    }

    @RequestMapping(value = "/getAreaDetail", method = RequestMethod.GET)
    public Subject_area getAreaDetail(HttpSession session){
        String subject_id = (String)session.getAttribute("subject_id");
        Subject_area subject_area = new Subject_area();
        subject_area.setSubject_id(subject_id);
        Subject_area area = forumService.getSubject_areaById(subject_area);
        return area;
    }

    @RequestMapping(value = "/getStickers", method = RequestMethod.GET)
    public Page<Theme_sticker> getStickers(HttpSession session, @RequestParam(value = "page", defaultValue = "1") String page) throws ForumException {
        String subject_id = (String)session.getAttribute("subject_id");
        Subject_area subject_area = new Subject_area();
        subject_area.setSubject_id(subject_id);
        Page<Theme_sticker> stickers = forumService.getTheme_stickersBySubject_id(subject_area, page);
        return stickers;
    }

}

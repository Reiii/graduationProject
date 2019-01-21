package com.yan.service.impl;

import com.yan.dao.ForumMapper;
import com.yan.domain.Post;
import com.yan.domain.Subject_area;
import com.yan.domain.Theme_sticker;
import com.yan.domain.User;
import com.yan.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Package ：com.yan.service.impl
 * Description：
 * date： 2019/1/21 上午10:40
 * author： Li KaiYan
 */

public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumMapper forumMapper;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public Boolean addSubject_area(Subject_area subject_area, User user) {
        if("0".equals(user.getType())){
            forumMapper.addSubjectArea(subject_area);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delSubject_area(Subject_area subject_area, User user) {
        if("0".equals(user.getType())){
            forumMapper.delSubjectArea(subject_area);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateSubject_area(Subject_area subject_area, User user) {
        if("0".equals(user.getType())){
            lock.writeLock().lock();
            try{
                Subject_area area_in_db = forumMapper.selectSubjectAreaById(subject_area);
                area_in_db.setName(subject_area.getName());
                forumMapper.updateSubjectArea(area_in_db);
                return true;
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                lock.writeLock().unlock();
            }
        }
        return false;
    }

    @Override
    public Subject_area getSubject_areaById(String id) {
        Subject_area subject_area = new Subject_area();
        subject_area.setSubject_id(id);
        return forumMapper.selectSubjectAreaById(subject_area);
    }

    @Override
    public Subject_area[] getAllSubject_area() {
        return forumMapper.selectAllSubjectArea();
    }

    @Override
    public Boolean addTheme_sticker(Theme_sticker theme_sticker, User user) {
        if("1".equals(user.getStatus())){
            theme_sticker.setUid(user.getUid());
            forumMapper.addThemeSticker(theme_sticker);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delTheme_sticker(Theme_sticker theme_sticker, User user) {
        Theme_sticker theme_in_db = forumMapper.selectThemeStickerById(theme_sticker);
        if(theme_in_db.getUid().equals(user.getUid())){
            for
        }
    }

    @Override
    public Boolean updateTheme_sticker(Theme_sticker theme_sticker, User user) {
        return null;
    }

    @Override
    public Theme_sticker getTheme_stickerById(String id) {
        return null;
    }

    @Override
    public Theme_sticker[] getTheme_stickersBySubject_id(String id) {
        return new Theme_sticker[0];
    }

    @Override
    public Theme_sticker[] getTheme_stickerByTitle(String title) {
        return new Theme_sticker[0];
    }

    @Override
    public Theme_sticker[] getTheme_stickerByClassification(String classification, String subject_id) {
        return new Theme_sticker[0];
    }

    @Override
    public Theme_sticker[] getTheme_stickerByUser(User user) {
        return new Theme_sticker[0];
    }

    @Override
    public Theme_sticker[] getTheme_stickerByType(String type, String subject_id) {
        return new Theme_sticker[0];
    }

    @Override
    public Theme_sticker[] getTheme_stickerByStatus(String status) {
        return new Theme_sticker[0];
    }

    @Override
    public Boolean addPost(Post post, User user) {
        return null;
    }

    @Override
    public Boolean delPost(Post post, User user) {
        return null;
    }

    @Override
    public Boolean updatePost(Post post, User user) {
        return null;
    }

    @Override
    public Post getPostById(String id) {
        return null;
    }

    @Override
    public Post[] getPostsByTheme_sticker(Theme_sticker sticker) {
        return new Post[0];
    }

    @Override
    public Post[] getPostsByUser(User user) {
        return new Post[0];
    }

    @Override
    public Post[] getPostsByStatus(String status) {
        return new Post[0];
    }
}

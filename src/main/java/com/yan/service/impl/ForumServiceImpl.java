package com.yan.service.impl;

import com.yan.dao.ForumMapper;
import com.yan.domain.Post;
import com.yan.domain.Subject_area;
import com.yan.domain.Theme_sticker;
import com.yan.domain.User;
import com.yan.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.context.Theme;

import java.util.Date;
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
            lock.writeLock().lock();
            try{
                theme_sticker.setUid(user.getUid());
                forumMapper.addThemeSticker(theme_sticker);
                Subject_area subject_area = new Subject_area();
                subject_area.setSubject_id(theme_sticker.getSubject_id());
                Subject_area area_in_db = forumMapper.selectSubjectAreaById(subject_area);
                area_in_db.setTheme_num(String.valueOf(Integer.parseInt(area_in_db.getTheme_num()) + 1));
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
    public Boolean delTheme_sticker(Theme_sticker theme_sticker, User user) {
        Theme_sticker theme_in_db = forumMapper.selectThemeStickerById(theme_sticker);
        if(theme_in_db.getUid().equals(user.getUid())){
            lock.writeLock().lock();
            try{
                forumMapper.delThemeSticker(theme_sticker);
                Subject_area subject_area = new Subject_area();
                subject_area.setSubject_id(theme_sticker.getSubject_id());
                Subject_area area_in_db = forumMapper.selectSubjectAreaById(subject_area);
                area_in_db.setTheme_num(String.valueOf(Integer.parseInt(area_in_db.getTheme_num()) - 1));
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
    public Boolean updateTheme_sticker(Theme_sticker theme_sticker, User user) {
        Theme_sticker theme_in_db = forumMapper.selectThemeStickerById(theme_sticker);
        if(theme_in_db.getUid().equals(user.getUid())){
            theme_in_db.setTitle(theme_sticker.getTitle());
            theme_in_db.setClassification(theme_sticker.getClassification());
            return true;
        }
        return false;
    }

    @Override
    public Theme_sticker getTheme_stickerById(String id) {
        Theme_sticker theme_sticker = new Theme_sticker();
        theme_sticker.setTheme_id(id);
        lock.writeLock().lock();
        try{
            Theme_sticker sticker_in_db = forumMapper.selectThemeStickerById(theme_sticker);
            sticker_in_db.setViews(String.valueOf(Integer.valueOf(sticker_in_db.getViews()) + 1));
            forumMapper.updateThemeSticker(sticker_in_db);
            return sticker_in_db;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
        return null;
    }

    @Override
    public Theme_sticker[] getTheme_stickersBySubject_id(String id) {
        Subject_area subject_area = new Subject_area();
        subject_area.setSubject_id(id);
        return forumMapper.selectThemeStickerBySubjectArea(subject_area);
    }

    @Override
    public Theme_sticker[] getTheme_stickerByTitle(String title) {
        Theme_sticker theme_sticker = new Theme_sticker();
        theme_sticker.setTitle(title);
        return forumMapper.selectThemeStickerByTitle(theme_sticker);
    }

    @Override
    public Theme_sticker[] getTheme_stickerByClassification(String classification, String subject_id) {
        Theme_sticker theme_sticker = new Theme_sticker();
        theme_sticker.setSubject_id(subject_id);
        theme_sticker.setClassification(classification);
        return forumMapper.selectThemeStickerByTitle(theme_sticker);
    }

    @Override
    public Theme_sticker[] getTheme_stickerByUser(User user) {
        return forumMapper.selectThemeStickerByUser(user);
    }

    @Override
    public Theme_sticker[] getTheme_stickerByType(String type, String subject_id) {
        Theme_sticker theme_sticker = new Theme_sticker();
        theme_sticker.setType(type);
        theme_sticker.setSubject_id(subject_id);
        return forumMapper.selectThemeStickerByTitle(theme_sticker);
    }

    @Override
    public Theme_sticker[] getTheme_stickerByStatus(String status) {
        Theme_sticker theme_sticker = new Theme_sticker();
        theme_sticker.setStatus(status);
        return forumMapper.selectThemeStickerByTitle(theme_sticker);
    }

    @Override
    public Boolean addPost(Post post, User user) {
        if("1".equals(user.getStatus())){
            post.setUid(user.getUid());
            post.setTime(String.valueOf(new Date().getTime()));
            post.setStatus("0");
            forumMapper.addPost(post);
            lock.writeLock().lock();
            try{
                Theme_sticker theme_sticker = new Theme_sticker();
                theme_sticker.setSubject_id(post.getTheme_id());
                Theme_sticker sticker_in_db = forumMapper.selectThemeStickerById(theme_sticker);
                sticker_in_db.setReply_num(String.valueOf(Integer.valueOf(sticker_in_db.getReply_num()) + 1));
                sticker_in_db.setLast_reply_time(String.valueOf(new Date().getTime()));
                forumMapper.updateThemeSticker(sticker_in_db);
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
    public Boolean delPost(Post post, User user) {
        Post post_in_db = forumMapper.selectPostById(post);
        if(post_in_db.getUid().equals(user.getUid())){
            Theme_sticker theme_sticker = new Theme_sticker();
            theme_sticker.setSubject_id(post_in_db.getTheme_id());
            lock.writeLock().lock();
            try{
                forumMapper.delPost(post_in_db);
                Theme_sticker sticker_in_db = forumMapper.selectThemeStickerById(theme_sticker);
                sticker_in_db.setReply_num(String.valueOf(Integer.valueOf(sticker_in_db.getReply_num()) - 1));
                forumMapper.updateThemeSticker(sticker_in_db);
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
    public Boolean updatePost(Post post, User user) {
        Post post_in_db = forumMapper.selectPostById(post);
        if(post_in_db.getUid().equals(user.getUid())){
            Theme_sticker theme_sticker = new Theme_sticker();
            theme_sticker.setSubject_id(post_in_db.getTheme_id());
            lock.writeLock().lock();
            try{
                post_in_db.setContent(post.getContent());
                post_in_db.setTime(String.valueOf(new Date().getTime()));
                forumMapper.updatePost(post_in_db);
                Theme_sticker sticker_in_db = forumMapper.selectThemeStickerById(theme_sticker);
                sticker_in_db.setLast_reply_time(String.valueOf(new Date().getTime()));
                forumMapper.updateThemeSticker(sticker_in_db);
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
    public Post getPostById(String id) {
        Post post = new Post();
        post.setPost_id(id);
        return forumMapper.selectPostById(post);
    }

    @Override
    public Post[] getPostsByTheme_sticker(Theme_sticker sticker) {
        return forumMapper.selectPostByThemeSticker(sticker);
    }

    @Override
    public Post[] getPostsByUser(User user) {
        return forumMapper.selectPostByUser(user);
    }

    @Override
    public Post[] getPostsByStatus(String status) {
        Post post = new Post();
        post.setStatus(status);
        return forumMapper.selectPostByStatus(post);
    }
}

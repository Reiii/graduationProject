package com.yan.service.impl;

import com.yan.dao.ForumMapper;
import com.yan.dao.UserMapper;
import com.yan.domain.Post;
import com.yan.domain.Subject_area;
import com.yan.domain.Theme_sticker;
import com.yan.domain.User;
import com.yan.exception.ForumException;
import com.yan.service.ForumService;
import com.yan.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.Theme;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Package ：com.yan.service.impl
 * Description：
 * date： 2019/1/21 上午10:40
 * author： Li KaiYan
 */
@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private UserMapper userMapper;
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
    public Subject_area getSubject_areaById(Subject_area subject_area) {
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
    public Theme_sticker getTheme_stickerById(Theme_sticker theme_sticker) {
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
    public Page<Theme_sticker> getTheme_stickersBySubject_id(Subject_area subject_area, String page) throws ForumException{
        int total = forumMapper.countStickerBySubjectArea(subject_area);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new ForumException(ForumException.PAGE_OVER_LIMIT);
        }
        Theme_sticker[] stickers = forumMapper.selectThemeStickerBySubjectArea(subject_area, (Integer.parseInt(page) - 1) * 20);
        Page<Theme_sticker> stickerPage = new Page<>();
        stickerPage.setCurrentPage(Integer.parseInt(page));
        stickerPage.setStartPage(1);
        stickerPage.setEndPage(total_page);
        stickerPage.setData(Arrays.asList(stickers));
        return stickerPage;
    }

    @Override
    public Page<Theme_sticker> getTheme_stickerByTitle(Theme_sticker theme_sticker, String page) throws ForumException {
        int total = forumMapper.countStickerByTitle(theme_sticker);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new ForumException(ForumException.PAGE_OVER_LIMIT);
        }
        Theme_sticker[] stickers = forumMapper.selectThemeStickerByTitle(theme_sticker, (Integer.parseInt(page) - 1) * 20);
        Page<Theme_sticker> stickerPage = new Page<>();
        stickerPage.setCurrentPage(Integer.parseInt(page));
        stickerPage.setStartPage(1);
        stickerPage.setEndPage(total_page);
        stickerPage.setData(Arrays.asList(stickers));
        return stickerPage;
    }

    @Override
    public Theme_sticker[] getTheme_stickerByUser(User user) {
        Theme_sticker[] stickers = forumMapper.selectThemeStickerByUser(user);
        return stickers;
    }

    @Override
    public Page<Theme_sticker> getTheme_stickerByType(Subject_area subject_area, Theme_sticker theme_sticker, String page) throws ForumException{
        int total = forumMapper.countStickerByType(subject_area, theme_sticker);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new ForumException(ForumException.PAGE_OVER_LIMIT);
        }
        Theme_sticker[] stickers = forumMapper.selectThemeStickerByType(theme_sticker, (Integer.parseInt(page) - 1) * 20);
        Page<Theme_sticker> stickerPage = new Page<>();
        stickerPage.setCurrentPage(Integer.parseInt(page));
        stickerPage.setStartPage(1);
        stickerPage.setEndPage(total_page);
        stickerPage.setData(Arrays.asList(stickers));
        return stickerPage;
    }

    @Override
    public Theme_sticker[] getTheme_stickerByStatus(String status) {
        Theme_sticker theme_sticker = new Theme_sticker();
        theme_sticker.setStatus(status);
        return forumMapper.selectThemeStickerByStatus(theme_sticker);
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
    public Post getPostById(Post post) {
        return forumMapper.selectPostById(post);
    }

    @Override
    public Page<Map<String, Object>> getPostsByTheme_sticker(Theme_sticker theme_sticker, String page) throws ForumException{
        int total = forumMapper.countPostByThemeSticker(theme_sticker);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new ForumException(ForumException.PAGE_OVER_LIMIT);
        }
        Post[] posts = forumMapper.selectPostByThemeSticker(theme_sticker, (Integer.parseInt(page) - 1) * 20);
        List<Map<String, Object>> list = new ArrayList<>();
        for(Post p : posts){
            Map<String, Object> map = new HashMap<>();
            User user = new User();
            user.setUid(p.getUid());
            User user_in_db = userMapper.selectById(user);
            map.put("user", user_in_db);
            map.put("post", p);
            list.add(map);
        }
        Page<Map<String, Object>> postPage = new Page<>();
        postPage.setCurrentPage(Integer.parseInt(page));
        postPage.setStartPage(1);
        postPage.setEndPage(total_page);
        postPage.setData(list);
        return postPage;
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

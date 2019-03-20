package com.yan.service;

import com.yan.domain.Post;
import com.yan.domain.Subject_area;
import com.yan.domain.Theme_sticker;
import com.yan.domain.User;
import com.yan.exception.ForumException;
import com.yan.util.Page;

import java.util.Map;

/**
 * Package ：com.yan.service
 * Description：
 * date： 2019/1/9 下午2:13
 * author： Li KaiYan
 */

public interface ForumService {

    /**
     * 添加主题区域
     * @param subject_area
     * @param user
     * @return
     */
    Boolean addSubject_area(Subject_area subject_area, User user);

    /**
     * 删除主题区域
     * @param subject_area
     * @param user
     * @return
     */
    Boolean delSubject_area(Subject_area subject_area, User user);

    /**
     * 更新主题区域
     * @param subject_area
     * @param user
     * @return
     */
    Boolean updateSubject_area(Subject_area subject_area, User user);

    /**
     * 根据id获取主题区域
     * @param subject_area
     * @return
     */
    Subject_area getSubject_areaById(Subject_area subject_area);

    /**
     * 获取全部主题区域
     * @return
     */
    Subject_area[] getAllSubject_area();

    /**
     * 添加主题贴
     * @param theme_sticker
     * @param user
     * @return
     */
    Boolean addTheme_sticker(Theme_sticker theme_sticker, User user);

    /**
     * 删除主题贴
     * @param theme_sticker
     * @param user
     * @return
     */
    Boolean delTheme_sticker(Theme_sticker theme_sticker, User user);

    /**
     * 更新主题贴
     * @param theme_sticker
     * @param user
     * @return
     */
    Boolean updateTheme_sticker(Theme_sticker theme_sticker, User user);

    /**
     * 根据id获取主题贴
     * @param theme_sticker
     * @return
     */
    Theme_sticker getTheme_stickerById(Theme_sticker theme_sticker);

    /**
     * 根据主题区域获取主题贴
     * @param subject_area
     * @param page
     * @return
     */
    Page<Map<String, Object>> getTheme_stickersBySubject_id(Subject_area subject_area, String page) throws ForumException;

    /**
     * 根据分类获取主题贴
     * @param theme_sticker
     * @param page
     * @return
     * @throws ForumException
     */
    Page<Map<String, Object>> getTheme_stickerByClassification(Theme_sticker theme_sticker, String page) throws ForumException;


    /**
     * 根据标题获取主题贴
     * @param theme_sticker
     * @param page
     * @return
     */
    Page<Map<String, Object>> getTheme_stickerByTitle(Theme_sticker theme_sticker, String page) throws ForumException;

    /**
     * 根据用户获取主题贴
     * @param user
     * @return
     */
    Theme_sticker[] getTheme_stickerByUser(User user);

    /**
     * 根据主题贴类型（普通、加精）获取指定区域中的主题贴
     * @param subject_area
     * @param theme_sticker
     * @param page
     * @return
     */
    Page<Theme_sticker> getTheme_stickerByType(Subject_area subject_area, Theme_sticker theme_sticker, String page) throws ForumException;

    /**
     * 根据主题贴状态获取主题贴
     * @param status
     * @return
     */
    Theme_sticker[] getTheme_stickerByStatus(String status);


    /**
     * 添加帖子
     * @param post
     * @param user
     * @return
     */
    Boolean addPost(Post post, User user);

    /**
     * 删除帖子
     * @param post
     * @param user
     * @return
     */
    Boolean delPost(Post post, User user);

    /**
     * 更新帖子
     * @param post
     * @param user
     * @return
     */
    Boolean updatePost(Post post, User user);

    /**
     * 根据id获取帖子
     * @param post
     * @return
     */
    Post getPostById(Post post);

    /**
     * 根据主题贴获取帖子
     * @param sticker
     * @return
     */
    Page<Map<String, Object>> getPostsByTheme_sticker(Theme_sticker sticker, String page) throws ForumException;

    /**
     * 根据用户获取主题贴
     * @param user
     * @return
     */
    Post[] getPostsByUser(User user) throws ForumException;

    /**
     * 根据状态获取帖子
     * @param status
     * @return
     */
    Post[] getPostsByStatus(String status) throws ForumException;

}

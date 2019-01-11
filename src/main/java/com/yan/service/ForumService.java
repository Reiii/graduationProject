package com.yan.service;

import com.yan.domain.Post;
import com.yan.domain.Subject_area;
import com.yan.domain.Theme_sticker;
import com.yan.domain.User;

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
     * @param id
     * @return
     */
    Subject_area getSubject_areaById(String id);

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
     * @param id
     * @return
     */
    Theme_sticker getTheme_stickerById(String id);

    /**
     * 根据主题区域获取主题贴
     * @param id
     * @return
     */
    Theme_sticker[] getTheme_stickersBySubject_id(String id);

    /**
     * 根据标题获取主题贴
     * @param title
     * @return
     */
    Theme_sticker[] getTheme_stickerByTitle(String title);

    /**
     * 根据分类获取指定主题区域中的主题贴
     * @param classification
     * @param subject_id
     * @return
     */
    Theme_sticker[] getTheme_stickerByClassification(String classification, String subject_id);

    /**
     * 根据用户获取主题贴
     * @param user
     * @return
     */
    Theme_sticker[] getTheme_stickerByUser(User user);

    /**
     * 根据主题贴类型（普通、加精）获取指定区域中的主题贴
     * @param type
     * @param subject_id
     * @return
     */
    Theme_sticker[] getTheme_stickerByType(String type, String subject_id);

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
     * @param id
     * @return
     */
    Post getPostById(String id);

    /**
     * 根据主题贴获取帖子
     * @param sticker
     * @return
     */
    Post[] getPostsByTheme_sticker(Theme_sticker sticker);

    /**
     * 根据用户获取主题贴
     * @param user
     * @return
     */
    Post[] getPostsByUser(User user);

    /**
     * 根据状态获取帖子
     * @param status
     * @return
     */
    Post[] getPostsByStatus(String status);

}

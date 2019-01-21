package com.yan.dao;

import com.yan.domain.Post;
import com.yan.domain.Subject_area;
import com.yan.domain.Theme_sticker;
import com.yan.domain.User;

/**
 * Package ：com.yan.dao
 * Description：论坛dao接口
 * date： 2019/1/7 下午3:51
 * author： Li KaiYan
 */

public interface ForumMapper {

    /**
     * 增加主题区域
     * @param subject_area
     * @return
     */
    String addSubjectArea(Subject_area subject_area);

    /**
     * 删除主题区域
     * @param subject_area
     * @return
     */
    String delSubjectArea(Subject_area subject_area);

    /**
     * 更新主题区域
     * @param subject_area
     * @return
     */
    String updateSubjectArea(Subject_area subject_area);

    /**
     * 根据Id选择主题区域
     * @param subject_area
     * @return
     */
    Subject_area selectSubjectAreaById(Subject_area subject_area);

    /**
     * 选择全部主题区域
     * @return
     */
    Subject_area[] selectAllSubjectArea();

    /**
     * 增加主题贴
     * @param theme_sticker
     * @return
     */
    String addThemeSticker(Theme_sticker theme_sticker);

    /**
     * 删除主题贴
     * @param theme_sticker
     * @return
     */
    String delThemeSticker(Theme_sticker theme_sticker);

    /**
     * 更新主题贴
     * @param theme_sticker
     * @return
     */
    String updateThemeSticker(Theme_sticker theme_sticker);

    /**
     * 根据Id选择主题贴
     * @param theme_sticker
     * @return
     */
    Theme_sticker selectThemeStickerById(Theme_sticker theme_sticker);

    /**
     * 根据类型选择主题贴
     * @param theme_sticker
     * @return
     */
    Theme_sticker[] selectThemeStickerByType(Theme_sticker theme_sticker);

    /**
     * 根据主题区域选择主题贴
     * @param subject_area
     * @return
     */
    Theme_sticker[] selectThemeStickerBySubjectArea(Subject_area subject_area);

    /**
     * 根据用户选择主题贴
     * @param user
     * @return
     */
    Theme_sticker[] selectThemeStickerByUser(User user);

    /**
     * 根据标题选择主题贴
     * @param title
     * @return
     */
    Theme_sticker[] selectThemeStickerByTitle(Theme_sticker title);

    /**
     * 增加帖子
     * @param post
     * @return
     */
    String addPost(Post post);

    /**
     * 删除帖子
     * @param post
     * @return
     */
    String delPost(Post post);

    /**
     * 更新帖子
     * @param post
     * @return
     */
    String updatePost(Post post);

    /**
     * 根据Id选择帖子
     * @param post
     * @return
     */
    Post selectPostById(Post post);

    /**
     * 根据主题贴选择帖子
     * @param theme_sticker
     * @return
     */
    Post[] selectPostByThemeSticker(Theme_sticker theme_sticker);

    /**
     * 根据用户选择帖子
     * @param user
     * @return
     */
    Post[] selectPostByUser(User user);

    /**
     * 根据状态选择帖子
     * @param post
     * @return
     */
    Post[] selectPostByStatus(Post post);

}

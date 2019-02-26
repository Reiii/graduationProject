package com.yan.dao;

import com.yan.domain.Post;
import com.yan.domain.Subject_area;
import com.yan.domain.Theme_sticker;
import com.yan.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Package ：com.yan.dao
 * Description：论坛dao接口
 * date： 2019/1/7 下午3:51
 * author： Li KaiYan
 */
@Mapper
public interface ForumMapper {

    /**
     * 增加主题区域
     * @param subject_area
     * @return
     */
    int addSubjectArea(Subject_area subject_area);

    /**
     * 删除主题区域
     * @param subject_area
     * @return
     */
    int delSubjectArea(Subject_area subject_area);

    /**
     * 更新主题区域
     * @param subject_area
     * @return
     */
    int updateSubjectArea(Subject_area subject_area);

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
    int addThemeSticker(Theme_sticker theme_sticker);

    /**
     * 删除主题贴
     * @param theme_sticker
     * @return
     */
    int delThemeSticker(Theme_sticker theme_sticker);

    /**
     * 更新主题贴
     * @param theme_sticker
     * @return
     */
    int updateThemeSticker(Theme_sticker theme_sticker);

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
    Theme_sticker[] selectThemeStickerByType(@Param("theme_sticker") Theme_sticker theme_sticker, @Param("start") int start);

    /**
     * 根据主题区域选择主题贴
     * @param subject_area
     * @return
     */
    Theme_sticker[] selectThemeStickerBySubjectArea(@Param("subject_area") Subject_area subject_area, @Param("start") int start);

    /**
     * 根据用户选择主题贴
     * @param user
     * @return
     */
    Theme_sticker[] selectThemeStickerByUser(User user);

    /**
     * 根据标题选择主题贴
     * @param theme_sticker
     * @Param page
     * @return
     */
    Theme_sticker[] selectThemeStickerByTitle(@Param("theme_sticker") Theme_sticker theme_sticker, @Param("start") int start);

    /**
     * 根据主题贴状态选择主题贴
     * @param theme_sticker
     * @return
     */
    Theme_sticker[] selectThemeStickerByStatus(@Param("theme_sticker") Theme_sticker theme_sticker);

    /**
     * 主题区域的主题贴数量
     * @param subject_area
     * @return
     */
    int countStickerBySubjectArea(Subject_area subject_area);

    /**
     * 根据标题获取主题贴数量
     * @param theme_sticker
     * @return
     */
    int countStickerByTitle(Theme_sticker theme_sticker);

    /**
     * 根据用户获取主题贴数量
     * @param user
     * @return
     */
    int countStickerByUser(User user);

    /**
     * 根据类型获取主题贴数量
     * @param theme_sticker
     * @return
     */
    int countStickerByType(Subject_area subject_area, Theme_sticker theme_sticker);

    /**
     * 增加帖子
     * @param post
     * @return
     */
    int addPost(Post post);

    /**
     * 删除帖子
     * @param post
     * @return
     */
    int delPost(Post post);

    /**
     * 更新帖子
     * @param post
     * @return
     */
    int updatePost(Post post);

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
    Post[] selectPostByThemeSticker(@Param("theme_sticker") Theme_sticker theme_sticker, @Param("start") int start);

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

    /**
     * 根据主题贴获取帖子数量
     * @param theme_sticker
     * @return
     */
    int countPostByThemeSticker(@Param("theme_sticker") Theme_sticker theme_sticker);


}

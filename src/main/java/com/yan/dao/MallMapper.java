package com.yan.dao;

import com.yan.domain.*;
import com.yan.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Package ：com.yan.dao
 * Description：商城dao接口类
 * date： 2019/1/7 下午3:36
 * author： Li KaiYan
 */
@Mapper
public interface MallMapper {

    /**
     *  增加二手玩具
     * @param toy
     * @return
     */
    int addToy(Toy toy);

    /**
     * 根据玩具Id选择玩具
     * @param toy
     * @return
     */
    Toy selectToyById(Toy toy);

    /**
     * 根据类型选择玩具(分页)
     * @param type
     * @return
     */
    Toy[] selectToyByType_page(String type, String page);

    /**
     * 根据省份选择玩具(分页)
     * @param province
     * @return
     */
    Toy[] selectToyByProvince_page(String province, String page);

    /**
     * 根据城市选择玩具(分页)
     * @param city
     * @return
     */
    Toy[] selectToyByCity_page(String city, String page);

    /**
     * 根据用户选择玩具(分页)
     * @param user
     * @return
     */
    Toy[] selectToyByUser_page(User user, String page);

    /**
     * 根据标题选择玩具(分页)
     * @param title
     * @return
     */
    Toy[] selectToyByTitle_page(@Param("title") String title, @Param("start") int start);

    /**
     * 根据用户选择玩具
     * @param user
     * @return
     */
    Toy[] selectToyByUser(User user);

    /**
     * 删除玩具
     * @param toy
     * @return
     */
    int delToy(Toy toy);

    /**
     * 更新玩具信息
     * @param toy
     * @return
     */
    int updateToy(Toy toy);


    /**
     * 增加订单
     * @param order
     * @return
     */
    int addOrder(Order order);

    /**
     * 删除订单
     * @param order
     * @return
     */
    int delOrder(Order order);

    /**
     * 修改订单
     * @param order
     * @return
     */
    int updateOrder(Order order);

    /**
     * 根据用户选择订单
     * @param user
     * @return
     */
    Order[] selectOrderByUser(User user);

    /**
     * 根据订单id选择订单
     * @param order
     * @return
     */
    Order selectOrderById(Order order);

    /**
     * 根据订单状态选择订单
     * @param status
     * @return
     */
    Order[] selectOrderByStatus(String status);

    /**
     * 增加评论
     * @param comment
     * @return
     */
    int addComment(Comment comment);

    /**
     * 删除评论
     * @param comment
     * @return
     */
    int delComment(Comment comment);

    /**
     * 根据商品选择评论
     * @param toy
     * @return
     */
    Comment[] selectCommentByToy(Toy toy);

    /**
     * 根据用户选择评论
     * @param user
     * @return
     */
    Comment[] selectCommentByUser(User user);

    /**
     * 根据用户选择收藏商品
     * @param user
     * @return
     */
    Favorite[] selectFavoriteByUser(User user, Page page);

    /**
     * 获得所有玩具
     * @return
     */
    Toy[] selectAllToy(@Param("start") int start);

    /**
     * 计算玩具总量
     * @return
     */
    int countAllToy();

    /**
     * 根据关键字计算总量
     * @param keyword
     * @return
     */
    int countToyByKeyword(String keyword);

    /**
     * 根据类型计算总量
     * @param type
     * @return
     */
    int countToyByType(String type);

    /**
     * 根据省份计算总量
     * @param province
     * @return
     */
    int countToyByProvince(String province);

    /**
     * 根据城市计算总量
     * @param city
     * @return
     */
    int countToyByCity(String city);

    /**
     * 根据用具计算总量
     * @param user
     * @return
     */
    int countToyByUser(User user);

    /**
     * 添加收藏夹
     * @return
     */
    int addFavorite(Favorite favorite);

    /**
     * 删除收藏夹
     * @return
     */
    int delFavorite(Favorite favorite);

    /**
     * 根据收藏id选择收藏夹
     * @param favorite
     * @return
     */
    Favorite selectFavoriteById(Favorite favorite);

    /**
     * 根据玩具id获取图片（获取一张 封面）
     * @param toy
     * @return
     */
    Picture selectPictureByCommodity_id(Toy toy);

    /**
     * 根据玩具id获取图片 多张
     * @param toy
     * @return
     */
    Picture[] selectPicturesByCommodity_id(Toy toy);

    /**
     * 根据玩具选择用户
     * @param toy
     * @return
     */
    User selectUserByToy(Toy toy);
}

package com.yan.dao;

import com.yan.domain.*;

/**
 * Package ：com.yan.dao
 * Description：商城dao接口类
 * date： 2019/1/7 下午3:36
 * author： Li KaiYan
 */

public interface MallMapper {

    /**
     *  增加二手玩具
     * @param toy
     * @return
     */
    String addToy(Toy toy);

    /**
     * 根据玩具Id选择玩具
     * @param toy
     * @return
     */
    Toy selectToyById(Toy toy);

    /**
     * 根据类型选择玩具
     * @param type
     * @return
     */
    Toy[] selectToyByType(String type);

    /**
     * 根据省份选择玩具
     * @param province
     * @return
     */
    Toy[] selectToyByProvince(String province);

    /**
     * 根据城市选择玩具
     * @param city
     * @return
     */
    Toy[] selectToyByCity(String city);

    /**
     * 根据用户选择玩具
     * @param user
     * @return
     */
    Toy[] selectToyByUser(User user);

    /**
     * 根据标题选择玩具
     * @param title
     * @return
     */
    Toy[] selectToyByTitle(String title);

    /**
     * 删除玩具
     * @param toy
     * @return
     */
    String delToy(Toy toy);

    /**
     * 更新玩具信息
     * @param toy
     * @return
     */
    String updateToy(Toy toy);

    /**
     * 增加订单
     * @param order
     * @return
     */
    String addOrder(Order order);

    /**
     * 删除订单
     * @param order
     * @return
     */
    String delOrder(Order order);

    /**
     * 修改订单
     * @param order
     * @return
     */
    String updateOrder(Order order);

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
    String addComment(Comment comment);

    /**
     * 删除评论
     * @param comment
     * @return
     */
    String delComment(Comment comment);

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
    Favorite[] selectFavoriteByUser(User user);

    /**
     * 获得所有玩具
     * @return
     */
    Toy[] selectAllToy();

    /**
     * 添加收藏夹
     * @return
     */
    String addFavorite(Favorite favorite);

    /**
     * 删除收藏夹
     * @return
     */
    String delFavorite(Favorite favorite);

    /**
     * 根据收藏id选择收藏夹
     * @param favorite
     * @return
     */
    Favorite selectFavoriteById(Favorite favorite);
}

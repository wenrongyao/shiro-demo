package com.honor.service.base;

import com.honor.config.Mapper2;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by rongyaowen
 * on 2018/12/11.
 * <p>
 * 基础接口，所有需要基础mapper方法的service接口继承这个接口
 */
public interface IBaseService<T> {

    /**
     * 全属性插入
     *
     * @param t
     * @return
     */
    public int insert(T t);

    /**
     * 按给定属性插入,效率比较高
     *
     * @param t
     * @return
     */
    public int insertSelective(T t);

    /**
     * 批量插入
     *
     * @param t
     * @return
     */
    public int insertList(List<T> t);

    /**
     * 查询
     *
     * @param t
     * @return
     */
    public List<T> select(T t);

    /**
     * 查询一个
     *
     * @param t
     * @return
     */
    public T selectOne(T t);

    /**
     * 获取全部数据，通常和分页一起用
     *
     * @return
     */
    public List<T> selectAll();

    /**
     * 查询符合条件的数据总条数
     *
     * @param t
     * @return
     */
    public int selectCount(T t);

    /**
     * 根据模板查询条数
     *
     * @param example
     * @return
     */
    public int selectCountByExample(Example example);

    /**
     * 根据主键查询
     *
     * @param t
     * @return
     */
    public T selectByPrimaryKey(T t);

    /**
     * 根据模板查询
     *
     * @param example
     * @return
     */
    public List<T> selectByExample(Example example);

    /**
     * 根据模板查询数据
     *
     * @param example
     * @return
     */
    public T selectOneByExample(Example example);

    /**
     * 根据行号查询
     *
     * @param t
     * @param rowBounds
     * @return
     */
    public List<T> selectByRowBounds(T t, RowBounds rowBounds);

    /**
     * 根据模板和行号查询
     *
     * @param example
     * @param rowBounds
     * @return
     */
    public List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds);

    /**
     * 根据模板删除
     *
     * @param example
     * @return
     */
    public int deleteByExample(Example example);

    /**
     * 根据主键删除
     *
     * @param t
     * @return
     */
    public int deleteByPrimaryKey(T t);

    /**
     * 删除
     *
     * @param t
     * @return
     */
    public int delete(T t);

    /**
     * 根据模板更新，全字段更新
     *
     * @param example
     * @return
     */
    public int updateByExample(T t, Example example);

    /**
     * 根据模板更新选定子段
     *
     * @param t
     * @param example
     * @return
     */
    public int updateByExampleSelective(T t, Example example);

    /**
     * 根据主键更新,将设置的属性进行更新，如果不设置属性，自动更新为null。
     *
     * @param t
     * @return
     */
    public int updateByPrimaryKey(T t);

    /**
     * 根据主键更新,将设置的属性进行更新，没有设置的属性不更新。推荐
     *
     * @param t
     * @return
     */
    public int updateByPrimaryKeySelective(T t);


}

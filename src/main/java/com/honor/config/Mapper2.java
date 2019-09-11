package com.honor.config;

import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by rongyaowen
 * on 2019/9/4.
 */
public interface Mapper2<T> extends tk.mybatis.mapper.common.Mapper<T>, MySqlMapper<T> {
}

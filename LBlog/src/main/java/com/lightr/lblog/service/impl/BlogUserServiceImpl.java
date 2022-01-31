package com.lightr.lblog.service.impl;

import com.lightr.lblog.model.entity.BlogUser;
import com.lightr.lblog.mapper.BlogUserMapper;
import com.lightr.lblog.service.IBlogUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lightr
 * @since 2022-01-18
 */
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements IBlogUserService {

}

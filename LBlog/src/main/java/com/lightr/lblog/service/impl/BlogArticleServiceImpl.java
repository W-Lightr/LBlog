package com.lightr.lblog.service.impl;

import com.lightr.lblog.model.entity.BlogArticle;
import com.lightr.lblog.mapper.BlogArticleMapper;
import com.lightr.lblog.service.IBlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lightr
 * @since 2022-01-20
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {

}

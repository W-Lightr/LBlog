package com.lightr.lblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lightr.lblog.model.entity.BlogArticle;
import com.lightr.lblog.service.IBlogArticleService;
import com.lightr.lblog.util.Result;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Lightr
 * @since 2022-01-20
 */
@RestController
@RequestMapping("/blogs")
public class BlogArticleController {
    @Autowired
    IBlogArticleService service;

    @RequestMapping("/blog")
    public Page selectBlogs(int currentPage) {
        try {
            Page page = new Page(currentPage, 8);
            QueryWrapper<BlogArticle> wrapper = new QueryWrapper<>();
            wrapper.orderByAsc("id");
            wrapper.eq("status", 1);
            Page page1 = service.pageMaps(page, wrapper);

            return page1;
        } catch (Exception e) {
            throw new MybatisPlusException("查询文章失败！");
        }
    }

    @RequiresAuthentication
    @RequestMapping("/edit")
    public Result edit(@Validated BlogArticle blog) {
        BlogArticle temp = null;
        if (blog.getId() != null) {
            temp = service.getById(blog.getId());
        } else {
            temp = new BlogArticle();
            temp.setCreatetime(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            temp.setStatus(true);
        }
        temp.setTitle(blog.getTitle());
        temp.setContent(blog.getContent());
        temp.setDescription(blog.getDescription());
        temp.setUserid(blog.getUserid());
        if (service.saveOrUpdate(temp))
            return Result.Successful("数据更改成功!");
        else
            throw new MybatisPlusException("更改失败!");
    }

    @RequestMapping("/delete")
    public Result delete(int id) {
        if (service.removeById(id))
            return Result.Successful("删除成功!");
        else
            throw new MybatisPlusException("删除失败!");

    }

    @RequestMapping("/article")
    public Result article(int id) {
        try {
            BlogArticle blog = service.getById(id);
            return Result.Successful(200, "获取成功!", blog);
        } catch (Exception e) {
            throw new MybatisPlusException("获取文章失败!");
        }
    }

    @RequestMapping("/manage")
    public Page Manage(int userId, int currentPage) {
        try {
            Page page = new Page(currentPage, 8);
            QueryWrapper<BlogArticle> wrapper = new QueryWrapper<>();
            wrapper.orderByAsc("id");
            wrapper.eq("userid", userId);
            Page page1 = service.pageMaps(page, wrapper);
            return page1;
        } catch (Exception e) {
            throw new MybatisPlusException("查询用户文章失败！");
        }
    }


}

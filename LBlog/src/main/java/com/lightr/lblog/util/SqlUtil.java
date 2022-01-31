package com.lightr.lblog.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lightr.lblog.mapper.BlogUserMapper;
import com.lightr.lblog.model.entity.BlogUser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlUtil {
    @Autowired
    BlogUserMapper userMapper;

    public boolean verifyUser(String name, String password) {
        BlogUser Source = getUser(name);
        if (Source == null)
            return false;
        QueryWrapper<BlogUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.eq("password", new Md5Hash(password, Source.getSalt()).toHex());
        BlogUser user = userMapper.selectOne(wrapper);
        return user != null;
    }

    public boolean existUser(int id) {
        return getUserById(id) == null;
    }

    public String getSalt(int id) {
        try {
            return getUserById(id).getSalt();
        } catch (Exception e) {
            return null;
        }
    }

    public BlogUser getUser(String name) {
        QueryWrapper<BlogUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return userMapper.selectOne(wrapper);
    }

    private BlogUser getUserById(int id) {
        QueryWrapper<BlogUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return userMapper.selectOne(wrapper);
    }

}

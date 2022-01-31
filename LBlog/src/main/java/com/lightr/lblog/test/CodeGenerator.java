package com.lightr.lblog.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
// 代码生成器
public class CodeGenerator {
    String path = "F:\\Code\\JavaCode\\Project\\LBlog\\src\\main";
    String url = "jdbc:mysql://localhost:3306/lblog?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    String username = "root";
    String password = "123456";
    String packageName = "com.lightr.lblog";

    public void MyBuild() {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Lightr") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(packageName) // 设置父包名
                            //.moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, path + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("blog_article");// 设置需要生成的表名


                    //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    public static void main(String[] args) {
        new CodeGenerator().MyBuild();
    }
}

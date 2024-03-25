package com.test;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.test.common.BasePO;
import com.test.config.EnhanceFreemarkerTemplateEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author losingways
 */
public class AutoGenerator {
    private static void copyFolder(Path src, Path dest) throws IOException {
        Files.walk(src).forEach(source -> {
            Path destination = Paths.get(dest.toString(), source.toString().substring(src.toString().length()));
            try {
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static void clearDirectory(Path dir) throws IOException {
        if (Files.isDirectory(dir)) {
            try (Stream<Path> entries = Files.list(dir)) {
                for (Path entry : entries.collect(Collectors.toList())) {
                    if (Files.isDirectory(entry)) {
                        clearDirectory(entry);
                    }
                    Files.delete(entry);
                }
            }
        }
    }
    public static void main(String[] args) {
        //jdbc路径
        String url="jdbc:mysql://localhost:3306/buying-dev?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
        //数据库账号
        String username="root";
        //数据库密码
        String password="root";
        //作者
        String author= "losingways";

        try {
            Path currentRelativePath = Paths.get("").toAbsolutePath();
            //System.out.println(currentRelativePath);

            Path sourceDirectory = Paths.get(currentRelativePath + "/src/main/resources/generalTemplates");
            //System.out.println(sourceDirectory);
            // 定义目标目录为当前目录的父目录下的 losingways 文件夹
            Path targetDirectory = Paths.get(currentRelativePath + "/src/main/java/com/general");
            clearDirectory(targetDirectory);
            //System.out.println(targetDirectory);
            copyFolder(sourceDirectory, targetDirectory);

        }catch (IOException e) {
            e.printStackTrace();
        }

        //生成了controller/data/model/service
        //父包名
        String parentPackage="com.general";
        // 指定输出目录
        String outputDir=System.getProperty("user.dir")+"\\src\\main\\java\\";
        // 设置mapperXml生成路径
        String outputXmlDir=System.getProperty("user.dir")+"\\src\\main\\resources\\mapper\\";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author)
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .dateType(DateType.ONLY_DATE)//时间策略	DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .commentDate("MM-dd")//默认值: yyyy-MM-dd
                            .disableOpenDir()//禁止打开输出目录(即生成完成后不打开输出路径)
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackage) //父包名
                            .controller("controller")//Controller 包名
                            .service("service")//Service 包名
                            .serviceImpl("service.impl")//service.impl
                            .mapper("model.dao")//Mapper 包名	默认值:mapper
                            .entity("model.po")//Entity 包名	默认值:entity
//                            .moduleName("mybatis-plus-parent") // 设置父包模块名
//                            .other("model.dto") // 设置dto包名
                            .other("data.vo") // 设置vo包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,outputXmlDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
                            .addInclude("bas_menu")
//                            .addInclude("bas_role")
//                            .addInclude("bas_role_menu")
//                            .addInclude("bas_user")
//                            .addInclude("bas_user_role")
//                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                    .controllerBuilder()//controller 策略配置
                            .enableRestStyle()//开启生成@RestController 控制器	默认值:false
                            .formatFileName("%sController")//格式化文件名称	 默认为:Controller
                        .build()
                    .serviceBuilder()//service 策略配置
                            .formatServiceFileName("%sBizService")//格式化 service 接口文件名称
                            .formatServiceImplFileName("%sBizServiceImpl")//格式化 service 实现类文件名称
                        .build()
                    .entityBuilder()//实体策略配置
                            .superClass(BasePO.class)//设置父类
                            .disableSerialVersionUID()//禁用生成 serialVersionUID
                            .enableLombok()//开启 lombok 模型
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解 默认值:false 【eg:  @TableField("province")】
                            .addSuperEntityColumns(Arrays.asList("is_deleted","create_user","create_time","modify_time","create_user_name","modify_user","modify_user_name"))//添加父类公共字段
                            .idType(IdType.AUTO)//全局主键类型	自动生成
//                            .addTableFills(new Column("create_time", FieldFill.INSERT))//添加表字段填充
//                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))//添加表字段填充
                            .formatFileName("%sPo")//格式化文件名称
                        .build()
                    .mapperBuilder()//Mapper策略配置
                                .enableMapperAnnotation()//开启 @Mapper 注解
                                .enableBaseResultMap()//启用 BaseResultMap 生成
                                .enableBaseColumnList()//启用 BaseColumnList
                                .formatMapperFileName("%sMapper")//格式化 mapper 文件名称
//                                .formatXmlFileName("%sXml")//格式化 xml 实现类文件名称
                            .build()
                    ;
                })
                //模板配置(TemplateConfig)
                .templateConfig( builder -> builder .disable(TemplateType.ENTITY)
                        .entity("/losingwaysTemplates/entity.java")
                        .service("/losingwaysTemplates/serviceLosingwaysCustom.java")
                        .serviceImpl("/losingwaysTemplates/serviceImplLosingwaysCustom.java")
                        .mapper("/losingwaysTemplates/mapperLosingwaysCustom.java")
                        .xml("/losingwaysTemplates/mapperLosingwaysCustom.xml")
                        .controller("/losingwaysTemplates/controllerLosingwaysCustom.java"))
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new EnhanceFreemarkerTemplateEngine()) // 使用自定义Freemarker引擎模板
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    // DTO
                    customFile.put("Dto.java", "/losingwaysTemplates/entityDto.java.ftl");
                    customFile.put("Vo.java", "/losingwaysTemplates/entityVo.java.ftl");
                    customFile.put("QueryVo.java", "/losingwaysTemplates/entityQueryVo.java.ftl");
                    customFile.put("AddVo.java", "/losingwaysTemplates/entityAddVo.java.ftl");
                    customFile.put("UpdateVo.java", "/losingwaysTemplates/entityUpdateVo.java.ftl");
                    customFile.put("ServiceImpl.java", "/losingwaysTemplates/ServiceImpl.java.ftl");
                    customFile.put("Service.java", "/losingwaysTemplates/Service.java.ftl");
                    consumer.customFile(customFile);
                })
                .execute();


    }


}

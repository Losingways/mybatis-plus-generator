package com.test.config;


import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.jetbrains.annotations.NotNull;


import java.io.File;
import java.util.Map;

/**
 * 代码生成器支持自定义[DTO\VO等]模版
 */
public final class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String otherPath = this.getPathInfo(OutputFile.other);
//        JSONObject packageStr= JSON.parseObject(objectMap.get("package").toString());

        // 指定输出目录
        String outputDir=System.getProperty("user.dir")+"\\src\\main\\java\\";

        customFile.forEach((key, value) -> {
            String otherPathTemp="";
            String fileName="";
            switch (key){
                case "Service.java":
                    otherPathTemp=outputDir+"com\\general\\service";
                    fileName = String.format(otherPathTemp + File.separator + entityName + "%s", key);
                    break;
                case "ServiceImpl.java":
                    otherPathTemp=outputDir+"com\\general\\service\\impl";
                    fileName = String.format(otherPathTemp + File.separator + entityName + "%s", key);
                    break;
                case "Dto.java":
                    otherPathTemp=outputDir+"com\\general\\data\\dto";
                    fileName = String.format(otherPathTemp + File.separator + entityName + "%s", key);
                    break;
                case "Vo.java" : case "QueryVo.java" : case "AddVo.java" : case "UpdateVo.java" :
                    otherPathTemp=outputDir+"com\\general\\data\\vo";
                    fileName = String.format(otherPathTemp + File.separator + entityName + "%s", key);
                    break;
            }
            this.outputFile(new File(fileName), objectMap, value);
           /* String fileName = String.format(otherPath + File.separator + entityName + "%s", key);
            this.outputFile(new File(fileName), objectMap, value);*/
        });
    }
}
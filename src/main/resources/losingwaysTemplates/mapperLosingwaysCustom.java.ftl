package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if mapperAnnotation>
import org.apache.ibatis.annotations.Mapper;
</#if>
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.general.data.vo.${table.entityName}QueryVo;


/**
 * ${table.comment!} Mapper 接口
 * @author ${author}
 * @description losingways代码生成器
 * @since ${date}
 */
<#if mapperAnnotation>
@Mapper
</#if>
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

  /**
    * 分页查询
    * @param page
    * @param param
    * @return
  */
  IPage<${entity}> pageQuery(Page page, @Param("param") ${entity}QueryVo param);

 }
</#if>

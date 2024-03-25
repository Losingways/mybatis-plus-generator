package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.general.data.vo.${table.entityName}Vo;
import com.general.data.vo.${table.entityName}QueryVo;
import com.general.data.vo.${table.entityName}AddVo;
import com.general.data.vo.${table.entityName}UpdateVo;
import com.general.data.dto.${table.entityName}Dto;

/**
 * ${table.comment!} 服务类
 * @author ${author}
 * @description losingways代码生成器
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

      /**
      * 分页查询列表接口
      * @return
      */
      IPage<${entity}Dto> pageQuery(${entity}QueryVo  param);

      /**
      * 新增接口
      * @param param
      * @return
      */
      Boolean add(${entity}AddVo param);

      /**
      * 更新接口
      * @param param
      * @return
      */
      Boolean updateByParam(${entity}UpdateVo  param);

      /**
      * 删除接口
      * @param id 主键
      * @return
      */
      Boolean delete(Long id);

      /**
      * 获取详情接口
      * @param id 主键
      * @return
      */
     ${entity}Dto detailQuery(Long id);


}
</#if>

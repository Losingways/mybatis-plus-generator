package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Propagation;
import com.general.common.CommonResponseDto;
import com.general.data.vo.${table.entityName}Vo;
import com.general.data.vo.${table.entityName}QueryVo;
import com.general.data.vo.${table.entityName}AddVo;
import com.general.data.vo.${table.entityName}UpdateVo;
import com.general.data.dto.${table.entityName}Dto;

/**
* ${table.comment!} 前端控制器
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
 class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
 <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
 <#else>
public class ${table.controllerName} {
 </#if>

       /**
       * ${table.comment!} service
       */
       @Autowired
       private ${table.serviceName} ${table.entityPath}Service;

       /**
       * 分页查询列表接口
       * @return
       */
       @RequestMapping(value = "/pagedQuery",method = RequestMethod.POST ,produces="application/json;charset=UTF-8" )
       public CommonResponseDto<IPage<${entity}Dto>> pageQuery(@RequestBody   @Validated  ${entity}QueryVo param) {
         return CommonResponseDto.success(${table.entityPath}Service.pageQuery(param));
       }

      /**
      * 新增接口
      * @param param
      * @return
      */
      @RequestMapping(value = "/add",method = RequestMethod.POST ,produces="application/json;charset=UTF-8" )
      public CommonResponseDto<Boolean> add(@RequestBody  @Validated  ${entity}AddVo  param) {
        return CommonResponseDto.success(${table.entityPath}Service.add(param));
      }


       /**
       * 更新接口
       * @param param
       * @return
       */
       @RequestMapping(value = "/update",method = RequestMethod.POST ,produces="application/json;charset=UTF-8" )
       public CommonResponseDto<Boolean> update(@RequestBody  @Validated  ${entity}UpdateVo  param) {
           return CommonResponseDto.success(${table.entityPath}Service.updateByParam(param));
       }

       /**
       * 删除接口
       * @param id 主键
       * @return
       */
       @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET ,produces="application/json;charset=UTF-8" )
       public CommonResponseDto<Boolean> delete(@PathVariable Long id) {
         return CommonResponseDto.success(${table.entityPath}Service.delete(id));
       }

       /**
       * 详情查询接口
       * @param id 主键
       * @return
       */
       @RequestMapping(value = "/{id}",method = RequestMethod.GET ,produces="application/json;charset=UTF-8" )
       public CommonResponseDto<${entity}Dto> detailQuery(@PathVariable  Long id) {
          return CommonResponseDto.success(${table.entityPath}Service.detailQuery(id));
       }

 }
</#if>


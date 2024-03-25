package com.general.service;

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
public interface ${entity}Service{


       /**
       * 分页查询列表接口
       * @return
       */
        CommonResponseDto<IPage<${entity}Dto>> pageQuery( ${entity}QueryVo param) ;

      /**
      * 新增接口
      * @param param
      * @return
      */
       CommonResponseDto<Boolean> add( ${entity}AddVo  param) ;


       /**
       * 更新接口
       * @param param
       * @return
       */
        CommonResponseDto<Boolean> update( ${entity}UpdateVo  param) ;

       /**
       * 删除接口
       * @param id 主键
       * @return
       */
        CommonResponseDto<Boolean> delete( Long id) ;

       /**
       * 详情查询接口
       * @param id 主键
       * @return
       */
        CommonResponseDto<${entity}Dto> detailQuery(Long id) ;

 }


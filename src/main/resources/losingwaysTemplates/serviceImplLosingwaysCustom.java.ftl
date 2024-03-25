package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.util.Objects;
import com.general.enums.ExceptionEnum;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import com.general.data.vo.${table.entityName}Vo;
import com.general.data.vo.${table.entityName}QueryVo;
import com.general.data.vo.${table.entityName}AddVo;
import com.general.data.vo.${table.entityName}UpdateVo;
import com.general.data.dto.${table.entityName}Dto;
import com.general.util.BizAssertUtil;


/**
 * ${table.comment!} 服务实现类
 * @author ${author}
 * @description losingways代码生成器
 * @since ${date}
 */
@Slf4j
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

      /**
      * 分页查询列表接口
      * @return
     */
     @Override
     public IPage<${entity}Dto> pageQuery(${entity}QueryVo param) {
       IPage<${entity}> iPage=baseMapper.pageQuery(new Page<>(param.getPageNum(),param.getPageSize()),param);
        return iPage.convert(item -> cover${entity}ToDto(item));
      }

     /**
     * 新增接口
     * @param param
     * @return
     */
     @Override
     @Transactional(propagation= Propagation.REQUIRED)
     public Boolean add(${entity}AddVo param) {
       ${entity} po=BeanUtil.copyProperties(param,${entity}.class);
       //po.setUserId(UserUtils.getWechatUserId());
      return baseMapper.insert(po)>0?true:false;
     }


      /**
       * 更新接口
       * @param param
       * @return
      */
      @Override
      @Transactional(propagation= Propagation.REQUIRED)
      public Boolean updateByParam(${entity}UpdateVo param) {
        ${entity} po=BeanUtil.copyProperties(param,${entity}.class);
        baseMapper.updateById(po);
        return true;
      }

     /**
     * 删除接口
     * @param id 主键
     * @return
     */
     @Override
     @Transactional(propagation= Propagation.REQUIRED)
     public Boolean delete(Long id) {
       return baseMapper.deleteById(id)>0?true:false;
     }


      /**
      * 转为为dto返回数据
      * @param item
      * @return
      */
      private ${entity}Dto cover${entity}ToDto(${entity} item) {
        ${entity}Dto dto=BeanUtil.copyProperties(item,${entity}Dto.class);
         return dto;
      }

     /**
     * 获取详情接口
     * @param id 主键
     * @return
     */
     @Override
     public ${entity}Dto detailQuery(Long id) {
       ${entity} po=baseMapper.selectById(id);
       BizAssertUtil.isTrue(Objects.isNull(po), ExceptionEnum.LOSINGWAYS_COMMON_EXCEPTION_001);
       return BeanUtil.copyProperties(po,${entity}Dto.class);
     }

}
</#if>

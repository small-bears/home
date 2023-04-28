package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ErshoutushuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ErshoutushuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ErshoutushuView;


/**
 * 二手图书
 *
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
public interface ErshoutushuService extends IService<ErshoutushuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ErshoutushuVO> selectListVO(Wrapper<ErshoutushuEntity> wrapper);
   	
   	ErshoutushuVO selectVO(@Param("ew") Wrapper<ErshoutushuEntity> wrapper);
   	
   	List<ErshoutushuView> selectListView(Wrapper<ErshoutushuEntity> wrapper);
   	
   	ErshoutushuView selectView(@Param("ew") Wrapper<ErshoutushuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ErshoutushuEntity> wrapper);
   	

}


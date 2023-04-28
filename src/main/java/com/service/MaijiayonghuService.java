package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.MaijiayonghuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.MaijiayonghuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.MaijiayonghuView;


/**
 * 卖家用户
 *
 * @author 
 * @email 
 * @date 2022-04-23 15:10:36
 */
public interface MaijiayonghuService extends IService<MaijiayonghuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MaijiayonghuVO> selectListVO(Wrapper<MaijiayonghuEntity> wrapper);
   	
   	MaijiayonghuVO selectVO(@Param("ew") Wrapper<MaijiayonghuEntity> wrapper);
   	
   	List<MaijiayonghuView> selectListView(Wrapper<MaijiayonghuEntity> wrapper);
   	
   	MaijiayonghuView selectView(@Param("ew") Wrapper<MaijiayonghuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MaijiayonghuEntity> wrapper);
   	

}


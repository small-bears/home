package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.MaijiayonghuDao;
import com.entity.MaijiayonghuEntity;
import com.service.MaijiayonghuService;
import com.entity.vo.MaijiayonghuVO;
import com.entity.view.MaijiayonghuView;

@Service("maijiayonghuService")
public class MaijiayonghuServiceImpl extends ServiceImpl<MaijiayonghuDao, MaijiayonghuEntity> implements MaijiayonghuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MaijiayonghuEntity> page = this.selectPage(
                new Query<MaijiayonghuEntity>(params).getPage(),
                new EntityWrapper<MaijiayonghuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<MaijiayonghuEntity> wrapper) {
		  Page<MaijiayonghuView> page =new Query<MaijiayonghuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<MaijiayonghuVO> selectListVO(Wrapper<MaijiayonghuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public MaijiayonghuVO selectVO(Wrapper<MaijiayonghuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<MaijiayonghuView> selectListView(Wrapper<MaijiayonghuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public MaijiayonghuView selectView(Wrapper<MaijiayonghuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}

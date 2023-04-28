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


import com.dao.DiscussershoutushuDao;
import com.entity.DiscussershoutushuEntity;
import com.service.DiscussershoutushuService;
import com.entity.vo.DiscussershoutushuVO;
import com.entity.view.DiscussershoutushuView;

@Service("discussershoutushuService")
public class DiscussershoutushuServiceImpl extends ServiceImpl<DiscussershoutushuDao, DiscussershoutushuEntity> implements DiscussershoutushuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussershoutushuEntity> page = this.selectPage(
                new Query<DiscussershoutushuEntity>(params).getPage(),
                new EntityWrapper<DiscussershoutushuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussershoutushuEntity> wrapper) {
		  Page<DiscussershoutushuView> page =new Query<DiscussershoutushuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiscussershoutushuVO> selectListVO(Wrapper<DiscussershoutushuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussershoutushuVO selectVO(Wrapper<DiscussershoutushuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussershoutushuView> selectListView(Wrapper<DiscussershoutushuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussershoutushuView selectView(Wrapper<DiscussershoutushuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}

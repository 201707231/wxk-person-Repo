package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.mapper.AnswerMapper;
import com.exam.service.AnswerService;
import com.exam.vo.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private AnswerMapper answerMapper;

    @Override
    public IPage<AnswerVO> findAll(Page<AnswerVO> page) {
        return answerMapper.findAll(page);
    }
}

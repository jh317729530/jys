package com.gunn.jys.controller;

import com.gunn.jys.annotation.Pagination;
import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.InfoResult;
import com.gunn.jys.bo.Result;
import com.gunn.jys.entity.TeachResult;
import com.gunn.jys.mapper.TeachResultMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/teachResult")
public class TeachResultController extends BaseController {

    @Resource
    private TeachResultMapper teachResultMapper;

    @RequestMapping("add")
    public Result add(@RequestParam String title, @RequestParam String fileUrl) {
        TeachResult teachResult = new TeachResult();
        teachResult.setTitle(title);
        teachResult.setFileUrl(fileUrl);
        teachResult.setTeacherId(getTeacherId());
        teachResult.setCreated(new Date());
        teachResult.setDownloadCount(0);
        teachResultMapper.insert(teachResult);
        return new Result();
    }

    @Pagination
    @RequestMapping("page")
    public Result page(String title) {
        InfoResult result = new InfoResult();
        result.setInfo(teachResultMapper.findPageBy(title));
        return result;
    }

    @RequestMapping("update")
    public Result update(Integer id) {
        TeachResult teachResult = teachResultMapper.selectByPrimaryKey(id);
        teachResult.setDownloadCount(teachResult.getDownloadCount() + 1);
        teachResultMapper.updateByPrimaryKey(teachResult);
        return new Result();
    }

    @RequestMapping("delete")
    public Result delete(Integer id) {
        teachResultMapper.deleteByPrimaryKey(id);
        return new Result();
    }
}

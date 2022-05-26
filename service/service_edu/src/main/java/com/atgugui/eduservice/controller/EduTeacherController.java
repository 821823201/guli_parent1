package com.atgugui.eduservice.controller;


import com.atgugui.commonutils.R;
import com.atgugui.eduservice.entity.EduTeacher;
import com.atgugui.eduservice.entity.vo.TeacherQuery;
import com.atgugui.eduservice.service.EduTeacherService;
import com.atgugui.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    //访问地址： http://localhost:8001/eduservice/edu_teacher/findAll
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }
    @DeleteMapping("/delete/{id}")
    public boolean removeById(@PathVariable("id") String id){
        boolean flag =  eduTeacherService.removeById(id);
        return flag;
    }
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        try {
            int i = 10/0;
        } catch (Exception e) {
            throw new GuliException(20001,"执行了自定义异常处理");
        }
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> recodes = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",recodes);
    }
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false)  TeacherQuery teacherQuery){
        Page<EduTeacher> PageTeacher = new Page<>(current, limit);
        QueryWrapper<EduTeacher> Wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String ending = teacherQuery.getEnding();
        if (!ObjectUtils.isEmpty(name)) {
            Wrapper.like("name", name);

        }
        if (!ObjectUtils.isEmpty(level)) {
            Wrapper.like("level", level);

        }
        if (!ObjectUtils.isEmpty(begin)) {
            Wrapper.like("gmt_create", begin);

        }
        if (!ObjectUtils.isEmpty(ending)) {
            Wrapper.like("gmt_create", ending);

        }
        eduTeacherService.page(PageTeacher, Wrapper);
        long total = PageTeacher.getTotal();
        List<EduTeacher> recodes = PageTeacher.getRecords();
        return R.ok().data("total", total).data("rows", recodes);
    }
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //添加讲师接口的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}


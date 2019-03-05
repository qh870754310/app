package com.sjsz.app.controller;

import com.sjsz.app.service.ApiService;
import com.sjsz.app.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/3/1 16:08
 * @Description:
 * @Api：用在类上，说明该类的作用
 * @ApiOperation：用在方法上，说明方法的作用
 * @ApiImplicitParams：用在方法上包含一组参数说明
 * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 *  paramType：参数放在哪个地方
 *      header-->请求参数的获取：@RequestHeader
 *      query-->请求参数的获取：@RequestParam
 *      path（用于restful接口）-->请求参数的获取：@PathVariable
 *      body（不常用）
 *      form（不常用）
 *  name：参数名
 *  dataType：参数类型
 *  required：参数是否必须传
 *  value：参数的意思
 *  defaultValue：参数的默认值
 * @ApiResponses：用于表示一组响应
 * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
 *   code：数字，例如400
 *   message：信息，例如"请求参数没填好"
 *   response：抛出异常的类
 * @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 *   @ApiModelProperty：描述一个model的属性
 */
@Api(description = "上海天华学院相关的api")
@RestController
@RequestMapping("/api/")
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * 查询温湿度
     * @return
     */
    @RequestMapping(value = "getHumiture", method = RequestMethod.GET)
    @ApiOperation(value = "查询最新一条的温湿度", notes = "查询最新一条的温湿度")
    @ApiResponses({
            @ApiResponse(code=0, message = "Result -> {'code': '状态码', 'msg': '状态信息', 'data':{'Temperature': '温度', 'Humidity': '湿度', 'Time':'日期'}}")
    })
    public Result getHumiture() {
        Map<String, Object> map =  apiService.getHumiture();
        return Result.success(map);
    }
}

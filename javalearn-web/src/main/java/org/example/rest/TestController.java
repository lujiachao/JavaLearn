package org.example.rest;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.example.WebException;
import org.example.enums.WebResultCode;
import org.example.manager.TestManager;
import org.example.model.request.TestThreeRequest;
import org.example.model.request.TestTwoRequest;
import org.example.model.response.TestExceptionResponse;
import org.example.model.response.TestTwoResponse;
import org.example.rest.base.config.swagger.model.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/JavaLearn/Test")
public class TestController {
    @Autowired
    private TestManager _manager;

    @PostMapping("Exception")
    public WebResponse<TestExceptionResponse> TestException() throws WebException
    {
        return WebResponse.success(_manager.TestOneException());
    }

    @PostMapping("Two")
    public WebResponse<TestTwoResponse> TestTwo(@RequestBody TestTwoRequest testTwoRequest) throws WebException
    {
        return WebResponse.success(_manager.TestTwo(testTwoRequest));
    }

    @PostMapping("Three")
    public WebResponse<String> TestThree(@RequestBody TestThreeRequest testThreeRequest) throws WebException
    {
        return WebResponse.success("测试接收入参");
    }

    // 测试数据库相关操作
    @PostMapping("Four")
    public WebResponse<String> TestFour() throws WebException
    {
        _manager.TestFour();
        return WebResponse.success(null);
    }
}

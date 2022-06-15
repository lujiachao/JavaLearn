package org.example.manager;

import org.example.AdminOperationLogServiceImpl;
import org.example.WebException;
import org.example.enums.WebResultCode;
import org.example.mapper.AdminOperationLogMapper;
import org.example.model.request.TestTwoRequest;
import org.example.model.response.TestExceptionResponse;
import org.example.model.response.TestTwoResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestManager {
    @Resource
    private AdminOperationLogServiceImpl _adminOperationLogServiceImpl;

    public TestExceptionResponse TestOneException() throws WebException{
        throw new WebException(WebResultCode.EXEC_DAPP_SDK_API_BUS_ERROR, "测试接口异常");
    }

    public TestTwoResponse TestTwo(TestTwoRequest request)
    {
        TestTwoResponse response = new TestTwoResponse();
        response.setTestColOne(request.getTestColOne());
        response.setTestColTwo(request.getTestColTwo());
        response.setTestColThree(request.getTestColThree());
        List<TestTwoResponse.TestList> lists = new ArrayList<>();
        if (request.getTestColFour().size() > 0) {
            request.getTestColFour().forEach((e) -> {
                TestTwoResponse.TestList list = new TestTwoResponse().new TestList();
                list.setTestListColOne(e.getTestListColOne());
                list.setTestListColTwo(e.getTestListColTwo());
                list.setTestListColThree(e.getTestListColThree());
                lists.add(list);
            });
        }
        response.setTestColFour(lists);
        return response;
    }

    public void TestFour()
    {
        int result = _adminOperationLogServiceImpl.count();
    }
}

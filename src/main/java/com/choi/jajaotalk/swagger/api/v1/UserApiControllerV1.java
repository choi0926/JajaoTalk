package com.choi.jajaotalk.swagger.api.v1;


import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "UserApiController V1")
@RequestMapping("/api/user/login")
public class UserApiControllerV1 {

    @ApiOperation(value = "API", notes = "User API")

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !!"),
            @ApiResponse(code = 404, message = "Not Found !!")
    })

    @PostMapping(value = "api/user/login")
    public Map<String, String> selectOneUser(@RequestParam String no) {
        Map<String, String> result = new HashMap<>();
//        result.put("nickname", "test");
//        result.put("password", "test");
        return result;
    }
}

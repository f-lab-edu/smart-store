package com.project.smartStore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


/**
 *
 * @RestController : @Controller와 @ResponseBody가 합쳐진 기능의 어노테이션입니다.
 * 또한 @RequestMapping 메서드가 @ResponseBody를 수행한다는 의미도 있습니다.
 *
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

}

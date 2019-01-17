package com.udriving.drivingapi.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Coder shihaiyang
 * @Date 2019-01-01 20:57
 */
@RestController
@RequestMapping("/todos")
@PreAuthorize("hasRole('USER')")
public class TodoController {
    // 略去
}
package com.hong_world.common.di.scoped;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Date: 2018/4/4. 17:59
 * Author: hong_world
 * Description:
 * Version:
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}

package com.linguiqing.mychanage.ui.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * ***************************************
 * statement: 自定义Scope（范围） ActivityScope
 * auther: lingguiqin
 * date created : 2017/8/25 0025
 * ***************************************
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {
}

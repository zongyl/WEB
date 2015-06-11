package com.longlong.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface myAnn {
	String hello() default "hellostring";
	String world();
	int[] array() default {1, 2, 3};
	Class style() default String.class;
}

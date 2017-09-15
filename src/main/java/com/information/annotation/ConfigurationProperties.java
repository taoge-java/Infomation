/**
 * 
 */
package com.information.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author taoge
 * @version 1.0
 * @create_at 2017年9月9日上午11:45:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface ConfigurationProperties {

	String prefix() default "";
	
	String localtion() default "";
}

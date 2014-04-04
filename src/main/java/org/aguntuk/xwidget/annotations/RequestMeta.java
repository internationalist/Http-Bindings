package org.aguntuk.xwidget.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aguntuk.xwidget.util.OutputFormat;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RequestMeta {

	String requestName();
	String requestObject() default "";
	String templateFile() default "";
	String outputType() default OutputFormat.HTML;
	String[] requestKeyName() default {};
	String jsp() default "";
}

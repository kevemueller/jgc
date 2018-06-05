package hu.keve.jgc.util.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.params.provider.ArgumentsSource;

@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(PathsArgumentsProvider.class)
public @interface PathsSource {

	/**
	 * The path empty.
	 */
	String path();

	int maxDepth() default Integer.MAX_VALUE;

	boolean dirs() default false;

	String regexp() default "";
}

package hu.keve.jgc.util.junit;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apiguardian.api.API;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * {@code @CsvFileSource} is an {@link ArgumentsSource} which is used to load
 * comma-separated value (CSV) files from one or more classpath resources.
 *
 * <p>
 * The lines of these CSV files will be provided as arguments to the annotated
 * {@code @ParameterizedTest} method.
 *
 * @since 5.0
 * @see CsvSource
 * @see org.junit.jupiter.params.provider.ArgumentsSource
 * @see org.junit.jupiter.params.ParameterizedTest
 */
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(FilesArgumentsProvider.class)
public @interface FilesSource {

	/**
	 * The path empty.
	 */
	String path();

	int maxDepth() default Integer.MAX_VALUE;
	
	boolean dirs() default false;

	String regexp() default "";
}

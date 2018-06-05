package hu.keve.jgc.util.junit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

class PathsArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<PathsSource> {
	private Path path;
	private int maxDepth;
	private boolean dirs;

	private Pattern regexp;

	@Override
	public void accept(PathsSource annotation) {
		path = new File(annotation.path()).toPath();
		maxDepth = annotation.maxDepth();
		dirs = annotation.dirs();
		String regexpString = annotation.regexp();
		regexp = regexpString.isEmpty() ? null : Pattern.compile(regexpString);
	}

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
		try {
			return Files.find(path, maxDepth, (p, a) -> {
				if (a.isDirectory() && !dirs) {
					return false;
				}
				if (null != regexp) {
					return regexp.matcher(p.toString()).matches();
				}
				return true;
			}).map(Arguments::of);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}

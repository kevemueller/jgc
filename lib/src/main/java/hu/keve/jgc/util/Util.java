package hu.keve.jgc.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicLong;

public final class Util {
	private Util() {
	}

	public static long size(final Iterable<?> iterable) {
		return size(iterable.spliterator());
	}

	private static long size(final Spliterator<?> spliterator) {
		long size = spliterator.getExactSizeIfKnown();
		if (-1 == size) {
			AtomicLong asize = new AtomicLong();
			spliterator.forEachRemaining(o -> {
				asize.incrementAndGet();
			});
			size = asize.longValue();
		}
		return size;
	}

	public static boolean equals(Iterable<?> i1, Iterable<?> i2) {
		if (i1 == i2) {
			return true;
		}
		if (null == i1 || null == i2) {
			return false;
		}
		Iterator<?> it1 = i1.iterator();
		Iterator<?> it2 = i2.iterator();

		while (it1.hasNext() && it2.hasNext()) {
			if (!it1.next().equals(it2.next())) {
				return false;
			}
		}
		return !it1.hasNext() && !it2.hasNext();
	}

	public static <T> Set<T> setOf(Iterable<T> elementIterator) {
		HashSet<T> set = new HashSet<T>();
		elementIterator.forEach(set::add);
		return set;
	}
}

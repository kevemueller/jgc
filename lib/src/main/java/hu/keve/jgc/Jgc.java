package hu.keve.jgc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;

import javax.xml.bind.JAXBException;

import hu.keve.jgc.dao.jaxb.AbstractGnuCashJAXB;
import hu.keve.jgc.dao.jdo.GnuCashJDO;

public class Jgc {
	private static byte[] SQLITE_HEADER = "SQLite format 3\0".getBytes();

	//TODO: use StandardFileOpenOptions instead of boolean readOnly, boolean create (+ downstream)
	public static GnuCash fromFile(File file, boolean readOnly) throws IOException, JAXBException {
		byte[] header = new byte[16];

		try (InputStream in = Files.newInputStream(file.toPath())) {
			if (-1 == in.readNBytes(header, 0, header.length)) {
				throw new IllegalArgumentException("short file");
			}
		}
		// int magic = (header[1] & 0xff << 8) | (header[0] & 0xff);
		// if (GZIPInputStream.GZIP_MAGIC == magic) {
		// }
		if (Arrays.equals(SQLITE_HEADER, header)) {
			return GnuCashJDO.fromFile(file, readOnly, false);
		}
		return AbstractGnuCashJAXB.fromFile(file, readOnly, false);
	}
}

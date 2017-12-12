package pdfConverter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

public class PDFConverter {
	private InputStream	pdfStream;
	private String		outputFileName;

	public PDFConverter(InputStream pdfFile, String fileName) {
		pdfStream = pdfFile;
		outputFileName = fileName;
	}

	public File convertTo(String outputExt, URL url) throws IOException {

		DocumentType outputType = parseOutputType(outputExt);
		File scriptFolder = new File(url.getFile() + "\\documents4j");
		scriptFolder.mkdir();
		File pdfFile = new File(scriptFolder + "\\" + outputFileName);
		Files.copy(pdfStream, pdfFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		File wordFile = new File(scriptFolder + "\\" + outputFileName + "." + outputExt.toLowerCase());

		IConverter converter = LocalConverter.builder().baseFolder(scriptFolder)
				.workerPool(20, 25, 10, TimeUnit.SECONDS).processTimeout(15, TimeUnit.SECONDS).build();
		converter.convert(pdfFile).as(DocumentType.PDF).to(wordFile).as(outputType).execute();

		return wordFile;
	}

	private DocumentType parseOutputType(String output) {
		switch (output) {
		case "DOC":
			return DocumentType.DOC;
		case "DOCX":
			return DocumentType.DOCX;
		default:
			return null;
		}
	}
}

package de.appslock.pdfslidenotices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

public class PdfSlideNotices {
	public static final String TEMPLATE_LANDSCAPE = "template_landscape.pdf";
	public static final String TEMPLATE_PORTRAIT = "template_portrait.pdf";
	private String destination;
	private String orientation = "template_landscape.pdf";

	public void handleOneFile(File file) throws DocumentException,
			FileNotFoundException, IOException {
		Document document = new Document();
		PdfCopy copy = new PdfCopy(document, new FileOutputStream(
				this.destination + File.separator + "PRINT_" + file.getName()));
		document.open();

		PdfReader templateReader = new PdfReader(this.orientation);

		PdfReader reader = new PdfReader(file.getPath());
		int n = reader.getNumberOfPages();
		for (int page = 0; page < n;) {
			copy.addPage(copy.getImportedPage(reader, ++page));
			copy.addPage(copy.getImportedPage(templateReader, 1));
		}
		copy.freeReader(reader);
		reader.close();
		document.close();
	}

	void setDestination(String destination) {
		this.destination = destination;
	}

	String getDestination() {
		return this.destination;
	}

	void setOrientation(String orientation) {
		this.orientation = orientation;
	}
}
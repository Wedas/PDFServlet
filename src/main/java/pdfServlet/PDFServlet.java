package pdfServlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import pdfConverter.PDFConverter;

@MultipartConfig
public class PDFServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Part pdfPart = req.getPart("file");
		String fileName = Paths.get(pdfPart.getSubmittedFileName()).getFileName().toString();
		InputStream pdfFile = pdfPart.getInputStream();
		PDFConverter converter = new PDFConverter(pdfFile, fileName);
		File outputFile = converter.convertTo(req.getParameter("outputExt"), getServletContext().getResource("/"));
		req.setAttribute("result", outputFile.getName());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/output.jsp");
		rd.forward(req, resp);

	}
}

package sessionListener;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.io.FileUtils;

public class DeleteFileListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {		
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {		
		
		try {
			FileUtils.cleanDirectory(new File(arg0.getSession().getServletContext().getResource("/").getFile()+
					"\\documents4j"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}

}

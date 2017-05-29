package se.com.ui;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

/**
 * The <code>ViewerComponentExample</code> class is an example of how to use
 * <code>SwingController</code> and <code>SwingViewBuilder</code>
 * to build a PDF viewer component.  A file specified at the command line is
 * opened in a JFrame which contains the viewer component.
 *
 * @since 2.0
 */
public class PdfViewer {
	
    public void showPdf(String pdfFile) {
        // Get a file from the command line to open
        String filePath = pdfFile;

        // build a component controller
        SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        // add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationFrame.getContentPane().add(viewerComponentPanel);

		// Now that the GUI is all in place, we can try openning a PDF
        controller.openDocument(filePath);
//		try {
//			controller.openDocument(new URL("https://iotap.docfactory.com/api/v1/cnt/iotap/name/topic:How_to_change_the_powder_screw_pdf?api-key=RUxorkQlkk2SIhrCZPk3Kw!_l3fccObOEOArjXxD8Xurg"));
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}       
        // show the component
        applicationFrame.pack();
        applicationFrame.setVisible(true);
    }
}
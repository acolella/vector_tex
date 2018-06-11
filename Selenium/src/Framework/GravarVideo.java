package Framework;

import java.awt.*;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import static org.monte.media.VideoFormatKeys.*;

public class GravarVideo {
	
	    private ScreenRecorder screenRecorder;

	    public void IniciarGravacao() throws Exception {
	       
	    	 GraphicsConfiguration gc = GraphicsEnvironment
	    	.getLocalGraphicsEnvironment()
	    	.getDefaultScreenDevice()
	    	.getDefaultConfiguration();
	    	
	    	 this.screenRecorder = new ScreenRecorder(gc,
	    		 new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
	    		 new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	    		            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	    		            DepthKey, 24, FrameRateKey, Rational.valueOf(15),
	    		            QualityKey, 1.0f,
	    		            KeyFrameIntervalKey, 15 * 60),
	    		 new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
	    		            FrameRateKey, Rational.valueOf(30)),
	    		 null);

	    	 this.screenRecorder.start();
	    	

	    }

	    public void FinalizarGravacao() throws Exception {
	        this.screenRecorder.stop();
	    }
	}



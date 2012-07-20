package servlet;

import DAO.PlayerDao;
import DAO.TeamDao;
import DAO.TournamentDao;
import DAO.UserDao;
import model.Player;
import model.Team;
import model.Tournament;
import model.User;

import javax.media.jai.JAI;
import javax.media.jai.OpImage;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.media.jai.codec.SeekableStream;

import java.awt.RenderingHints;
import java.awt.image.renderable.ParameterBlock;
import java.io.*;
import java.util.logging.Logger;

public class ImageController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    public static final String image = "/Users/NoePodesta/Pictures/laboratorio/tournament.jpg";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException {
		
		String action = request.getParameter("action");
		String value  = request.getParameter("value");
        int size = Integer.valueOf(request.getParameter("size"));
		
		if(action.equals("USER")){
			User user = UserDao.getUserByUserName(request.getRemoteUser());
			if(user.getPhoto().equals(null)){
                byte[] image = user.getPhoto();
                getImage(response, image, size);
            }else{
                getImage(response,defaultImage(image),size);
            }
		}else if(action.equals("TOURNAMENT")){
			Tournament tournament = TournamentDao.getTournamentById(Integer.parseInt(value));
			if(tournament.getPhoto().length>0){
				byte[] image = tournament.getPhoto();
				getImage(response, image, size);
			}else{
				getImage(response,defaultImage(image),size);
			}
		}else if(action.equals("TEAM")){
			Team team = TeamDao.getTeamById(value);
			if(team.getPhoto().length>0){
				byte[] image = team.getPhoto();
				getImage(response, image, size);
			}else{
				getImage(response,defaultImage(image),size);
				System.out.println("No hay Foto");
			}
			
		}else if(action.equals("PLAYER")){
            Player player = PlayerDao.getPlayer(Integer.valueOf(value));
            if(player.getPhoto().length>0){
                byte[] image = player.getPhoto();
                getImage(response, image, size);
            }else{
                getImage(response,defaultImage(image),size);
            }
        }
		
		
		
	
	    

		
	}

    public byte[] defaultImage(String path){
        File file = new File(path);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //create FileInputStream which obtains input bytes from a file in a file system
        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.

        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		doPost(request, response);
	}

    public void getImage(HttpServletResponse response, byte[] image, int size) throws IOException{
		response.setContentType("image/jpeg");
		byte[] imageBytes = resizeImageAsJPG(image, size);
	    OutputStream os;
	    if(imageBytes.length!=0){
	            try {
	            	
	                response.setContentLength(imageBytes.length);
	            	os = response.getOutputStream();
	                response.getOutputStream().write(imageBytes);
	                response.getOutputStream().flush();  
	                os.close();

	            } catch (IOException e) {
	                    e.printStackTrace();
	            }
	    }
	}
	
	

    private static final String JAI_STREAM_ACTION = "stream";
    private static final String JAI_SUBSAMPLE_AVERAGE_ACTION = "SubsampleAverage";
    private static final String JAI_ENCODE_FORMAT_JPEG = "JPEG";
    private static final String JAI_ENCODE_ACTION = "encode";
    private int mMaxWidth = 250;
 
    public byte[] resizeImageAsJPG(byte[] pImageData, int pMaxWidth) throws IOException {
    InputStream imageInputStream = new ByteArrayInputStream(pImageData);
    // read in the original image from an input stream
    SeekableStream seekableImageStream = SeekableStream.wrapInputStream(imageInputStream, true);
    RenderedOp originalImage = JAI.create(JAI_STREAM_ACTION, seekableImageStream);
    ((OpImage) originalImage.getRendering()).setTileCache(null);
    int origImageWidth = originalImage.getWidth();
    // now resize the image
    double scale = 1.0;
    if (pMaxWidth > 0 && origImageWidth > pMaxWidth) {
        scale = (double) pMaxWidth / originalImage.getWidth();
    }
    ParameterBlock paramBlock = new ParameterBlock();
    paramBlock.addSource(originalImage); // The source image
    paramBlock.add(scale); // The xScale
    paramBlock.add(scale); // The yScale
    paramBlock.add(0.0); // The x translation
    paramBlock.add(0.0); // The y translation
 
    RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);
 
    RenderedOp resizedImage = JAI.create(JAI_SUBSAMPLE_AVERAGE_ACTION, paramBlock, qualityHints);
 
    // lastly, write the newly-resized image to an output stream, in a specific encoding
    ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
    JAI.create(JAI_ENCODE_ACTION, resizedImage, encoderOutputStream, JAI_ENCODE_FORMAT_JPEG, null);
    // Export to Byte Array
    byte[] resizedImageByteArray = encoderOutputStream.toByteArray();
    return resizedImageByteArray;
    }
	

	


}

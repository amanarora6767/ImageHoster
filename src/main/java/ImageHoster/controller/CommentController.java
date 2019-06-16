package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {

     @Autowired
     private CommentService commentService;
    //Add comment to the data base and return the updated image to the page
    @RequestMapping(value ="/image/{imageId}/{imageTitle}/comments" , method = RequestMethod.POST)
    public String SubmitComment(@RequestParam("comment")String comment, @PathVariable("imageId") int imageId, @PathVariable("imageTitle") String imageTitle, HttpSession session) {

       Comment commentobj = new Comment();
       commentobj.setCreatedDate(new Date());
       commentobj.setText(comment);
       commentobj.setUser((User) session.getAttribute("loggeduser"));
       Image image = new Image();
       image.setId(imageId);
       image.setTitle(imageTitle);
       commentobj.setImage(image);
       commentService.addComment(commentobj);

       return "redirect:/images/"+imageId+"/"+imageTitle;
    }
}

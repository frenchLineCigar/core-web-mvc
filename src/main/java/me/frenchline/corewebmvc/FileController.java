package me.frenchline.corewebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-25
 */
@Controller
public class FileController {

    //파일 업로드 폼
    @GetMapping("/file")
    public String fileUploadForm(Model model) { //Model을 아규먼트로 선언만 해두면 자동으로 파일 업로드 처리 후 넘어오는 message가 담김
        return "files/index";
    }

    //파일 업로드 처리
    @PostMapping("/file")
    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        // save 가정
        // 파일 업로드가 되었다는 메세지를 RedirectAttributes를 사용해 Flash Attribute로 보내보자
        System.out.println("file name: " + file.getName());
        System.out.println("file original name: " + file.getOriginalFilename());
        System.out.println("file content type: " + file.getContentType());
        String message = file.getOriginalFilename() + " is uploaded";
        RedirectAttributes message1 = attributes.addFlashAttribute("message", message);//message가 세션에 담김 -> redirect 후 사용된 다음에 세션에서 바로 제거됨
        return "redirect:/file";
    }
}

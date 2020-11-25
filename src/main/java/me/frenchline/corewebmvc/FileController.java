package me.frenchline.corewebmvc;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-25
 */
@Controller
public class FileController {

    @Autowired
    private ResourceLoader resourceLoader;

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

    //파일 다운로드
    @GetMapping("/file/{filename}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filename); //클래스패스 기준으로 해당 이름의 파일을 읽어온다
        File file = resource.getFile();

        Tika tika = new Tika(); //Tika 객체 자체는 재사용을 해도 되므로, Bean으로 등록해서 사용해도 된다.
        String mediaType = tika.detect(file);
        System.out.println("mediaType = " + mediaType);

        return ResponseEntity.ok() //상태 코드 200 설정
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, mediaType)
                .header(HttpHeaders.CONTENT_LENGTH, file.length() + "")
                .body(resource);



    }

    /**
     * ResponseEntity
     * ● 응답 상태 코드
     * ● 응답 헤더
     * ● 응답 본문
     *
     * ResponseEntity<Resource>가 응답 헤더와 응답 본문(Response Body)의 내용을 포함한 타입(Resource를 응답용으로 감싼 래퍼객체)이기 때문에
     * 리턴 타입이 ResponseEntity의 경우 메서드에 @ResponseBody를 안 붙여도 상관 없다.
     *
     *
     * 1. ResponseEntity<Resource>: ResponseEntity의 제네릭으로 응답 본문(Response Body)의 타입을 정의할 수 있다.
     * // Resource 자체를 응답 본문에 담는다.
     *
     * 2. ResponseEntity.ok(): 상태 코드 값을 200으로 결정
     *
     * 3. 헤더에 설정해주면 좋은 값들이 몇 가지 있다.
     * 1) .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() )
     * - CONTENT_DISPOSITION: 사용자가 이 파일을 다운로드 받을 때 팝업 창이 열리면서 어떤 이름으로 저장할 지 이름을 정해줄 수 있다
     * 2) .header(HttpHeaders.CONTENT_TYPE, "image/jpg")
     * - CONTENT_TYPE: 이 파일이 어떠한 파일인지 MediaType을 알려준다
     * 3) .header(HttpHeaders.CONTENT_LENGTH, file.length() + "") //헤더는 문자열이어야 해서 뒤에 "" 를 붙여 String 타입으로 넣어준다
     * - CONTENT_LENGTH: 파일의 크기 정보를 알려준다
     *
     * 4. 최종적으로 Body에 리소스를 넣어주면 다운로드가 가능해진다
     * .body(resource);
     *
     * Response Headers 정보
     *  Content-Disposition: attachment; filename="test.jpg"
     *  Content-Length: 116011
     *  Content-Type: image/jpeg
     */
}

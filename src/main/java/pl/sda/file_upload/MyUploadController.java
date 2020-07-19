package pl.sda.file_upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-upload")
public class MyUploadController {

    @GetMapping("/show-upload-form")
    public String showMyUploadForm() {
        return "my-upload-form";
    }

    @PostMapping("/file-consumer")
    public String consumeFile() {
        return "redirect:/";
    }

    @GetMapping("/all-files")
    public String showAllFilesOnServer() {
        return "all-files";
    }
}

package pl.sda.file_upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import pl.sda.file_upload.storage.StorageService;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/my-upload")
//@RequestMapping("${my-app-upload.prefix}")
public class MyUploadController {

    private static final Logger log = LoggerFactory.getLogger(MyUploadController.class);
    private static final String filesKey = "files";

    private final StorageService storageService;

    public MyUploadController(final StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/show-upload-form")
    public String showMyUploadForm() {
        return "my-upload-form";
    }

    @PostMapping("/file-consumer")
    public String consumeFile(@RequestParam("twit")MultipartFile file) {
        storageService.store(file);
        return "redirect:/my-upload/all-files";
    }

    @GetMapping("/all-files")
    public String showAllFilesOnServer(Model model) {
        model.addAttribute(filesKey, storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(MyUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));
        return "all-files";
    }

    @ExceptionHandler(Exception.class)
    public String handler(Exception exception) {
        log.warn("caugthed exception", exception);
        return "sorry";
    }
}

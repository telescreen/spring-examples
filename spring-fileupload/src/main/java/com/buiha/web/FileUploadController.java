package com.buiha.web;

import com.buiha.storage.StorageException;
import com.buiha.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by tal on 2017/03/07.
 */

@Controller
public class FileUploadController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/")
    public String listUploadFiles(Model model) {
        return "uploadForm";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        String message = "You successfully uploaded " + file.getOriginalFilename() + "!";
        try {
            storageService.store(file);
        } catch (StorageException e) {
            message = e.getMessage();
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/";
    }
}

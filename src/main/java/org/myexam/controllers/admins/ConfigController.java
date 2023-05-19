package org.myexam.controllers.admins;

<<<<<<< HEAD
=======
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.myexam.commons.configs.ConfigInfoService;
import org.myexam.commons.configs.ConfigSaveService;
>>>>>>> adminpage
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
@Controller
@RequestMapping("/admin/config")
public class ConfigController {
    @GetMapping
    public String config(Model model) {
        commonProcess(model);
//        model.addAttribute("message", "수정완료!");
=======
@Log
@Controller
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigSaveService saveService;
    private final ConfigInfoService infoService;
    private String code = "siteConfig";

    @GetMapping
    public String config(Model model) {
        commonProcess(model);
        ConfigForm configForm = infoService.get(code, ConfigForm.class);

        model.addAttribute("configForm", configForm == null ? new ConfigForm() : configForm);
>>>>>>> adminpage
        return "admin/config";
    }

    @PostMapping
<<<<<<< HEAD
    public String configPs(Model model) {
        commonProcess(model);

=======
    public String configPs(ConfigForm configForm, Model model) {
        commonProcess(model);

        saveService.save(code, configForm);

        model.addAttribute("message", "설정이 저장되었습니다.");

>>>>>>> adminpage
        return "admin/config";
    }

    private void commonProcess(Model model) {
        String title = "사이트 설정";
        String menuCode = "config";
        model.addAttribute("pageTitle", title);
        model.addAttribute("title", title);
        model.addAttribute("menuCode", menuCode);
    }
}

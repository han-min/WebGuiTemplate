package org.hanmin.controller.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormSubmitController {

    @RequestMapping(value = "/form_submit_get", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("formTemplate", new FormTemplate());
        return "form_submit_show"; // templates/form_submit_show.html
    }

    @RequestMapping(value = "/form_submit_submit", method = RequestMethod.POST)
    public String submit(@ModelAttribute("formTemplate") FormTemplate theFormTemplate,
                         BindingResult result,
                         ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("name", theFormTemplate.getFormName());
        model.addAttribute("id", theFormTemplate.getFormId());
        model.addAttribute("description", theFormTemplate.getFormLongDescription());
        return "form_submit_result"; // templates/form_submit_result.html
    }

}

package com.incra;

import com.incra.models.Game;
import com.incra.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @Autowired
    private GameService siteService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String index(ModelMap model, int siteId) {

        Game site = siteService.findEntityById(siteId);

        //model.addAttribute("user", new User());
        //model.addAttribute("users", users);
        return "page/index";
    }

}

package com.incra.controllers;

import com.incra.models.Episode;
import com.incra.models.Game;
import com.incra.models.propertyEditor.GamePropertyEditor;
import com.incra.services.EpisodeService;
import com.incra.services.PageFrameworkService;
import com.incra.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The <i>BoxController</i> controller implements CRUD operations on Boxes.
 *
 * @author Jeffrey Risberg
 * @since 03/12/14
 */
@Controller
public class EpisodeController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(EpisodeController.class);

    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private GameService siteService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public EpisodeController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
        dataBinder.registerCustomEditor(Game.class,
                new GamePropertyEditor(siteService));
    }

    @RequestMapping(value = "/episode/**")
    public String index() {
        return "redirect:/episode/list";
    }

    @RequestMapping(value = "/episode/list")
    public ModelAndView list(Object criteria) {

        List<Episode> episodeList = episodeService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("episode/list");
        modelAndView.addObject("episodeList", episodeList);
        return modelAndView;
    }

    @RequestMapping(value = "/episode/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Episode episode = episodeService.findEntityById(id);
        if (episode != null) {
            model.addAttribute(episode);
            return "episode/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Box with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/episode/list";
        }
    }

    @RequestMapping(value = "/episode/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Episode episode = new Episode();
        List<Game> siteList = siteService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("episode/create");
        modelAndView.addObject("siteList", siteList);
        modelAndView.addObject("command", episode);
        return modelAndView;
    }

    @RequestMapping(value = "/episode/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {
        
        Episode episode = episodeService.findEntityById(id);
        List<Game> siteList = siteService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("episode/edit");
        modelAndView.addObject("siteList", siteList);
        modelAndView.addObject("command", episode);

        return modelAndView;
    }

    @RequestMapping(value = "/episode/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Episode episode,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "episode/edit";
        }

        try {
            if (episode.getDateCreated() == null) episode.setDateCreated(new Date());
            episode.setLastUpdated(new Date());

            episodeService.save(episode);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/episode/list";
        }
        return "redirect:/episode/list";
    }

    @RequestMapping(value = "/episode/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Episode episode = episodeService.findEntityById(id);
        if (episode != null) {
            try {
                episodeService.delete(episode);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/episode/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Box with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/episode/list";
    }
}
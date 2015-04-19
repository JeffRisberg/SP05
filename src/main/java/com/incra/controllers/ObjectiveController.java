package com.incra.controllers;

import com.incra.models.Episode;
import com.incra.models.Game;
import com.incra.models.Objective;
import com.incra.models.propertyEditor.EpisodePropertyEditor;
import com.incra.models.propertyEditor.GamePropertyEditor;
import com.incra.services.EpisodeService;
import com.incra.services.GameService;
import com.incra.services.ObjectiveService;
import com.incra.services.PageFrameworkService;
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
 * The <i>ObjectiveController</i> controller implements CRUD operations on Objectives.
 *
 * @author Jeffrey Risberg
 * @since 03/12/14
 */
@Controller
public class ObjectiveController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(ObjectiveController.class);

    @Autowired
    private GameService gameService;
    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private ObjectiveService objectiveService;

    @Autowired
    private PageFrameworkService pageFrameworkService;

    public ObjectiveController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
        dataBinder.registerCustomEditor(Game.class,
                new GamePropertyEditor(gameService));
        dataBinder.registerCustomEditor(Episode.class,
                new EpisodePropertyEditor(episodeService));
    }

    @RequestMapping(value = "/objective/**")
    public String index() {
        return "redirect:/objective/list";
    }

    @RequestMapping(value = "/objective/list")
    public ModelAndView list(Object criteria) {

        List<Objective> objectiveList = objectiveService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("objective/list");
        modelAndView.addObject("objectiveList", objectiveList);
        return modelAndView;
    }

    @RequestMapping(value = "/objective/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Objective objective = objectiveService.findEntityById(id);
        if (objective != null) {
            model.addAttribute(objective);
            return "objective/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Objective with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/objective/list";
        }
    }

    @RequestMapping(value = "/objective/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Objective objective = new Objective();
        List<Game> gameList = gameService.findEntityList();
        List<Episode> episodeList = episodeService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("objective/create");
        modelAndView.addObject("gameList", gameList);
        modelAndView.addObject("episodeList", episodeList);
        modelAndView.addObject("command", objective);
        return modelAndView;
    }

    @RequestMapping(value = "/objective/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {

        Objective objective = objectiveService.findEntityById(id);
        List<Game> gameList = gameService.findEntityList();
        List<Episode> episodeList = episodeService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("objective/edit");
        modelAndView.addObject("gameList", gameList);
        modelAndView.addObject("episodeList", episodeList);
        modelAndView.addObject("command", objective);

        return modelAndView;
    }

    @RequestMapping(value = "/objective/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Episode episode,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "objective/edit";
        }

        try {
            if (episode.getDateCreated() == null) episode.setDateCreated(new Date());
            episode.setLastUpdated(new Date());

            episodeService.save(episode);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/objective/list";
        }
        return "redirect:/objective/list";
    }

    @RequestMapping(value = "/objective/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Episode episode = episodeService.findEntityById(id);
        if (episode != null) {
            try {
                episodeService.delete(episode);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/objective/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Objective with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/objective/list";
    }
}
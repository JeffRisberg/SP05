package com.incra.controllers;

import com.incra.models.Game;
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
 * The <i>SiteController</i> controller implements CRUD operations on Sites.
 *
 * @author Jeffrey Risberg
 * @since 03/12/14
 */
@Controller
public class GameController extends AbstractAdminController {
    protected static Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;
    @Autowired
    private PageFrameworkService pageFrameworkService;

    public GameController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) throws Exception {
        dataBinder.registerCustomEditor
                (Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), false));
    }

    @RequestMapping(value = "/game/**")
    public String index() {
        return "redirect:/game/list";
    }

    @RequestMapping(value = "/game/list")
    public ModelAndView list(Object criteria) {

        List<Game> gameList = gameService.findEntityList();

        ModelAndView modelAndView = new ModelAndView("game/list");
        modelAndView.addObject("gameList", gameList);
        return modelAndView;
    }

    @RequestMapping(value = "/game/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, Model model, HttpSession session) {

        Game game = gameService.findEntityById(id);
        if (game != null) {
            model.addAttribute(game);
            return "game/show";
        } else {
            pageFrameworkService.setFlashMessage(session, "No Site with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/game/list";
        }
    }

    @RequestMapping(value = "/game/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Game game = new Game();

        ModelAndView modelAndView = new ModelAndView("game/create");
        modelAndView.addObject("command", game);

        return modelAndView;
    }

    @RequestMapping(value = "/game/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {
        Game game = gameService.findEntityById(id);

        ModelAndView modelAndView = new ModelAndView("game/edit");
        modelAndView.addObject("command", game);

        return modelAndView;
    }

    @RequestMapping(value = "/game/save", method = RequestMethod.POST)
    public String save(final @ModelAttribute("command") @Valid Game game,
                       BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "game/edit";
        }

        try {
            if (game.getDateCreated() == null) game.setDateCreated(getNow());
            game.setLastUpdated(getNow());

            gameService.save(game);
        } catch (RuntimeException re) {
            pageFrameworkService.setFlashMessage(session, re.getMessage());
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
            return "redirect:/game/list";
        }
        return "redirect:/game/list";
    }

    @RequestMapping(value = "/game/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpSession session) {

        Game game = gameService.findEntityById(id);
        if (game != null) {
            try {
                gameService.delete(game);
            } catch (RuntimeException re) {
                pageFrameworkService.setFlashMessage(session, re.getMessage());
                pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
                return "redirect:/game/show/" + id;
            }
        } else {
            pageFrameworkService.setFlashMessage(session, "No Site with that id");
            pageFrameworkService.setIsRedirect(session, Boolean.TRUE);
        }

        return "redirect:/game/list";
    }
}
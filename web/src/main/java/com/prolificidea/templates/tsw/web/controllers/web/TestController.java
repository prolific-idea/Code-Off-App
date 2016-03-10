package com.prolificidea.templates.tsw.web.controllers.web;

import com.prolificidea.templates.tsw.domain.entities.AppUser;
import com.prolificidea.templates.tsw.domain.entities.AppUserDetails;
import com.prolificidea.templates.tsw.domain.entities.AppUserRole;
import com.prolificidea.templates.tsw.domain.entities.Role;
import com.prolificidea.templates.tsw.services.providers.AppUserDetailsService;
import com.prolificidea.templates.tsw.services.providers.AppUserRoleService;
import com.prolificidea.templates.tsw.services.providers.AppUserService;
import com.prolificidea.templates.tsw.services.providers.RoleService;
import com.prolificidea.templates.tsw.web.models.test.TestAppUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AppUserRoleService appUserRoleService;

    @RequestMapping(value = "/test/crud", method = RequestMethod.GET)
    public ModelAndView testCrud() {
        List<AppUser> appUserList = appUserService.getAllAppUser();
        TestAppUserModel testAppUserModel = new TestAppUserModel();
        ModelMap model = new ModelMap();
        model.addAttribute("appUserList", appUserList);
        model.addAttribute("testAppUserModel", testAppUserModel);
        return new ModelAndView("/test/crud", model);
    }

    @RequestMapping(value = "/test/crud", method = RequestMethod.POST)
    public ModelAndView testCrudPost(
            @ModelAttribute("testAppUserModel") @Valid TestAppUserModel testAppUserModel,
            BindingResult bindingResult) {

        AppUser appUser = new AppUser();
        appUser.setUsername(testAppUserModel.getUsername());
        appUser.setPassword(hashWithSha256(testAppUserModel.getPassword(), testAppUserModel.getUsername()));
        appUser.setEnabled(true);

        AppUserDetails appUserDetails = new AppUserDetails();
        appUserDetails.setFirstName(testAppUserModel.getFirstName());
        appUserDetails.setLastName(testAppUserModel.getLastName());
        appUserDetails.setEmailAddress(testAppUserModel.getUsername());

        appUserDetails = appUserDetailsService.createAppUserDetails(appUserDetails);

        appUser.setAppUserDetails(appUserDetails);

        appUserService.createAppUser(appUser);

        AppUserRole appUserRole = new AppUserRole();
        Role role = new Role();
        role.setDescription("SIMPLE");
        role = roleService.createRole(role);
        appUserRole.setAppUserId(appUser);
        appUserRole.setRoleId(role);

        appUserRoleService.createAppUserRole(appUserRole);

        return new ModelAndView("redirect:/test/crud", null);
    }
    public static String hashWithSha256(String value, String salt) {
        BasePasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
        return  passwordEncoder.encodePassword(value, salt);
    }


    @RequestMapping(value = "/test/init", method = RequestMethod.GET)
    public void testInitialize() {
        List<AppUser> appUserList = appUserService.getAllAppUser();
        for(AppUser appUser: appUserList) {
            System.out.println(appUser.getUsername());
            System.out.println(appUser.getPassword());
            for(AppUserRole appUserRole :appUser.getAppUserRoles()) {
                System.out.println("ROLE: " + appUserRole.getRoleId().getDescription());
            }
        }

    }

    @RequestMapping(value = "/test/data", method = RequestMethod.GET)
    public void testData() {
        List<AppUser> appUserList = appUserService.getAllAppUser();
        for(AppUser appUser: appUserList) {
            System.out.println(appUser.getUsername());
        }
    }

}

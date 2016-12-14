package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by yjkim on 2016-09-28.
 */

@Controller
@RequestMapping(value = {"/client"})
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 클라이언트 메인 페이지 이동
     *
     * @return client main
     */
    @RequestMapping(value = {"/clientMain"}, method = RequestMethod.GET)
    public ModelAndView getClientMain() {
        return new ModelAndView(){{setViewName("/client/clientMain");}};
    }

    /**
     * 클라이언트 등록 페이지 이동
     *
     * @return client insert form
     */
    @RequestMapping(value = {"/clientForm"}, method = RequestMethod.GET)
    public ModelAndView getClientForm() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("INSERT_FLAG", Constants.CUD_C);
        mv.setViewName("/client/clientForm");

        return mv;
    }

    /**
     * 클라이언트 조회/수정 페이지 이동
     *
     * @param req
     * @return client update form
     */
    @RequestMapping(value = {"/clientForm"}, method = RequestMethod.POST)
    public ModelAndView getClientForm(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("INSERT_FLAG", Constants.CUD_U);
        mv.addObject("CLIENT_ID", req.getParameter("clientId"));
        mv.setViewName("/client/clientForm");

        return mv;
    }


    /**
     * 클라이언트 목록 조회
     *
     * @param param the param
     * @return client list (map)
     */
    @RequestMapping(value = {"/getClientList"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getClientList(@RequestBody Map<String, Object> param) throws Exception {
        LOGGER.info("ClientController - getClientList " );
        if (param.get("searchKeyword") != null)
            LOGGER.info("kerword :  " + param.get("searchKeyword"));
        return commonService.procRestTemplate("/client/getClientList", HttpMethod.POST, param, null);
    }


    /**
     * 클라이언트 정보 조회
     *
     * @param param the param
     * @return client (map)
     */
    @RequestMapping(value = {"/getClient"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getClient(@RequestBody Map<String, Object> param) throws Exception {
        return commonService.procRestTemplate("/client/getClient", HttpMethod.POST, param, null);
    }

    /**
     * 클라이언트 등록
     *
     * @param param the param
     * @return result (map)
     */
    @RequestMapping(value = {"/registerClient"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerClient(@RequestBody Map<String, Object> param) throws Exception {
        return commonService.procRestTemplate("/client/registerClient", HttpMethod.POST, param, null);
    }

    /**
     * 클라이언트 수정
     *
     * @param param the param
     * @return result (map)
     */
    @RequestMapping(value = {"/updateClient"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateClient(@RequestBody Map<String, Object> param) throws Exception {
        return commonService.procRestTemplate("/client/updateClient", HttpMethod.POST, param, null);
    }

    /**
     * 클라이언트 삭제
     *
     * @param param the param
     * @return result (map)
     */
    @RequestMapping(value = {"/deleteClient"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteClient(@RequestBody Map<String, Object> param) throws Exception {
        return commonService.procRestTemplate("/client/deleteClient", HttpMethod.POST, param, null);
    }

}

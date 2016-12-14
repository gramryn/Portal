package org.openpaas.paasta.portal.web.admin.controller;

import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.model.ServiceBroker;
import org.openpaas.paasta.portal.web.admin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Org Controller
 *
 * @author nawkm
 * @version 1.0
 * @since 2016.8.29 최초작성
 */
@Controller
public class ServiceBrokerController extends Common {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBrokerController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 서비스 브로커 화면
     *
     * @return
     */
    @RequestMapping(value = {"/service/serviceBrokerMain"}, method = RequestMethod.GET)
    public ModelAndView serviceBroker() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("service/serviceBrokerMain");

        return mv;
    }

    /**
     * 서비스 브로커상세 화면
     *
     * @return
     */
    @RequestMapping(value = {"/service/serviceBrokerDetail"}, method = RequestMethod.GET)
    public ModelAndView serviceBrokerDetail() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("service/serviceBrokerDetail");

        return mv;
    }


    /**
     * 서비스 브로커 등록 화면
     *
     * @return
     */
    @RequestMapping(value = {"/service/serviceBrokerCreate"}, method = RequestMethod.GET)
    public ModelAndView serviceBrokerCreate() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("service/serviceBrokerCreate");

        return mv;
    }


    /**
     * 서비스 브로커 리스트 조회
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/getServiceBrokers"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getServiceBrokers(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procRestTemplate("/service/getServiceBrokers", HttpMethod.POST, serviceBroker, this.getToken());

    }

    /**
     * 서비스 브로커 조회
     *
     * @param serviceBroker serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/getServiceBroker"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getServiceBroker(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procRestTemplate("/service/getServiceBroker", HttpMethod.POST, serviceBroker, this.getToken());

    }


    /**
     * 서비스 브로커 등록
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/createServiceBroker"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createServiceBroker(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procRestTemplate("/service/createServiceBroker", HttpMethod.POST, serviceBroker, this.getToken());
    }


    /**
     * 서비스 브로커 수정
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/updateServiceBroker"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateServiceBroker(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procRestTemplate("/service/updateServiceBroker", HttpMethod.POST, serviceBroker, this.getToken());
    }


    /**
     * 서비스 브로커 삭제
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/deleteServiceBroker"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteServiceBroker(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procRestTemplate("/service/deleteServiceBroker", HttpMethod.POST, serviceBroker, this.getToken());

    }


    /**
     * 서비스 브로커 이름 수정
     *
     * @param serviceBroker the serviceBroker
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/service/renameServiceBroker"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> renameServiceBroker(@RequestBody ServiceBroker serviceBroker) {

        return commonService.procRestTemplate("/service/renameServiceBroker", HttpMethod.POST, serviceBroker, this.getToken());

    }


}


<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="tempInitMessage" class="tab-title-box" style="background-color: #f5f7f8;"> Waiting for Status data..</div>
<div id="appStatusView" style="display: none;">
    <div class="custom-tab-title-box" style="background-color: #f5f7f8;"> 상태정보</div>
    <table id="appStatusTable" class="event-table" style="display: none;">

        <thead>
        <tr height="40" style="border:1px solid #dedede;">
            <th style="border:1px solid #dedede; text-align:center; color:#848383; font-size:17px;">NO</th>
            <th style="border:1px solid #dedede; text-align:center; color:#848383; font-size:17px;">Status</th>
            <th style="border:1px solid #dedede; text-align:center; color:#848383; font-size:17px;">CPU</th>
            <th style="border:1px solid #dedede; text-align:center; color:#848383; font-size:17px;">MEMORY</th>
            <th style="border:1px solid #dedede; text-align:center; color:#848383; font-size:17px;">DISK</th>
            <th style="border:1px solid #dedede; text-align:center; color:#848383; font-size:17px;">UPTIME</th>
            <th style="border:1px solid #dedede; text-align:center; color:#848383; font-size:17px;">RESTART</th>
        </tr>
        </thead>
        <tbody id="appStatusListTable">
        </tbody>
    </table>
</div>


<script type="text/javascript">
    // SET APP STATUS TAB
    var procSetAppStatusTab = function (data) {
        var objViewArea = $('#appStatusView');
        var objTableArea = $('#appStatusTable');
        var objTable = $('#appStatusListTable');
        var htmlString = [];

        var tempResultStatus = null;
        var resultStatus = null;
        var resultCpu = null;
        var resultMemory = null;
        var resultDisk = null;
        var resultUptime = null;

        $.each(data, function (key, dataObject) {
            tempResultStatus = dataObject.state;
            resultCpu = procCalculateFloor((dataObject.stats.usage.cpu * 100), 2);
            resultMemory = procConvertFormatNumber(dataObject.stats.usage.mem);
            resultDisk = procConvertFormatNumber(dataObject.stats.usage.disk);
            resultUptime = procConvertFormatNumber(procCalculateFloor((dataObject.stats.uptime / 60), 0));

            if ('' != tempResultStatus && 'RUNNING' == tempResultStatus.toUpperCase()) {
                resultStatus = 'style="color: #006400;"><span class="glyphicon glyphicon-ok-circle mr10" aria-hidden="true"></span> ' + tempResultStatus;

            } else if ('' != tempResultStatus && 'STARTING' == tempResultStatus.toUpperCase()) {
                resultStatus = 'style="color: #00bfff;"><span class="glyphicon glyphicon-refresh mr10" aria-hidden="true"></span> ' + tempResultStatus;

            } else {
                resultStatus = 'style="color: #ff0000;"><span class="glyphicon glyphicon-remove-circle mr10" aria-hidden="true"></span> ' + tempResultStatus;
            }

            htmlString.push('<tr>'
                    + '<td class="col-md-1 tac" style="font-size: 15px; color:#979696;">' + key + ' </td>'
                    + '<td class="col-md-2 tac" style="font-size: 15px; color:#31cc45;" ' + resultStatus + ' </td>'
                    + '<td class="col-md-2 tac" style="font-size: 15px;color:#979696;">' + resultCpu + ' % </td>'
                    + '<td class="col-md-2 tac" style="font-size: 15px;color:#979696;">' + resultMemory + ' Bytes </td>'
                    + '<td class="col-md-2 tac" style="font-size: 15px;color:#979696;">' + resultDisk + ' Bytes </td>'
                    + '<td class="col-md-2 tac" style="font-size: 15px;color:#979696;" title="' + procConvertFormatNumber(dataObject.stats.uptime) + ' (sec)">' + resultUptime + ' (min) </td>'
                    + '<td class="col-md-1 tac"><button type="button" style="margin-top: 5px;" class="btn btn-default buttonAppInstanceRestart" id="' + key + '"> RESTART </button></td>'
                    + '</tr>');
        });

        $('#tempInitMessage').hide();
        objTable.html('');
        objTableArea.hide();
        objViewArea.hide();

        objTable.append(htmlString);
        objTableArea.show();
        objViewArea.show();

        // BIND :: BUTTON CLICK
        $(".buttonAppInstanceRestart").on("click", function () {
            procAppInstanceRestart(this.id);
            $('#alert').focus();
        });
    };


    // APP INSTANCE RESTART
    var procAppInstanceRestart = function (appInstanceId) {
        procCallSpinner(SPINNER_SPIN_START);

        var APP_INSTANCE_RESTART_PROC_URL = "<c:url value='/app/executeTerminateAppInstanceByIndex' />";
        var param = {
            orgName: currentOrg,
            spaceName: currentSpace,
            guid: currentAppGuid,
            appInstanceIndex: appInstanceId
        };

        procCallAjax(APP_INSTANCE_RESTART_PROC_URL, param, procCallbackAppInstanceRestart);
    };


    // CALLBACK :: APP INSTANCE RESTART
    var procCallbackAppInstanceRestart = function (data) {
        if (RESULT_STATUS_FAIL != data.RESULT) {
            showAlert('success', "<spring:message code='common.info.result.success' />");
        }

        getAppStats();
        procCallSpinner(SPINNER_SPIN_STOP);

    };

    // CALCULATE FLOOR
    var procCalculateFloor = function (reqNumber, reqPosition) {
        var digits = Math.pow(10, reqPosition);
        var num = Math.round(reqNumber * digits) / digits;

        return num.toFixed(reqPosition);
    };


    // CONVERT FORMAT NUMBER
    var procConvertFormatNumber = function (reqString) {
        return reqString.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };

</script>
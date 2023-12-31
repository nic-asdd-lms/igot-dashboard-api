package igot.integration.dashboard;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.UUID;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igot.integration.log.LogModel;
import igot.integration.log.LogService;
import igot.integration.model.metricsapiresponse.MetricsApiFinalResponse;
import igot.integration.user.UserService;
import igot.integration.util.Constants;

@RestController
@RequestMapping("/ehrmsservice/apis/igot")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @Autowired
    LogService logService;

    @Autowired
    UserService userService;

    @GetMapping("/dashboard/analytics/{parentMapId}")
    public ResponseEntity<MetricsApiFinalResponse> getMetrics(@PathVariable("parentMapId") String orgId,
            @RequestHeader("id") UUID id,@RequestHeader(Constants.AUTHORIZATION) String token) throws IOException, ParseException {
        LogModel logModel = new LogModel(id, orgId, "getMetrics", LocalDateTime.now());
        logService.createLog(logModel);

        MetricsApiFinalResponse response = dashboardService.getOrgMetrics(id, orgId,token);
        return new ResponseEntity<>(response, response.getResponseCode());

    }

    
    

}


package hotel.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by uengine on 2018. 11. 21..
 */

//@FeignClient(name="reserveOK", url="http://localhost:8084")

@FeignClient(name="reserveOK", url="${api.url.product}")
public interface ComfirmProcesingService {

    @RequestMapping(method= RequestMethod.GET, path="/comfirmProcesings")
    public void reservationOk(@RequestBody ComfirmProcesing comfirmProcesing);

}
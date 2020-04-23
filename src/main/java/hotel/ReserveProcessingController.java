package hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class ReserveProcessingController {

  @Autowired
  ReserveProcessingRepository reserveProcessingRepository;
//
@RequestMapping(value = "/reserveProcessings/create",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8")

public void reservation(@RequestBody ReserveProcessing reserveProcessing)
        throws Exception {
        reserveProcessingRepository.save(reserveProcessing);
        System.out.println("##### /reserveProcessing/reservation  called #####");
        }
//
//@RequestMapping(value = "/",
//        method = RequestMethod.PATCH,
//        produces = "application/json;charset=UTF-8")
//
//public void reservechange(HttpServletRequest request, HttpServletResponse response)
//        throws Exception {
//        System.out.println("##### /reserveProcessing/reservechange  called #####");
//        }
//
//@RequestMapping(value = "/",
//        method = RequestMethod.DELETE,
//        produces = "application/json;charset=UTF-8")
//
//public void reservecancel(HttpServletRequest request, HttpServletResponse response)
//        throws Exception {
//        System.out.println("##### /reserveProcessing/reservecancel  called #####");
//        }
 }

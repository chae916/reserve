package hotel;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="ReserveProcessing_table")
public class ReserveProcessing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String date;
    private String phone;
    private String userid;



    // 오류시 delete start
    private String mtype;

    public String getMtype() {
        return mtype;
    }

    public void setMytype(String mtype) {
        this.mtype = mtype;
    }

    // end

    @PostPersist
    public void onPostPersist(){
        Reserved reserved = new Reserved();
        BeanUtils.copyProperties(this, reserved);
        reserved.publish();
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        // 오류시 delete start
        System.out.println("mType : "+ mtype);
        // end

        hotel.external.ComfirmProcesing comfirmProcesing = new hotel.external.ComfirmProcesing();

        comfirmProcesing.setId(this.getId());

        if (mtype.equals("reserved")) {
            comfirmProcesing.setIflag("01");
        }
        else {
            comfirmProcesing.setIflag("02");
        }


        // mappings goes here
        Application.applicationContext.getBean(hotel.external.ComfirmProcesingService.class)
                .reservationOk(comfirmProcesing);


    }

    // 예약변경 시
    @PostUpdate
    public void onPostUpdate(){
        Changed changed = new Changed();
        BeanUtils.copyProperties(this, changed);
        changed.publish();


    }

    // 예약취소 시
    @PostRemove
    public void onPostRemove(){
        Canceled canceled = new Canceled();
        BeanUtils.copyProperties(this, canceled);
        canceled.publish();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}

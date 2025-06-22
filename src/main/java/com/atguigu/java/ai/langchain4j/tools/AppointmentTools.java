package com.atguigu.java.ai.langchain4j.tools;
import com.atguigu.java.ai.langchain4j.entity.Appointment;
import com.atguigu.java.ai.langchain4j.service.AppointmentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AppointmentTools {
    @Autowired
    private AppointmentService appointmentService;
    @Tool(name="预约教练", value = "根据参数，先执行工具方法queryName查询是否可预约，并直接给用户回答可预约时间，并让用户确认所有预约信息，用户确认后再进行预约。")
    public String bookAppointment(Appointment appointment){
//查找数据库中是否包含对应的预约记录
        Appointment appointmentDB = appointmentService.getOne(appointment);
        if(appointmentDB == null){
            appointment.setId(null);//防止大模型幻觉设置了id
            if(appointmentService.save(appointment)){
                return "预约成功，并返回预约详情";
            }else{
                return "预约失败";
            }
        }
        return "您在相同的教练和时间已有预约";
    }
    @Tool(name="取消预约教练", value = "根据参数，查询预约是否存在，如果存在则删除预约记录并返回取 消预约成功，否则返回取消预约失败")
    public String cancelAppointment(Appointment appointment){
        Appointment appointmentDB = appointmentService.getOne(appointment);
        if(appointmentDB != null){
//删除预约记录
            if(appointmentService.removeById(appointmentDB.getId())){
                return "取消预约成功";
            }else{
                return "取消预约失败";
            }
        }
//取消失败
        return "您没有预约记录，请核对预约科室和时间";
    }
    @Tool(name = "查询是否有教练", value="教练姓名，性别，时间和训练是否有空余时间，并返回给用户")
    public boolean queryName(
            @P(value = "教练姓名") String name,
            @P(value = "性别") String gender,
            @P(value = "时间，可选值：上午、下午") String time,
            @P(value = "训练类型", required = false) String type
    ) {
        System.out.println("查询是否有时间空余");
        System.out.println("教练姓名：" + name);
        System.out.println("性别：" + gender);
        System.out.println("时间：" + time);
        System.out.println("训练类型：" + type);
        return true;
    }
}
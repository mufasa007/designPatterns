package com.activeclub.designPatterns.adapter;

public class AdaptorTest {

    public static void main(String[] args) {
        // 直接使用充电宝，充电
        PowerBank powerBank = new PowerBank();
        System.out.println(powerBank.outputV5());

        // 使用110V转换后充电
        AdaptorPower adaptorPower1 = new AdaptorPower();
        adaptorPower1.setPower110V(new Power110V());
        System.out.println(adaptorPower1.outputV5());

        // 使用220V转换后充电
        AdaptorPower adaptorPower2 = new AdaptorPower();
        adaptorPower2.setPower220V(new Power220V());
        System.out.println(adaptorPower2.outputV5());

    }
}

/**
 * 通用手机充电，转换或者直接输出5v电压
 */
interface UniversalMobilePhoneCharging {
    String outputV5();
}

/**
 * 充电宝
 */
class PowerBank implements UniversalMobilePhoneCharging{

    @Override
    public String outputV5() {
        return "输出5V直流电";
    }
}

class AdaptorPower implements UniversalMobilePhoneCharging{
    private Power110V power110V;
    private Power220V power220V;

    public Power110V getPower110V() {
        return power110V;
    }

    public void setPower110V(Power110V power110V) {
        this.power110V = power110V;
    }

    public Power220V getPower220V() {
        return power220V;
    }

    public void setPower220V(Power220V power220V) {
        this.power220V = power220V;
    }

    @Override
    public String outputV5() {
        if(power110V!=null){
            return power110V.output110V();
        }else if(power220V!=null){
            return power220V.output220V();
        }
        return "无电力输出";
    }
}

/**
 * 国标220V市电
 */
class Power220V{
    public String output220V(){
        return "输出220V电压";
    }
}

/**
 * 美标110V市电
 */
class Power110V{
    public String output110V(){
        return "输出110V电压";
    }
}
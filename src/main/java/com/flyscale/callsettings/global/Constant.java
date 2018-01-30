package com.flyscale.callsettings.global;

/**
 * Created by MrBian on 2018/1/29.
 */

public class Constant {

    public static final String GSM_TYPE = "gms_type";

    public static final String GSM_CALL_WAITING = "gsm_call_waiting";
    public static final String GSM_CALL_BLOCK_BA_ALL = "gsm_call_block_ba_all";
    public static final String GSM_CALL_BLOCK_BA_MO = "gsm_call_block_ba_mo";
    public static final String GSM_CALL_BLOCK_BA_MT = "gsm_call_block_ba_mt";
    public static final String GSM_CALL_BLOCK_BAOC = "gsm_call_block_baoc";
    public static final String GSM_CALL_BLOCK_BAOIC = "gsm_call_block_baoic";
    public static final String GSM_CALL_BLOCK_BAOICXH = "gsm_call_block_baoicxh";
    public static final String GSM_CALL_BLOCK_BAIC = "gsm_call_block_baic";
    public static final String GSM_CALL_BLOCK_BAICR = "gsm_call_block_baicr";

    public static final String END_OF_USSD_COMMAND = "%23";
    public static final String ACTION_ACTIVATE = "**";
    public static final String ACTION_DEACTIVATE = "%23%23";
    public static final String ACTION_INTERROGATE = "*%23";
    public static final String ACTION_REGISTER = "**";
    public static final String ACTION_ERASURE = "%23%23";

    // Call Forwarding
    public static final String SC_CFU = "21";
    public static final String SC_CFB = "67";
    public static final String SC_CFNRy = "61";
    public static final String SC_CFNR = "62";
    public static final String SC_CF_All = "002";
    public static final String SC_CF_All_Conditional = "004";

    // Call Waiting
    public static final String SC_WAIT = "43";


     // Call Barring
    /**
     * 禁拨国际长途
     */
    public static final String SC_BAOIC = "331";

    public static final String SC_BAOICxH = "332";

    /**
     * 国际漫游时禁止拨入
     */
    public static final String SC_BAICr = "351";

    /**
     * 禁止所有拨入，拨出
     * */
    public static final String SC_BA_ALL = "330";
    /**
     * 禁止所有拨出，33或者333
     */
    public static final String SC_BAOC = "33";
    public static final String SC_BA_MO = "333";
    /**
     * 禁止所有拨入，35或者353
     */
    public static final String SC_BAIC = "35";
    public static final String SC_BA_MT = "353";


    public static final String SI = "si";
    public static final String SP = "callsettings_sp";
    public static final String DELAY_TO_DIAL_ENABLED = "delay_to_dial_enabled";
    public static final String DELAY_SECOND = "delay_second";
    public static final String IP_DIAL = "ip_dial";
    public static final String IP_DIAL_ENABLED = "ip_dial_enabled";
}

package com.hachikuji.server.constant;

public class NormalConstants {

    /**
     * 未激活
     */
    public static int ACTIVATION_UNDO = 0;
    /**
     * 已激活
     */
    public static int ACTIVATION_DONE = 1;

    /**
     * 重复激活
     */
    public static int ACTIVATION_REPEAT = 2;

    /**
     * 操作失败
     */
    public static int SERVICE_ERROR = -1;

    /**
     * 无效
     */

    public static int TICKET_INVALID = 0;

    /**
     * 有效
     */
    public static int TICKET_VALID = 1;

    /**
     *  评论类型
     */
    public static int ENTITY_TYPE_POST = 1;
    public static int ENTITY_TYPE_COMMENT = 2;

    /**
     *  私信类型
     */
    public static int MESSAGE_UNREAD = 0;
    public static int MESSAGE_READ = 1;




}

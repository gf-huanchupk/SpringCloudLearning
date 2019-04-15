package com.gf.config;


/**
 * 添加header常量，用于http协议传输rootId、parentId、childId三个context属性
 */
public class CatHttpConstants {

    /**
     * http header 常量
     */
    public static final String CAT_HTTP_HEADER_ROOT_MESSAGE_ID = "X-CAT-ROOT-MESSAGE-ID";
    public static final String CAT_HTTP_HEADER_PARENT_MESSAGE_ID = "X-CAT-ROOT-PARENT-ID";
    public static final String CAT_HTTP_HEADER_CHILD_MESSAGE_ID = "X-CAT-ROOT-CHILD-ID";

}

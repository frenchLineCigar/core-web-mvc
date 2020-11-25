package me.frenchline.corewebmvc;

/**
 * JSON View
 * 도메인 또는 DTO 클래스를 이용해서 다양한 JSON 뷰를 만들 수 있는 기능
 */

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;
import java.util.Set;

/**
 * JSON View 정의
 */
public class BookJsonView {

    interface BookSimpleInfo {}
    interface BookDetailInfo extends BookSimpleInfo {}

}

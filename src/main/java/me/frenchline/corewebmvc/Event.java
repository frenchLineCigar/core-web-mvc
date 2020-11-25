package me.frenchline.corewebmvc;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
public class Event {

    private Integer id;

    @NotBlank(message = "필수 입력 정보입니다")
    private String name;

    @Min(value = 0, message = "0 이상의 값을 입력해주세요")
    private Integer limit;

    /* 포매터(Formatter) 설정 */
    // 날짜 타입 바인딩 시 적용할 포멧팅: ISO 패턴 or 문자열 패턴(pattern = "yyyy-MM-dd")으로 정의 가능
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}

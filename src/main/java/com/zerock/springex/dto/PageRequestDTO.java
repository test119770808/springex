package com.zerock.springex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    // 페이지 처리를 위해서 페이지번호와 한페이지당 보여줄 데이터의 수가...

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    private String link;   // 페이지와 사이즈 정보를 같이 넘기기 위한 변수

    private String[] types;   // 검색 종류, 제목,작성자
    private String keyword;   // 검색 값
    private boolean finished; // 완료여부 - 필터링
    private LocalDate from;   // 일시 - 필터링
    private LocalDate to;     // 일시 - 필터링

    public int getSkip() {
        return (page - 1) * size;
    }

    public String getLink() {
        if(link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&size="+this.size);

            if(finished){
                builder.append("&finished=on");
            }

            if(types != null && types.length > 0) {
                for(int i = 0; i < types.length ; i++) {
                    builder.append("&types="+types[i]);
                }
            }

            if(keyword != null) {
                try {
                    builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8"));
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if(from != null) {
                builder.append("&from="+from.toString());
            }

            if(to != null) {
                builder.append("&to="+to.toString());
            }

            link = builder.toString();
        }
        return link;
    }

    public boolean checkType(String type) {
        if(types == null || types.length == 0) {
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }

}

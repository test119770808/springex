package com.zerock.springex.dto;

import lombok.Builder;

import java.util.List;

public class PageResponseDTO<E> {
    // TodoDTO의 목록
    // 전체 데이터의 수
    // 페이지 번호의 처리를 위한 데이터들

    private int page;
    private int size;
    private int total;

    //시작페이지 번호
    private int start;
    //끝페이지 번호
    private int end;

    // 이전 페이지의 존재 여부
    private boolean prev;

    // 다음 페이지 존재 여부
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")  //생성자에 Lombok @Builder 적용
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;


        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;   //10은  페이지 번호 개수
        this.start = this.end - 9;

        // realEnd
        int last = (int)(Math.ceil((total/(double)size)));

    }


}

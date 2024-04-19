package com.zerock.springex.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@ToString
@Service
@RequiredArgsConstructor
public class SampleService {

//    @Autowired  //1.
    @Qualifier("normal")
    private final SampleDAO sampleDAO;
    //2. final로 지정하고, RequiredArgsConstructor 지정생성자 주입이 일어난 것임.

//    @Autowired  /3.
//    public void setSampleDAO(SampleDAO sampleDAO1){
//        this.sampleDAO = sampleDAO1;
//    }
}

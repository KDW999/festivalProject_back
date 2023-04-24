package com.festival.back.dto.response;

import com.festival.back.common.constant.ResponseMessage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "set")
@ApiModel(value = "Response Format")
public class ResponseDto<D> {
    @ApiModelProperty(value = "작업상태 결과",example = "true",required = true)
    // ? response 의 결과 상태 정상이면 true 비정상이면 false
    private boolean result;
    // ? response 의 결과 message;
    @ApiModelProperty(value = "작업상태 결과 메세지",example =ResponseMessage.SUCCESS,required = true)
    private String message;
    // ? response 결과 data;
    @ApiModelProperty(value = "작업상태 결과 데이터",required = true)
    private D data;

    //# 성공시에대한 인스턴스를 생성하주는 static 생성자
    // ? <D> Response<D 는 해당 메서드에서 독릷으로 사용할 제너릭을 지칭
    // ? 뒤에오는 <D> 는 ResponseDto 클래스 타입에 필요로 하는 제너릭을 지칭.
    public static <D> ResponseDto<D> setSuccess(String message, D data){
        return ResponseDto.set(true,message,data);
    }
    
    // # 실패시에 대한 인스턴스를 생성해주는 static 생성자

    public static <D> ResponseDto<D> setFail(String massage){
        return ResponseDto.set(false, massage, null);
    }
}

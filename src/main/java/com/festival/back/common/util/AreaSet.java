package com.festival.back.common.util;

import java.util.ArrayList;
import java.util.List;

public class AreaSet {
    public static  List<String> getAreaList(String area) {
        List<String> festivalAreaList = new ArrayList<>();

        if(area.equals("충남") || area.equals("충청남도")) {
            festivalAreaList.add("충남");
            festivalAreaList.add("충청남도");
        }

        if(area.equals("경남") || area.equals("경상남도")){
            festivalAreaList.add("경남");
            festivalAreaList.add("경상남도");
        }

        if(area.equals("서울") || area.equals("서울특별시")){
            festivalAreaList.add("서울");
            festivalAreaList.add("서울특별시");
        }

        if(area.equals("인천") || area.equals("인천광역시")){
            festivalAreaList.add("인천");
            festivalAreaList.add("인천광역시");
        }

        if(area.equals("대전") || area.equals("대전광역시")){
            festivalAreaList.add("대전");
            festivalAreaList.add("대전광역시");
        }

        if(area.equals("대구") || area.equals("대구광역시")){
            festivalAreaList.add("대구");
            festivalAreaList.add("대구광역시");
        }

        if(area.equals("광주") || area.equals("광주광역시")){
            festivalAreaList.add("광주");
            festivalAreaList.add("광주광역시");
        }

        if(area.equals("부산") || area.equals("부산광역시")){
            festivalAreaList.add("부산");
            festivalAreaList.add("부산광역시");
        }

        if(area.equals("울산") || area.equals("울산광역시")){
            festivalAreaList.add("울산");
            festivalAreaList.add("울산광역시");
        }

        if(area.equals("세종") || area.equals("세종특별자치시")){
            festivalAreaList.add("세종");
            festivalAreaList.add("세종특별자치시");
        }

        if(area.equals("경기") || area.equals("경기도")){
            festivalAreaList.add("경기");
            festivalAreaList.add("경기도");
        }

        if(area.equals("충북") || area.equals("충청북도")){
            festivalAreaList.add("충북");
            festivalAreaList.add("충청북도");
        }

        if(area.equals("경북") || area.equals("경상북도")){
            festivalAreaList.add("경북");
            festivalAreaList.add("경상북도");
        }

        if(area.equals("전남") || area.equals("전라남도")){
            festivalAreaList.add("전남");
            festivalAreaList.add("전라남도");
        }
        
        if(area.equals("전북") || area.equals("전라북도")){
            festivalAreaList.add("전북");
            festivalAreaList.add("전라북도");
        }

        if(area.equals("강원") || area.equals("강원도")){
            festivalAreaList.add("강원");
            festivalAreaList.add("강원도");
        }

        if(area.equals("제주") || area.equals("제주특별자치도")){
            festivalAreaList.add("제주");
            festivalAreaList.add("제주특별자치도");
        }

        return festivalAreaList;
    }
}

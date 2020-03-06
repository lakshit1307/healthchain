//package com.healthedge.cm.common.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Sort;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class ParamHelper {
//    public Sort getSortingParams(String sortUrl) {
//        ArrayList<Sort.Order> sortList = new ArrayList<>();
//
//        List<String> SortStr = Arrays.asList(sortUrl.split(","));
//        SortStr.stream().forEach(sortItem -> {
//            List<String> sortParam = Arrays.asList(sortItem.split(" "));
//            Sort sort;
//            if (sortParam.size() > 1) {
//                if (sortParam.get(1).equals("desc")) {
//                    sort = new Sort(Sort.Direction.DESC, sortParam.get(0));
//                } else {
//                    sort = new Sort(Sort.DEFAULT_DIRECTION, sortParam.get(0));
//                }
//            } else {
//                sort = new Sort(Sort.DEFAULT_DIRECTION, sortParam.get(0));
//            }
//            for (Sort.Order order : sort) {
//                sortList.add(order);
//            }
//        });
//        return Sort.by(sortList);
//    }
//}

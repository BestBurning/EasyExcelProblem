package com.di1shuai.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;

import java.io.InputStream;
import java.util.LinkedHashMap;

/**
 * @author shea
 * @since 2023/6/25
 */
public class Main {

    public static void main(String[] args) {
        String fileName = "time_problem.xlsx";
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        int skipHeaderNumber = 1;
        String sheetName = null;
        EasyExcel.read(
                        in,
                        null,
                        new ReadListener() {

                            @Override
                            public void invoke(Object data, AnalysisContext context) {
                                LinkedHashMap<Integer, Object> dataMap = (LinkedHashMap) data;
                                dataMap.entrySet()
                                        .forEach(
                                                dataE -> {
                                                    System.out.println(dataE.getKey() + " -> " + dataE.getValue());
                                                });
                            }

                            @Override
                            public void doAfterAllAnalysed(AnalysisContext context) {
                                System.out.println("读取成功");
                            }
                        })
                .sheet(sheetName)
                .headRowNumber((int) skipHeaderNumber)
                .doRead();

    }

}

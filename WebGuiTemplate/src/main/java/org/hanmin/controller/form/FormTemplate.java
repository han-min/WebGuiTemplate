package org.hanmin.controller.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class FormTemplate {

    private String formName;
    private long formId;
    private boolean formYesNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date formDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime formDateTime;

}

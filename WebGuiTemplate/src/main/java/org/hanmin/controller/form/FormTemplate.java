package org.hanmin.controller.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class FormTemplate {

    public enum FormImportance {
        LOW, NORMAL, HIGH, IMMEDIATE
    }

    private String formName;
    private long formId;
    private boolean formYesNo;
    private FormImportance formImportance = FormImportance.NORMAL;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date formDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime formDateTime;

}

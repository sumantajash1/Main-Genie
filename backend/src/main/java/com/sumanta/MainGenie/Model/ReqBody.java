package com.sumanta.MainGenie.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReqBody {
    private String problemStatement;
    private String ideCode;
}

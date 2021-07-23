package kr.co.cmt.assetmanager.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    Boolean useYn;
    String remark;
    Long departmentId;
    Long authorityId;
}

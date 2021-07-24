package kr.co.cmt.assetmanager.dto;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    Boolean useYn;
    String remark;
    Long departmentId;
    Collection<Long> departmentIds;
    Long authorityId;
    Collection<Long> authorityIds;
}

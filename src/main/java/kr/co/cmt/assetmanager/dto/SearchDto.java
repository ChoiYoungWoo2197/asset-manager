package kr.co.cmt.assetmanager.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    String name;
    Boolean useYn;
    String remark;
    String position;
    Long departmentId;
    Collection<Long> departmentIds;
    Long authorityId;
    Collection<Long> authorityIds;
}

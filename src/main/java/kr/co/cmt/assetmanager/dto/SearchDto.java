package kr.co.cmt.assetmanager.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    String code;
    String name;
    Boolean useYn;
    String type;
    String remark;
    String position;
    Long departmentId;
    Collection<Long> departmentIds;
    Long authorityId;
    Collection<Long> authorityIds;
    Long categoryId;
    Long partnerCompanyId;
    Long memberId;
    String contractDateAt;
//    String expireDateAt;
    String receivedDateAt;
}

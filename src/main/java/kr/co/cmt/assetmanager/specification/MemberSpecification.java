package kr.co.cmt.assetmanager.specification;

import kr.co.cmt.assetmanager.model.Member;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class MemberSpecification {
    public static Specification<Member> searchWith(Map<String, Object> searchKeyword) {
        return (Specification<Member>) ((root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(searchKeyword, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        });
    }

    private static List<Predicate> getPredicateWithKeyword(Map<String, Object> searchKeyword, Root<Member> root, CriteriaBuilder builder) {
        List<Predicate> predicate = new ArrayList<>();
        for (String key : searchKeyword.keySet()) {
            if(searchKeyword.get(key)  == null || searchKeyword.get(key)  == "") continue;
            if("remark".equals(key)){ //'remark' 조건은 like 검색
                predicate.add(builder.like(root.get(key), "%"+searchKeyword.get(key)+"%"));
            } else if("departmentId".equals(key) || "departmentIds".equals(key) ||
                    "authorityId".equals(key) || "authorityIds".equals(key)) {
                String column = key.substring(0, key.toLowerCase().indexOf("id"));
                if ("departmentId".equals(key) == true || "authorityId".equals(key) == true ) {
                    predicate.add(builder.equal(root.get(column), searchKeyword.get(key)));
                } else {
                    predicate.add(root.get(column).in((Collection<Long>)searchKeyword.get(key)));
                }
            }
            else{ //이외의 모든 조건 파라미터에 대해 equal 검색
                predicate.add(builder.equal(root.get(key), searchKeyword.get(key)));
            }
        }
        return predicate;
    }
}
package kr.co.cmt.assetmanager.specification;

import kr.co.cmt.assetmanager.model.Department;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DepartmentSpecification {
    public static Specification<Department> searchWith(Map<String, Object> searchKeyword) {
        return (Specification<Department>) ((root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(searchKeyword, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        });
    }

    private static List<Predicate> getPredicateWithKeyword(Map<String, Object> searchKeyword, Root<Department> root, CriteriaBuilder builder) {
        List<Predicate> predicate = new ArrayList<>();
        for (String key : searchKeyword.keySet()) {
            if(searchKeyword.get(key)  == null) continue;

            if("remark".equals(key)){ //'name' 조건은 like 검색
                predicate.add(builder.like(root.get(key), "%"+searchKeyword.get(key)+"%"));
            } else{ // 'name', 'partner' 이외의 모든 조건 파라미터에 대해 equal 검색
                predicate.add(builder.equal(root.get(key), searchKeyword.get(key)));
            }
        }
        return predicate;
    }
}

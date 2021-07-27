package kr.co.cmt.assetmanager.specification;

import kr.co.cmt.assetmanager.model.CategorySpecification;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CategorySpecifications {
    public static Specification<CategorySpecification> searchWith(Map<String, Object> searchKeyword) {
        return (Specification<CategorySpecification>) ((root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(searchKeyword, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        });
    }

    private static List<Predicate> getPredicateWithKeyword(Map<String, Object> searchKeyword, Root<CategorySpecification> root, CriteriaBuilder builder) {
        List<Predicate> predicate = new ArrayList<>();
        for (String key : searchKeyword.keySet()) {
            if(searchKeyword.get(key)  == null || searchKeyword.get(key)  == "") continue;
            if("remark".equals(key) || "name".equals(key)){
                predicate.add(builder.like(root.get(key), "%"+searchKeyword.get(key)+"%"));
            } else if("categoryId".equals(key)) {
                String column = key.substring(0, key.toLowerCase().indexOf("id"));
                predicate.add(builder.equal(root.get(column), searchKeyword.get(key)));
            } else{ //이외의 모든 조건 파라미터에 대해 equal 검색
                predicate.add(builder.equal(root.get(key), searchKeyword.get(key)));
            }
        }
        return predicate;
    }
}

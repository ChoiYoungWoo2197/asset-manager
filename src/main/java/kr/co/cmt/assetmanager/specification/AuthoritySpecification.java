package kr.co.cmt.assetmanager.specification;

import kr.co.cmt.assetmanager.model.Authority;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AuthoritySpecification {
    public static Specification<Authority> searchWith(Map<String, Object> searchKeyword) {
        return (Specification<Authority>) ((root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(searchKeyword, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        });
    }

    private static List<Predicate> getPredicateWithKeyword(Map<String, Object> searchKeyword, Root<Authority> root, CriteriaBuilder builder) {
        List<Predicate> predicate = new ArrayList<>();
        for (String key : searchKeyword.keySet()) {
            if(searchKeyword.get(key)  == null || searchKeyword.get(key)  == "") continue;

            if("remark".equals(key) || "name".equals(key)){ //'name' 조건은 like 검색
                predicate.add(builder.like(root.get(key), "%"+searchKeyword.get(key)+"%"));
            } else{ // 'name', 'partner' 이외의 모든 조건 파라미터에 대해 equal 검색
                predicate.add(builder.equal(root.get(key), searchKeyword.get(key)));
            }
//            if("name".equals(key)){ //'name' 조건은 like 검색
//                predicate.add(builder.like(root.get(key), "%"+searchKeyword.get(key)+"%"));
//            }else if("partner".equals(key)){ // 'partner' 조건은 partner객체 안에 있는 keword데이터를 2차 가공하여 검색
//                Join<Item,Partner> join = root.join("partner");
//                Map<String, Object> partnerKeyword = CommonObjectUtils.convertObjectToMap(searchKeyword.get(key));
//                for (String partnerKey : partnerKeyword.keySet()) {
//                    if("name".equals(partnerKey)){
//                        predicate.add(builder.like(join.get("name"), "%"+ partnerKeyword.get("name")+"%"));
//                    }
//                }
//            }else{ // 'name', 'partner' 이외의 모든 조건 파라미터에 대해 equal 검색
//                predicate.add(builder.equal(root.get(key), searchKeyword.get(key)));
//            }
        }
        return predicate;
    }
}


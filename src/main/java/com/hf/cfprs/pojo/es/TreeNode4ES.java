package com.hf.cfprs.pojo.es;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

/**
 * @Author:hanfei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "tree")
public class TreeNode4ES {


    //    @Id
//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
//    private String id;
    private String name;
    private Double value;
    private List<TreeNode4ES> children;
    public TreeNode4ES(String name, Double value) {
        this.name = name;
        this.value = value;
    }
}

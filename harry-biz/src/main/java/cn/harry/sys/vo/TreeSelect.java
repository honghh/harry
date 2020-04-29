package cn.harry.sys.vo;

import cn.harry.sys.entity.SysDept;
import cn.harry.sys.entity.SysMenu;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: TreeSelect
 * Description:
 *
 * @author honghh
 * Date 2020/04/14 15:57
 * Copyright (C) www.honghh.top
 */
@Data
public class TreeSelect implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect() {

    }

    public TreeSelect(SysDept dept) {
        this.id = dept.getId();
        this.label = dept.getName();
        this.children = dept.getChildren() == null ? null : dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysMenu menu) {

        this.id = menu.getId();
        this.label = menu.getName();
        this.children = menu.getChildren() == null ? null : menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

}

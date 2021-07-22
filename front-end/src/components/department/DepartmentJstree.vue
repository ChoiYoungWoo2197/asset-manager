<template>
  <div id="department-div">
  </div>
</template>

<script>
import $ from "jquery";
import axios from 'axios';

export default {
  name: "DepartmentJstree",
  data() {
    return {
      datas : null,
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      const vm = this;
      this.getInstance().jstree({
        "core" : {
          "animation" : 0,
          "themes" : { "stripes" : true },
          'check_callback': true,
          "multiple" : false,
          'data' : {
            'url' : vm.getJstreeData(),
            'data' : function (node) {
              return { 'id' : node.id };
            }
          },
          'strings' : {
            'Loading ...' : '불러오는 중 ...'
          },
        },
        "types" : {
          "root" : {
            "icon" : "uil uil-folder-lock",
          },
          "Dept" : {
            "icon" : "uil uil-folder-lock",
            "valid_children" : ["Dept"]
          }
        },
        "contextmenu":{
          'select_node': false, //우클릭시 노드가 선택을 않기 위해
          "items": function($node) {
            return vm.getMenus($node);
          }
        },
        "plugins" : vm.plugins(),
      }).on("refresh.jstree", function () {
        vm.getInstance().jstree(true).open_all();
      });
    },
    getInstance() {
      return $("#" + "department-div");
    },
    getJstreeData() {
      const vm = this;
      axios.get('http://localhost:8080/api/departments', {}
      ).then(response => {
        vm.datas = [];
        vm.datas = response.data.slice(0);

        let jstreeDatas = [];
        jstreeDatas.push({
          "id": "0", "parent": "#", "text": "전체 부서 목록" , "type" : "root",
          "a_attr": {
            class: "disable_checkbox"
          }
        });

        vm.datas.forEach((department) => {
          jstreeDatas.push({
            "id" : department.id,
            "parent" : department.parentId === null?0:department.parentId,
            "text" :  department.code + " / " + department.name,
            "type" : "Dept",
          });
        })

        vm.getInstance().jstree(true).settings.core.data = jstreeDatas;
        vm.getInstance().jstree(true).refresh();
      }).catch(e => {
        alert(e);
      })

    },
    plugins() {
      var plugins = ["contextmenu", "types", "wholerow", "search"];
      return plugins;
    },
    getMenus($node){
      const vm = this;
      let menus = {};
      let jsTreeInstance = vm.getInstance().jstree(true);
      if($node.type !== "root") {
        $.extend(menus, {
          "Create": {
            "separator_before": false,
            "separator_after": false,
            "label": "생성",
            "action": function (obj) {
              console.log(obj, $node, vm, menus, jsTreeInstance);
              /*            vm.departmentIdIndex = vm.getMaxId() + 1;
                          $node = jsTreeInstance.create_node($node, {"text" : "new-department-" + vm.departmentIdIndex, "type": "Dept"});
                          jsTreeInstance.edit($node);*/
            }
          }
        });
        $.extend(menus, {
          "Rename": {
            "separator_before": false,
            "separator_after": false,
            "label": "이름변경",
            "action": function (obj) {
              console.log(obj, $node, vm, menus, jsTreeInstance);
              $node.text = $node.text.split("/")[0]
              jsTreeInstance.edit($node);
            }
          }
        });
      }
      return menus;
    },


  }
}
</script>

<style scoped>

</style>
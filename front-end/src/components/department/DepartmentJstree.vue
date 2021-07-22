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
      activeMode : 'create',
      datas : null,
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    getInstance() {
      return $("#" + "department-div");
    },
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
      }).on("rename_node.jstree", function (event, renamed_node) {
        if(vm.activeMode === 'create') {
          vm.createBtnClick(renamed_node.node);
        }
      }).on("select_node.jstree", function (event, selected_node) {
        console.log(event);
        vm.$emit("changedDepartmentActive", selected_node.node);
      });
    },
    getJstreeData() {
      const vm = this;
      axios.get('http://localhost:8080/api/departments', {}
      ).then(response => {
        vm.datas = [];
        vm.datas = response.data.slice(0);

        console.log(vm.datas);

        let jstreeDatas = [];
        jstreeDatas.push({
          "id": "0", "parent": "#", "text": "전체 부서 목록" , "type" : "root",
          "a_attr": {
            class: "disable_checkbox"
          }
        });

        vm.datas.forEach((department) => {
          if(department.useYn === true) {
            jstreeDatas.push({
              "id" : department.id,
              "parent" : department.parentId === null?0:department.parentId,
              "text" :  department.code + " / " + department.name,
              "type" : "Dept",
            });
          }
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
      $.extend(menus, {
        "Create": {
          "separator_before": false,
          "separator_after": false,
          "label": "생성",
          "action": function () {
            $node = jsTreeInstance.create_node($node, {"text" : "code", "type": "Dept"});
            jsTreeInstance.edit($node);
            vm.activeMode = "create";
          }
        }
      });
      return menus;
    },
    createBtnClick(node) {
      const vm = this;
      axios.get('http://localhost:8080/api/departments/' + node.text + '/exists')
          .then(response => {
            if(response.status === 200) {
              if(response.data === true) {
                alert("중복된 코드입니다.");
                vm.getJstreeData();
              } else {
                axios.post('http://localhost:8080/api/departments', {
                  parentId : node.parent === "0" ? null : node.parent,
                  name : node.text,
                  code : node.text,
                  remark : '',
                  useYn : true,
                  register : "1",
                }).then(response => {
                  console.log(response);
                  vm.getJstreeData();
                }).catch(e => {
                  alert(e);
                });
              }
            }
          }).catch(e => {
        alert(e);
      });
    },
  }
}
</script>

<style scoped>

</style>
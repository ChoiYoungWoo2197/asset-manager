<template>
  <div>
    <template v-if="department === null">
      <div>
        <i class="fas fa-info-circle pr-1"></i>정보가 없습니다.
      </div>
    </template>
    <template v-else>
      <div class="card-body">
        <form>
          <div class="form-group">
            <label for="nameEdit">부서명<span class="text-danger">*</span></label>
            <input type="text" class="form-control " id="nameEdit" placeholder="" :value="name">
          </div>
          <div class="form-group">
            <label for="parentName">상위 부서명<span class="text-danger"></span></label>
            <input type="text" class="form-control " id="parentName" placeholder="" :value="parentName" readonly>
          </div>
          <div class="form-group">
            <label for="codeEdit">부서코드<span class="text-danger"></span></label>
            <input type="text" class="form-control" id="codeEdit"  placeholder="" :value="code" readonly>
          </div>
          <div class="form-group">
            <label for="remarkEdit">비고<code></code></label>
            <textarea class="form-control" id="remarkEdit" :value="remark"></textarea>
          </div>
          <div class="form-group">
            <div class="custom-control custom-radio d-inline pr-1">
              <input class="custom-control-input" type="radio" id="" name="useYnRadio" @click="clickRadioBtn(false)">
              <label for="" class="custom-control-label">비활성화</label>
            </div>
            <div class="custom-control custom-radio d-inline">
              <input class="custom-control-input" type="radio" id="useYnTrueEdit" name="useYnRadio" checked="" @click="clickRadioBtn(true)">
              <label for="useYnTrueEdit" class="custom-control-label">활성화</label>
            </div>
          </div>
          <div class="form-group text-right btn-group-default">
            <button class="create btn btn-secondary mr-1">
              <i class="far fa-window-close pr-1"></i>취소
            </button>
            <button class="update btn btn-primary" @click.prevent="updateBtnClick">
              <i class="far fa-edit pr-1"></i>수정
            </button>
          </div>
        </form>
      </div>
    </template>
  </div>
</template>

<script>
import $ from "jquery";
import axios from 'axios';

export default {
  name: "DepartmentEditor",
  props: {
    pDepartment: Object,
  },
  data() {
    return {
      department : null,
      activeDepartment : null,
      activeParent : null,
      id : null,
      name : null,
      parentName : null,
      code : null,
      remark : null,
      useYn : null,
    }
  },
  watch: {
    pDepartment() {
      this.department = this.pDepartment;
      this.findParentAndActiveDepartment();
    }
  },
  mounted() {
    this.department = this.pDepartment;
  },
  methods: {
    findParent() {
      const vm = this;
      if(vm.department === null) return false;
      return axios.get('http://localhost:8080/api/departments/' + vm.department.parent);
    },
    findActiveDepartment(){
      const vm = this;
      if(vm.department === null) return false;
      return axios.get('http://localhost:8080/api/departments/' + vm.department.id);
    },
    findParentAndActiveDepartment(){
      const vm = this;
      if(vm.department === null) return false;

      axios.all([this.findParent(), this.findActiveDepartment()])
      .then(axios.spread(function (parent, child) {
        vm.activeParent = parent.data;
        vm.activeDepartment = child.data;

        let texts = vm.department.text.split("/");
        vm.id = vm.activeDepartment.id;
        vm.name = texts[1].trim();
        vm.code = texts[0].trim();
        vm.remark = vm.activeDepartment.remark;
        vm.useYn = vm.activeDepartment.useYn;
        vm.parentName = vm.activeParent !== "" ? vm.activeParent.name : "";

        if(vm.useYn === true) {
          $('#useYnTrueEdit').prop("checked", true);
        } else {
          $('#useYnFalseEdit').prop("checked", true);
        }

      })).catch(e => {
        alert(e);
      });
    },
    setData() {
      let texts = this.department.text.split("/");
      this.id = this.department.id;
      this.name = texts[1].trim();
      this.code = texts[0].trim();
      this.remark = this.activeDepartment.remark;
      this.useYn = this.activeDepartment.useYn;
      console.log('setdAta : ',this.activeParent)
      this.parentName = this.activeParent !== null ? this.activeParent.name : '';

      if(this.useYn === true) {
        $('#useYnTrueEdit').prop("checked", true);
      } else {
        $('#useYnFalseEdit').prop("checked", true);
      }
    },
    clickRadioBtn(flag){
      this.useYn = flag;
    },
    updateBtnClick() {
      const vm = this;
      if($( 'input#nameEdit' ).val() ==="") {
        alert("부서명을 입력해주세요.");
        return false;
      } else if(vm.department.children.length > 0 && vm.useYn === false) {
        alert("자식요소가 존재할 경우 비활성화를 할 수 없습니다.");
        return false;
      }

      axios.put('http://localhost:8080/api/departments/' + vm.department.id, {
        name : $( 'input#nameEdit' ).val(),
        code : $( 'input#codeEdit' ).val(),
        remark : $( 'textarea#remarkEdit' ).val(),
        useYn : this.useYn,
      }).then(response => {
        // console.log(response);
        if(response.status === 200) {
          vm.$emit("updateData", response.data);
        }
      }).catch(e => {
        alert(e);
      })
    }

  }
}
</script>

<style scoped>

</style>
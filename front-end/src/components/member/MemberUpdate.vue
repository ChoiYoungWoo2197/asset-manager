<template>
  <!-- Modal -->
  <div class="modal fade" id="member-update-modal" tabindex="-1" aria-labelledby="authority-create-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header ">
          <h2 class="tit_step">회원수정</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-lg">
              <div class="card card-primary card-outline">
                <div class="card-body">
                  <form>
                    <div class="form-group">
                      <label for="nameEdit">이름<span class="text-danger">*</span></label>
                      <input type="text" class="form-control " id="nameEdit" placeholder="" :value="name">
                    </div>
                    <div class="form-group">
                      <label for="emailEdit">이메일<span class="text-danger"></span></label>
                      <div class="form-group row">
                        <div class="col-sm">
                          <input type="text" class="form-control " id="emailEdit" placeholder="" readonly disabled :value="email">
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="passwordInit">비밀번호 초기화<span class="text-danger"></span></label>
                      <div class="form-group row">
                        <div class="col-sm">
                          <input type="button" class="form-control btn btn-primary btn-sm" value="초기화" @click="clickPasswordInitBtn">
                        </div>
                      </div>
                    </div>

                    <div class="form-group">
                      <label for="birthdayEdit">생년월일<span class="text-danger"></span></label>
                      <input class="form-control" type="text" id="birthdayEdit" placeholder="생년월일을 입력하세요." :value="birthday">
                    </div>
                    <div class="form-group">
                      <label for="phoneEdit">전화번호<span class="text-danger"></span></label>
                      <input type="text" class="form-control " id="phoneEdit" placeholder="" :value="phone">
                    </div>
                    <div class="form-group">
                      <label for="departmentsEdit">부서<span class="text-danger">*</span></label>
                      <select class="form-control " style="width: 100%;" id="departmentsEdit">
                        <option v-for="(department, index) in getDepartments"
                                :key="index"
                                :value="department.id">
                          {{ department.name }}
                        </option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="positionEdit">직급<span class="text-danger">*</span></label>
                      <select class="form-control " style="width: 100%;" id="positionEdit">
                        <option v-for="(position, index) in positions"
                                :key="index"
                                :value="position.code"
                                :selected="index === 0">
                          {{ position.name }}
                        </option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="authorityEdit">권한<span class="text-danger">*</span></label>
                      <select class="form-control " style="width: 100%;" id="authorityEdit">
                        <option v-for="(authority, index) in getAuthoritys"
                                :key="index"
                                :value="authority.id"
                                :selected="authority.index === 0">
                          {{ authority.name }}
                        </option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="remarkEdit">비고<code></code></label>
                      <textarea class="form-control" id="remarkEdit" :value="remark"></textarea>
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-radio d-inline pr-1">
                        <input class="custom-control-input" type="radio" id="useYnFalseEditModal" name="useYnRadio" @click="clickRadioBtn(false)">
                        <label for="useYnFalseEditModal" class="custom-control-label">비활성화</label>
                      </div>
                      <div class="custom-control custom-radio d-inline">
                        <input class="custom-control-input" type="radio" id="useYnTrueEditModal" name="useYnRadio" checked="" @click="clickRadioBtn(true)">
                        <label for="useYnTrueEditModal" class="custom-control-label">활성화</label>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">  <i class="far fa-window-close pr-1"></i>취소</button>
          <button type="button" class="btn btn-primary" @click="updateBtnClick"> <i class="far fa-edit pr-1"></i>수정</button>
          <button type="button" class="btn btn-danger" @click="deleteBtnClick"><i class="far fa-trash-alt pr-1"></i>삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import $ from "jquery";
import moment from "moment";

export default {
  name: "MemberUpdate",
  props: {
    pDepartment: Object,
  },
  data() {
    return {
      id : null,
      name : null,
      email : null,
      password : null,
      remark : null,
      birthday : null,
      phone : null,
      position : null,
      authorityId : null,
      departmentId : null,
      useYn : null,
      authoritys : null,
      departments : null,
      positions : [
        {code : 'e' , name : '사원'},
        {code : 'am' , name : '대리'},
        {code : 'm' , name : '과장'},
        {code : 'dgm' , name : '차장'},
        {code : 'gm' , name : '부장'},
        {code : 'd' , name : '이사'},
        {code : 'md' , name : '상무'},
        {code : 'smd' , name : '전무'},
        {code : 'p' , name : '사장'},
      ]
    }
  },
  computed: {
    getAuthoritys() {
      return this.authoritys;
    },
    getDepartments() {
      return this.departments;
    },
  },
  watch: {
    pDepartment() {
      this.department = this.pDepartment;
      this.findAuthoriyAndDepartment();
    }
  },
  mounted() {
    this.initDatePicker();
  },
  methods: {
    setData(data) {
      // console.log(data);
      this.id = data.id;
      this.name = data.name;
      this.email = data.email;
      this.password = data.password;
      this.remark = data.remark;
      this.birthday = data.birthday;
      this.phone = data.phone;
      this.position = data.position;
      this.authorityId = data.authorityId;
      this.departmentId = data.departmentId;
      this.useYn = data.useYn;

      $('#member-update-modal #departmentsEdit').val(this.departmentId);
      $('#member-update-modal #authorityEdit').val(this.authorityId);
      $('#member-update-modal #positionEdit').val(this.position);

      if(this.useYn === true) {
        $('#member-update-modal #useYnTrueEditModal').prop("checked", true);
      } else {
        $('#member-update-modal #useYnFalseEditModal').prop("checked", true);
      }
    },
    initDatePicker() {
      $('input#birthdayEdit').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'),10),
        autoUpdateInput: false,
        locale : {
          format: 'YYYY-MM-DD',
          applyLabel: "적용",
          cancelLabel: "닫기",
          daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"],
          monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        }
      }).on('apply.daterangepicker', function(ev, picker) {
        console.log(picker)
        $(this).val(picker.startDate.format('YYYY-MM-DD'));
      }).on('cancel.daterangepicker', function(ev, picker) {
        console.log(ev, picker)
        $(this).val('');
      });
    },
    clickRadioBtn(flag){
      this.useYn = flag;
    },
    updateBtnClick() {
      const vm = this;

      if($( 'input#nameEdit' ).val() ==="") {
        alert("이름을 입력해주세요.");
        return false;
      }

      axios.put('http://localhost:8080/api/members/' + this.id, {
        name : $( '#member-update-modal input#nameEdit' ).val(),
        remark : $( '#member-update-modal textarea#remarkEdit' ).val(),
        birthday : $('#member-update-modal input#birthdayEdit').val(),
        phone : $('#member-update-modal input#phoneEdit').val(),
        position : $('#member-update-modal select#positionEdit').val(),
        authorityId : $('#member-update-modal select#authorityEdit').val(),
        departmentId : $('#member-update-modal select#departmentsEdit').val(),
        useYn : this.useYn,
      }).then(response => {
        console.log(response);
        if(response.status === 200) {
          // this.$router.push('Authority');
          vm.$emit("updateData", response.data);
          $('#member-update-modal').modal("hide");
        }
      }).catch(e => {
        alert(e);
      })
    },
    deleteBtnClick() {
      const vm = this;

      if(!confirm(this.name + "을 삭제하시겠습니까?")) {
        return false;
      }

      axios.delete('http://localhost:8080/api/members/' + this.id)
          .then(response => {
            // console.log(response);
            if(response.status === 200) {
              // this.$router.push('Authority');
              vm.$emit("updateData", response.data);
              $('#member-update-modal').modal("hide");
            }
          }).catch(e => {
        alert(e);
      })
    },
    findAuthoritys() {
      const vm = this;
      if(vm.department === null) return false;
      return axios.get('http://localhost:8080/api/authoritys');
    },
    findDepartments() {
      const vm = this;
      if(vm.department === null) return false;
      return axios.get('http://localhost:8080/api/departments');
    },
    findAuthoriyAndDepartment(){
      const vm = this;
      if(vm.department === null) return false;

      axios.all([this.findAuthoritys(), this.findDepartments()])
          .then(axios.spread(function (authoritys, departmenets) {
            vm.authoritys = authoritys.data.filter(authority => authority.useYn === true);
/*            vm.departments = departmenets.data.filter(department => {
              return (department.useYn === true && department.id === Number(vm.department.id));
            });*/
            vm.departments = departmenets.data.filter(department => {
              return (department.useYn === true);
            });
          })).catch(e => {
        alert(e);
      });
    },
    clickPasswordInitBtn() {
      const vm = this;
      if(!confirm("생년월일로 초기화 됩니다(YYYY/MM/DD). 초기화 하시겠습니까?")) {
        return false;
      }

/*      if($( 'input#birthdayEdit' ).val() ==="") {
        alert("생년월일을 입력해주세요.");
        return false;
      }*/

      axios.put('http://localhost:8080/api/members/' + this.id + "/password-init", {
        // birthday : $('#member-update-modal input#birthdayEdit').val(),
      }).then(response => {
        console.log(response);
        if(response.status === 200) {
          // this.$router.push('Authority');
          vm.$emit("updateData", response.data);
          $('#member-update-modal').modal("hide");
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
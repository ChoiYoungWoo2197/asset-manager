<template>
  <!-- Modal -->
  <div class="modal fade" id="member-create-modal" tabindex="-1" aria-labelledby="authority-create-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header ">
          <h2 class="tit_step">계정등록</h2>
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
                      <label for="name">이름<span class="text-danger">*</span></label>
                      <input type="text" class="form-control " id="name" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="email">이메일<span class="text-danger">*</span></label>
                      <div class="form-group row">
                        <div class="col-sm-10">
                          <input type="text" class="form-control " id="email" placeholder="" @keydown="keydownEmailInput">
                        </div>
                        <div class="col-sm">
                          <input type="button" class="form-control btn btn-primary btn-sm" value="중복체크" @click="clickEmailCheckBtn">
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="password">비밀번호<span class="text-danger">*</span></label>
                      <input type="password" class="form-control " id="password" placeholder="">
                    </div>
                    <!--            <div class="form-group">
                                  <label for="passwordCheck">비밀번호 재설정<span class="text-danger">*</span></label>
                                  <input type="password" class="form-control " id="passwordCheck" placeholder="">
                                </div>-->
                    <div class="form-group">
                      <label for="birthday">생년월일<span class="text-danger"></span></label>
                      <input class="form-control" type="text" id="birthday" placeholder="생년월일을 입력하세요.">
                      <!--              <div class="input-group date" id="birthday" data-target-input="nearest">
                                      <input type="text" class="form-control datetimepicker-input" data-target="#birthday"/>
                                      <div class="input-group-append" data-target="#birthday" data-toggle="datetimepicker">
                                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                      </div>
                                    </div>-->
                    </div>
                    <div class="form-group">
                      <label for="phone">전화번호<span class="text-danger"></span></label>
                      <input type="text" class="form-control " id="phone" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="departments">부서<span class="text-danger">*</span></label>
                      <select class="form-control " style="width: 100%;" id="departments" disabled readonly="readonly">
                        <option v-for="(department, index) in getDepartments"
                                :key="index"
                                :value="department.id">
                          {{ department.name }}
                        </option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="position">직급<span class="text-danger">*</span></label>
                      <select class="form-control " style="width: 100%;" id="position">
                        <option v-for="(position, index) in positions"
                                :key="index"
                                :value="position.code"
                                :selected="index === 0">
                          {{ position.name }}
                        </option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="authority">권한<span class="text-danger">*</span></label>
                      <select class="form-control " style="width: 100%;" id="authority">
                        <option v-for="(authority, index) in getAuthoritys"
                                :key="index"
                                :value="authority.id"
                                :selected="authority.index === 0">
                          {{ authority.name }}
                        </option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="remark">비고<code></code></label>
                      <textarea class="form-control" id="remark"></textarea>
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-radio d-inline pr-1">
                        <input class="custom-control-input" type="radio" id="useYnFalse" name="useYnRadio" @click="clickRadioBtn(false)">
                        <label for="useYnFalse" class="custom-control-label">비활성화</label>
                      </div>
                      <div class="custom-control custom-radio d-inline">
                        <input class="custom-control-input" type="radio" id="useYnTrue" name="useYnRadio" checked="" @click="clickRadioBtn(true)">
                        <label for="useYnTrue" class="custom-control-label">활성화</label>
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
          <button id="createBtn" type="button" class="btn btn-secondary" @click="createBtnClick"> <i class="far fa-edit pr-1"></i>등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import axios from "axios";
import moment from 'moment';
import "daterangepicker/daterangepicker.js";
import "daterangepicker/daterangepicker.css";

export default {
  name: "MemberCreate",
  props: {
    pDepartment: Object,
  },
  data() {
    return {
      department : null,
      useYn : true,
      isExistEmail : null,
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
    clearData() {
      $( 'input#name' ).val("");
      $( 'input#email' ).val("");
      $( 'input#password' ).val("");
      $( 'input#phone' ).val("");
      $( 'textarea#remark' ).val("");
      $('button#createBtn').addClass('btn-primary');
      $('button#createBtn').addClass('btn-secondary');
      this.isExistEmail = null;
      this.useYn = true;
    },
    initDatePicker() {
      $('input#birthday').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'),10),
        locale : {
          format: 'YYYY-MM-DD',
          applyLabel: "적용",
          cancelLabel: "닫기"
        }
      });
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
            vm.departments = departmenets.data.filter(department => {
              return (department.useYn === true && department.id === Number(vm.department.id));
            });
          })).catch(e => {
            alert(e);
          });
    },
    clickRadioBtn(flag){
      this.useYn = flag;
    },
    createBtnClick() {
      const vm = this;
      if($( 'input#name' ).val() ==="" || $( 'input#email' ).val() === "" || $( 'input#password' ).val() === ""
          || this.getDepartments === null || this.getDepartments.length === 0
          || this.getAuthoritys === null || this.getAuthoritys.length === 0
          || this.isExistEmail !== false) {
        if($( 'input#name' ).val() === '') {
          alert("이름을 입력해주세요.");
          return false;
        } else if($( 'input#email' ).val() === "" ) {
          alert("이메일을 입력해주세요.");
          return false;
        } else if($( 'input#password' ).val() === "") {
          alert("패스워드를 입력해주세요.");
          return false;
        } else if(this.getDepartments === null || this.getDepartments.length === 0) {
          alert("부서를 생성해주세요.");
          return false;
        } else if(this.getAuthoritys === null || this.getAuthoritys.length === 0) {
          alert("권한을 생성해주세요.");
          return false;
        } else if( this.isExistEmail !== false ) {
          alert('이메일 중복체크를 해주세요.');
          return false;
        }
      }

      axios.post('http://localhost:8080/api/members', {
        name : $( '#member-create-modal input#name' ).val(),
        email : $( '#member-create-modal input#email' ).val(),
        password : $( '#member-create-modal input#password' ).val(),
        remark : $( '#member-create-modal textarea#remark' ).val(),
        birthday : $('#member-create-modal input#birthday').val(),
        phone : $('#member-create-modal input#phone').val(),
        position : $('#member-create-modal select#position').val(),
        authorityId : $('#member-create-modal select#authority').val(),
        departmentId : $('#member-create-modal select#departments').val(),
        register : "1",
        useYn : this.useYn,
      }).then(response => {
        console.log(response);
        if(response.status === 200) {
          vm.$emit("updateData", response.data);
          $('#member-create-modal').modal("hide");
        }
      }).catch(e => {
        alert(e);
      })
    },
    clickEmailCheckBtn() {
      const vm = this;
      let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

      if($( 'input#email' ).val() === "" || $( 'input#email' ).val().match(regExp) === null) {
        alert("올바른 이메일 주소를 입력하세요.");
        return false;
      }

      axios.get('http://localhost:8080/api/members/' + $( 'input#email' ).val() + '/exists')
          .then(response => {
            if(response.status === 200) {
              if(response.data === true) {
                vm.isExistEmail = true;
                alert("중복된 이메일 입니다.");
              } else {
                vm.isExistEmail = false;
                $('button#createBtn').removeClass('btn-secondary');
                $('button#createBtn').addClass('btn-primary');
                alert("사용 가능한 이메일 입니다.");
              }
            }
          }).catch(e => {
        alert(e);
      })
    },
    keydownEmailInput() {
      this.isExistEmail = true;
      $('button#createBtn').removeClass('btn-primary');
      $('button#createBtn').addClass('btn-secondary');
    }
  }
}
</script>

<style scoped>

</style>
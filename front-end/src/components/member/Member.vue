<template>
  <div>
    <template v-if="department === null">
      <div>
        <i class="fas fa-info-circle pr-1"></i>정보가 없습니다.
      </div>
    </template>
    <template v-else>
      <div class="card-body" >
        <form class="member">
          <div class="form-row">
            <div class="col-3">
              <div class="form-group">
                <label for="position">직급</label>
                <select class="form-control " style="width: 100%;" id="position">
                  <option v-for="(position, index) in positions"
                          :key="index"
                          :value="position.code"
                          :selected="position.name === '해당없음'">
                    {{ position.name }}
                  </option>
                </select>
              </div>
            </div>
            <div class="col-3">
              <div class="form-group">
                <label for="authority">권한</label>
                <select class="form-control " style="width: 100%;" id="authority">
                  <option v-for="(authority, index) in getAuthoritys"
                          :key="index"
                          :value="authority.id"
                          :selected="authority.name === '해당없음'">
                    {{ authority.name }}
                  </option>
                </select>
              </div>
            </div>
            <div class="col-3">
              <div class="form-group">
                <label for="departments">부서</label>
                <select class="form-control " style="width: 100%;" id="departments">
                  <option v-for="(department, index) in getDepartments"
                          :key="index"
                          :value="department.id"
                          :selected="department.name === '해당없음'">
                    {{ department.name }}
                  </option>
                </select>
              </div>
            </div>
            <div class="col-3">
              <div class="form-group">
                <label for="useYn">사용유무</label>
                <select class="form-control " style="width: 100%;" id="useYn">
                  <option value="">해당없음</option>
                  <option value="false">비활성화</option>
                  <option value="true">활성화</option>
                </select>
              </div>
            </div>
          </div>
        </form>
        <div class="form-group">
          <div class="input-group">
            <input id="search" type="search" class="form-control" placeholder="이름" v-model=search>
            <div class="input-group-append">
              <button type="submit" class="btn btn-primary" @click.prevent="clickSearchBtn">
                <i class="fa fa-search"></i>
              </button>
            </div>
          </div>
        </div>
        <div class="card-body table-responsive p-0 table-hover" style="height: 300px;">
          <table class="table table-head-fixed text-nowrap">
            <thead>
            <tr>
              <th>#</th>
              <!--                                            <th><input type="checkbox"></th>-->
              <th>이름</th>
              <th>이메일</th>
              <th>생년월일</th>
              <th>직급</th>
              <th>권한</th>
              <th>부서</th>
              <th>전화번호</th>
              <th>비고</th>
              <th>사용유무</th>
              <th>등록일자</th>
              <th>수정일자</th>
              <th>등록자</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(data, index) in getDatas"  :key="index" @click="clickTrTag(data)">
              <td v-text="index+1" class=""></td>
              <td v-text="data.name" class=""></td>
              <td v-text="data.email" class=""></td>
              <td v-text="data.birthday" class=""></td>
              <td v-text="getPostionNameByPosition(data.position)" class=""></td>
              <td v-text="getAuthorityNameById(data.authorityId)" class=""></td>
              <td v-text="getDepartmentNameById(data.departmentId)" class=""></td>
              <td v-text="data.phone" class=""></td>
              <td v-text="data.remark" class=""></td>
              <td v-text="data.useYn === true ? '활성화' : '비활성화'" class=""></td>
              <td v-text="data.registedDateAt" class=""></td>
              <td v-text="data.updatedDateAt" class=""></td>
              <td v-text="data.register" class=""></td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="card-footer clearfix">
        <h4 class="card-title">{{pageInfo}}</h4>
        <ul class="pagination pagination-sm m-0 float-right">
          <li class="page-item" :class="{disabled: isFirstPageGroup}">
            <a class="page-link" href="#" aria-label="Previous" @click="clickPreviousPageBtn">&laquo;</a>
          </li>
          <li class="page-item" v-for="page in pages" :key="page" :class="{active: page-1 === currentPage}">
            <a class="page-link" v-text="page" href="#" @click="clickPageBtn(page-1)"></a>
          </li>
          <li class="page-item" :class="{disabled: isLastPageGroup}">
            <a class="page-link" href="#" aria-label="Next" @click="clickNextPageBtn">&raquo;</a>
          </li>
        </ul>
      </div>
    </template>

    <!-- 모달 컴포넌트  -->
    <MemberCreate ref="memberCreate" :p-department="pDepartment" @updateData="handleUpdateData" style="display: none"></MemberCreate>
    <MemberUpdate ref="memberUpdate" :p-department="pDepartment" @updateData="handleUpdateData" style="display: none"></MemberUpdate>
    <!-- /모달 컴포넌트  -->
  </div>
</template>

<script>
import axios from "axios";
import $ from "jquery";
import MemberCreate from "@/components/member/MemberCreate.vue";
import MemberUpdate from "@/components/member/MemberUpdate.vue";

export default {
  name: "Member",
  components: {
    MemberCreate,
    MemberUpdate,
  },
  props: {
    pDepartment: Object,
  },
  data() {
    return {
      department : null,
      responseData : null,
      data : null,
      pageable : null,
      currentPage : 0, //현재 페이지
      perPageNum : 10, //게시글의 수
      displayPageNum : 10, // 페이지 번호의 수
      pages : [],
      pageInfo : null,
      search : '',
      activeAuthority : '해당없음',
      authoritys : null,
      departments : null,
      positions : [
        {code : '' , name : '해당없음'},
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
    getDatas() {
      return this.data;
    },
    getAuthoritys() {
      return this.authoritys;
    },
    getDepartments() {
      return this.departments;
    },
    isFirstPageGroup() {
      if(this.responseData === null) return false;
      return this.responseData.data.first;
    },
    isLastPageGroup() {
      if(this.responseData === null) return false;
      return this.responseData.data.last;
    },

  },
  watch: {
    pDepartment() {
      this.currentPage = 0;
      this.department = this.pDepartment;
      this.findAuthoriyAndDepartment();
      this.searchData(false);
    },
    currentPage() {
      this.searchData();
    }
  },
  mounted() {
    this.department = this.pDepartment;
    this.findAuthoriyAndDepartment();
  },
  methods: {
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
              return (department.useYn === true &&
                  vm.department.children_d.find(child => Number(child) === department.id));
            });

            vm.authoritys.push({ id : 0, code : '', name : '해당없음' });
            vm.departments.push({ id : 0, code : '', name : '해당없음' });
            // console.log(vm.authoritys, vm.departments,  vm.department.children_d);

          })).catch(e => {
        alert(e);
      });
    },
    searchData(isSearch) {
      const vm = this;
      if(vm.department === null) return false;

      let departmentIds = vm.department.children_d;
      departmentIds.push(vm.department.id);
      if(isSearch && $('form.member #departments').val() !== "0") {
        departmentIds = [];
        departmentIds.push($('form.member #departments').val());
      }


      //부서에 속한 회원 + 하위 부서에 속한 회원까지 조회.
      axios.get('http://localhost:8080/api/members', {
        params: {
          page : vm.currentPage,
          size : vm.perPageNum,
          departmentIds: departmentIds,
          authorityId : $('form.member #authority').val() === undefined ? '' : ($('form.member #authority').val() !== "0"
              ? $('form.member #authority').val() : ''),
          position : $('form.member #position').val() === undefined ? '' : $('form.member #position').val(),
          useYn:$('form.member #useYn').val() === undefined ? '' : $('form.member #useYn').val(),
          name: vm.search
        },
        paramsSerializer: function (paramObj) {
          //배열로 전송 할 때 []를 떼고 보내자.
          const params = new URLSearchParams();
          for (const key in paramObj) {
            params.append(key, paramObj[key])
          }
          // console.log(params, paramObj);
          return params.toString();
        },
      }).then(response => {

        if(response.status === 200) {
          vm.responseData = response;
          vm.data = response.data.content;
          vm.pageable = response.data.pageable;
          vm.getStartAndEndPage();
          // console.log(vm.responseData, vm.data, vm.pageable);
        }
      }).catch(e => {
        alert(e);
      })
    },
    clickTrTag(data) {
      this.$refs.memberUpdate.setData(data);
      this.showUpdateModal();
    },
    getStartAndEndPage() {
      this.pages = [];
      let endPage = Math.ceil((this.pageable.pageNumber+1) / this.displayPageNum) * this.displayPageNum;
      let startPage = (endPage - this.displayPageNum) + 1;
      let tempEndPage = Math.ceil(this.responseData.data.totalElements / this.perPageNum);

      if(endPage > tempEndPage) {
        endPage = tempEndPage;
      }

      for(var i=startPage; i<=endPage; i++) {
        this.pages.push(i);
      }

      this.pageInfo = String.format("Showing {0} to {1} of {2} entries", (this.pageable.pageNumber * this.perPageNum)+1,
          (this.pageable.pageNumber * this.perPageNum)+this.responseData.data.numberOfElements, this.responseData.data.totalElements);

    },
    clickPreviousPageBtn() {
      this.currentPage = this.pageable.pageNumber === 0 ? 0 : this.pageable.pageNumber-1;

    },
    clickNextPageBtn() {
      let tempEndPage = Math.ceil(this.responseData.data.totalElements / this.perPageNum);
      this.currentPage = this.pageable.pageNumber > tempEndPage ? tempEndPage : this.pageable.pageNumber+1;
    },
    clickPageBtn(page) {
      this.currentPage = page;
    },
    clickSearchBtn() {
      this.currentPage = 0;
      this.searchData(true);
    },
    getAuthorityNameById(authorityId) {
      const vm = this;
      if(vm.department === null) return '';

      let selectAuthority = vm.authoritys.filter(authority => authority.id === authorityId)[0];
      return selectAuthority !== undefined ? selectAuthority.name : '';
    },
    getDepartmentNameById(departmentId) {
      const vm = this;
      if(vm.department === null) return '';

      let selectDepartment = vm.departments.filter(department => department.id === departmentId)[0];
      return selectDepartment !== undefined ? selectDepartment.name  : '' ;
    },
    getPostionNameByPosition(positionCode) {
      const vm = this;
      if(vm.department === null) return '';

      let selectPosition = vm.positions.filter(position => position.code === positionCode)[0];
      return selectPosition !== undefined ? selectPosition.name : "";
    },
    handleUpdateData() {
      this.currentPage = 0;
      this.searchData();
    },
    showCreateModal() {
      this.$refs.memberCreate.clearData();
      $('#member-create-modal').modal("show");
    },
    showUpdateModal() {
      $('#member-update-modal').modal("show");
    }
  }
}
</script>

<style scoped>

</style>